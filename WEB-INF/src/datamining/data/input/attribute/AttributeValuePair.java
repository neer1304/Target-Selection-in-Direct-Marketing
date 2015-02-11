package datamining.data.input.attribute;

/**
 * Represents a pair of two attribute values.
 */
public class AttributeValuePair implements Comparable<AttributeValuePair> {
    private Comparable firstValue;
    private Comparable secondValue;

    public AttributeValuePair() {
        super();
    }

    public AttributeValuePair( Comparable firstValue, Comparable secondValue ) {
        this();
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @SuppressWarnings("unchecked")
    public int compareTo( AttributeValuePair valuePair ) {
        int comparisonResult
            = this.firstValue.compareTo( valuePair.firstValue );

        if ( comparisonResult == 0 ) {
            comparisonResult
                = this.secondValue.compareTo( valuePair.secondValue );
        }

        return comparisonResult;
    }

    @Override
    public String toString() {
        return "( " + firstValue + ", " + secondValue + " )";
    }

    public Comparable getFirstValue() {
        return firstValue;
    }
    public void setFirstValue( Comparable firstValue ) {
        this.firstValue = firstValue;
    }
    public Comparable getSecondValue() {
        return secondValue;
    }
    public void setSecondValue( Comparable secondValue ) {
        this.secondValue = secondValue;
    }
}
