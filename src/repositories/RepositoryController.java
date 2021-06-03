package repositories;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import mappers.Mapper;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public  class RepositoryController<T> extends LocalDateAdapter implements Mapper<T> {
    public RepositoryController() {
        super();
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
        String json = serialize(tList);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(json);
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public  void throwList(String fileName, List<T> list){
        StringBuilder json = new StringBuilder();
        Gson gson= new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine())!= null){
                json.append(line);
            }
            br.close();
        }catch (FileNotFoundException e){
            e.getCause();
        }catch (IOException e){
            e.printStackTrace();
        }
        list.add(gson.fromJson(json.toString(), new TypeToken<List<T>>(){}.getType()));
    }

    /*public  void showRepository(String fileName){
        ArrayList<T> tList = throwList(fileName);
        tList.forEach(System.out::println);
    }*/

    @Override
    public String serialize(List<T> tList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        return gson.toJson(tList);
    }

    @Override
    public List<T> deserialize(StringBuilder json, Type clazz) {
        return new Gson().fromJson(json.toString(),  clazz);
    }
}
