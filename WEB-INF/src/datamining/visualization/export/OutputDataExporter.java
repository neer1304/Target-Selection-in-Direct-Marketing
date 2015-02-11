package datamining.visualization.export;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.jfree.chart.JFreeChart;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGDocument;

/**
 * Export the data mining model to other formats. Useful for GUIs using this
 * library.
 */
public class OutputDataExporter {
    /**
     * Exports charts created using JFreeChart to SVG (which can be embedded
     * in web pages or anywhere else).
     *
     * @param chart the chart being exported
     * @param width the width of the SVG
     * @param height the height of the SVG
     * @return SVG document
     *
     * @see datamining.visualization.clustering.ChartGenerator
     */
    public SVGDocument exportChartToSVG( JFreeChart chart, Integer width,
            Integer height ) {
        DOMImplementation domImplementation
            = SVGDOMImplementation.getDOMImplementation();

        String svgNamespace = SVGDOMImplementation.SVG_NAMESPACE_URI;

        SVGDocument svgDocument
            = (SVGDocument) domImplementation.createDocument(
                svgNamespace, "svg", null );

        SVGGraphics2D svgGraphics2D = new SVGGraphics2D( svgDocument );

        Rectangle2D rectangle2D = new Rectangle2D.Double( 0, 0, width, height );

        chart.draw( svgGraphics2D, rectangle2D );

        svgGraphics2D.dispose();

        svgGraphics2D.setSVGCanvasSize( new Dimension( width, height ) );
        Element rootElement = svgDocument.getDocumentElement();

        svgGraphics2D.getRoot( rootElement );

        return svgDocument;
    }
}
