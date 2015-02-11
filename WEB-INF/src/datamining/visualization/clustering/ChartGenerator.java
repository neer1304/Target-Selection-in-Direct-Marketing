package datamining.visualization.clustering;

import java.util.List;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import datamining.data.output.classification.Classifier;
import datamining.data.output.clustering.Cluster;

/**
 * Generates various charts using JFreeChart based on the output data.
 */
public class ChartGenerator {
    /**
     * Generates a XY chart visualizing the clusters found by a clustering
     * algorithm. Limited to two dimensions (because the chart is 2D).
     *
     * @param clusters clusters found by a clustering algorithm
     * @param firstAttributeIndex the index of the 1st dimension to visualize
     * @param firstAttributeName the name of the 1st dimension to visualize
     * @param secondAttributeIndex the index of the 2nd dimension to visualize
     * @param secondAttributeName the name of the 2nd dimension to visualize
     * @return a 2D XY chart visualizing the clusters found
     */
    public JFreeChart generateXYChart( List<Cluster> clusters,
            Integer firstAttributeIndex, String firstAttributeName,
            Integer secondAttributeIndex, String secondAttributeName ) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for ( Cluster cluster : clusters ) {
            XYSeries series = new XYSeries( cluster.getName() );

            for ( Vector<Double> point : cluster.getPoints() ) {
                series.add( point.get( firstAttributeIndex ),
                    point.get( secondAttributeIndex ) );
            }

            dataset.addSeries( series );
        }

        return ChartFactory.createScatterPlot( "Cluster Analysis",
            firstAttributeName, secondAttributeName, dataset,
            PlotOrientation.VERTICAL, true, true, false );
    }

    /**
     * Generates a pie chart showing the percentage of points falling into each
     * cluster.
     *
     * @param clusters clusters found by a clustering algorithm
     * @return a pie chart showing the distribution of points into clusters
     */
    public JFreeChart generatePieChart( List<Cluster> clusters ) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for ( Cluster cluster : clusters ) {
            dataset.setValue( cluster.getName(), cluster.getPointPercentage() );
        }

        return ChartFactory.createPieChart3D( "Cluster Analysis", dataset,
            true, true, false );
    }
    public JFreeChart generateBarChart(List<Classifier> classifiers){
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    	for(Classifier classifier : classifiers){
    		dataset.addValue(classifier.getAttributeValueLikelihood(), classifier.getDecisionValue(),classifier.getName());
    	}
    	JFreeChart chart = ChartFactory.createBarChart3D(
    	            "Probablity Analysis for Attributes",      	// chart title
    	            "Attributes",               				// domain axis label
    	            "Probablity Value Likelihood",              // range axis label
    	            dataset,                  					// data
    	            PlotOrientation.HORIZONTAL, 					// orientation
    	            true,                     					// include legend
    	            true,                     					// tooltips
    	            false                     					// urls
    	        );
    	return chart;
    	
    }
}
