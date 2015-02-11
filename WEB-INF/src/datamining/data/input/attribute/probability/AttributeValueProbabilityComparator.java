package datamining.data.input.attribute.probability;

import java.util.Comparator;

import datamining.data.input.attribute.incidence.AttributeValueIncidence;
import datamining.data.input.attribute.incidence.AttributeValueIncidenceComparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Compares two probability values ignoring the attribute values used.
 */
public class AttributeValueProbabilityComparator
        implements Comparator<AttributeValueProbability> {
    private static Log log
        = LogFactory.getLog( AttributeValueIncidenceComparator.class );

    public int compare( AttributeValueProbability first,
            AttributeValueProbability second ) {
        log.debug( "first AVL: " + first.toString() );
        log.debug( "second AVL: " + second.toString() );

        return first.getProbability().compareTo( second.getProbability() );
    }
}
