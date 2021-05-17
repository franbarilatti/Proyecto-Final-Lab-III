package users;

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


    @Override
    public String toString() {
        return "User:" +
                "\nNickName='" + nickName;
    }

    public abstract void userMenu();
}
