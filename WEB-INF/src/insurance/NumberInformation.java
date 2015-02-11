package insurance;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Driver;

import datamining.algorithms.classification.BayesAlgorithm;
import datamining.data.input.InputData;
import datamining.data.input.InputDataBuilder;
import datamining.data.input.attribute.Attribute;
import datamining.data.input.attribute.Instance;
import datamining.data.output.classification.ClassificationDataMiningModel;
import datamining.data.sources.jdbc.JDBCDataSource;

/**
 * Servlet implementation class for Servlet: NumberInformation
 *
 */
 public class NumberInformation extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	public NumberInformation() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		NumberInformationBean nIB = new NumberInformationBean();
		nIB.setPrivateThirdPartyInsurance(request.getParameter("1"));
		nIB.setThirdPartyInsuranceFirms(request.getParameter("2"));
		nIB.setThirdPartyInsuranceAgricultures(request.getParameter("3"));
		nIB.setCarPolicies(request.getParameter("4"));
		nIB.setDeliveryVanPolicies(request.getParameter("5"));
		nIB.setContributionMotorcycleScooter(request.getParameter("6"));
		nIB.setLorryPolicies(request.getParameter("7"));
		nIB.setTrailerPolicies(request.getParameter("8"));
		nIB.setTractorPolicies(request.getParameter("9"));
		nIB.setAgricultureMachinesPolices(request.getParameter("10"));
		nIB.setMopedPolicies(request.getParameter("11"));
		nIB.setLifeInsurances(request.getParameter("12"));
		nIB.setPrivateInsurancePolicies(request.getParameter("13"));
		nIB.setFamilyAccidentInsurancePolicies(request.getParameter("14"));
		nIB.setDisabilityInsurancePolicies(request.getParameter("15"));
		nIB.setFirePolicies(request.getParameter("16"));
		nIB.setSurfboardPolicies(request.getParameter("17"));
		nIB.setBoatPolicies(request.getParameter("18"));
		nIB.setBicyclePolicies(request.getParameter("19"));
		nIB.setPropertyInsurancePolicies(request.getParameter("20"));
		nIB.setSocialSecurityInsurancePolicies(request.getParameter("21"));

		HttpSession session = request.getSession(true);
		session.setAttribute("number", nIB);


		String connectionString = "jdbc:mysql://localhost:3306/insurancedata";
		String userName = "root";
		String password = "password";
		String query = "select * from trainingset";//limit 1, 1000
		ClassificationDataMiningModel dataMiningModel=null;
		try{

			InsuranceDAO id = new InsuranceDAO();
			PersonalInformationBean pIB =(PersonalInformationBean) session.getAttribute("personal");
			Random rnd = new Random();
			int number=0;

			number = (Integer.parseInt(pIB.getCity())*100 )+(Integer.parseInt(pIB.getOccupation())*10)+Integer.parseInt(pIB.getIncome());
			/*while (number > 1735 || number < 1){
				number = rnd.nextInt();
				number /= 100000;
				System.out.println("random number : "+number);
			}*/
			SociodemographicBean sb = id.getSociodemographicData(number);
			ContributionInformationBean cIB = (ContributionInformationBean) session.getAttribute("contribution");

			Integer[] attributeValue=new Integer[100];// = ;


			attributeValue[1] = sb.getMOSTYPE();
			attributeValue[2] = sb.getMAANTHUI();
			attributeValue[3] = sb.getMGEMOMV();
			attributeValue[4] = sb.getMGEMLEEF();
			attributeValue[5] = sb.getMoshoofd();
			attributeValue[6] = sb.getMGODRK();
			attributeValue[7] = sb.getMGODPR();
			attributeValue[8] = sb.getMGODOV();
			attributeValue[9] = sb.getMGODGE();
			attributeValue[10] = sb.getMRELGE();
			attributeValue[11] = sb.getMRELSA();
			attributeValue[12] = sb.getMRELOV();
			attributeValue[13] = sb.getMFALLEEN();
			attributeValue[14] = sb.getMFGEKIND();
			attributeValue[15] = sb.getMFWEKIND();
			attributeValue[16] = sb.getMOPLHOOG();
			attributeValue[17] = sb.getMOPLMIDD();
			attributeValue[18] = sb.getMOPLLAAG();
			attributeValue[19] = sb.getMBERHOOG();
			attributeValue[20] = sb.getMBERZELF();
			attributeValue[21] = sb.getMBERBOER();
			attributeValue[22] = sb.getMBERMIDD();
			attributeValue[23] = sb.getMBERARBG();
			attributeValue[24] = sb.getMBERARBO();
			attributeValue[25] = sb.getMSKA();
			attributeValue[26] = sb.getMSKB1();
			attributeValue[27] = sb.getMSKB2();
			attributeValue[28] = sb.getMSKC();
			attributeValue[29] = sb.getMSKD();
			attributeValue[30] = sb.getMHHUUR();
			attributeValue[31] = sb.getMHKOOP();
			attributeValue[32] = sb.getMAUT1();
			attributeValue[33] = sb.getMAUT2();
			attributeValue[34] = sb.getMAUT0();
			attributeValue[35] = sb.getMZFONDS();
			attributeValue[36] = sb.getMZPART();
			attributeValue[37] = sb.getMINKM30();
			attributeValue[38] = sb.getMINK3045();
			attributeValue[39] = sb.getMINK4575();
			attributeValue[40] = sb.getMINK7512();
			attributeValue[41] = sb.getMINK123M();
			attributeValue[42] = sb.getMINKGEM();
			attributeValue[43] = sb.getMKOOPKLA();

			attributeValue[44] = Integer.parseInt( cIB.getPrivateThirdPartyInsurance());
			attributeValue[45] = Integer.parseInt( cIB.getThirdPartyInsuranceFirms());
			attributeValue[46] = Integer.parseInt( cIB.getThirdPartyInsuranceAgricultures());
			attributeValue[47] = Integer.parseInt( cIB.getCarPolicies());
			attributeValue[48] = Integer.parseInt( cIB.getDeliveryVanPolicies());
			attributeValue[49] = Integer.parseInt( cIB.getContributionMotorcycleScooter());
			attributeValue[50] = Integer.parseInt( cIB.getLorryPolicies());
			attributeValue[51] = Integer.parseInt( cIB.getTrailerPolicies());
			attributeValue[52] = Integer.parseInt( cIB.getTractorPolicies());
			attributeValue[53] = Integer.parseInt( cIB.getAgricultureMachinesPolices());
			attributeValue[54] = Integer.parseInt( cIB.getMopedPolicies());
			attributeValue[55] = Integer.parseInt( cIB.getLifeInsurances());
			attributeValue[56] = Integer.parseInt( cIB.getPrivateAccidentInsurancePolicies());
			attributeValue[57] = Integer.parseInt( cIB.getFamilyAccidentInsurancePolicies());
			attributeValue[58] = Integer.parseInt( cIB.getDisabilityInsurancePolicies());
			attributeValue[59] = Integer.parseInt( cIB.getFirePolicies());
			attributeValue[60] = Integer.parseInt( cIB.getSurfboardPolicies());
			attributeValue[61] = Integer.parseInt( cIB.getBoatPolicies());
			attributeValue[62] = Integer.parseInt( cIB.getBicyclePolicies());
			attributeValue[63] = Integer.parseInt( cIB.getPropertyInsurancePolicies());
			attributeValue[64] = Integer.parseInt( cIB.getSocialSecurityInsurancePolicies());


 			attributeValue[65] = Integer.parseInt( nIB.getPrivateThirdPartyInsurance());
			attributeValue[66] = Integer.parseInt( nIB.getThirdPartyInsuranceFirms());
			attributeValue[67] = Integer.parseInt( nIB.getThirdPartyInsuranceAgricultures());
			attributeValue[68] = Integer.parseInt( nIB.getCarPolicies());
			attributeValue[69] = Integer.parseInt( nIB.getDeliveryVanPolicies());
			attributeValue[70] = Integer.parseInt( nIB.getContributionMotorcycleScooter());
			attributeValue[71] = Integer.parseInt( nIB.getLorryPolicies());
			attributeValue[72] = Integer.parseInt( nIB.getTrailerPolicies());
			attributeValue[73] = Integer.parseInt( nIB.getTractorPolicies());
			attributeValue[74] = Integer.parseInt( nIB.getAgricultureMachinesPolices());
			attributeValue[75] = Integer.parseInt( nIB.getMopedPolicies());
			attributeValue[76] = Integer.parseInt( nIB.getLifeInsurances());
			attributeValue[77] = Integer.parseInt( nIB.getPrivateInsurancePolicies());
			attributeValue[78] = Integer.parseInt( nIB.getFamilyAccidentInsurancePolicies());
			attributeValue[79] = Integer.parseInt( nIB.getDisabilityInsurancePolicies());
			attributeValue[80] = Integer.parseInt( nIB.getFirePolicies());
			attributeValue[81] = Integer.parseInt( nIB.getSurfboardPolicies());
			attributeValue[82] = Integer.parseInt( nIB.getBoatPolicies());
			attributeValue[83] = Integer.parseInt( nIB.getBicyclePolicies());
			attributeValue[84] = Integer.parseInt( nIB.getPropertyInsurancePolicies());
			attributeValue[85] = Integer.parseInt( nIB.getSocialSecurityInsurancePolicies());


			//Driver driver = DriverManager.registerDriver(Driver);

		JDBCDataSource dataSource = new JDBCDataSource(new Driver(),connectionString,userName, password, query);
		//InsuranceDAO id = new InsuranceDAO();
		BayesAlgorithm bayesAlgorithm = new BayesAlgorithm();
		InputDataBuilder inputDataBuilder=new InputDataBuilder();
		InputData inputData = inputDataBuilder.build(dataSource);
		List<Attribute> attributes = inputData.getAttributes();
		String[] attributeNames = new String[86];
		int i=0;
		for(Attribute attribute : attributes){
			attributeNames[i]= (attribute.getName());
			//System.out.println(attribute.getName());
			i++;
		}
		Instance instanceToBeClassified = new Instance();
		/*String[] attributeValue ={"6",	"1",	"3",	"2",	"2","0","5",	"0",	"4",	"5",	"2",	"2",	"1",	"4",	"5",	"5",	"4",	"0",	"5",	"0",	"0",	"4", "0",	"0",	"4",	"3",	"0",	"2",	"1",	"3",	"6",	"9",	"0",	"0",	"7",	"2",	"1",	"1",	"5",	"4",	"0",	"6",	"8",	"2",	"0",	"0",	"6",	"0",	"4",	"0",	"0",	"0",	"0",	"0",	"3",	"0",	"0",	"0",	"4",	"0",	"0",	"0",	"0",	"0",	"1",	"0",	"0",	"1",	"0",	"1",	"0",	"0",	"0",	"0",	"0",	"2",	"0",	"0","0",	"1",	"0",	"0",	"0",	"0",	"0"};
		Integer[] attributeValue ={6,	1,	3,	2,	2,0,5,	0,4,	5,	2,	2,	1,	4,	5,	5,	4,	0,	5,	0,	0,	4, 0,	0,	4,	3,	0,	2,	1,	3,	6,	9,	0,	0,	7,2,	1,	1,	5,	4,	0,	6,	8,	2,	0,	0,	6,	0,	4,	0,	0,	0,	0,	0,	3,	0,	0,0,	4,	0,	0,	0,	0,	0,	1,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	2,	0,	0,0,	1,	0,	0,	0,	0,	0};
		int[] attributeValue ={33,	1,	4,	2,	8,	0,	6,	0,	3,	5,	0,	4,	1,	1,	8,	2,	2,	6,	0,	0,	1,	2,	6,	1,	0,	2,	1,	5,	3,	1,	8,	8,	1,	1,	8,	1,	3,	3,	3,	0,	0,	3,	3,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	4,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0};
		Integer[] attributeValue = {9,1,2,3,3,0,6,1,2,7,1,1,5,1,4,5,4,0,4,0,0,5,1,0,1,8,0,0,0,9,0,8,1,1,4,5,0,5,4,0,0,5,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		Integer[] attributeValue = {31,1,2,4,7,0,2,0,7,9,0,0,0,6,3,0,0,9,0,0,0,2,4,4,0,0,0,7,2,9,0,7,2,0,9,0,5,4,0,0,0,3,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0};
		Integer[] attributeValue = {35,1,2,4,8,2,5,1,2,8,0,1,2,5,3,1,5,4,2,0,0,3,3,3,1,1,5,4,0,8,1,8,1,1,4,5,2,5,2,0,0,3,5,2,0,0,6,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0};
		Integer[] attributeValue = {33,	1,	3,	2,	8,	0,	5,	1,	3,	7,	0,	2,	1,	2,	6,	1,	2,	7,	1,	0,	1,	2,	5,	2,	1,	1,	2,	6,	1,	1,	8,	8,	0,	1,	8,	1,	0,	4,	5,	0,	0,	4,	3,	0,	0,	0,	6,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	5,	0,	0,	0,	0,	0,	0,	0,	0,	1,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	1,	4,	0,	2,	0,	0};
		Integer[] attributeValue = {23,1,1,1,5,1,4,2,3,4,1,4,5,4,1,1,4,4,2,1,0,3,4,0,1,1,0,7,0,8,1,6,0,3,7,2,4,0,5,0,0,4,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0};
		Integer[] attributeValue = {36,1,3,3,8,2,1,0,6,8,0,1,1,1,7,1,5,4,2,1,0,4,3,0,2,2,2,4,0,5,4,6,2,1,2,7,2,3,3,2,1,5,3,2,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,1,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
		Integer[] attributeValue = {8,1,4,3,2,1,4,1,4,7,1,2,0,0,9,3,6,0,0,0,0,9,0,0,3,0,6,0,0,0,9,6,2,1,5,4,0,0,5,0,4,9,7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};*/
		for(i = 0; i<attributeNames.length-1; i++){
			instanceToBeClassified.addAttributeValue(attributeNames[i],new Double(attributeValue[i+1]));
		}
		inputData.setInstanceToBeClassified(instanceToBeClassified);


		inputData.setAttributeAsDecision(attributeNames[85]);
		dataMiningModel = bayesAlgorithm.analyze(inputData);

		}catch(Exception e){
			e.printStackTrace();
		}
		session.setAttribute("dataminingmodel",dataMiningModel);

		RequestDispatcher rd = request.getRequestDispatcher("/process.jsp");
		rd.forward(request,response);
	}
}