package datamining.data.operators;

import datamining.data.output.RuleElement;

/**
 * Represents the logical operator inside rules.
 */
public enum LogicalOperator {
    AND {
        @Override
        public boolean evaluate( boolean leftArgument, boolean rightArgument ) {
            return leftArgument && rightArgument;
        }
    }, OR {
        @Override
        public boolean evaluate( boolean leftArgument, boolean rightArgument ) {
            return leftArgument || rightArgument;
        }
    }, XOR {
        @Override
        public boolean evaluate( boolean leftArgument, boolean rightArgument ) {
            return leftArgument ^ rightArgument;
        }
    };

    public abstract boolean evaluate( boolean leftArgument,
        boolean rightArgument );
}
