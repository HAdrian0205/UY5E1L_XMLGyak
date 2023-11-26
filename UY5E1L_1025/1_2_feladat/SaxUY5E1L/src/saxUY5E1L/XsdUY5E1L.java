package saxUY5E1L;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

public class XsdUY5E1L {
    public static void main(String[] args) {
        try {
            String xmlFilePath = "src\\hu\\saxuy5e1l\\UY5E1L_kurzusfelvetel.xml";

            String xsdFilePath = "src\\hu\\saxuy5e1l\\UY5E1L_kurzusfelvetel.xsd";

            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source[] sources = new Source[]{new StreamSource(new File(xsdFilePath))};
            Schema schema = schemaFactory.newSchema(sources);

            javax.xml.validation.Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFilePath)));

            System.out.println("XSD Validation successful.");

        } catch (SAXException e) {
            System.err.println("Unsuccessful validation: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}