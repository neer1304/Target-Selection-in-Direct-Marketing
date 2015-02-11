
package datamining.algorithms.classification.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import datamining.data.input.attribute.AttributeValuePair;
import datamining.data.input.attribute.incidence.AttributeValueIncidence;
import datamining.data.input.attribute.incidence.AttributeValueIncidenceComparator;
import datamining.data.operators.EqualityOperator;
import datamining.data.output.Item;
import datamining.data.output.Rule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Partitions attributes with numeric values. Used by 1-rule algorithm.
 *
 * <pre>
 *
 * 1. Sort value pairs by first value ascending.
 * 2. Find groups of value pairs according to the second value (a group is
 * created when at least <code>minimumMajorityClassExamples</code> are found or
 * there are no pairs left).
 * 3. Create rules defining breakpoints between groups.
 * 4. Merge adjacent groups with the same majority class.
 * 5. Create final partitioning rules.
 *
 * </pre>
 */
public class AttributeValuePartitioner {
    private static Log log
        = LogFactory.getLog( AttributeValuePartitioner.class );

    /**
     * Partitions attribute value pairs using the algorithm described above.
     *
     * This method needs heavy refactoring.
     */
    public List<Rule> partitionValues( String firstAttributeName,
            String secondAttributeName, List<AttributeValuePair> valuePairs,
            int minimumMajorityClassExamples ) {
        Collections.sort( valuePairs );

        AttributeValueIncidenceComparator incidenceComparator
            = new AttributeValueIncidenceComparator();

        List<AttributeValuePair> breakpoints
            = new ArrayList<AttributeValuePair>();

        List<AttributeValueIncidence> majorityClassExamples
            = new ArrayList<AttributeValueIncidence>();

        AttributeValueIncidence valueWithHighestIncidence = null;

        for ( AttributeValuePair valuePair : valuePairs ) {
            log.debug( "valuePair: " + valuePair.toString() );

            Comparable secondAttributeValue = valuePair.getSecondValue();

            if ( ( valueWithHighestIncidence != null )
                    && ( valueWithHighestIncidence.getCurrentIncidence()
                        >= minimumMajorityClassExamples )
                    && ( valueWithHighestIncidence.getAttributeValue()
                            .equals( secondAttributeValue ) == false ) ) {
                AttributeValuePair lastBreakpoint
                    = breakpoints.get( breakpoints.size() - 1 );

                Comparable finalBreakpointValue = calculateBreakpointValue(
                    lastBreakpoint.getFirstValue(),
                    valuePair.getFirstValue() );

                lastBreakpoint.setFirstValue( finalBreakpointValue );

                majorityClassExamples.clear();
            }

            AttributeValueIncidence valueIncidence
                = new AttributeValueIncidence( secondAttributeValue );

            int valueIncidenceIndex
                = majorityClassExamples.indexOf( valueIncidence );

            if ( valueIncidenceIndex >= 0 ) {
                AttributeValueIncidence currentValueIncidence
                    = majorityClassExamples.get( valueIncidenceIndex );
                currentValueIncidence.checkAttributeValue(
                    secondAttributeValue );
            } else {
                majorityClassExamples.add( valueIncidence );
                valueIncidence.checkAttributeValue( secondAttributeValue );
            }

            valueWithHighestIncidence
                = Collections.max( majorityClassExamples, incidenceComparator );

            log.debug( "attributeValueWithHighestIncidence: "
                + valueWithHighestIncidence.toString() );

            Integer highestIncidence
                = valueWithHighestIncidence.getCurrentIncidence();

            if ( valueIncidence.equals( valueWithHighestIncidence ) ) {
                if ( highestIncidence == minimumMajorityClassExamples ) {
                    AttributeValuePair breakpoint
                        = new AttributeValuePair( valuePair.getFirstValue(),
                            valueWithHighestIncidence.getAttributeValue() );

                    breakpoints.add( breakpoint );
                } else if ( highestIncidence > minimumMajorityClassExamples ) {
                    AttributeValuePair lastBreakpoint
                        = breakpoints.get( breakpoints.size() - 1 );
                    lastBreakpoint.setFirstValue(
                        valuePair.getFirstValue() );
                }
            }
        }

        Comparable lastPartitionAttributeValue
            = determineLastPartitionAttributeValue( majorityClassExamples,
                incidenceComparator );

        log.debug( "lastPartitionAttributeValue="
            + lastPartitionAttributeValue );

        mergePartitionsWithIdenticalAttributeValue(
            breakpoints, lastPartitionAttributeValue );
        log.debug( "breakpoints: " + breakpoints.toString() );

        List<Rule> partitioningRules = new ArrayList<Rule>();

        for ( int i = 0; i < breakpoints.size(); ++i ) {
            Comparable firstAttributeValue
                = breakpoints.get( i ).getFirstValue();
            Comparable secondAttributeValue
                = breakpoints.get( i ).getSecondValue();

            if ( i == 0 ) {
                Rule firstRule = new Rule();
                firstRule.defineIf().attribute( firstAttributeName )
                    .isLowerThanOrEqualTo( firstAttributeValue );
                firstRule.defineThen().attribute( secondAttributeName )
                    .equals( secondAttributeValue );

                partitioningRules.add( firstRule );
                continue;
            }

            Comparable previousBreakpointAttributeValue
                = breakpoints.get( i - 1 ).getFirstValue();

            Rule partitioningRule = new Rule();
            partitioningRule.defineIf().attribute( firstAttributeName )
                .isGreaterThan( previousBreakpointAttributeValue )
                .and( partitioningRule ).attribute( firstAttributeName )
                .isLowerThanOrEqualTo( firstAttributeValue );
            partitioningRule.defineThen().attribute( secondAttributeName )
                .equals( secondAttributeValue );
            partitioningRules.add( partitioningRule );
        }

        if ( breakpoints.isEmpty() ) {
            Rule globalRule = new Rule();
            globalRule.defineThen().attribute( secondAttributeName )
                .equals( lastPartitionAttributeValue );

            partitioningRules.add( globalRule );
        } else {
            Comparable lastBreakpointValue
                = breakpoints.get( breakpoints.size() - 1 ).getFirstValue();

            Rule lastRule = new Rule();
            lastRule.defineIf().attribute( firstAttributeName )
                .isGreaterThan( lastBreakpointValue );
            lastRule.defineThen().attribute( secondAttributeName )
                .equals( lastPartitionAttributeValue );

            partitioningRules.add( lastRule );
        }

        return partitioningRules;
    }

    protected void mergePartitionsWithIdenticalAttributeValue(
            List<AttributeValuePair> breakpoints,
            Comparable lastPartitionAttributeValue ) {
        for ( int i = 0; i < breakpoints.size() - 1; ++i ) {
            Comparable currentAttributeValue
                = breakpoints.get( i ).getSecondValue();
            Comparable nextAttributeValue
                = breakpoints.get( i + 1 ).getSecondValue();

            if ( currentAttributeValue.equals( nextAttributeValue ) ) {
                breakpoints.remove( i );
            }
        }

        if ( ( breakpoints.size() == 1 )
                && breakpoints.get( 0 ).getSecondValue().equals(
                    lastPartitionAttributeValue ) ) {
            breakpoints.remove( 0 );
        }
    }

    protected Comparable determineLastPartitionAttributeValue(
            List<AttributeValueIncidence> majorityClassExamples,
            AttributeValueIncidenceComparator incidenceComparator ) {
        log.debug( "majorityClassExamples.size()="
            + majorityClassExamples.size() );

        Collections.sort( majorityClassExamples, incidenceComparator );
        Collections.reverse( majorityClassExamples );

        int valuesWithHighestIncidenceCount = 1;

        for ( int i = 0; i < majorityClassExamples.size() - 1; ++i ) {
            if ( incidenceComparator.compare(
                    majorityClassExamples.get( i ),
                    majorityClassExamples.get( i + 1 ) ) == 0 ) {
                ++valuesWithHighestIncidenceCount;
            } else {
                break;
            }
        }

        log.debug( "valuesWithHighestIncidenceCount="
            + valuesWithHighestIncidenceCount );

        Random random = new Random();
        AttributeValueIncidence attributeValueIncidence
            = majorityClassExamples.get(
                random.nextInt( valuesWithHighestIncidenceCount ) );

        return attributeValueIncidence.getAttributeValue();
    }

    protected Comparable calculateBreakpointValue( Comparable previousValue,
            Comparable currentValue ) {
        Double firstDouble = (Double) previousValue;
        Double secondDouble = (Double) currentValue;

        return ( firstDouble + secondDouble ) / 2;
    }
}
