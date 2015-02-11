package datamining.data.operators;

/**
 * Represents the equality operator inside rules.
 */
public enum EqualityOperator {
    EQUAL {
        @Override
        public boolean evaluate( Comparable leftArgument,
                Comparable rightArgument ) {
            return leftArgument.equals( rightArgument );
        }
        @Override
        public String toString() {
            return "=";
        }
    }, NOT_EQUAL {
        @Override
        public boolean evaluate( Comparable leftArgument,
                Comparable rightArgument ) {
            return ( leftArgument.equals( rightArgument ) == false );
        }
        @Override
        public String toString() {
            return "!=";
        }
    }, GREATER_THAN {
        @SuppressWarnings("unchecked")
        @Override
        public boolean evaluate( Comparable leftArgument,
                Comparable rightArgument ) {
            if ( leftArgument.compareTo( rightArgument ) > 0 ) {
                return true;
            }
            return false;
        }
        @Override
        public String toString() {
            return ">";
        }
    }, GREATER_THAN_OR_EQUAL {
        @SuppressWarnings("unchecked")
        @Override
        public boolean evaluate( Comparable leftArgument,
                Comparable rightArgument ) {
            if ( leftArgument.compareTo( rightArgument ) >= 0 ) {
                return true;
            }
            return false;
        }
        @Override
        public String toString() {
            return ">=";
        }
    }, LOWER_THAN {
        @SuppressWarnings("unchecked")
        @Override
        public boolean evaluate( Comparable leftArgument,
                Comparable rightArgument ) {
            if ( leftArgument.compareTo( rightArgument ) < 0 ) {
                return true;
            }
            return false;
        }
        @Override
        public String toString() {
            return "<";
        }
    }, LOWER_THAN_OR_EQUAL {
        @SuppressWarnings("unchecked")
        @Override
        public boolean evaluate( Comparable leftArgument,
                Comparable rightArgument ) {
            if ( leftArgument.compareTo( rightArgument ) <= 0 ) {
                return true;
            }
            return false;
        }
        @Override
        public String toString() {
            return "<=";
        }
    };

    public abstract boolean evaluate(
        Comparable leftArgument, Comparable rightArgument );
}
