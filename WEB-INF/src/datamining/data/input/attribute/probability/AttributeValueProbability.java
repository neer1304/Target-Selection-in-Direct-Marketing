package datamining.data.input.attribute.probability;

/**
 * Associates an attribute value with the probability that an instance will be
 * classified as this attribute value.
 */
public class AttributeValueProbability {
    private Comparable attributeValue;
    private Double probability;

    public AttributeValueProbability() {
        super();
    }

    public AttributeValueProbability( Comparable attributeValue,
            Double probability ) {
        this();
        this.attributeValue = attributeValue;
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "( " + attributeValue + ": " + probability + " )";
    }

    public Comparable getAttributeValue() {
        return attributeValue;
    }
    public void setAttributeValue( Comparable attributeValue ) {
        this.attributeValue = attributeValue;
    }
    public Double getProbability() {
        return probability;
    }
    public void setProbability( Double probability ) {
        this.probability = probability;
    }
}
