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
			
			//A lekérdezésekhez szükséges NodeList-ek létrehozása
			NodeList orderList = doc.getElementsByTagName("rendeles");
			NodeList orderedProductList = doc.getElementsByTagName("rendelttermek");
			NodeList productList = doc.getElementsByTagName("termek");
			NodeList ugyreList = doc.getElementsByTagName("ugyre");
			NodeList customerList = doc.getElementsByTagName("ugyfel");
			NodeList shipperList = doc.getElementsByTagName("szallito");
			
			//A 6-os ID-jű rendelésben rendelt termék adatainak lekérdezése
			
			System.out.println("1. Lekérdezés:");
			System.out.println("--------------");
			
			//A keresett rendelés azonósítója
			String searchedOrderID = "6";
			
			//Először megkeressük a megfelelő azonosítójú rendelést
			for(int i = 0; i < orderList.getLength(); i++) {
				Element order = (Element) orderList.item(i);
				String orderID = order.getAttribute("rendelesid");
				
				//Ellenőrizzük, hogy a rendelés azonosítója megegyezik-e az általunk megadott értékkel
				if(searchedOrderID.equals(orderID)) {
					
					/* Ha megegyezik, akkor végigmegyünk a rendelt termékek listáján keresve benne azt az elemet,
					 * amelyben a megfelelő azonosítójú rendelés kulcsa szerepel
					*/
					for(int j = 0; j < orderedProductList.getLength(); j++) {
						Element orderedProduct = (Element) orderedProductList.item(j);
						String orderedProductOrderID = orderedProduct.getAttribute("rendelesid");
						String productFkey = orderedProduct.getAttribute("termekid");
						
						//Ellenőrizzük, hogy a rendelt termékben szereplő idegen kulcs megegyezik-e a rendelés kulcsával
						if(orderedProductOrderID.equals(orderID)) {
							/* Ha megegyezik, akkor végig megyünk a termékek listáján keresve, hogy a rendelt terméknél
							 * szereplő termékazonosító melyik termék azonosítója 
							*/
							for(int k = 0; k < productList.getLength(); k++) {
								Element product = (Element) productList.item(k);
								String productID = product.getAttribute("termekid");
								
								/* Ellenőrizzük, hogy az éppen "vizsgált" termék azonosítója megegyezik-e a rendelt termékben 
								 * szereplő kulccsal, majd ha egyezést találunk, akkor kiíratjuk a termék adatait
								 */
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
			
			//A keresett rendelés azonósítója
			searchedOrderID = "10";
			
			//Először megkeressük a megfelelő azonosítójú rendelést
			for(int i = 0; i < orderList.getLength(); i++) {
				Element order = (Element) orderList.item(i);
				String orderID = order.getAttribute("rendelesid");
				
				//Ellenőrizzük, hogy a rendelés azonosítója megegyezik-e az általunk megadott értékkel
				if(searchedOrderID.equals(orderID)) {
					
					/* Ha megegyezik, akkor végigmegyünk az ügyfél-rendelés kapcsolatok listáján keresve benne azt az elemet,
					 * amelyben a rendelés kulcsa megegyezik az általunk keresett rendelésazonosítóval
					*/
					for(int j = 0; j < ugyreList.getLength(); j++) {
						Element ugyre = (Element) ugyreList.item(j);
						String ugyreOrderID = ugyre.getAttribute("rendelesid");
						
						//Ellenőrizzük, hogy a rendelés azonosítója megegyezik-e az Ügy-Re kapcsolatban szereplő kulccsal
						if(orderID.equals(ugyreOrderID)) {
							
							//Ha megegyezik, kiíratjuk a benne szereplő megjegyzést
							System.out.println("A rendeléshez tartozó megjegyzés:");
							System.out.println(ugyre.getElementsByTagName("megjegyzes").item(0).getTextContent());
							String ugyreCustomerID = ugyre.getAttribute("ugyfelid");
							
							/* Ezt követően végigmegyünk az ügyfelek listáján keresve azt az ügyfelet,
							 * akihez a megjegyzés (illetve a rendelés) tartozik
							 */
							for(int k = 0; k < customerList.getLength(); k++) {
								Element customer = (Element) customerList.item(k);
								String customerID = customer.getAttribute("ugyfelid");
								
								//Ha egyezést találunk, akkor kiíratjuk az ügyfél adatait
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
			
			//Létrehozzuk a listát, amiben később tároljuk az ügyfelek azonosítóit
			List<String> customerIDList = new ArrayList<>();
			
			//Végigmegyünk a rendelések listáján
			for(int i = 0; i < orderList.getLength(); i++) {
				Element order = (Element) orderList.item(i);
				String customerID = order.getAttribute("ugyfelid");
				
				//Ha egy ügyfél azonosítója még nem szerepel a listában, akkor hozzáadjuk
				if(!customerIDList.contains(customerID))
					customerIDList.add(customerID);
			}
			
			System.out.println("Ügyfelek adatai, akikhez nem tartozik rendelés:\n");
			
			int counter = 1;
			
			//Végigmegyünk az ügyfelek listáján
			for(int i = 0; i < customerList.getLength(); i++) {
				Element customer = (Element) customerList.item(i);
				String customerID = customer.getAttribute("ugyfelid");
				
				
				/* Ellenőrizzük, hogy az éppen "vizsgált" ügyfél azonosítója szerepel-e a listánkban.
				 * Ha az ügyfél azonosítója nem szerepel a listánkban, az azt jelenti, hogy nem tartozik
				 * hozzá rendelés, ezért kiíratjuk az adatait. 
				 */
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
			
			//A szállítók adatai, akik 3-mas idjű terméket szállítanak/szállítottak
			System.out.println("4. Lekérdezés:");
			System.out.println("--------------");
			
			//A keresett termék azonósítója
			String searchedProductID = "3";
			
			System.out.println("Szállítók, melyek szállítanak/szállítottak 3-mas idjű terméket:\n");
			
			counter = 1;
			
			//Először megkeressük a megfelelő rendelt terméket
			for(int i = 0; i < orderedProductList.getLength(); i++) {
				Element orderedProduct = (Element) orderedProductList.item(i);
				String productFkey = orderedProduct.getAttribute("termekid");
				String orderFkey = orderedProduct.getAttribute("rendelesid");
				
				//Ellenőrizzük, hogy a rendelt terméknél a termék kulcsa megegyezik-e az általunk megadott értékkel
				if(productFkey.equals(searchedProductID)) {
					
					/* Ha megegyezik, akkor végigmegyünk a rendelések listáján keresve benne azt az elemet,
					 * amelynél a rendelésazonosító megegyezik a rendelt terméknél szereplő kulccsal
					*/
					for(int j = 0; j < orderList.getLength(); j++) {
						Element order = (Element) orderList.item(j);
						String orderID = order.getAttribute("rendelesid");
						String shipperFkey = order.getAttribute("szallitoid");
						
						//Ellenőrizzük, hogy a rendelésazonosító megegyezik-e a rendelt terméknél szereplő kulccsal
						if(orderID.equals(orderFkey)) {
							
							/* Ha megegyezik, akkor végigmegyünk a szállítók listáján keresve benne azt az elemet,
							 * amelynél a szállító azonosítója megegyezik a rendelésben szereplő kulccsal
							*/
							for(int k = 0; k < shipperList.getLength(); k++) {
								Element shipper = (Element) shipperList.item(k);
								String shipperID = shipper.getAttribute("szallitoid");
								
								
								/* Ellenőrizzük, hogy a szállító azonosítója megegyezik-e a rendelésben szereplő kulccsal.
								 * Ha megegyezik, akkor megtaláltuk azt a szállítót, aki olyan rendelésnek (is) a szállítója,
								 * amelyben szerepel a 3-mas azonosítójú termék.
								 */
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
			
			/* Létrehozunk egy Map-et, amiben tárolni fogjuk a termékek azonosítóit, illetve azt, 
			 * hogy hány darabot rendeltek már belőle
			 */
			Map<String, Integer> productAmountMap = new HashMap<>();
			
			System.out.println("Termékek, melyekből 3-nál több darabot rendeltek:\n");
			
			/* Végigmegyünk a rendelt termékek listáján, majd hozzáadjuk az ott szereplő termékazonosítókat,
			 * illetve hozzájuk tartozó mennyiséget (végül a mennyiségek összegét) a Map-hez
			 */
			for(int i = 0; i < orderedProductList.getLength(); i++) {
				Element orderedProduct = (Element) orderedProductList.item(i);
				String productFkey = orderedProduct.getAttribute("termekid");
				
				//A termékazonosítót átkonvertáljuk integer-ré, hogy számként tudjuk kezelni
				int amount = Integer.parseInt(orderedProduct.getElementsByTagName("mennyiseg").item(0).getTextContent());
				
				/* Ellenőrizzük, hogy a Map tartalmazza-e már a rendelt terméknél szereplő termékazonosítót,
				 * és csak akkor adjuk hozzá a kulcsot és a mennyiséget, ha még nem szerepel a Map-ben.
				 * 
				 * Ha már szerepel, abban az esetben lekérjük a kulcshoz tartozó értéket (mennyiség) egy változóba,
				 * majd hozzáadjuk a jelenleg lekért mennyiséget a változóhoz, és az alapján  "frissítjük" az értéket a Map-ben. 
				 */
				if(!productAmountMap.containsKey(productFkey)) {
					productAmountMap.put(productFkey, amount);
				} else {
					int tempAmount = productAmountMap.get(productFkey);
					tempAmount += amount;
					productAmountMap.put(productFkey, tempAmount);
				}
			}
			
			counter = 1;
			
			//Végigmegyünk a termékek listáján
			for(int i = 0; i < productList.getLength(); i++) {
				Element product = (Element) productList.item(i);
				String productID = product.getAttribute("termekid");
				
				/* Ha az éppen vizsgált termék azonosítója szerepel a Map-ben, illetve teljesül
				 * a feltétel, hogy a hozzátartozó érték (mennyiség) nagyobb, mint 3, akkor kiíratjuk
				 * a termék adatait, illetve a rendelések számát, ami azt takarja, hogy eddig
				 * összesen hány darabot rendeltek a termékből.
				 */
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
