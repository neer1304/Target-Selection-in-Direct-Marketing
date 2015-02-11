package datamining.data.input.attribute;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Defines an attribute (a set of values with a common name - similar to a
 * column in a table).
 */
public class Attribute {
    /**
     * Produces an appropriate attribute type based on the values.
     */
    private static AttributeTypeFactory attributeTypeFactory
        = new AttributeTypeFactory();

    private String name;
    private AttributeType type;
    private List<Comparable> values = new ArrayList<Comparable>();

    public Attribute() {
        super();
        type = AttributeType.EMPTY;
    }

    public Attribute( String name ) {
        this();
        this.name = name;
    }

    /**
     * Sets the name, values and the appropriate type.
     */
    public Attribute( String name, List<Comparable> values ) {
        this( name );
        this.values = values;

        if ( values.isEmpty() == false ) {
            this.type = attributeTypeFactory.getAttributeType(
                values.get( 0 ).getClass() );
        }
    }

    public void addValue( Comparable value ) {
        values.add( value );
    }

    /**
     * @return all attribute values as an array
     */
    public Comparable[] getValuesAsArray() {
        Comparable[] allValues = new Comparable[ values.size() ];

        return values.toArray( allValues );
    }

    /**
     * @return distinct values of this attribute
     */
    public List<Comparable> getDistinctValues() {
        List<Comparable> distinctValues = new ArrayList<Comparable>();

        for ( Comparable value : values ) {
            if ( distinctValues.contains( value ) == false ) {
                distinctValues.add( value );
            }
        }

        return distinctValues;
    }

    /**
     * @return a map (key - each attribute value, value - the number of times
     *      this value appears in the value set)
     */
    public Map<Comparable, Integer> getValuesCount() {
        Map<Comparable, Integer> valuesCount
            = new LinkedHashMap<Comparable, Integer>();

        for ( Comparable value : values ) {
            if ( valuesCount.containsKey( value ) == false ) {
                valuesCount.put( value, 1 );
            } else {
                Integer currentCount = valuesCount.get( value );

                valuesCount.put( value, currentCount + 1 );
            }
        }

        return valuesCount;
    }

    public Integer getAsInteger( int valueIndex ) {
        return (Integer) values.get( valueIndex );
    }
    public Double getAsDouble( int valueIndex ) {
        return (Double) values.get( valueIndex );
    }
    public String getAsString( int valueIndex ) {
        return (String) values.get( valueIndex );
    }
    public Boolean getAsBoolean( int valueIndex ) {
        return (Boolean) values.get( valueIndex );
    }
    public Date getAsDate( int valueIndex ) {
        return (Date) values.get( valueIndex );
    }

    public AttributeType getType() {
        return type;
    }
    public void setType( AttributeType type ) {
        this.type = type;
    }

    public List<Comparable> getValues() {
        return values;
    }

    public void setValues( List<Comparable> values ) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
