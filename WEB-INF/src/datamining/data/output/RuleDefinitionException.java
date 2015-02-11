package datamining.data.output;

/**
 * Thrown when a rule is broken and cannot be used in its current form.
 */
public class RuleDefinitionException extends RuntimeException {
    public RuleDefinitionException() {
        super();
    }

    public RuleDefinitionException( String message ) {
        super( message );
    }

    public RuleDefinitionException( Throwable cause ) {
        super( cause );
    }

    public RuleDefinitionException( String message, Throwable cause ) {
        super( message, cause );
    }
}
