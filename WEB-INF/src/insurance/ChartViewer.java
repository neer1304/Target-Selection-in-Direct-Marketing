package insurance;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.JFreeChart;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet implementation class for Servlet: ChartViewer
 *
 */
 public class ChartViewer extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ChartViewer() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 get the chart from storage
		HttpSession session = request.getSession(true);
	      JFreeChart  chart = (JFreeChart) session.getAttribute( "chart" );
	      // set the content type so the browser can see this as it is
	      response.setContentType( "image/jpeg" );
	   
	      // send the picture
	      BufferedImage buf = chart.createBufferedImage(750, 2400, null);
	      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder( response.getOutputStream() );
	      JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam( buf );
	      param.setQuality( 0.75f, true );
	      encoder.encode( buf, param ); 
	   
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	    
}