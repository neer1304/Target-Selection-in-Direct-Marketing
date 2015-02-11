package datamining.data.sources;

/**
 * Thrown when a data source could not retrieve the required data.
 */
public class DataSourceException extends RuntimeException {
    public DataSourceException() {
        super();
    }

    public DataSourceException( String message ) {
        super( message );
    }

    public DataSourceException( Throwable cause ) {
        super( cause );
    }

    public DataSourceException( String message, Throwable cause ) {
        super( message, cause );
    }
}
