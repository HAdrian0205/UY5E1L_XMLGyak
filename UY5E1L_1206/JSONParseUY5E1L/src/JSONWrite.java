import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONWrite {

    public static void main(String[] args) {
        try {
            FileReader fr = new FileReader("C:\\Users\\Bandi\\Desktop\\Oktatás\\UY5E1L_1206\\JSONParseUY5E1L\\src\\kurzusfelvetelUY5E1L.json");
            BufferedReader br = new BufferedReader(fr);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Object obj = gson.fromJson(br, Object.class);

            FileWriter fw = new FileWriter("kurzusfelvetelUY5E1L_1.json");
            BufferedWriter bw = new BufferedWriter(fw);

            gson.toJson(obj, bw);
            bw.flush();

            fr.close();
            br.close();
            fw.close();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
