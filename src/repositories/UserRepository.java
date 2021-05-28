package repositories;

import Interface.RepositoryController;
import com.google.gson.Gson;
import users.User;

import java.io.*;
import java.util.List;

public class UserRepository implements RepositoryController {
    private static File userFile = new File("UserRepositori.json");
    private BufferedReader bufferedReader;

    {
        try {
            bufferedReader = new BufferedReader( new FileReader(userFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Gson userGson = new Gson();

    @Override
    public void add(Object o) {
        String json = userGson.toJson(o);

        try{
            FileWriter writer = new FileWriter(userFile);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Object o) {

    }

    @Override
    public void show() {

    }

    @Override
    public void modify(Object o) {

    }
}
