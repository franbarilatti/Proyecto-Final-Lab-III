package repositories;


import mappers.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class RepositoryController<T> extends Mapper {
    public abstract void createFile();
    public abstract void addObjet(T t);
    public abstract void addList(List<T> tList);
    public abstract ArrayList<T> throwList();
    public abstract void showRepository();

}
