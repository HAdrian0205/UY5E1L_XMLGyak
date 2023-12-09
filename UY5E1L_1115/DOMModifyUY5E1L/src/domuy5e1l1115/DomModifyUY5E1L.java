package domuy5e1l1115;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;

public class DomModifyUY5E1L {

	public static void main(String[] args) {
		try {
			File input = new File("src/domuy5e1l1115/UY5E1L_kurzusfelvetel.xml");
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(input);
			
			String output = "src/domuy5e1l1115/UY5E1L_kurzusfelvetel1.xml";
			
			modifyXML(doc);
			saveXMLDocument(doc, output);
			
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}

	}
	
	public static void modifyXML(Document doc) {
		NodeList kurzusLista = doc.getElementsByTagName("kurzus");
		int count = 1;
		
		for(int i = 0; i < kurzusLista.getLength(); i++) {
			Node kurzusNode = kurzusLista.item(i);
			
			if(kurzusNode.getNodeType() == Node.ELEMENT_NODE) {
				Element kurzus = (Element) kurzusNode;
				count++;
				NodeList children = kurzus.getChildNodes();
				Boolean exists = false;
				
				for(int j = 0; j < children.getLength(); j++) {
					Node kurzusChild = children.item(j);
					if(kurzusChild.getNodeType() == Node.ELEMENT_NODE) {
						if(kurzusChild.getNodeName().equals("óraadó")) {
							exists = true;
						}
					}
				}
				
				if(!exists) {
					Element element = doc.createElement("óraadó");
					element.appendChild(doc.createTextNode("Óraadó tanár" + count));
					kurzus.appendChild(element);
				}
			}
		}
		
	}
	
	private static void saveXMLDocument(Document document, String filePath) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
