package json;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;


public class JsonUtilities {

    public static void grabar(JSONArray array,String fileName) {
        try {
            FileWriter file = new FileWriter(fileName);
            file.write(array.toString());
            file.flush();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String leer(String fileName)
    {
        String contenido = "";
        try
        {
            contenido = new String(Files.readAllBytes(Paths.get(fileName)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contenido;
    }
}
