package hu.domparse.uy5e1l;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.w3c.dom.NodeList;

public class DOMQueryUY5E1L {

	public static void main(String[] args) {
		try {
			//Fájl beolvasása
			File inputFile = new File("src/hu/domparse/uy5e1l/XMLTaskUY5E1L.xml");
			
			//DOM létrehozása
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder domBuilder = dbFactory.newDocumentBuilder();
			Document doc = domBuilder.parse(inputFile);
			
			//A 6-os ID-jű rendelésben rendelt termék adatainak lekérdezése
			
			System.out.println("1. Lekérdezés:");
			System.out.println("--------------");
			
			String searchedOrderID = "6";
			
			NodeList orderList = doc.getElementsByTagName("rendeles");
			NodeList orderedProductList = doc.getElementsByTagName("rendelttermek");
			NodeList productList = doc.getElementsByTagName("termek");
			
			for(int i = 0; i < orderList.getLength(); i++) {
				Element order = (Element) orderList.item(i);
				String orderID = order.getAttribute("rendelesid");
				if(searchedOrderID.equals(orderID)) {
					for(int j = 0; j < orderedProductList.getLength(); j++) {
						Element orderedProduct = (Element) orderedProductList.item(j);
						String orderedProductOrderID = orderedProduct.getAttribute("rendelesid");
						String productFkey = orderedProduct.getAttribute("termekid");
						if(orderedProductOrderID.equals(orderID)) {
							for(int k = 0; k < productList.getLength(); k++) {
								Element product = (Element) productList.item(k);
								String productID = product.getAttribute("termekid");
								if(productID.equals(productFkey)) {
									System.out.println("A rendelésben szereplő termék");
									System.out.println("\tNeve: " + product.getElementsByTagName("nev").item(0).getTextContent());
									System.out.println("\tÁra: " + product.getElementsByTagName("ar").item(0).getTextContent());
								}
							}
						}
					}
				}
			}
			
			System.out.println();
			
			//A 10-es ID-jű rendelésnél az ügyfél-rendelés kapcsolat adatainak lekérdezése (megjegyzés, ügyfél adatai)
			System.out.println("2. Lekérdezés:");
			System.out.println("--------------");
			searchedOrderID = "10";
			
			NodeList ugyreList = doc.getElementsByTagName("ugyre");
			NodeList customerList = doc.getElementsByTagName("ugyfel");
			
			for(int i = 0; i < orderList.getLength(); i++) {
				Element order = (Element) orderList.item(i);
				String orderID = order.getAttribute("rendelesid");
				if(searchedOrderID.equals(orderID)) {
					for(int j = 0; j < ugyreList.getLength(); j++) {
						Element ugyre = (Element) ugyreList.item(j);
						String ugyreOrderID = ugyre.getAttribute("rendelesid");
						if(orderID.equals(ugyreOrderID)) {
							System.out.println("A rendeléshez tartozó megjegyzés:");
							System.out.println(ugyre.getElementsByTagName("megjegyzes").item(0).getTextContent());
							String ugyreCustomerID = ugyre.getAttribute("ugyfelid");
							for(int k = 0; k < customerList.getLength(); k++) {
								Element customer = (Element) customerList.item(k);
								String customerID = customer.getAttribute("ugyfelid");
								if(customerID.equals(ugyreCustomerID)) {
									String customerFirstName = customer.getElementsByTagName("keresztnev").item(0).getTextContent();
									String customerLastName = customer.getElementsByTagName("vezeteknev").item(0).getTextContent();
									String postalCode = customer.getElementsByTagName("iranyitoszam").item(0).getTextContent();
									String city = customer.getElementsByTagName("varos").item(0).getTextContent();
									String street = customer.getElementsByTagName("kozterneve").item(0).getTextContent();
									String houseNumber = customer.getElementsByTagName("hazszam").item(0).getTextContent();
									String phoneNumber = customer.getElementsByTagName("telefonszam").item(0).getTextContent();
									String email = customer.getElementsByTagName("email").item(0).getTextContent();
									
									System.out.println("\nAz ügyfél adatai, akihez akihez a rendelés tartozik:");
									System.out.println("\tNév: " + customerLastName + " " + customerFirstName);
									System.out.println("\tLakcím: " + postalCode + ", " + city + ", " + street + " " + houseNumber + ".");
									System.out.println("\tTelefonszám: " + phoneNumber);
									System.out.println("\tE-mail: " + email);
								}
							}
						}
					}
				}
			}
			
			System.out.println();
			
			//Minden olyan ügyfél adatai, akihez nem tartozik rendelés
			System.out.println("3. Lekérdezés:");
			System.out.println("--------------");
			
			List<String> customerIDList = new ArrayList<>();
			
			for(int i = 0; i < orderList.getLength(); i++) {
				Element order = (Element) orderList.item(i);
				String customerID = order.getAttribute("ugyfelid");
				if(!customerIDList.contains(customerID))
					customerIDList.add(customerID);
			}
			
			System.out.println("Ügyfelek adatai, akikhez nem tartozik rendelés:\n");
			
			int counter = 1;
			
			for(int i = 0; i < customerList.getLength(); i++) {
				Element customer = (Element) customerList.item(i);
				String customerID = customer.getAttribute("ugyfelid");
				if(!customerIDList.contains(customerID)) {
					System.out.println(counter + ". Ügyfél (ID:" + customerID +")");
					
					String customerFirstName = customer.getElementsByTagName("keresztnev").item(0).getTextContent();
					String customerLastName = customer.getElementsByTagName("vezeteknev").item(0).getTextContent();
					String postalCode = customer.getElementsByTagName("iranyitoszam").item(0).getTextContent();
					String city = customer.getElementsByTagName("varos").item(0).getTextContent();
					String street = customer.getElementsByTagName("kozterneve").item(0).getTextContent();
					String houseNumber = customer.getElementsByTagName("hazszam").item(0).getTextContent();
					String phoneNumber = customer.getElementsByTagName("telefonszam").item(0).getTextContent();
					String email = customer.getElementsByTagName("email").item(0).getTextContent();
					
					System.out.println("\tNév: " + customerLastName + " " + customerFirstName);
					System.out.println("\tLakcím: " + postalCode + ", " + city + ", " + street + " " + houseNumber + ".");
					System.out.println("\tTelefonszám: " + phoneNumber);
					System.out.println("\tE-mail: " + email + "\n");
					
					counter++;
				}
			}
			
			//A szállítók adatai, akik szállítanak 3-as idjű terméket szállítanak/szállítottak
			System.out.println("4. Lekérdezés:");
			System.out.println("--------------");
			
			NodeList shipperList = doc.getElementsByTagName("szallito");
			String searchedProductID = "3";
			
			System.out.println("Szállítók, melyek szállítanak/szállítottak 3-mas idjű terméket:\n");
			
			counter = 1;
			
			for(int i = 0; i < orderedProductList.getLength(); i++) {
				Element orderedProduct = (Element) orderedProductList.item(i);
				String productFkey = orderedProduct.getAttribute("termekid");
				String orderFkey = orderedProduct.getAttribute("rendelesid");
				if(productFkey.equals(searchedProductID)) {
					for(int j = 0; j < orderList.getLength(); j++) {
						Element order = (Element) orderList.item(j);
						String orderID = order.getAttribute("rendelesid");
						String shipperFkey = order.getAttribute("szallitoid");
						if(orderID.equals(orderFkey)) {
							for(int k = 0; k < shipperList.getLength(); k++) {
								Element shipper = (Element) shipperList.item(k);
								String shipperID = shipper.getAttribute("szallitoid");
								if(shipperID.equals(shipperFkey)) {
									System.out.println(counter + ". Szállító (ID:" + shipperID +")");
									
									String name = shipper.getElementsByTagName("nev").item(0).getTextContent();
									String phoneNumber = shipper.getElementsByTagName("telefonszam").item(0).getTextContent();
									String postalCode = shipper.getElementsByTagName("iranyitoszam").item(0).getTextContent();
									String city = shipper.getElementsByTagName("varos").item(0).getTextContent();
									String street = shipper.getElementsByTagName("kozterneve").item(0).getTextContent();
									String houseNumber = shipper.getElementsByTagName("hazszam").item(0).getTextContent();
									
									System.out.println("\tNév: " + name);
									System.out.println("\tKözpont címe: " + postalCode + ", " + city + ", " + street + " " + houseNumber + ".");
									System.out.println("\tTelefonszám: " + phoneNumber);
									
									counter++;
								}
							}
						}
					}
				}
			}
		
			System.out.println();
			
			//Minden olyan termék adatai, amelyből már 3-nál több darabot rendeltek
			System.out.println("5. Lekérdezés:");
			System.out.println("--------------");
			
			Map<String, Integer> productAmountMap = new HashMap<>();
			
			System.out.println("Termékek, melyekből 3-nál több darabot rendeltek:\n");
			
			for(int i = 0; i < orderedProductList.getLength(); i++) {
				Element orderedProduct = (Element) orderedProductList.item(i);
				String productFkey = orderedProduct.getAttribute("termekid");
				int amount = Integer.parseInt(orderedProduct.getElementsByTagName("mennyiseg").item(0).getTextContent());
				
				if(!productAmountMap.containsKey(productFkey)) {
					productAmountMap.put(productFkey, amount);
				} else {
					int tempAmount = productAmountMap.get(productFkey);
					tempAmount += amount;
					productAmountMap.put(productFkey, tempAmount);
				}
			}
			
			counter = 1;
			
			for(int i = 0; i < productList.getLength(); i++) {
				Element product = (Element) productList.item(i);
				String productID = product.getAttribute("termekid");
				
				if(productAmountMap.containsKey(productID)) {
					if(productAmountMap.get(productID) > 3) {
						System.out.println(counter + ". Termék (ID:" + productID +", Rendelések száma: " + productAmountMap.get(productID) + ")");
						System.out.println("\tNeve: " + product.getElementsByTagName("nev").item(0).getTextContent());
						System.out.println("\tÁra: " + product.getElementsByTagName("ar").item(0).getTextContent());
						
						counter++;
					}
				}
			}
		} catch(ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}

}
