package log;

import javax.servlet.ServletException;

import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class for Servlet: LogInitialize
 *
 */
 public class LogInitialize extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LogInitialize() {
		super();
	}   	 	  	  	  
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		String prefix =  getServletContext().getRealPath("/");
		String file = getInitParameter("log4j-init-file");
		// if the log4j-init-file is not set, then no point in trying
		if(file != null) {
		PropertyConfigurator.configure(prefix+file);
		}   
	}
 }