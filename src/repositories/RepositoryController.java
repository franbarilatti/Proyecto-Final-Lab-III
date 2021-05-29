package repositories;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public abstract class RepositoryController<T> {
    private static File userFile = new File("UserRepositori.json");
    private BufferedReader userBF;
    {
        try {
            userBF = new BufferedReader( new FileReader(userFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private Gson userGson = new Gson();

    public static File getUserFile() {
        return userFile;
    }

    public BufferedReader getuserBF() {
        return userBF;
    }

    public Gson getUserGson() {
        return userGson;
    }


    public static void setUserFile(File userFile) {
        RepositoryController.userFile = userFile;
    }


    public void setuserBF(BufferedReader userBF) {
        this.userBF = userBF;
    }


    public void setUserGson(Gson userGson) {
        this.userGson = userGson;
    }

    public abstract void add(T t);
    public abstract void remove(T t);
    public abstract void show(T t);
    public abstract void modify(T t);

    public abstract void findById(T t);

}
