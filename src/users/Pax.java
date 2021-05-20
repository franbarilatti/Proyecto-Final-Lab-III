package users;

import model.Reservation;

import java.util.Scanner;
import java.util.UUID;

public class Pax extends User{
    //------ Attributes ------//
    private String name;
    private String surname;
    private String address;
    private String dni;
    private String nationality;
    private boolean ingress;
    private Reservation reserve;

    //------ Constructors ------//
    public Pax() {
        name = "";
        surname = "";
        address = "";
        dni = "";
        nationality = "";
        ingress = false;
        reserve = null;
    }

    public Pax(String name, String surname, String location, String dni, String nationality) {
        id = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.address = location;
        this.dni = dni;
        this.nationality = nationality;
        reserve = null;
    }
    //------ Getters ------//

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getDni() {
        return dni;
    }

    public String getNationality() {
        return nationality;
    }

    public boolean isIngress() {
        return ingress;
    }

    public Reservation getReserve() {
        return reserve;
    }

    //------ Setters ------//

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setIngress(boolean ingress) {
        this.ingress = ingress;
    }

    public void setReserve(Reservation reserve) {
        this.reserve = reserve;
    }

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
        return "\n-----------------------------\n"+
                "Pax= "+ name +" "+surname+
                "\nAddress= " + address +
                "\nDni= " + dni +
                "\nNationality= " + nationality+
                "\n-----------------------------\n";
    }

}
