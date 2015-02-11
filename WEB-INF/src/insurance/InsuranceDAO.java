package insurance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datamining.data.input.InputData;

public class InsuranceDAO {

	private Connection  connection;
	private ResultSet rs;
	public InsuranceDAO(){
		super();
		getConnection();
	}
	public void getConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurancedata","root","password");
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public void closeConnection(){
		try{
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	public InputData getTrainingSet(){
		InputData inputData = new InputData();

		PreparedStatement ps;
		String query = "select * from traningset";
		try{
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()){

			}
		}catch(Exception e){
			e.printStackTrace();
		}
			return inputData;
	}
	public SociodemographicBean getSociodemographicData(int number){
		SociodemographicBean sb = new SociodemographicBean();
		PreparedStatement ps;
		String query = "select * from sociodemographic_data where rowid = ?";
		try{
			ps = connection.prepareStatement(query);
			ps.setInt(1,number);
			rs = ps.executeQuery();
			System.out.println("Before fetch");
			while(rs.next()){
				sb.setMOSTYPE(rs.getInt(2));
				sb.setMAANTHUI(rs.getInt(3));
				sb.setMGEMOMV(rs.getInt(4));
				sb.setMGEMLEEF(rs.getInt(5));
				sb.setMoshoofd(rs.getInt(6));
				sb.setMGODRK(rs.getInt(7));
				sb.setMGODPR(rs.getInt(8));
				sb.setMGODOV(rs.getInt(9));
				sb.setMGODGE(rs.getInt(10));
				sb.setMRELGE(rs.getInt(11));
				sb.setMRELSA(rs.getInt(12));
				sb.setMRELOV(rs.getInt(13));
				sb.setMFALLEEN(rs.getInt(14));
				sb.setMFGEKIND(rs.getInt(15));
				sb.setMFWEKIND(rs.getInt(16));
				sb.setMOPLHOOG(rs.getInt(17));
				sb.setMOPLMIDD(rs.getInt(18));
				sb.setMOPLLAAG(rs.getInt(19));
				sb.setMBERHOOG(rs.getInt(20));
				sb.setMBERZELF(rs.getInt(21));
				sb.setMBERBOER(rs.getInt(22));
				sb.setMBERMIDD(rs.getInt(23));
				sb.setMBERARBG(rs.getInt(24));
				sb.setMBERARBO(rs.getInt(25));
				sb.setMSKA(rs.getInt(26));
				sb.setMSKB1(rs.getInt(27));
				sb.setMSKB2(rs.getInt(28));
				sb.setMSKC(rs.getInt(29));
				sb.setMSKD(rs.getInt(30));
				sb.setMHHUUR(rs.getInt(31));
				sb.setMHKOOP(rs.getInt(32));
				sb.setMAUT1(rs.getInt(33));
				sb.setMAUT2(rs.getInt(34));
				sb.setMAUT0(rs.getInt(35));
				sb.setMZFONDS(rs.getInt(36));
				sb.setMZPART(rs.getInt(37));
				sb.setMINKM30(rs.getInt(38));
				sb.setMINK3045(rs.getInt(39));
				sb.setMINK4575(rs.getInt(40));
				sb.setMINK7512(rs.getInt(41));
				sb.setMINK123M(rs.getInt(42));
				sb.setMINKGEM(rs.getInt(43));
				sb.setMKOOPKLA(rs.getInt(44));
				System.out.println("After fetch");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb;
	}
}