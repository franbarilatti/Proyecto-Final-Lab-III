package repositories;

import users.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository extends RepositoryController<User> {
    private final String userFileName = "userFile.json";

    public UserRepository() {
    }


    @Override
    public void createFile() {
        File userFile = new File(userFileName);
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
    public void addObjet(User user) {
        String json = "";
        json = serialize(user);
        try{
            BufferedWriter userBW = new BufferedWriter(new FileWriter(userFileName));
            userBW.write(json);
            userBW.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addList(List<User> userList) {
        String json = "";
        json = serialize(userList);
        try{
            BufferedWriter userBW = new BufferedWriter(new FileWriter(userFileName));
            userBW.write(json);
            userBW.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> throwList() {
        StringBuilder json = new StringBuilder();
        String line;
        try{
            BufferedReader userBR = new BufferedReader(new FileReader(userFileName));
            while ((line = userBR.readLine())!= null){
                json.append(line);
            }
            userBR.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return deserialize(json.toString());
    }

    @Override
    public void showRepository() {
        ArrayList<User> userList = throwList();
        userList.forEach(System.out::println);
    }
}
