package mappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public interface Mapper<T> {

    public String serialize(List<T> tList);

    public ArrayList<T> deserialize(String json);

}
