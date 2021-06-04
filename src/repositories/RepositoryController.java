package repositories;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import mappers.Mapper;
import model.Reservation;
import model.Room;
<<<<<<< HEAD
import users.Admin;
=======
>>>>>>> 2a5d3f4aaf61a9b51bcbb93facd6785d41d9fe98
import users.Pax;
import users.User;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RepositoryController<T> extends LocalDateAdapter {

    List<T> data = new ArrayList<>();
    T myClass;

    public void createFile(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            if (!file.canWrite()) {
                file.setWritable(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addList(String fileName, List<T> tList) {
        String json = serialize(tList);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(json);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<T> throwList(String fileName,String filePath) throws FileNotFoundException {

        List<User> usersData = new ArrayList<>();
        List<Reservation> reservationsData = new ArrayList<>();
        List<Pax> paxesData = new ArrayList<>();
        List<Room> roomsData = new ArrayList<>();
        StringBuilder json = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            Gson gson = new Gson();
<<<<<<< HEAD
            Reader reader = Files.newBufferedReader(Paths.get("C:\\FACU\\3 Cuatrimestre\\Labo III\\Proyecto-Final-Lab-III\\userFile.json"));
            usersData = new Gson().fromJson(reader, new TypeToken<List<User>>(){}.getType());

           // System.out.println(usersData.get(1));
=======
            /*Reader reader = Files.newBufferedReader(Paths.get("C:\\FACU\\3 Cuatrimestre\\Labo III\\Proyecto-Final-Lab-III\\userFile.json"));*/
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            if(fileName.equals("userFile.json")) {
                List<User> users = new Gson().fromJson(reader, new TypeToken<List<User>>() {}.getType());
            }else if(fileName.equals("paxFile.json")){
                List<Pax> paxes = new Gson().fromJson(reader, new TypeToken<List<Pax>>() {}.getType());
            }else if(fileName.equals("roomFile.json")){
                List<Room> rooms = new Gson().fromJson(reader, new TypeToken<List<Room>>() {}.getType());
            }else if(fileName.equals("reserveFile.json")){
                List<Reservation> reserves = new Gson().fromJson(reader, new TypeToken<List<Reservation>>() {}.getType());
            }
>>>>>>> 2a5d3f4aaf61a9b51bcbb93facd6785d41d9fe98
            reader.close();

        } catch (FileNotFoundException e) {
            e.getCause();
        } catch (IOException e) {
            e.printStackTrace();
        }
<<<<<<< HEAD
        return (List<T>) usersData;
=======
        return list;
>>>>>>> 2a5d3f4aaf61a9b51bcbb93facd6785d41d9fe98
    }

    /*public  void showRepository(String fileName){
        ArrayList<T> tList = throwList(fileName);
        tList.forEach(System.out::println);
    }*/


    public String serialize(List<T> tList) {
        Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        return gson.toJson(tList);
    }


    public List<T> deserialize(StringBuilder json, Type clazz) {
        return new Gson().fromJson(json.toString(), clazz);
    }

}

