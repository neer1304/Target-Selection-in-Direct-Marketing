package datamining.data.sources.beans;

import java.util.List;
import java.util.Map;

import datamining.data.sources.DataSource;


/**
 * The simplest data source. A specific map has to be prepared (keys are
 * attribute names, values are lists of attribute values).
 */
public class MapOfListsDataSource implements DataSource {
    private Map<String, List<Comparable>> attributes;

    /**
     * @see datamining.data.sources.DataSource#getAttributes()
     */
    public Map<String, List<Comparable>> getAttributes() {
        return attributes;
    }

    public void setAttributes( Map<String, List<Comparable>> attributes ) {
        this.attributes = attributes;
    }
}
