package datamining.data.input.attribute.incidence;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Compares two incidence values ignoring the attribute values used.
 */
public class AttributeValueIncidenceComparator
        implements Comparator<AttributeValueIncidence> {
    private static Log log
        = LogFactory.getLog( AttributeValueIncidenceComparator.class );

    public int compare( AttributeValueIncidence first,
            AttributeValueIncidence second ) {
        log.debug( "first AVI: " + first.toString() );
        log.debug( "second AVI: " + second.toString() );

        return first.getCurrentIncidence().compareTo(
            second.getCurrentIncidence() );
    }
}
