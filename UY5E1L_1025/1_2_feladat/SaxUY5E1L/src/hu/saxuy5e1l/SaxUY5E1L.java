package hu.saxuy5e1l;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;


public class SaxUY5E1L {

    private static class CustomHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            System.out.println("Start: " + qName);

            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.println("{" + attributes.getQName(i) + ":" + attributes.getValue(i) + "}");
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            System.out.println("End: " + qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String text = new String(ch, start, length).trim();
            if (!text.isEmpty()) {
                System.out.println("Content: " + text);
            }
        }
    }

    public static void main(String[] args) {
        try {
            String filePath = "src\\hu\\saxuy5e1l\\UY5E1L_kurzusfelvetel.xml";

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            CustomHandler customHandler = new CustomHandler();

            saxParser.parse(new File(filePath), customHandler);

        } catch (SAXException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
