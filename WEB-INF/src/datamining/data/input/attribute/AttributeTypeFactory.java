package datamining.data.input.attribute;

import java.util.Date;


/**
 * Determines the attribute type based on the class of its values.
 */
public class AttributeTypeFactory {
    public AttributeType getAttributeType( Class attributeClass ) {
        AttributeType attributeType = AttributeType.EMPTY;

        if ( Double.class.equals( attributeClass )
                || Date.class.equals( attributeClass ) ) {
            attributeType = AttributeType.NUMERIC;
        } else if ( String.class.equals( attributeClass )
                || Boolean.class.equals( attributeClass ) ) {
            attributeType = AttributeType.NOMINAL;
        }

        return attributeType;
    }
}
