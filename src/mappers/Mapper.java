package mappers;


import java.lang.reflect.Type;
import java.util.List;


public interface Mapper<T> {

    public String serialize(List<T> tList);

    public List<T> deserialize(StringBuilder json, Type clazz);

}
