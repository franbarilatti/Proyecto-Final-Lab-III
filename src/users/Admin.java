package users;

import java.util.UUID;

public class Admin extends User{
    //------ Constructors ------//

    public Admin() {
        nickName = "";
        password = "";
        id = UUID.randomUUID();
    }

    //------ Methods ------//
    @Override
    public void userMenu() {

    }

    @Override
    public String toString() {
        return "Admin: "+ nickName;
    }
}
