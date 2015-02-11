package datamining.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.math.stat.descriptive.moment.Mean;
import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math.stat.descriptive.summary.SumOfSquares;


/**
 * A utility class that simplifies some calculations required in data mining
 * algorithms, e.g. Bayes Classifier.
 */
public class MathCalculator {
    private static Log log = LogFactory.getLog( MathCalculator.class );

    /**
     * Calculates the normal distribution for the attribute value of the
     * instance being classified. Uses all known values of this attribute
     * to calculate the mean and standard deviation. The probability density
     * function used:
     * <pre>
     *              1            ( -(x-m)^2 )
     * f(x) = ------------- * exp( -------- )
     *        sd*sqrt(2*PI)      ( 2*(sd)^2 )
     * </pre>
     *
     * <code>m</code> - mean, <code>sd</code> - standard deviation
     *
     * @param attributeValues all values of the attribute (for m and sd)
     * @param x the attribute value of the instance being classified
     * @return the normal distribution for x
     */
    public Double calculateNormalDistribution(
            Comparable[] attributeValues, Double x ) {
        Double mean = calculateMean( attributeValues );
        Double standardDeviation
            = calculateStandardDeviation( attributeValues, mean );

        Double normalDistribution
            = 1 / ( Math.sqrt( 2 * Math.PI ) * standardDeviation );
        normalDistribution *= Math.exp(
            -Math.pow( ( x - mean ) / standardDeviation, 2 ) / 2 );

        log.debug( "normalDistribution( " + x + " ) = " + normalDistribution );

        return normalDistribution;
    }

    /**
     * Calculates the mean of all attribute values.
     *
     * @param attributeValues attribute values
     * @return the mean
     */
    public Double calculateMean( Comparable[] attributeValues ) {
        Mean mean = new Mean();

        Double evaluatedMean
            = mean.evaluate( convertToPrimitives( attributeValues ) );

        log.debug( "mean = " + evaluatedMean );

        return evaluatedMean;
    }

    /**
     * Calculates the standard deviation of all attribute values.
     *
     * @param attributeValues attribute values
     * @return the standard deviation
     */
    public Double calculateStandardDeviation( Comparable[] attributeValues,
            Double mean ) {
        StandardDeviation standardDeviation = new StandardDeviation();

        Double evaluatedStdDev = standardDeviation.evaluate(
            convertToPrimitives( attributeValues ), mean );

        log.debug( "standardDeviation( " + mean + " ) = " + evaluatedStdDev );

        return evaluatedStdDev;
    }

    /**
     * Calculates the distance between two points in a nD space (assumes that
     * n = firstPoint.size() = secondPoint.size()).
     *
     * @param firstPoint the first point
     * @param secondPoint the second point
     * @return the distance between both points
     */
    public Double calculateDistance( Vector<Double> firstPoint,
            Vector<Double> secondPoint ) {
        SumOfSquares sumOfSquares = new SumOfSquares();

        for ( int i = 0; i < firstPoint.size(); ++i ) {
            sumOfSquares.increment(
                secondPoint.get( i ) - firstPoint.get( i ) );
        }

        return Math.sqrt( sumOfSquares.getResult() );
    }

    /**
     * Calculates the centroid of all given points in a nD space (assumes that
     * all points have n coordinates). Each coordinate of the centroid is a mean
     * of all values of the same coordinate of each point.
     *
     * @param points all points
     * @return the centroid of all given points
     */
    public Vector<Double> calculateCentroid( List<Vector<Double>> points ) {
        List<Mean> coordinateMeans = new ArrayList<Mean>();

        for ( int i = 0; i < points.get( 0 ).size(); ++i ) {
            coordinateMeans.add( new Mean() );
        }

        for ( Vector<Double> point : points ) {
            for ( int i = 0; i < point.size(); ++i ) {
                coordinateMeans.get( i ).increment( point.get( i ) );
            }
        }

        Vector<Double> centroid = new Vector<Double>();

        for ( Mean mean : coordinateMeans ) {
            centroid.add( mean.getResult() );
        }

        return centroid;
    }

    protected double[] convertToPrimitives( Comparable[] values ) {
        double[] primitives = new double[ values.length ];

        for ( int i = 0; i < values.length; i++ ) {
            primitives[ i ] = (Double) values[ i ];
        }

        return primitives;
    }
}
