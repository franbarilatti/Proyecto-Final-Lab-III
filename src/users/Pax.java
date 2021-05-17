package users;

import java.util.Scanner;
import java.util.UUID;

public class Pax extends User{
    //------ Attributes ------//
    private String name;
    private String surname;
    private String address;
    private String dni;
    private String nationality;

    //------ Constructors ------//
    public Pax() {
        id = UUID.randomUUID();
        nickName = "";
        password = "";
        name = "";
        surname = "";
        address = "";
        dni = "";
        nationality = "";
    }

    public Pax(String nickName, String password, String name, String surname, String location, String dni, String nationality) {
        id = UUID.randomUUID();
        this.nickName = nickName;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = location;
        this.dni = dni;
        this.nationality = nationality;
    }
    //------ Getters ------//
    //------ Setters ------//
    //------ Methods ------//
    @Override
    public void userMenu(Scanner scan) {
        int opt;
        int back = 0;
        System.out.println("Welcome, "+name+" "+surname);
        while (back == 0){
            System.out.println("\nSelect one option:\n[1]- Check Reserves\n[2]- Room service\n[3]- Log out");
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
        return "Pax= "+ name +" "+surname+
                "\nAddress= " + address +
                "\nDni= " + dni +
                "\nNationality= " + nationality;
    }
}
