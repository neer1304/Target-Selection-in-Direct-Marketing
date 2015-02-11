package datamining.data.output;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Defines a data mining model (output data produced by data mining algorithms,
 * e.g. a list of rules).
 */
public class DataMiningModel {
    protected List<Rule> rules = new ArrayList<Rule>();

    public void addRule( Rule rule ) {
        rules.add( rule );
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules( List<Rule> rules ) {
        this.rules = rules;
    }
}
