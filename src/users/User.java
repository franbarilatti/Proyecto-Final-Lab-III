package users;

import app.Hotel;
import model.Room;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public abstract class User implements Serializable {

    //------ Attributes ------//
    @Serial
    private static final long serialVersionUID = 6124898757881206654L;
    protected UUID id;
    protected String nickName;
    protected String password;


    //------ Constructors ------//
    public User() {
        id = UUID.randomUUID();
        nickName = "";
        password = "";

    }

    public User(String nickName, String password) {
        id = UUID.randomUUID();
        this.nickName = nickName;
        this.password = password;
    }

    //------ Getters ------//
    public UUID getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPassword() {
        return password;
    }

    //------ Setters ------//

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //------ Methods ------//

    public Room searchRoomByNumber(Hotel hotel,int number){
        return hotel.getRooms().stream().
                filter(room1 -> room1.getNumber()==number).
                findFirst().
                orElse(null);
    }

    @Override
    public String toString() {
        return "User:" +
                "\nNickName='" + nickName;
    }

}
