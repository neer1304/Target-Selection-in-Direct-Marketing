package datamining.data.output;

import java.util.List;

import datamining.data.input.attribute.Instance;
import datamining.data.operators.EqualityOperator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Defines an item - an attribute name, value and a relation between them
 * (equal, not equal etc.).
 */
public class Item implements Evaluable {
    private static Log log
        = LogFactory.getLog( Item.class );

    private String attributeName;
    private Comparable attributeValue;
    private EqualityOperator equalityOperator;

    public Item() {
        super();
    }

    public Item( String attributeName,
            Comparable attributeValue, EqualityOperator equalityOperator ) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
        this.equalityOperator = equalityOperator;
    }

    public boolean evaluate( Instance instance ) {
        log.debug( "left: " + instance.getValue( this.attributeName ) );
        log.debug( "right: " + this.attributeValue );

        return equalityOperator.evaluate(
            instance.getValue( this.attributeName ), this.attributeValue );
    }

    @Override
    public String toString() {
        String output = attributeName;
        output += " ";
        output += equalityOperator.toString();
        output += " ";
        output += attributeValue.toString();

        return output;
    }

    public String getAttributeName() {
        return attributeName;
    }
    public void setAttributeName( String attributeName ) {
        this.attributeName = attributeName;
    }
    public Comparable getAttributeValue() {
        return attributeValue;
    }
    public void setAttributeValue( Comparable attributeValue ) {
        this.attributeValue = attributeValue;
    }
    public EqualityOperator getEqualityOperator() {
        return equalityOperator;
    }
    public void setEqualityOperator( EqualityOperator equalityOperator ) {
        this.equalityOperator = equalityOperator;
    }
}
