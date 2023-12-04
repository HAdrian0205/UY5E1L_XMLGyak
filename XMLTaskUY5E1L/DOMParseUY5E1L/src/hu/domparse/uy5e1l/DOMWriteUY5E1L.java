package hu.domparse.uy5e1l;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DOMWriteUY5E1L {

	public static void main(String[] args) {
		try {		
			//DOM létrehozása
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder domBuilder = dbFactory.newDocumentBuilder();
			Document doc = domBuilder.newDocument();
			
			//Gyökér element hozzáadása
			Element root = doc.createElement("webshop");
			root.setAttribute("xmlns:xs", "http://www.w3.org/2001/XMLSchema-instance");
			root.setAttribute("xs:noNamespaceSchemaLocation", "XMLTaskUY5E1L.xsd");
			doc.appendChild(root);
		    
			//Ügyfél hozzáadása
			root.appendChild(doc.createComment("Ügyfelek"));
			createCustomer(doc, root, "1", "Nagy", "Zsófia", "1122", "Budapest", "Rózsavölgy ", "7", "+36201234567", 
					"valami@example.com");
			createCustomer(doc, root, "2", "Kovács", "Máté", "2040", "Budaörs", "Fenyves utca", "12", "+36309876543", 
					"valami@example.com");
			createCustomer(doc, root, "3", "Szabó", "Adrienn", "3300", "Eger", "Tavasz utca", "21", "+36705551122", 
					"valami@example.com");
			createCustomer(doc, root, "4", "Kiss", "Dániel", "1138", "Budapest", "Tiszavirág utca", "45", "+36208765432", 
					"valami@example.com");
			createCustomer(doc, root, "5", "Horváth", "Viktória", "9027", "Győr", "Búzavirág utca", "33", "+36703337788", 
					"valami@example.com");
			
			//Ügy-Re kapcsolat hozzáadása
			root.appendChild(doc.createComment("Ügy-Re kapcsolótábla"));
			createCustomerOrderRelationship(doc, root, "1", "2", "1", "Szeretném, ha SOS ideérne a csomagom!");
			createCustomerOrderRelationship(doc, root, "2", "3", "5", "Pénteken nem vagyok otthon!");
			createCustomerOrderRelationship(doc, root, "3", "1", "2", "Tegye a csomagomat a hátsó ajtó elé!");
			createCustomerOrderRelationship(doc, root, "4", "5", "4", "A csengő nem működik!");
			createCustomerOrderRelationship(doc, root, "5", "4", "3", "A kutya nem harap! Jöjjön be nyugodtan!");
			
			//Rendelés hozzáadása
			root.appendChild(doc.createComment("Rendelések"));
			createOrder(doc, root, "1", "aktív", "online", "2", "5", "214.68", "2023-12-04");
			createOrder(doc, root, "2", "teljesítve", "online", "1", "2", "2049.98", "2023-12-01");
			createOrder(doc, root, "3", "törölve", "online", "4", "2", "3649.97", "2023-12-01");
			createOrder(doc, root, "4", "aktív", "online", "5", "3", "6464.96", "2023-12-04");
			createOrder(doc, root, "5", "fizetésre vár", "online", "3", "1", "1044.99", "2023-12-03");
			
			//Rendelt termék létrehozása
			root.appendChild(doc.createComment("Rendelt Termékek"));
			createOrderedProduct(doc, root, "1", "4", "3", "1199.99", "27%", "3");
			createOrderedProduct(doc, root, "2", "2", "5", "999.99", "27%", "1");
			createOrderedProduct(doc, root, "3", "5", "4", "1599.99", "27%", "4");
			createOrderedProduct(doc, root, "4", "3", "1", "4.99", "27%", "32");
			createOrderedProduct(doc, root, "5", "2", "2", "999.99", "27%", "2");
			
			//Termék hozzáadása
			root.appendChild(doc.createComment("Termékek"));
			createProduct(doc, root, "1", "igen", "Logitech MX Master 3S Egér", "459.99");
			createProduct(doc, root, "2", "igen", "QuantumTech Okosóra", "999.99");
			createProduct(doc, root, "3", "igen", "Zöld Energiaital Elite Edition", "4.99");
			createProduct(doc, root, "4", "igen", "Gyorsfőző Pizza Sütő", "1199.99");
			createProduct(doc, root, "5", "igen", "Kényelmi Zóna Masszázsfotel", "1599.99");
			
			//Szállító hozzáadása
			root.appendChild(doc.createComment("Szállítók"));
			createShipper(doc, root, "1", "ExpressMove Logistics", "+36201234567", "45", "1037", "Budapest", "Montevideo utca", "14");
			createShipper(doc, root, "2", "RapidCargo Solutions", "+36309876543", "50", "3529", "Miskolc", "Alkotmány utca", "3");
			createShipper(doc, root, "3", "SwiftShip Express", "+36705551122", "65", "2045", "Törökbálint", "Tavaszi utca", "21");
			createShipper(doc, root, "4", "PrimeTransit Services", "+36308765432", "40", "1132", "Budapest", "Váci út", "45");
			createShipper(doc, root, "5", "VelocityFreight", "+36703337788", "55", "3300", "Eger", "Eperjesi utca", "8");
			
		    //Mentés fájlba
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");
		    
			DOMSource source = new DOMSource(doc);
			File outputFile = new File("src/hu/domparse/uy5e1l/XMLUY5E1LWrite.xml");
			StreamResult file = new StreamResult(outputFile);
			transformer.transform(source, file);
			StreamResult console = new StreamResult(System.out);
			transformer.transform(source, console);
	    } catch (ParserConfigurationException | TransformerException e) {
	         e.printStackTrace();
	    }
	}
	
	public static void createCustomer(Document doc, Element root, String ugyfelid, String vezeteknev, String keresztnev, String iranyitoszam, String varos,
			String kozterneve, String hazszam, String telefonszam, String email) {
		
		Element customer = doc.createElement("ugyfel");
		customer.setAttribute("ugyfelid", ugyfelid);
		
		Element name = doc.createElement("nev");
		Element lastName = createElementWithValue(doc, "vezeteknev", vezeteknev);
		Element firstName = createElementWithValue(doc, "keresztnev", keresztnev);
		
		Element address = doc.createElement("lakcim");
		Element postalCode = createElementWithValue(doc, "iranyitoszam", iranyitoszam);
		Element city = createElementWithValue(doc, "varos", varos);
		Element street = createElementWithValue(doc, "kozterneve", kozterneve);
		Element houseNumber = createElementWithValue(doc, "hazszam", hazszam);
		
		Element phoneNumber = createElementWithValue(doc, "telefonszam", telefonszam);
		Element emailAddress = createElementWithValue(doc, "email", email);
		
		name.appendChild(lastName);
		name.appendChild(firstName);
		
		address.appendChild(postalCode);
		address.appendChild(city);
		address.appendChild(street);
		address.appendChild(houseNumber);
		
		customer.appendChild(name);
		customer.appendChild(address);
		customer.appendChild(phoneNumber);
		customer.appendChild(emailAddress);
		
		root.appendChild(customer);
	}
	
	public static void createCustomerOrderRelationship(Document doc, Element root, String megjegyzesid, String ugyfelid, 
			String rendelesid, String megjegyzes) {
		
		Element relationship = doc.createElement("ugyre");
		relationship.setAttribute("megjegyzesid", megjegyzesid);
		relationship.setAttribute("ugyfelid", ugyfelid);
		relationship.setAttribute("rendelesid", rendelesid);
		
		Element comment = createElementWithValue(doc, "megjegyzes", megjegyzes);
		
		relationship.appendChild(comment);
		
		root.appendChild(relationship);
	}
	
	public static void createOrder(Document doc, Element root, String rendelesid, String statusz, 
			String fizetestipusa, String ugyfelid, String szallitoid, String osszeg, String datum) {
		
		Element order = doc.createElement("rendeles");
		order.setAttribute("rendelesid", rendelesid);
		order.setAttribute("statusz", statusz);
		order.setAttribute("fizetestipusa", fizetestipusa);
		order.setAttribute("ugyfelid", ugyfelid);
		order.setAttribute("szallitoid", szallitoid);
		
		Element amount = createElementWithValue(doc, "osszeg", osszeg);
		Element orderDate = createElementWithValue(doc, "datum", datum);
		
		order.appendChild(amount);
		order.appendChild(orderDate);
		
		root.appendChild(order);
	}
	
	public static void createOrderedProduct(Document doc, Element root, String rendelttermekid, String termekid, String rendelesid,
			String egysegar, String afa, String mennyiseg) {
		
		Element orderedProduct = doc.createElement("rendelttermek");
		orderedProduct.setAttribute("rendelttermekid", rendelttermekid);
		orderedProduct.setAttribute("termekid", termekid);
		orderedProduct.setAttribute("rendelesid", rendelesid);
		
		Element unitPrice = createElementWithValue(doc, "egysegar", egysegar);
		Element vat = createElementWithValue(doc, "afa", afa);
		Element amount = createElementWithValue(doc, "mennyiseg", mennyiseg);
		
		orderedProduct.appendChild(unitPrice);
		orderedProduct.appendChild(vat);
		orderedProduct.appendChild(amount);
		
		root.appendChild(orderedProduct);
	}
	
	public static void createProduct(Document doc, Element root, String termekid, String akcios, String nev, String ar) {
		
		Element product = doc.createElement("termek");
		product.setAttribute("termekid", termekid);
		product.setAttribute("akcios", akcios);
		
		Element name = createElementWithValue(doc, "nev", nev);
		Element price = createElementWithValue(doc, "ar", ar);
		
		product.appendChild(name);
		product.appendChild(price);
		
		root.appendChild(product);
	}
	
	public static void createShipper(Document doc, Element root, String szallitoid, String nev, String telefonszam, String szallitasiar,
			String iranyitoszam, String varos, String kozterneve, String hazszam) {
		
		Element shipper = doc.createElement("szallito");
		shipper.setAttribute("szallitoid", szallitoid);
		
		Element name = createElementWithValue(doc, "nev", nev);
		Element phoneNumber = createElementWithValue(doc, "telefonszam", telefonszam);
		Element shippingFee = createElementWithValue(doc, "szallitasiar", szallitasiar);
		
		Element headQuarters = doc.createElement("kozpontcime");
		Element postalCode = createElementWithValue(doc, "iranyitoszam", iranyitoszam);
		Element city = createElementWithValue(doc, "varos", varos);
		Element street = createElementWithValue(doc, "kozterneve", kozterneve);
		Element houseNumber = createElementWithValue(doc, "hazszam", hazszam);
		
		headQuarters.appendChild(postalCode);
		headQuarters.appendChild(city);
		headQuarters.appendChild(street);
		headQuarters.appendChild(houseNumber);
		
		shipper.appendChild(name);
		shipper.appendChild(phoneNumber);
		shipper.appendChild(shippingFee);
		shipper.appendChild(headQuarters);
		
		root.appendChild(shipper);
	}
	
	public static Element createElementWithValue(Document doc, String elementName, String elementValue) {
		Element elementWithValue = doc.createElement(elementName);
		elementWithValue.appendChild(doc.createTextNode(elementValue));
		
		return elementWithValue;
	}
}
