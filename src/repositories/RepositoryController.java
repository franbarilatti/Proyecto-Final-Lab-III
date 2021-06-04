package repositories;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.Reservation;
import model.Room;

import users.Pax;
import users.User;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RepositoryController<T> extends LocalDateAdapter {

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
        File file = new File(fileName);
        if (file.delete()){
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
                bw.write(json);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("El archivo no fue eliminado");
        }

    }


    public List<T> throwList(String fileName, String filePath) throws FileNotFoundException {

        List<User> usersData;
        List<Reservation> reservationsData;
        List<Pax> paxesData;
        List<Room> roomsData;
        List<T> returnList = new ArrayList<>();
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateDeserializer()).create();

            switch (fileName) {
                case "userFile.json" -> {
                    Reader reader = Files.newBufferedReader(Paths.get(filePath));
                    usersData = gson.fromJson(reader, new TypeToken<List<User>>() {
                    }.getType());
                    reader.close();
                    returnList= (List<T>) usersData;
                }
                case "roomFile.json" -> {
                    Reader reader1 = Files.newBufferedReader(Paths.get(filePath));
                    roomsData = gson.fromJson(reader1, new TypeToken<List<Room>>() {
                    }.getType());
                    reader1.close();
                    returnList= (List<T>) roomsData;
                }
                case "reserveFile.json" -> {
                    BufferedReader reader2 = new BufferedReader(new FileReader(fileName));
                    reservationsData = gson.fromJson(reader2, new TypeToken<List<Reservation>>(){}.getType());
                    reader2.close();
                    returnList = (List<T>) reservationsData;
                }
                case "paxFile.json" -> {
                    Reader reader3 = Files.newBufferedReader(Paths.get(filePath));
                    paxesData = gson.fromJson(reader3, new TypeToken<List<Pax>>() {
                    }.getType());
                    reader3.close();
                    returnList = (List<T>) paxesData;
                }
                default -> {return null;}
            }
        } catch (FileNotFoundException e) {
            e.getCause();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnList;
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

