package xpathuy5e1l;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xPathUY5E1L {

	public static void main(String[] args) throws Exception {
		//Document & Parser
		DocumentBuilderFactory studentFactory = DocumentBuilderFactory.newInstance();
		studentFactory.setNamespaceAware(true);
		DocumentBuilder studentBuilder = studentFactory.newDocumentBuilder();
		Document studentDoc = studentBuilder.parse("src/xpathuy5e1l/studentUY5E1L.xml");
		
		//XPath
		XPathFactory xpathStudentFactory = XPathFactory.newInstance();
		XPath studentXPath = xpathStudentFactory.newXPath();
		
		System.out.println("(1) Összes student element, melyek a class gyermekei:");
		
		XPathExpression studentExpr = studentXPath.compile("/class/student/*");
		Object result = studentExpr.evaluate(studentDoc, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	Node node = nodes.item(i);

	    	System.out.print(node.getNodeName());
	    	System.out.println(": " + node.getTextContent());
	    	
	    	if(node.getNodeName().equals("kor")) {
	    		System.out.println("");
	    	}
	    }
	    
	    System.out.println("(2) Student element, melynek van id-je és az '02':");
	    
	    studentExpr = studentXPath.compile("//*[@id = '02']");
		result = studentExpr.evaluate(studentDoc, XPathConstants.NODESET);
	    nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	Node node = nodes.item(i);

	    	System.out.print(node.getNodeName());
	    	System.out.println(": " + node.getTextContent());
	    	
	    	if(node.getNodeName().equals("kor")) {
	    		System.out.println("");
	    	}
	    }
		
	    System.out.println("(3) Az összes student element függetlenül attól, hogy hol helyezkednek el:");
	    
	    studentExpr = studentXPath.compile("//*");
	    result = studentExpr.evaluate(studentDoc, XPathConstants.NODESET);
	    nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	Node node = nodes.item(i);

	    	System.out.print(node.getNodeName());
	    	System.out.println(": " + node.getTextContent());
	    	
	    	if(node.getNodeName().equals("kor")) {
	    		System.out.println("");
	    	}
	    }
	    
	    System.out.println("(4) A második student element a class root-ból:");
	    
	    studentExpr = studentXPath.compile("/class/student[2]");
	    result = studentExpr.evaluate(studentDoc, XPathConstants.NODESET);
	    nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	Node node = nodes.item(i);

	    	System.out.print(node.getNodeName());
	    	System.out.println(": " + node.getTextContent());
	    	
	    	if(node.getNodeName().equals("kor")) {
	    		System.out.println("");
	    	}
	    }
	    
	    System.out.println("(5) Az utolsó student element a class root-ból:");
	    
	    studentExpr = studentXPath.compile("/class/student[last()]");
	    result = studentExpr.evaluate(studentDoc, XPathConstants.NODESET);
	    nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	Node node = nodes.item(i);

	    	System.out.print(node.getNodeName());
	    	System.out.println(": " + node.getTextContent());
	    	
	    	if(node.getNodeName().equals("kor")) {
	    		System.out.println("");
	    	}
	    }
	    
	    System.out.println("(6) Az utolsó előtti student element a class root-ból:");
	    
	    studentExpr = studentXPath.compile("/class/student[last()-1]");
	    result = studentExpr.evaluate(studentDoc, XPathConstants.NODESET);
	    nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	Node node = nodes.item(i);

	    	System.out.print(node.getNodeName());
	    	System.out.println(": " + node.getTextContent());
	    	
	    	if(node.getNodeName().equals("kor")) {
	    		System.out.println("");
	    	}
	    }
	    
	    System.out.println("(7) Az első két student element a class root-ból:");
	    
	    studentExpr = studentXPath.compile("/class/student[position()<3]");
	    result = studentExpr.evaluate(studentDoc, XPathConstants.NODESET);
	    nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	Node node = nodes.item(i);

	    	System.out.print(node.getNodeName());
	    	System.out.println(": " + node.getTextContent());
	    	
	    	if(node.getNodeName().equals("kor")) {
	    		System.out.println("");
	    	}
	    }
	    
	    System.out.println("(8) Az összes gyerek element a class root-ból:");
	    
	    studentExpr = studentXPath.compile("/class/*");
	    result = studentExpr.evaluate(studentDoc, XPathConstants.NODESET);
	    nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	Node node = nodes.item(i);

	    	System.out.print(node.getNodeName());
	    	System.out.println(": " + node.getTextContent());
	    	
	    	if(node.getNodeName().equals("kor")) {
	    		System.out.println("");
	    	}
	    }
	    
	    System.out.println("(9) Az összes student element aminek van attribútuma:");
	    
	    studentExpr = studentXPath.compile("//student[@*]");
	    result = studentExpr.evaluate(studentDoc, XPathConstants.NODESET);
	    nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	Node node = nodes.item(i);

	    	System.out.print(node.getNodeName());
	    	System.out.println(": " + node.getTextContent());
	    	
	    	if(node.getNodeName().equals("kor")) {
	    		System.out.println("");
	    	}
	    }
	    
	    System.out.println("(10) Az összes elem:");
	    
	    studentExpr = studentXPath.compile("/*");
	    result = studentExpr.evaluate(studentDoc, XPathConstants.NODESET);
	    nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	Node node = nodes.item(i);

	    	System.out.print(node.getNodeName());
	    	System.out.println(": " + node.getTextContent());
	    	
	    	if(node.getNodeName().equals("kor")) {
	    		System.out.println("");
	    	}
	    }
	    
	    System.out.println("(11) Az összes student element, ahoz a kor>20:");
	    
	    studentExpr = studentXPath.compile("/class/student[kor>20]");
	    result = studentExpr.evaluate(studentDoc, XPathConstants.NODESET);
	    nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	Node node = nodes.item(i);

	    	System.out.print(node.getNodeName());
	    	System.out.println(": " + node.getTextContent());
	    	
	    	if(node.getNodeName().equals("kor")) {
	    		System.out.println("");
	    	}
	    }
	    
	    System.out.println("(12) Az összes student vezeték- és keresztneve:");
	    
	    studentExpr = studentXPath.compile("/class/student/keresztnev | /class/student/vezeteknev");
	    result = studentExpr.evaluate(studentDoc, XPathConstants.NODESET);
	    nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	Node node = nodes.item(i);

	    	System.out.print(node.getNodeName());
	    	System.out.println(": " + node.getTextContent());
	    	
	    	if(node.getNodeName().equals("kor")) {
	    		System.out.println("");
	    	}
	    }
	}

}
