package repositories;

import model.Reservation;
import users.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReserveRepository extends RepositoryController<Reservation>{
    private final String reserveFileName = "reserveFile.json";
    @Override
    public void createFile() {
        File userFile = new File(reserveFileName);
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
    public void addObjet(Reservation reservation) {
        String json = "";
        json = serialize(reservation);
        try{
            BufferedWriter userBW = new BufferedWriter(new FileWriter(reserveFileName));
            userBW.write(json);
            userBW.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addList(List<Reservation> reservations) {
        String json = "";
        json = serialize(reservations);
        try{
            BufferedWriter userBW = new BufferedWriter(new FileWriter(reserveFileName));
            userBW.write(json);
            userBW.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Reservation> throwList() {
        StringBuilder json = new StringBuilder();
        String line;
        try{
            BufferedReader userBR = new BufferedReader(new FileReader(reserveFileName));
            while ((line = userBR.readLine())!= null){
                json.append(line);
            }
            userBR.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return deserialize(json.toString());
    }

    @Override
    public void showRepository() {
        ArrayList<Reservation> userList = throwList();
        userList.forEach(System.out::println);
    }
}
