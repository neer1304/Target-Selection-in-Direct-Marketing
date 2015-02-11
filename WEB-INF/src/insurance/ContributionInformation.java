package insurance;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: ContributionInformation
 *
 */
 public class ContributionInformation extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
  
	public ContributionInformation() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ContributionInformationBean cIB = new ContributionInformationBean();
		cIB.setPrivateThirdPartyInsurance(request.getParameter("1"));
		cIB.setThirdPartyInsuranceFirms(request.getParameter("2"));
		cIB.setThirdPartyInsuranceAgricultures(request.getParameter("3"));
		cIB.setCarPolicies(request.getParameter("4"));
		cIB.setDeliveryVanPolicies(request.getParameter("5"));
		cIB.setContributionMotorcycleScooter(request.getParameter("6"));
		cIB.setLorryPolicies(request.getParameter("7"));
		cIB.setTrailerPolicies(request.getParameter("8"));
		cIB.setTractorPolicies(request.getParameter("9"));
		cIB.setAgricultureMachinesPolices(request.getParameter("10"));
		cIB.setMopedPolicies(request.getParameter("11"));
		cIB.setLifeInsurances(request.getParameter("12"));
		cIB.setPrivateAccidentInsurancePolicies(request.getParameter("13"));
		cIB.setFamilyAccidentInsurancePolicies(request.getParameter("14"));
		cIB.setDisabilityInsurancePolicies(request.getParameter("15"));
		cIB.setFirePolicies(request.getParameter("16"));
		cIB.setSurfboardPolicies(request.getParameter("17"));
		cIB.setBoatPolicies(request.getParameter("18"));
		cIB.setBicyclePolicies(request.getParameter("19"));
		cIB.setPropertyInsurancePolicies(request.getParameter("20"));
		cIB.setSocialSecurityInsurancePolicies(request.getParameter("21"));
		
		HttpSession session = request.getSession(true);
		session.setAttribute("contribution", cIB);
		
		RequestDispatcher rd = request.getRequestDispatcher("number.jsp");
		rd.forward(request,response);
	}   	  	    
}