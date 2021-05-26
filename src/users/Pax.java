package users;

import enumn.MiniBar;
import model.Reservation;
import model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pax {
    //------ Attributes ------//
    private String name;
    private String surname;
    private String address;
    private String dni;
    private String nationality;
    private boolean ingress;
    private Reservation reserve;
    private List<Ticket> tickets;

    //------ Constructors ------//
    public Pax() {
        name = "";
        surname = "";
        address = "";
        dni = "";
        nationality = "";
        ingress = false;
        reserve = null;
        tickets = new ArrayList<>();
    }

    public Pax(String name, String surname, String location, String dni, String nationality) {
        this.name = name;
        this.surname = surname;
        this.address = location;
        this.dni = dni;
        this.nationality = nationality;
        reserve = null;
        this.tickets = new ArrayList<>();

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

    public List<Ticket> getTickets() { return tickets; }
    //------ Methods ------//

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
                    Service(scan);
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

    public void Service(Scanner scan){
        int op=0;
        int exit=0;
        double total=0;
        while (exit==0){
            System.out.println("Elija un producto, 0 para cancelar");
            for(MiniBar m : MiniBar.values()){
                System.out.println(m.ordinal()+1 +" "+ m.getProduct() + ": $" + m.getPrice());
            }
            op=scan.nextInt();
            switch (op) {
                case 1 -> total += MiniBar.COCA_COLA.getPrice();
                case 2 -> total += MiniBar.SPRITE.getPrice();
                case 3 -> total += MiniBar.VINO_TINTO.getPrice();
                case 4 -> total += MiniBar.VINO_BLANCO.getPrice();
                case 5 -> total += MiniBar.PAPAS_LAYS.getPrice();
                case 6 -> total += MiniBar.TABLETA_CHOCOLATE.getPrice();
                case 7 -> total += MiniBar.BOLSA_MANI.getPrice();
                case 0 -> exit++;
                default -> System.out.println("Opcion invalida, por favor elija una nueva");
            }
        }
        this.tickets.add(new Ticket(this, this.reserve.getRoom(), total));
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
