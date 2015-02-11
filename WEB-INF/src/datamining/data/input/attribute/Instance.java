package datamining.data.input.attribute;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * A set of single values from different attributes (similar to a row in
 * a table).
 */
public class Instance implements Comparable<Instance> {
    private Map<String, Comparable> attributeValues
        = new LinkedHashMap<String, Comparable>();

    public Instance() {
        super();
    }

    public Instance( Map<String, Comparable> attributeValues ) {
        this();
        this.attributeValues = attributeValues;
    }

    public boolean containsAttribute( String attributeName ) {
        return attributeValues.containsKey( attributeName );
    }

    public Set<String> getAttributeNames() {
        return attributeValues.keySet();
    }

    public Comparable getValue( String attributeName ) {
        return attributeValues.get( attributeName );
    }

    /**
     * Compares an instance to this instance using the attribute values.
     */
    @SuppressWarnings("unchecked")
    public int compareTo( Instance instance ) {
        Iterator<Comparable> thisValuesIterator
            = this.attributeValues.values().iterator();
        Iterator<Comparable> rowValuesIterator
            = instance.attributeValues.values().iterator();

        Integer thisValuesSize = this.attributeValues.size();
        Integer rowValuesSize = instance.attributeValues.size();

        int comparisonResult = thisValuesSize.compareTo( rowValuesSize );

        while ( thisValuesIterator.hasNext() ) {
            if ( comparisonResult != 0 ) {
                break;
            }

            Comparable thisValue = thisValuesIterator.next();
            Comparable rowValue = rowValuesIterator.next();

            comparisonResult = thisValue.compareTo( rowValue );
        }

        return comparisonResult;
    }

    @Override
    public boolean equals( Object obj ) {
        Instance instance = (Instance) obj;

        return this.attributeValues.equals( instance.attributeValues );
    }

    public int length() {
        return attributeValues.size();
    }

    public void addAttributeValue( String attributeName,
            Comparable attributeValue ) {
        attributeValues.put( attributeName, attributeValue );
    }
}
