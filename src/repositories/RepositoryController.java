package repositories;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import mappers.Mapper;
import users.User;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RepositoryController<T> extends LocalDateAdapter{
    public RepositoryController() {
        super();
    }

    public void createFile(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (!file.canWrite()) {
                file.setWritable(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addList(String fileName, List<T> tList) {
        String json = serialize(tList);
<<<<<<< HEAD
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(json);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
=======
        if(!tList.equals(json)){
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
                bw.write(json);
                bw.close();
            }catch (IOException e){
                e.printStackTrace();
            }
>>>>>>> fc51c60994459d2ba227e66b6f93f8ee15ca73f0
        }
    }

    public void throwList(String fileName, List<T> list) {


        StringBuilder json = new StringBuilder();
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                json.append(line);
            }
            JsonArray jsonArray = new JsonParser().parse(json.toString()).getAsJsonArray();
            for (JsonElement jsonElement:jsonArray){
                Type fooType = new TypeToken<List<T>>(){}.getType();
                list.add(gson.fromJson(jsonElement,fooType));
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.getCause();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(list.get(0).getClass());
        System.out.println(list);
    }

    /*public  void showRepository(String fileName){
        ArrayList<T> tList = throwList(fileName);
        tList.forEach(System.out::println);
    }*/


    public String serialize(List<T> tList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        return gson.toJson(tList);
    }


    public List<T> deserialize(StringBuilder json, Type clazz) {
        return new Gson().fromJson(json.toString(), clazz);
    }
}
