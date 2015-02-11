package datamining.data.input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import datamining.data.input.attribute.Attribute;
import datamining.data.input.attribute.Instance;

/**
 * The base input data for data mining algorithms.
 */
public class InputData {
    /**
     * Normal attributes.
     */
    protected List<Attribute> attributes = new ArrayList<Attribute>();
    /**
     * Decisions - attributes that participate directly in classifications.
     */
    protected List<Attribute> decisions = new ArrayList<Attribute>();
    /**
     * The instance that will be classified by a classification algorithm.
     */
    protected Instance instanceToBeClassified;

    public void addAttribute( Attribute attribute ) {
        attributes.add( attribute );
    }

    /**
     * Takes a particular attribute and marks it as a decision. The attribute
     * is removed from the set of available attributes and added to the set of
     * available decisions.
     *
     * @param attributeName the name of the attribute being changed
     */
    public void setAttributeAsDecision( String attributeName ) {
        for ( Attribute attribute : attributes ) {
            if ( attribute.getName().equals( attributeName ) ) {
                attributes.remove( attribute );
                decisions.add( attribute );
                break;
            }
        }
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<Attribute> getDecisions() {
        return decisions;
    }

    public Instance getInstanceToBeClassified() {
        return instanceToBeClassified;
    }

    public void setInstanceToBeClassified( Instance instanceToBeClassified ) {
        this.instanceToBeClassified = instanceToBeClassified;
    }
}
