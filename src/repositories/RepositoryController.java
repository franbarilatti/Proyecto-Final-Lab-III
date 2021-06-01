package repositories;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import mappers.Mapper;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public  class RepositoryController<T> extends LocalDateAdapter implements Mapper<T> {
    public RepositoryController() {
    }

    public  void createFile(String fileName){
        File file = new File(fileName);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            if (!file.canWrite()){
                file.setWritable(true);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public  void addList(String fileName,List<T> tList){
        String json = "";
        json = serialize(tList);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(json);
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public  ArrayList<T> throwList(String fileName){
        String json = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine())!= null){
                json+=line;
            }
            br.close();
        }catch (FileNotFoundException e){
            e.getCause();
        }catch (IOException e){
            e.printStackTrace();
        }

        return deserialize(json);
    }
    public  void showRepository(String fileName){
        ArrayList<T> tList = throwList(fileName);
        tList.forEach(System.out::println);
    }

    @Override
    public String serialize(List<T> tList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        String json = gson.toJson(tList);
        return json;
    }

    @Override
    public ArrayList<T> deserialize(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json,new TypeToken<ArrayList<T>>(){}.getType());
    }
}
