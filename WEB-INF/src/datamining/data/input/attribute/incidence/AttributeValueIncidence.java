package datamining.data.input.attribute.incidence;

/**
 * Represents the incidence of an attribute value (the number of times
 * this value has occurred).
 */
public class AttributeValueIncidence {
    private Comparable attributeValue;
    private Integer incidence = 0;

    public AttributeValueIncidence( Comparable attributeValue ) {
        this.attributeValue = attributeValue;
    }

    /**
     * Checks if the current attribute value is equal to this attribute value
     * and increases its incidence, if appropriate.
     */
    public void checkAttributeValue( Comparable currentAttributeValue ) {
        if ( attributeValue.equals( currentAttributeValue ) ) {
            ++incidence;
        }
    }

    @Override
    public boolean equals( Object obj ) {
        AttributeValueIncidence valueIncidence = (AttributeValueIncidence) obj;

        return this.attributeValue.equals( valueIncidence.attributeValue );
    }

    /**
     * Resets the incidence to zero.
     */
    public void reset() {
        incidence = 0;
    }

    @Override
    public String toString() {
        return "( " + attributeValue + ", " + incidence + " )";
    }

    public Comparable getAttributeValue() {
        return attributeValue;
    }

    public Integer getCurrentIncidence() {
        return incidence;
    }
}
