package users;

import java.util.UUID;

public class Recepcionist extends User{
    //------ Constructors ------//
    public Recepcionist() {
        id = UUID.randomUUID();
        nickName = "";
        password = "";
    }

    public Recepcionist(String nickName, String password) {
        id = UUID.randomUUID();
        this.nickName = nickName;
        this.password = password;
    }
    //------ Methods ------//
    @Override
    public void userMenu() {

    }

    @Override
    public String toString() {
        return "Recepcionist: "+nickName;
    }
}
