package datamining.data.output;

import java.util.List;
import java.util.Stack;

import datamining.data.input.attribute.Instance;
import datamining.data.operators.LogicalOperator;


/**
 * An abstract class that defines how to evaluate an expression consisting of
 * rule elements against an instance.
 */
public abstract class AbstractRuleElementEvaluable implements Evaluable {
    /**
     * Evaluates a list of rule elements against an instance. The evaluation
     * goes from left to right, all operators are equally important. If there
     * are no elements to evaluate, returns true.
     *
     * @see datamining.data.output.RuleElement
     */
    protected boolean evaluateRuleElements( List<RuleElement> ruleElements,
            Instance instance ) {
        Stack<Boolean> evaluationStack = new Stack<Boolean>();

        for ( RuleElement ruleElement : ruleElements ) {
            evaluationStack.push( ruleElement.evaluate( instance ) );

            if ( evaluationStack.size() == 2 ) {
                boolean secondEvaluationResult = evaluationStack.pop();
                boolean firstEvaluationResult = evaluationStack.pop();

                LogicalOperator logicalOperator
                    = ruleElement.getLogicalOperator();

                evaluationStack.push( logicalOperator.evaluate(
                    firstEvaluationResult, secondEvaluationResult ) );
            }
        }

        if ( evaluationStack.isEmpty() ) {
            return true;
        }

        return evaluationStack.pop();
    }
}
