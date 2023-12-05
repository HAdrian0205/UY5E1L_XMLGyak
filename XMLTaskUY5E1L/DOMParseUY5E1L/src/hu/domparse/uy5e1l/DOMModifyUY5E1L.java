package hu.domparse.uy5e1l;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyUY5E1L {

	public static void main(String[] args) {
		
		File inputFile = new File("src/hu/domparse/uy5e1l/XMLTaskUY5E1L.xml");
		
		try {
			
			//Document létrehozása
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder domBuilder = dbFactory.newDocumentBuilder();
			Document doc = domBuilder.parse(inputFile);
			
			//Módosítást végző metódus meghívása
			modifyDocument(doc);
			
			// Módosított fájl mentése
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc); 
			StreamResult result = new StreamResult(new File("src/hu/domparse/uy5e1l/modositott_xml.xml"));
			transformer.transform(source, result);
            
			//Console-ra való kiíratás
			StreamResult resultConsole = new StreamResult(System.out);
			transformer.transform(source, resultConsole);
		} catch(TransformerException | ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void modifyDocument(Document doc) {
		//Egy termék árának megváltoztatása (id 5)
		NodeList productList = doc.getElementsByTagName("termek");
		Element product = (Element) productList.item(4);
		product.getElementsByTagName("ar").item(0).setTextContent("895.99");
		
		//Egy ügyfél keresztnevének megváltoztatása (id 10)
		NodeList nameList = doc.getElementsByTagName("nev");
		Element name = (Element) nameList.item(9);
		name.getElementsByTagName("keresztnev").item(0).setTextContent("Béla");
		
		//Egy rendelés státuszának megváltoztatása (id 7)
		NodeList orderList = doc.getElementsByTagName("rendeles");
		Element order = (Element) orderList.item(6);
		order.getAttributeNode("statusz").setTextContent("teljesítve");
		
		//Egy rendelt termék mennyiségének megváltoztatása (id 16)
		NodeList orderedProductList = doc.getElementsByTagName("rendelttermek"); 
		Element orderedProduct = (Element) orderedProductList.item(15);
		orderedProduct.getElementsByTagName("mennyiseg").item(0).setTextContent("6");
		
		//Egy szállító telefonszámának megváltoztatása (id 2)
		NodeList shipperList = doc.getElementsByTagName("szallito"); 
		Element shipper = (Element) shipperList.item(1);
		shipper.getElementsByTagName("telefonszam").item(0).setTextContent("+36708887772");
	}

}
