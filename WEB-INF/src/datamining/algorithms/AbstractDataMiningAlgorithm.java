package datamining.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import datamining.data.input.attribute.AttributeConverter;
import datamining.data.input.attribute.Instance;
import datamining.data.output.Item;
import datamining.data.output.Rule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Base abstract class for data mining algorithms. All algorithms should extend
 * this class.
 */
public abstract class AbstractDataMiningAlgorithm
        implements DataMiningAlgorithm {
    private static Log log
        = LogFactory.getLog( AbstractDataMiningAlgorithm.class );

    protected AttributeConverter attributeConverter = new AttributeConverter();

    /**
     * Unused, but probably useful in GUIs.
     *
     * @return the current algorithm's name
     */
    protected abstract String getName();

    /**
     * Calculates the number of instances covered by the rule.
     * This method will be moved to another class.
     */
    protected Integer calculateCoverage( Rule rule, List<Instance> instances ) {
        Integer coverage = 0;

        for ( Instance currentInstance : instances ) {
            if ( rule.evaluate( currentInstance ) ) {
                ++coverage;
            }
        }

        log.debug( "coverage( " + rule + " ) = " + coverage );

        return coverage;
    }

    /**
     * Calculates the percentage (represented by a fraction 0..1) of instances
     * covered by the rule in the whole set.
     * This method will be moved to another class.
     */
    protected Double calculateAccuracy( Rule rule, List<Instance> instances ) {
        Double accuracy = calculateCoverage( rule, instances ).doubleValue();

        if ( accuracy == 0.0 ) {
            return accuracy;
        }

        Rule conditionsOnly = new Rule();
        conditionsOnly.setConditions( rule.getConditions() );

        accuracy /= calculateCoverage( conditionsOnly, instances );

        log.debug( "accuracy( " + rule + " ) = " + accuracy );

        return accuracy;
    }

    /**
     * Extracts instances covered by the rule from the whole set.
     * This method will be moved to another class.
     */
    protected List<Instance> getInstancesCoveredByRule( Rule rule,
            List<Instance> instances ) {
        List<Instance> instancesCovered = new ArrayList<Instance>();

        for ( Instance currentInstance : instances ) {
            if ( rule.evaluate( currentInstance ) ) {
                instancesCovered.add( currentInstance );
            }
        }

        return instancesCovered;
    }

    /**
     * Calculates the number of instances covered by the item set.
     * This method will be moved to another class.
     */
    protected Integer calculateCoverage( Set<Item> itemSet,
            List<Instance> instances ) {
        Integer coverage = 0;

        for ( Instance currentInstance : instances ) {
            boolean isItemSetCovered = true;

            for ( Item item : itemSet ) {
                isItemSetCovered &= item.evaluate( currentInstance );
            }

            if ( isItemSetCovered ) {
                ++coverage;
            }
        }

        log.debug( "coverage( " + itemSet + " ) = " + coverage );

        return coverage;
    }
}
