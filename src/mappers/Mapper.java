package mappers;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Mapper<T> {

    public void fileToJSON(String fileName,Gson gson, T t){
        String json = gson.toJson(t);
        try{
            FileWriter writer = new FileWriter(fileName);
            writer.write(json);
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
