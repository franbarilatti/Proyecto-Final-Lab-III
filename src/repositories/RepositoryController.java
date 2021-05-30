package repositories;

import com.google.gson.Gson;
import mappers.Mapper;
import users.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class RepositoryController<T> extends Mapper {
    public abstract void createFile(String fileName);
    public abstract void addObjet(String fileName, T t);
    public abstract void addList(String fileName, List<T> tList);
    public abstract void remove(String fileName, T t);
    public abstract List<T> throwList(String fileName);
    public abstract void modify(String fileName,T t);
    public abstract void findById(String fileName, UUID id);

}
