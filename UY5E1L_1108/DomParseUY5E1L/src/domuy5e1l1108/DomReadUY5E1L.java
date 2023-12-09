package domuy5e1l1108;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomReadUY5E1L {

	public static void main(String[] args) {
		try {
			File inputFile = new File("src/domuy5e1l1108/UY5E1L_kurzusfelvetel.xml");
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	        System.out.println("Gyökér element: " + doc.getDocumentElement().getNodeName());
	        NodeList nList = doc.getElementsByTagName("hallgato");
	        NodeList nkList = doc.getElementsByTagName("kurzus");
	        System.out.println("----------------------------");
	         
	        for (int temp = 0; temp < nList.getLength(); temp++) {
	           Node nNode = nList.item(temp);
	           System.out.println("\nJelenlegi element: " + nNode.getNodeName());
	           System.out.println("----------------------------------------------");
	           
	           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	              Element hElement = (Element) nNode;
	              System.out.println(" Évfolyam: " 
	            		  + hElement.getAttribute("evf"));
	              System.out.println(" Hallgató neve: " 
	            		  + hElement
	            		  .getElementsByTagName("hnev")
	            		  .item(0)
	            		  .getTextContent());
	              System.out.println(" Születési év: " 
	            		  + hElement
	 	                  .getElementsByTagName("szulev")
	 	                  .item(0)
	 	                  .getTextContent());	
	              System.out.println(" Szak: " 
	            		  + hElement
		                  .getElementsByTagName("szak")
	 	                  .item(0)
	 	                  .getTextContent());
	            }
	        }
	        System.out.println();
	        System.out.println("----------------------------------------------");
	        
	        for (int temp = 0; temp < nkList.getLength(); temp++) {
		           Node nkNode = nkList.item(temp);
		           System.out.println("\nJelenlegi element: " + nkNode.getNodeName());
		           System.out.println("----------------------------------------------");
		           
		           if (nkNode.getNodeType() == Node.ELEMENT_NODE) {
		        	   Element nkElement = (Element) nkNode;
		        	   System.out.println(" Kurzus azonosító: " 
			            		  + nkElement.getAttribute("id"));
		        	   System.out.println(" Kurzus nyelve: " 
			            		  + nkElement.getAttribute("nyelv"));
		        	   System.out.println(" Kurzus neve: " 
			            		  + nkElement
			            		  .getElementsByTagName("kurzusnev")
			            		  .item(0)
			            		  .getTextContent());
		        	   System.out.println(" Kredit: " 
			            		  + nkElement
			            		  .getElementsByTagName("kredit")
			            		  .item(0)
			            		  .getTextContent());
		        	   System.out.println(" Hely: " 
			            		  + nkElement
			            		  .getElementsByTagName("hely")
			            		  .item(0)
			            		  .getTextContent());
		        	   System.out.println(" Idõpont: " 
			            		  + nkElement
			            		  .getElementsByTagName("idopont")
			            		  .item(0)
			            		  .getTextContent());
		        	   System.out.println(" Oktató neve: " 
			            		  + nkElement
			            		  .getElementsByTagName("oktato")
			            		  .item(0)
			            		  .getTextContent());
		           }
		        }
	        
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
		  System.out.println();
	}

}
