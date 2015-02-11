package datamining.algorithms;

import datamining.data.input.InputData;
import datamining.data.output.DataMiningModel;

/**
 * Base interface for all data mining algorithms.
 */
public interface DataMiningAlgorithm {
    /**
     * Analyzes input data (attributes and decisions) and produces output data
     * (rules, decision trees, clusters, ...).
     */
    DataMiningModel analyze( InputData inputData );
}
