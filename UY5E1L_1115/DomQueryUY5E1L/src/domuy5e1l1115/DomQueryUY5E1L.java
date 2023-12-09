package domuy5e1l1115;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomQueryUY5E1L {

	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		try {
			File inputFile = new File("src/domuy5e1l1115/UY5E1L_orarend.xml");
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(inputFile);
	        doc.getDocumentElement().normalize();
	        NodeList orarendLista = doc.getElementsByTagName("ora");
	        
	        System.out.println("Elsõ feladat (a):");
	        System.out.println();
	        
	        for(int i = 0; i < orarendLista.getLength(); i++) {
	        	Node orarend = orarendLista.item(i);
	        	
	        	if (orarend.getNodeType() == Node.ELEMENT_NODE) {
	        		Element targy = (Element) orarend;
		        	System.out.println(targy.getElementsByTagName("targy").item(0).getTextContent());
	        	}
	        	
	        }
	        
	        System.out.println();
	        System.out.println("Elsõ feladat (b):");
	        System.out.println();
	        
	        FileWriter fileWriter = new FileWriter("src/domuy5e1l1115/UY5E1L_orarend_elso.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	        
	        Node elsoPeldanyNode = orarendLista.item(0);
	        if(elsoPeldanyNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element elsoPeldany = (Element) elsoPeldanyNode;
	        	
	        	List<String> fajlba = new ArrayList<>();
	        	
	        	fajlba.add("Tárgy id: " + elsoPeldany.getAttribute("id"));
	        	fajlba.add("Óra típusa: " + elsoPeldany.getAttribute("type"));
	        	fajlba.add("Tárgy neve: " + elsoPeldany.getElementsByTagName("targy").item(0).getTextContent());
	        	
	        	String nap = elsoPeldany.getElementsByTagName("nap").item(0).getTextContent();
	        	String tol = elsoPeldany.getElementsByTagName("tol").item(0).getTextContent();
	        	String ig = elsoPeldany.getElementsByTagName("ig").item(0).getTextContent();
	        	
	        	fajlba.add("Óra idõpontja: " + nap + " " + tol + "-" + ig);
	        	fajlba.add("Óra helyszíne: "  + elsoPeldany.getElementsByTagName("helyszin").item(0).getTextContent());
	        	fajlba.add("Oktató neve: "  + elsoPeldany.getElementsByTagName("oktato").item(0).getTextContent());
	        	fajlba.add("Szak: "  + elsoPeldany.getElementsByTagName("szak").item(0).getTextContent());
	        	
	        	for(String adat : fajlba) {
	        		System.out.println(adat);
	        		bufferedWriter.write(adat);
	        		bufferedWriter.newLine();
	        	}
	        }
	        
            bufferedWriter.close();
            
            System.out.println();
	        System.out.println("Elsõ feladat (c):");
	        System.out.println();
            
	        List<String> oktatok = new ArrayList<>();
	        
            for(int i = 0; i < orarendLista.getLength(); i++) {
	        	Node orarend = orarendLista.item(i);
	        	
	        	if (orarend.getNodeType() == Node.ELEMENT_NODE) {
	        		Element targy = (Element) orarend;
	        		String oktato = targy.getElementsByTagName("oktato").item(0).getTextContent();
	        		if(!oktatok.contains(oktato)) {
	        			oktatok.add(oktato);
	        		}
	        	}
	        	
	        }
            
            for(String oktatoNev : oktatok) {
            	System.out.println(oktatoNev);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
