package hu.domparse.uy5e1l;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

public class DOMReadUY5E1L {
	
	//A sorok listája
	public static List<String> allLines = new ArrayList<>();

	public static void main(String[] args) {
		try {
			//Fájl beolvasása
			File inputFile = new File("src/hu/domparse/uy5e1l/XMLTaskUY5E1L.xml");
			
			//DOM létrehozása
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder domBuilder = dbFactory.newDocumentBuilder();
			Document doc = domBuilder.parse(inputFile);
			
			//XML
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n".repeat(2);
			allLines.add(xml);
			
			//Gyökér element meghatározása
			Node root = doc.getDocumentElement();
			allLines.add(getRoot(root));
			
			//Gyermek elementek listájának létrehozása
			NodeList childNodeList = root.getChildNodes();
			
			//Behúzás megvalósítása
			int indent = 1;
			String indentStr = "\t";
			
			//Dokumentum elemeinek beolvasása
			for(int i = 0; i < childNodeList.getLength(); i++) {
				Node childNode = childNodeList.item(i);
				if(childNode.getNodeType() == Node.ELEMENT_NODE) {
					Element childElement = (Element) childNode;
					
					allLines.add("\n" + indentStr.repeat(indent) + "<" + childElement.getNodeName() + getAttributes(childElement) + ">");
					getChildNodes(childElement, indent, indentStr);
					allLines.add("\n" + indentStr.repeat(indent) + "</" + childElement.getNodeName() + ">\n");
				}
			}
			
			//Gyökér element lezárása a dokumentum végén
			allLines.add("\n"+ "</" + root.getNodeName() + ">");
			
			//Kiíratás consolre-ra és fájlba
			try {
				
				FileWriter writer = new FileWriter("src/hu/domparse/uy5e1l/XMLTaskUY5E1LOutput.xml");
				
				BufferedWriter buffWriter = new BufferedWriter(writer);
				
				for(String line : allLines) {
					System.out.print(line);
					buffWriter.write(line);
					buffWriter.flush();
				}
				
				buffWriter.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }		
	    } catch (ParserConfigurationException | SAXException | IOException e) {
	         e.printStackTrace();
	    }
	}
	
	public static void getChildNodes(Element parentElement, int indent, String indentStr) {
		if(parentElement.hasChildNodes()) {
			NodeList childNodeList = parentElement.getChildNodes();
			
			for(int i = 0; i < childNodeList.getLength(); i++) {
				Node childNode = childNodeList.item(i);
				if(childNode.getNodeType() == Node.ELEMENT_NODE) {
					Element childElement = (Element) childNode;
					indent++;
					
					//Ellenőrzés, hogy a childElement node-jai közt van-e element
					boolean hasInnerElements = false;
					
					NodeList checkNodeList = childElement.getChildNodes();
					
					for(int j = 0; j < checkNodeList.getLength(); j++) {
						Node checkNode = checkNodeList.item(j);
						if(checkNode.getNodeType() == Node.ELEMENT_NODE) {
							hasInnerElements = true;
						}
					}
					
					//Hozzáadja a sort a listához, annak függvényében, hogy az elementnek van-e gyermek elementje, vagy nincs
					if(hasInnerElements) {
						allLines.add("\n" + indentStr.repeat(indent) + "<" + childElement.getNodeName() + getAttributes(childElement) + ">");
						getChildNodes(childElement, indent, indentStr);
						allLines.add("\n" + indentStr.repeat(indent) + "</" + childElement.getNodeName() + ">");
					} else {
						allLines.add("\n"+ indentStr.repeat(indent) + "<" + childElement.getNodeName() + getAttributes(childElement) + ">");
						
						if(childElement.getTextContent().trim().length() > 0)
							allLines.add(childElement.getTextContent());
						
						allLines.add("</" + childElement.getNodeName() + ">");
					}

					indent--;
				}
			}
		}
	}
	
	//Elementek attribútumainak lekérése
	public static String getAttributes(Element parentElement) {
		NamedNodeMap attributes = parentElement.getAttributes();
		String attributeReturn = "";
		
		for(int i = 0; i < attributes.getLength(); i++) {
			Attr attribute = (Attr) attributes.item(i);
			
			attributeReturn += " " + attribute;
		}
		
		return attributeReturn;
	}
	
	//A gyökér element lekérdezése
    public static String getRoot(Node root){
        String rootReturn = "<" + root.getNodeName();
        NamedNodeMap rootElement = root.getAttributes();

        for(int i = 0; i < rootElement.getLength(); i++){
            Attr attribute = (Attr) rootElement.item(i);
            rootReturn += " " + attribute;
        }

        rootReturn += ">\n";

        return rootReturn;
    }
}
