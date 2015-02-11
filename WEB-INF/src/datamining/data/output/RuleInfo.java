
package datamining.data.output;

/**
 * Associates a rule with information about its coverage and accuracy against
 * an instance.
 */
public class RuleInfo implements Comparable<RuleInfo> {
    private Rule rule;
    private Integer coverage;
    private Double accuracy;

    public RuleInfo( Rule rule, Integer coverage, Double accuracy ) {
        this.rule = rule;
        this.coverage = coverage;
        this.accuracy = accuracy;
    }

    /**
     * Compares two rule infos using their accuracy and coverage.
     */
    public int compareTo( RuleInfo ruleInfo ) {
        int comparisonResult = this.accuracy.compareTo( ruleInfo.accuracy );

        if ( comparisonResult == 0 ) {
            comparisonResult = this.coverage.compareTo( ruleInfo.coverage );
        }

        return comparisonResult;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public Integer getCoverage() {
        return coverage;
    }

    public Rule getRule() {
        return rule;
    }
}
