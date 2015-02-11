package datamining.data.sources;

import java.util.List;
import java.util.Map;

/**
 * Base interface for all data sources.
 */
public interface DataSource {
    Map<String, List<Comparable>> getAttributes();
}
