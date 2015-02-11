package insurance;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: PersonalInformation
 *
 */
 public class PersonalInformation extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
  
	public PersonalInformation() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PersonalInformationBean pIB = new PersonalInformationBean();
		pIB.setName(request.getParameter("name"));
		pIB.setEmail(request.getParameter("email"));
		pIB.setSex(request.getParameter("sex"));
		pIB.setAge(request.getParameter("age"));
		pIB.setAddress(request.getParameter("address"));
		pIB.setCity(request.getParameter("city"));
		pIB.setOccupation(request.getParameter("occupation"));
		pIB.setIncome(request.getParameter("income"));
		
/*		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("sex"));
		System.out.println(request.getParameter("age"));
		System.out.println(request.getParameter("address"));
		System.out.println(request.getParameter("city"));
		System.out.println(request.getParameter("occupation"));
		System.out.println(request.getParameter("income"));*/
		
		HttpSession session = request.getSession(true);
		
		session.setAttribute("personal", pIB);
		
		RequestDispatcher rd=request.getRequestDispatcher("/contribution.jsp");
    	rd.forward(request,response);
	}   	  	    
}