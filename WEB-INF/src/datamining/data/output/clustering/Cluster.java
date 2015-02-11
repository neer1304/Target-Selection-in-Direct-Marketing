package datamining.data.output.clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.math.stat.descriptive.summary.SumOfSquares;

/**
 * Defines a cluster (currently a group of points relatively close to the
 * centroid; generally - a group of instances being similar to each other in
 * a way, but different from other instances; this cluster definition has also
 * a name and information about the percentage of all points falling into
 * this cluster).
 */
public class Cluster {
    public static final String ATTRIBUTE_NAME = "CLUSTER";

    private String name;
    private Vector<Double> centroid;
    private List<Vector<Double>> points = new ArrayList<Vector<Double>>();
    private Double pointPercentage;

    public void addPoint( Vector<Double> point ) {
        points.add( point );
    }

    @Override
    public String toString() {
        String output = "Cluster: " + name + "(" + pointPercentage + "%)\n";

        return output;
    }

    public List<Vector<Double>> getPoints() {
        return points;
    }
    public void setPoints( List<Vector<Double>> points ) {
        this.points = points;
    }
    public String getName() {
        return name;
    }
    public void setName( String name ) {
        this.name = name;
    }

    public Vector<Double> getCentroid() {
        return centroid;
    }

    public void setCentroid( Vector<Double> centroid ) {
        this.centroid = centroid;
    }

    public Double getPointPercentage() {
        return pointPercentage;
    }

    public void setPointPercentage( Double pointPercentage ) {
        this.pointPercentage = pointPercentage;
    }
}
