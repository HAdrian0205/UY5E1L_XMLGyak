package dom2uy5e1l1108;

import javax.xml.parsers.DocumentBuilderFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DomWriteUY5E1L {

	public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("src/domuy5e1l1108/UY5E1L_kurzusfelvetel.xml");
            
            FileWriter fileWriter = new FileWriter("src/dom2uy5e1l1108/kurzusfelvetel1UY5E1L.xml");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writeNodesToXMLFile(document.getChildNodes(), bufferedWriter, 0);
            
            bufferedWriter.close();
            
            System.out.println("XML fa struktúra:");

            printNodes(document.getChildNodes(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printNodes(NodeList nodeList, int level) {
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node node = nodeList.item(count);

            for (int i = 0; i < level; i++) {
                System.out.print("\t");
            }

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println(node.getNodeName());
                if (node.hasChildNodes()) {
                    printNodes(node.getChildNodes(), level + 1);
                }
            }
        }
    }
    
    private static void writeNodesToXMLFile(NodeList nodeList, BufferedWriter writer, int level) {
        try {
            for (int count = 0; count < nodeList.getLength(); count++) {
                Node node = nodeList.item(count);

                for (int i = 0; i < level; i++) {
                    writer.write("\t");
                }

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    writer.write("<" + node.getNodeName() + ">\n");
                    if (node.hasChildNodes()) {
                        writeNodesToXMLFile(node.getChildNodes(), writer, level + 1);
                    }
                    writer.write("</" + node.getNodeName() + ">\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
