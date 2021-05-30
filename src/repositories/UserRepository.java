package repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import users.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository extends RepositoryController<User> {

    public UserRepository() {
    }

    @Override
    public void createFile(String fileName) {
        File userFile = new File(fileName);
        try {
            if (!userFile.exists()) {
                userFile.createNewFile();
            }
            if(!userFile.canWrite()){
                userFile.setWritable(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addObjet(String fileName, User user) {
        String json = "";
        json = serialize(json,user);
        try{
            BufferedWriter userBW = new BufferedWriter(new FileWriter(fileName));
            userBW.write(json);
            userBW.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addList(String fileName, List<User> userList) {
        String json = "";
        json = serialize(json,userList);
        try{
            BufferedWriter userBW = new BufferedWriter(new FileWriter(fileName));
            userBW.write(json);
            userBW.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void remove(String fileName, User user) {

    }

    @Override
    public List<User> throwList(String fileName) {
        String json = "";
        String line;
        try{
            BufferedReader userBR = new BufferedReader(new FileReader(fileName));
            while ((line = userBR.readLine())!= null){
                json += line;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        List<User> list = deserialize(json);
        return list;
    }

    @Override
    public void modify(String fileName, User user) {

    }

    @Override
    public void findById(String fileName, UUID id) {

    }

}
