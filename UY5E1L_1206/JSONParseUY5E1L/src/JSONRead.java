import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

public class JSONRead {
    public static void main(String[] args) {
        try {
            String filePath = "C:\\Users\\Bandi\\Desktop\\Oktatás\\UY5E1L_1206\\JSONParseUY5E1L\\src\\kurzusfelvetelUY5E1L.json";
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(new FileReader(filePath));

            if (obj instanceof JSONObject) {
                printJsonObject((JSONObject) obj, 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printJsonObject(JSONObject jsonObject, int indent) {
        Iterator<Map.Entry> iterator = jsonObject.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            Object key = entry.getKey();
            Object value = entry.getValue();

            printIndent(indent);
            System.out.print(key + ": ");

            if (value instanceof JSONObject) {
                System.out.println();
                printJsonObject((JSONObject) value, indent + 1);
            } else if (value instanceof JSONArray) {
                System.out.println();
                printJsonArray((JSONArray) value, indent + 1, "  " + "kurzus");
            } else {
                printIndent(indent);
                System.out.println(value);
            }
        }
    }

    private static void printJsonArray(JSONArray jsonArray, int indent, String prefix) {
        for (Object element : jsonArray) {
            if (element instanceof JSONObject) {
                System.out.println(prefix + ": ");
                printJsonObject((JSONObject) element, indent + 1);
            } else if (element instanceof JSONArray) {
                System.out.println(prefix + ": ");
                printJsonArray((JSONArray) element, indent + 1, prefix);
            } else {
                printIndent(indent);
                System.out.println(prefix + ": " + element);
            }
        }
    }

    private static void printIndent(int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
        }
    }
}
