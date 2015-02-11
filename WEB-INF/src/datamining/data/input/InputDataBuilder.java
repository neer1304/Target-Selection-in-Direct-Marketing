
package datamining.data.input;

import java.util.List;
import java.util.Map;

import datamining.data.input.attribute.Attribute;
import datamining.data.sources.DataSource;


/**
 * Builds the input data for data mining algorithms.
 *
 */
public class InputDataBuilder {
    /**
     * Builds the input data using a particular data source.
     *
     * @param dataSource the data source providing all required data
     * @return the form of input data accepted by data mining algorithms
     */
    public InputData build( DataSource dataSource ) {
        InputData inputData = new InputData();

        Map<String, List<Comparable>> attributes = dataSource.getAttributes();

        for ( Map.Entry<String, List<Comparable>> attributeEntry
                : attributes.entrySet() ) {
            Attribute attribute = new Attribute(
                attributeEntry.getKey(), attributeEntry.getValue() );

            inputData.addAttribute( attribute );
        }

        return inputData;
    }
}
