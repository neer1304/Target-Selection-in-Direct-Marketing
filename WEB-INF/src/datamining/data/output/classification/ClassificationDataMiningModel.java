package datamining.data.output.classification;

import java.util.ArrayList;
import java.util.List;

import datamining.data.input.attribute.probability.AttributeValueProbability;
import datamining.data.output.DataMiningModel;

/**
 * Defines a data mining model specific to clustering algorithms.
 */
public class ClassificationDataMiningModel extends DataMiningModel {
    protected List<String> attributeNameOrder = new ArrayList<String>();
    protected List<Classifier> classifiers = new ArrayList<Classifier>();
    protected List<AttributeValueProbability> attributeValueProbability;
    public void addAttributeNameToOrder( String attributeName ) {
        attributeNameOrder.add( attributeName );
    }

    public void addClassifier( Classifier classifier ) {
    	classifiers.add( classifier );
    }

    public List<Classifier> getClassifiers() {
        return classifiers;
    }

    public void setClassifiers( List<Classifier> classifiers ) {
        this.classifiers = classifiers;
    }

    public List<String> getAttributeNameOrder() {
        return attributeNameOrder;
    }

    public void setAttributeNameOrder( List<String> attributeNameOrder ) {
        this.attributeNameOrder = attributeNameOrder;
    }

	public List<AttributeValueProbability> getAttributeValueProbability() {
		return attributeValueProbability;
	}

	public void setAttributeValueProbability(
			List<AttributeValueProbability> attributeValueProbability) {
		this.attributeValueProbability = attributeValueProbability;
	}
}
