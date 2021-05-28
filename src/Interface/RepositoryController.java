package Interface;

import users.User;

import javax.swing.table.TableRowSorter;
import java.util.List;

public interface RepositoryController <T>{
    public abstract void add(T t);
    public abstract void remove(T t);
    public abstract void show();
    public abstract void modify(T t);
}
