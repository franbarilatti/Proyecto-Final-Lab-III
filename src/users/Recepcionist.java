package users;

import java.util.Scanner;
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
    public void userMenu(Scanner scan) {
        int opt;
        int back = 0;
        System.out.println("Welcome, "+nickName);
        while (back == 0){
            System.out.println("\nSelect one option:\n[1]- Check in\n[2]-Check out\n[3]- Add new reserve\n[4]- Check reserves\n[5]- Check Rooms");
            opt = scan.nextInt();
            switch (opt){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    back++;
                    break;
                default:
                    System.out.println("Incorrect option");
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "Recepcionist: "+nickName;
    }
}
