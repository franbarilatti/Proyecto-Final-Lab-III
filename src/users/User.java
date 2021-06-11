package users;


import model.Room;


import java.io.Serial;
import java.io.Serializable;

import java.util.List;

import java.util.UUID;

public class User implements Serializable {

    //------ Attributes ------//
    @Serial
    private static final long serialVersionUID = 6124898757881206654L;
    private final UUID id;
    private String nickName;
    private String password;
    private String jobTitle;


    //------ Constructors ------//
    public User() {
        id = UUID.randomUUID();
        nickName = "";
        password = "";
    }

    public User(String nickName, String password, String jobTitle) {
        id = UUID.randomUUID();
        this.nickName = nickName;
        this.password = password;
        this.jobTitle = jobTitle;
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

    public String getJobTitle() {
        return jobTitle;
    }

    //------ Setters ------//

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    //------ Methods ------//

    public Room searchRoomByNumber(List<Room> rooms, int number) {
        return rooms.stream().
                filter(room1 -> room1.getNumber() == number).
                findFirst().
                orElse(null);
    }

    @Override
    public String toString() {
        return "User:" +
                "\nNickName='" + nickName;
    }


}
