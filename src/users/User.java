package users;

<<<<<<< HEAD
=======
import app.Hotel;
import model.Ticket;

import java.util.ArrayList;
import java.util.List;
>>>>>>> 35d9da1d483ebef7c28c26e695b8262c03e9dfa8
import java.util.Scanner;
import java.util.UUID;

public abstract class User {

    //------ Attributes ------//
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


    //------ Methods ------//


    @Override
    public String toString() {
        return "User:" +
                "\nNickName='" + nickName;
    }

<<<<<<< HEAD
    public abstract void userMenu(Scanner scan);

=======
    public abstract void userMenu(Scanner scan, Hotel hotel);
>>>>>>> 35d9da1d483ebef7c28c26e695b8262c03e9dfa8
}
