package datamining.data.sources.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import datamining.data.sources.DataSource;
import datamining.data.sources.DataSourceException;

/**
 * A JDBC data source. Retrieves attribute names and values using the given
 * queries. Each column in each query result produces another attribute. If many
 * queries return the same column name, the attribute is overwritten each time.
 * To avoid such a situation, use aliases for columns.
 *
 * Many data mining tools and libraries can retrieve data from only one table or
 * perspective. The ability to merge data from multiple queries is more flexible
 * - there is no need to prepare a special table/perspective before retrieving
 * data. There is one requirement though - each query should return the same
 * number of rows ordered in a way that corresponds to other queries so that
 * the whole instance set remains consistent.
 */
public class JDBCDataSource implements DataSource {
    private static Log log = LogFactory.getLog( JDBCDataSource.class );

    private String connectionString;
    private String userName;
    private String password;
    private List<String> queries = new ArrayList<String>();

    public JDBCDataSource( Driver driver, String connectionString,
            String userName, String password, String query ) {
        super();

        this.connectionString = connectionString;
        this.userName = userName;
        this.password = password;
        queries.add( query );

        try {
            DriverManager.registerDriver( driver );
        } catch ( SQLException e ) {
            throw new DataSourceException(
                "Could not register JDBC driver", e );
        }
    }

    public JDBCDataSource( Driver driver, String connectionString,
            String userName, String password, List<String> queries ) {
        this( driver, connectionString, userName, password, queries.get( 0 ) );

        for ( int i = 1; i < queries.size(); ++i ) {
            this.queries.add( queries.get( i ) );
        }
    }

    /**
     * @see datamining.data.sources.DataSource#getAttributes()
     */
    public Map<String, List<Comparable>> getAttributes() {
        Map<String, List<Comparable>> attributes
            = new LinkedHashMap<String, List<Comparable>>();

        try {
            Connection connection = DriverManager.getConnection(
                connectionString, userName, password );

            Statement statement = connection.createStatement();

            for ( String query : queries ) {
                ResultSet resultSet = statement.executeQuery( query );

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                for ( int i = 1; i <= columnCount; ++i ) {
                    String attributeName = metaData.getColumnName( i );
                    List<Comparable> attributeValues = new ArrayList<Comparable>();

                    attributes.put( attributeName, attributeValues );
                }

                while ( resultSet.next() ) {
                    for ( int i = 1; i <= columnCount; ++i ) {
                        List<Comparable> attributeValues
                            = attributes.get( metaData.getColumnName( i ) );

                        attributeValues.add(
                            getValueAsComparable( resultSet.getObject( i ) ) );
                    }
                }

                resultSet.close();
            }

            statement.close();
            connection.close();
        } catch ( SQLException e ) {
            throw new DataSourceException( "Could not retrieve data", e );
        }


        return attributes;
    }

    protected Comparable getValueAsComparable( Object value ) {
        Comparable comparable = null;

        if ( value instanceof Double || value instanceof Integer
                || value instanceof BigDecimal ) {
            comparable = Double.parseDouble( value.toString() );
        } else if ( value instanceof String ) {
            comparable = value.toString();
        } else if ( value instanceof Boolean ) {
            comparable = Boolean.parseBoolean( value.toString() );
        } else if ( value instanceof Date ) {
            comparable = new Date( ( (Date) value ).getTime() );
        } else {
            log.debug( "Unhandled value type: " + value.getClass().getName() );
        }

        return comparable;
    }
}
