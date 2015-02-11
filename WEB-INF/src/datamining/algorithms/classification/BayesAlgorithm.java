package datamining.algorithms.classification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import datamining.algorithms.AbstractDataMiningAlgorithm;
import datamining.data.input.InputData;
import datamining.data.input.attribute.Attribute;
import datamining.data.input.attribute.AttributeType;
import datamining.data.input.attribute.Instance;
import datamining.data.input.attribute.probability.AttributeValueProbability;
import datamining.data.input.attribute.probability.AttributeValueProbabilityComparator;
import datamining.data.output.Rule;
import datamining.data.output.RuleElement;
import datamining.data.output.classification.ClassificationDataMiningModel;
import datamining.data.output.classification.Classifier;
import datamining.util.MathCalculator;


/**
 * <pre>
 * 1. Calculate probabilities for all decision values. The probability for a
 * single decision value is a product of likelihoods calculated for all
 * attribute values in the instance being classified. Each likelihood can be
 * defined as: the number of times an attribute value resulted in a particular
 * decision value divided by the number of occurences of this decision value.
 * The whole product is multiplied by the number of occurences of this decision
 * value and divided by the number of instances.
 * 2. Output a classification rule based on the decision value with the highest
 * probability.
 * </pre>
 *
 * If an attribute is numeric, the likelihood is calculated using its normal
 * distribution.
 *
 * Why naive? Because the algorithm assumes that all attributes are equally
 * important. If the likelihood of an attribute value equals 0, then the whole
 * decision value probability is also 0.
 */
public class BayesAlgorithm extends AbstractDataMiningAlgorithm {
    private static Log log = LogFactory.getLog( BayesAlgorithm.class );

    protected MathCalculator mathCalculator = new MathCalculator();
    private ClassificationDataMiningModel dataMiningModel = new ClassificationDataMiningModel();
    public ClassificationDataMiningModel analyze( InputData inputData ) {
    	
        List<Rule> rules = dataMiningModel.getRules();

        for ( Attribute decision : inputData.getDecisions() ) {
            Rule ruleBasedOnDecision
                = analyzeDecision( decision, inputData.getAttributes(),
                    inputData.getInstanceToBeClassified() );

            rules.add( ruleBasedOnDecision );
        }

        return dataMiningModel;
    }

    /**
     * Analyzes a single decision.
     */
    protected Rule analyzeDecision( Attribute decision,
            List<Attribute> attributes, Instance instanceToBeClassified ) {
        List<AttributeValueProbability> decisionValueProbabilities
            = calculateDecisionValueProbabilities(
                decision, attributes, instanceToBeClassified );
        dataMiningModel.setAttributeValueProbability(decisionValueProbabilities);
        log.debug(
            "decisionValueProbabilities: " + decisionValueProbabilities );

        Collections.sort( decisionValueProbabilities,
            new AttributeValueProbabilityComparator() );
        AttributeValueProbability highestProbability
            = decisionValueProbabilities.get(
                decisionValueProbabilities.size() - 1 );

        Rule ruleBasedOnDecision = new Rule();
        RuleElement currentRuleElement = null;

        for ( String attributeName
                : instanceToBeClassified.getAttributeNames() ) {
            if ( currentRuleElement == null ) {
                currentRuleElement = ruleBasedOnDecision.defineIf()
                    .attribute( attributeName )
                    .equals( instanceToBeClassified.getValue( attributeName ) );
            } else {
                currentRuleElement = currentRuleElement.and( ruleBasedOnDecision ).attribute( attributeName )
                    .equals( instanceToBeClassified.getValue( attributeName ) );
            }
        }

        ruleBasedOnDecision.defineThen().attribute( decision.getName() )
            .equals( highestProbability.getAttributeValue() );

        return ruleBasedOnDecision;
    }

    /**
     * Calculates probabilities for all decision values.
     */
    protected List<AttributeValueProbability>
            calculateDecisionValueProbabilities(
                Attribute decision, List<Attribute> attributes,
                Instance instanceToBeClassified ) {
        List<Attribute> attributesWithDecision
            = new ArrayList<Attribute>( attributes );
        attributesWithDecision.add( decision );

        Set<String> attributeNames
            = new HashSet<String>( instanceToBeClassified.getAttributeNames() );
        attributeNames.add( decision.getName() );

        List<Attribute> filteredAttributes = filterSelectedAttributes(
            attributesWithDecision, attributeNames );

        List<Instance> instances
            = attributeConverter.convertToInstances( filteredAttributes );

        filteredAttributes.remove( decision );

        Map<Comparable, Integer> decisionValuesCount
            = decision.getValuesCount();

        List<AttributeValueProbability> probabilities
            = new ArrayList<AttributeValueProbability>();

        Double likelihoodSum = 0.0;

        for ( Comparable decisionValue : decision.getDistinctValues() ) {
            Double decisionValueLikelihood = 1.0;

            for ( Attribute filteredAttribute : filteredAttributes ) {
                Double attributeValueLikelihood
                    = calculateAttributeValueLikelihood( filteredAttribute,
                        decision.getName(), decisionValue,
                        decisionValuesCount.get( decisionValue ),
                        instanceToBeClassified, instances );
                if(!attributeValueLikelihood.isNaN()){
                	decisionValueLikelihood *= attributeValueLikelihood;
                	dataMiningModel.addClassifier(new Classifier(filteredAttribute.getName(),attributeValueLikelihood,decisionValue));
                }
            }

            log.debug( "count( " + decisionValue + " ) = "
                + decisionValuesCount.get( decisionValue ) );
            log.debug( "valuesCount = " + instances.size() );
            log.debug( "decisionValueLikelihoodbefore( " + decisionValue + " ) = "
                    + decisionValueLikelihood );
            decisionValueLikelihood
                *= decisionValuesCount.get( decisionValue );
            log.debug( "decisionValueLikelihoodafter( " + decisionValue + " ) = "
                    + decisionValueLikelihood );
            decisionValueLikelihood /= instances.size();

            log.debug( "likelihood( " + decisionValue + " ) = "
                + decisionValueLikelihood );

            AttributeValueProbability probability
                = new AttributeValueProbability(
                    decisionValue, decisionValueLikelihood );

            probabilities.add( probability );

            likelihoodSum += decisionValueLikelihood;
        }

        for ( AttributeValueProbability probability : probabilities ) {
            probability.setProbability(
                probability.getProbability() / likelihoodSum );
        }

        return probabilities;
    }

    /**
     * Calculates the likelihood for a single attribute value.
     */
    protected Double calculateAttributeValueLikelihood( Attribute attribute,
            String decisionName, Comparable decisionValue,
            Integer decisionValueCount, Instance instanceToBeClassified,
            List<Instance> instances ) {
        if ( attribute.getType().equals( AttributeType.NUMERIC ) ) {
            List<Comparable> selectedValues = attributeConverter
                .groupFirstAttributeValuesBySecondAttributeValue(
                    attribute.getName(), decisionName, decisionValue,
                    instances );

            return mathCalculator.calculateNormalDistribution(
                selectedValues.toArray(
                    new Comparable[ selectedValues.size() ] ),
                    (Double) instanceToBeClassified.getValue(
                    attribute.getName() ) );
        }

        String attributeName = attribute.getName();

        Rule rule = generateRule(
            attributeName,
            instanceToBeClassified.getValue( attributeName ),
            decisionName, decisionValue );

        Integer ruleCoverage = calculateCoverage( rule, instances );

        return ruleCoverage.doubleValue() / decisionValueCount;
    }

    /**
     * Filters attributes leaving only those explicitly specified.
     */
    protected List<Attribute> filterSelectedAttributes(
            List<Attribute> attributes, Set<String> attributeNames ) {
        List<Attribute> selectedAttributes = new ArrayList<Attribute>();

        for ( Attribute attribute : attributes ) {
            if ( attributeNames.contains( attribute.getName() ) ) {
                selectedAttributes.add( attribute );
            }
        }

        return selectedAttributes;
    }

    /**
     * Generates a rule: IF ( attributeName = attributeValue )
     * THEN ( decisionName = decisionValue ).
     */
    protected Rule generateRule(
            String attributeName, Comparable attributeValue,
            String decisionName, Comparable decisionValue ) {
        Rule rule = new Rule();
        rule.defineIf().attribute( attributeName ).equals( attributeValue );
        rule.defineThen().attribute( decisionName ).equals( decisionValue );

        return rule;
    }

    @Override
    protected String getName() {
        return "Bayes Algorithm";
    }
}
