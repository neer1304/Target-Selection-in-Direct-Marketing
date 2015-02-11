package datamining.data.input.comparators;

import java.util.Comparator;

import datamining.data.input.attribute.Attribute;


/**
 * Compares attributes using only their names.
 */
public class AttributeNameComparator implements Comparator<Attribute> {
    public int compare( Attribute firstAttribute, Attribute secondAttribute ) {
        return firstAttribute.getName().compareTo( secondAttribute.getName() );
    }
}
