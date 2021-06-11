package users;


import model.Ticket;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pax implements Serializable {

    //------ Attributes ------//
    @Serial
    private static final long serialVersionUID = 4186282981466276158L;
    private String name;
    private String surname;
    private String address;
    private String dni;
    private String nationality;
    private boolean ingress;
    private List<Ticket> tickets;

    //------ Constructors ------//
    public Pax() {
        name = "";
        surname = "";
        address = "";
        dni = "";
        nationality = "";
        ingress = false;
        tickets = new ArrayList<>();
    }

    public Pax(String name, String surname, String address, String dni, String nationality) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.dni = dni;
        this.nationality = nationality;
        this.tickets = new ArrayList<>();

    }
    //------ Getters ------//

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }



    public String getDni() {
        return dni;
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


    public List<Ticket> getTickets() {
        return tickets;
    }
    //------ Methods ------//


    @Override
    public String toString() {
        return "\n-----------------------------\n" +
                "Pax= " + name + " " + surname +
                "\nAddress= " + address +
                "\nDni= " + dni +
                "\nNationality= " + nationality +
                "\n-----------------------------\n";
    }

}
