package repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Room;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository extends RepositoryController<Room>{
    private final String roomFileName = "roomFile.json";

    @Override
    public void createFile() {
        File userFile = new File(roomFileName);
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
    public void addObjet(Room room) {
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        String json = gson.toJson(room) ;
        try{
            FileWriter writer = new FileWriter(roomFileName);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addList(List<Room> rooms) {
        String json = "";
        json = serialize(rooms);
        try{
            BufferedWriter userBW = new BufferedWriter(new FileWriter(roomFileName));
            userBW.write(json);
            userBW.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Room> throwList() {
        StringBuilder json = new StringBuilder();
        String line;
        try{
            BufferedReader userBR = new BufferedReader(new FileReader(roomFileName));
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
        ArrayList<Room> userList = throwList();
        userList.forEach(System.out::println);
    }
}
