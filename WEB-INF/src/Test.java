import java.util.List;

import com.mysql.jdbc.Driver;

import datamining.algorithms.classification.BayesAlgorithm;
import datamining.data.input.InputData;
import datamining.data.input.InputDataBuilder;
import datamining.data.input.attribute.Attribute;
import datamining.data.input.attribute.Instance;
import datamining.data.output.DataMiningModel;
import datamining.data.sources.jdbc.JDBCDataSource;

public class Test {
	public static void main(String args[]){
		String connectionString = "jdbc:mysql://localhost:3306/insurancedata";
		String userName = "root";
		String password = "password";
		String query = "select * from trainingset";
		DataMiningModel dataMiningModel=null;
		try{
		
			//Driver driver = DriverManager.registerDriver(Driver);
		
		JDBCDataSource dataSource = new JDBCDataSource(new Driver(),connectionString,userName, password, query);
		//InsuranceDAO id = new InsuranceDAO();
		BayesAlgorithm bayesAlgorithm = new BayesAlgorithm();
		InputDataBuilder inputDataBuilder=new InputDataBuilder();
		InputData inputData = inputDataBuilder.build(dataSource);
		List<Attribute> attributes = inputData.getAttributes();
		String[] attributeNames = null;
		int i=0;
		for(Attribute attribute : attributes){
			attributeNames[i]= (attribute.getName());
			i++;
		}
		Instance instanceToBeClassified = new Instance();
		int[] attributeValue ={6,	1,	3,	2,	2,0,5,	0,	4,	5,	2,	2,	1,	4,	5,	5,	4,	0,	5,	0,	0,	4, 0,	0,	4,	3,	0,	2,	1,	3,	6,	9,	0,	0,	7,	2,	1,	1,	5,	4,	0,	6,	8,	2,	0,	0,	6,	0,	4,	0,	0,	0,	0,	0,	3,	0,	0,	0,	4,	0,	0,	0,	0,	0,	1,	0,	0,	1,	0,	1,	0,	0,	0,	0,	0,	2,	0,	0,	0,	1,	0,	0,	0,	0,	0};
		for(i = 0; i<attributeNames.length-1; i++){
			instanceToBeClassified.addAttributeValue(attributeNames[i],attributeValue[i]);
		}
		inputData.setInstanceToBeClassified(instanceToBeClassified);
		dataMiningModel = bayesAlgorithm.analyze(inputData);
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
