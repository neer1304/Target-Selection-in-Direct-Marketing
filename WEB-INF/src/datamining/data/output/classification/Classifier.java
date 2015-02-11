package datamining.data.output.classification;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.math.stat.descriptive.summary.SumOfSquares;

/**
 * Defines a cluster (currently a group of points relatively close to the
 * centroid; generally - a group of instances being similar to each other in
 * a way, but different from other instances; this cluster definition has also
 * a name and information about the percentage of all points falling into
 * this cluster).
 */
public class Classifier {
    public static final String ATTRIBUTE_NAME = "CLASSIFIER";

    private String name;
    private Double attributeValueLikelihood;
    private Comparable decisionValue;
    @Override
    public String toString() {
        String output = "Classifier: " + name + "(" +decisionValue +") :"+ attributeValueLikelihood  + "\n";
        return output;
    }
	public Classifier(String name, Double attributeValueLikelihood, Comparable decisionValue) {
		super();
		// TODO Auto-generated constructor stub
		this.name = name;
		this.attributeValueLikelihood = attributeValueLikelihood;
		this.decisionValue = decisionValue;
	}
	public Double getAttributeValueLikelihood() {
		return attributeValueLikelihood;
	}
	public void setAttributeValueLikelihood(Double attributeValueLikelihood) {
		this.attributeValueLikelihood = attributeValueLikelihood;
	}
	public Comparable getDecisionValue() {
		return decisionValue;
	}
	public void setDecisionValue(Comparable decisionValue) {
		this.decisionValue = decisionValue;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Classifier() {
		super();
		// TODO Auto-generated constructor stub
	}

    }
