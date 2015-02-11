<%@ page import="insurance.*" contentType="text/html; charset=iso-8859-1" language="java"  errorPage="" %>
<%@ page import="datamining.data.output.*,datamining.data.output.classification.*,java.util.*,org.jfree.chart.*" %>
<%@ page import="datamining.visualization.clustering.*,datamining.visualization.export.*, org.w3c.dom.svg.SVGDocument" %>
<%@ taglib uri="/WEB-INF/cewolf.tld" prefix="cewolf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Insurance Data Mining in CRM Systems</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="images/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="wrapper">
      <h1><img src="images/title.png" width="800" height="80" alt="Insurance Data Mining in CRM Systems" /></h1>
      <div id="booking">
          <h2>Result: Classification Analysis</h2>
<% 

PersonalInformationBean pIB =(PersonalInformationBean) session.getAttribute("personal");
ContributionInformationBean cIB = (ContributionInformationBean) session.getAttribute("contribution");
NumberInformationBean nIB = (NumberInformationBean) session.getAttribute("number");

out.println("Classification Analysis Report for : "+ pIB.getName());
//out.println(pIB.getName());
ClassificationDataMiningModel dataMiningModel = (ClassificationDataMiningModel) session.getAttribute("dataminingmodel");
//out.println(dataMiningModel);
List<Rule> rules = dataMiningModel.getRules();
int i = 0;
out.println(dataMiningModel.getAttributeValueProbability());
//OutputDataExporter outputDataExporter = new OutputDataExporter();
ChartGenerator chartGenerator = new ChartGenerator();
JFreeChart chart = chartGenerator.generateBarChart(dataMiningModel.getClassifiers());
//pageContext.setAttribute("chart",chart);

//SVGDocument svgDocument = outputDataExporter.exportChartToSVG(chartGenerator.generateBarChart(dataMiningModel.getClassifiers()),600,400 );
session.setAttribute("chart",(Object)chart);

ChartRenderingInfo info = new ChartRenderingInfo();
//populate the info
chart.createBufferedImage(750, 2400, info);
String imageMap = ChartUtilities.getImageMap( "map", info );


%>

<IMG src="ChartViewer" usemap="#map" align="center">
     </div>  
      
      <img src="images/bottom.png" width="800" height="30"  />
      
</div>
</body>
</html>