package datamining.data.output;

import datamining.data.input.attribute.Instance;

/**
 * Base interface for all classes that can evaluate against an instance.
 */
public interface Evaluable {
    boolean evaluate( Instance instance );
}
