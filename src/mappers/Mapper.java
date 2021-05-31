package mappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Mapper<T> {

    public String serialize(T t){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(t);
        return json;
    }

    public ArrayList<T> deserialize(String json){
        Gson gson = new Gson();
        ArrayList<T> list = gson.fromJson(json, new TypeToken<ArrayList<T>>(){}.getType());
        return list;
    }

}
