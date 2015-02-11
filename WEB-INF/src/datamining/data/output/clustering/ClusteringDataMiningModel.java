package datamining.data.output.clustering;

import java.util.ArrayList;
import java.util.List;

import datamining.data.output.DataMiningModel;

/**
 * Defines a data mining model specific to clustering algorithms.
 */
public class ClusteringDataMiningModel extends DataMiningModel {
    protected List<String> attributeNameOrder = new ArrayList<String>();
    protected List<Cluster> clusters = new ArrayList<Cluster>();

    public void addAttributeNameToOrder( String attributeName ) {
        attributeNameOrder.add( attributeName );
    }

    public void addCluster( Cluster cluster ) {
        clusters.add( cluster );
    }

    public List<Cluster> getClusters() {
        return clusters;
    }

    public void setClusters( List<Cluster> clusters ) {
        this.clusters = clusters;
    }

    public List<String> getAttributeNameOrder() {
        return attributeNameOrder;
    }

    public void setAttributeNameOrder( List<String> attributeNameOrder ) {
        this.attributeNameOrder = attributeNameOrder;
    }
}
