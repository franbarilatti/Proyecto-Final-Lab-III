package app;

import model.Reservation;
import model.Room;
import org.w3c.dom.ls.LSOutput;
import users.Pax;
import users.User;

import java.security.DrbgParameters;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
     protected List<User> users;
    protected List<Room> rooms;
    protected List<Reservation> reserves;

    public Hotel() {
        users = new ArrayList<>();
        rooms = new ArrayList<>();
        reserves = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Reservation> getReserves() {
        return reserves;
    }

    public void addHistoryPax(Pax pax){
        users.add(pax);
    }

    public void showHistoryPax(){
        users.stream().filter((User u)->u instanceof Pax).forEach(System.out::println);
    }

    public User searchHistoryPaxs(String dni){
        return users.stream().
                filter((User u)-> u instanceof Pax).
                filter(u-> ((Pax) u).getDni().equalsIgnoreCase(dni)).
                findFirst().
                orElse(null);
    }

    public void addNewReserve(Reservation newReserve){
        this.reserves.add(newReserve);
    }

    public void showAllReserves(){
        if (reserves != null)
            reserves.stream().forEach(System.out::println);
        else
            throw new RuntimeException("No hay reservas cargadas en el sistema");
    }

    public void showTodayReserves(){
        if (reserves != null)
            reserves.stream().
                    filter((Reservation r)-> r.getCheckIn().equals(LocalDate.now())).
                    forEach(System.out::println);
        else
            throw new RuntimeException("No hay reservas cargadas en el sistema");
    }

    public Reservation searchReserve(Pax pax,Room room){
        return reserves.stream().
                filter((Reservation r)->r.getRoom().equals(room)).
                filter(r-> r.getPax().equals(pax)).
                findFirst().
                orElse(null);
    }

    public void eliminateReserve(Reservation reserve){
        reserves.remove(reserve);
    }

    public void showRooms(){

        if (rooms == null) {
            System.out.println("No hay habitaciones disponibles");
        } else {
            rooms.stream().forEach(System.out::println);
        }
    }

    public void showDisponibledRooms(){
        if (rooms == null) {
            System.out.println("No hay habitaciones disponibles");
        } else {
            rooms.stream().
                    filter((Room r)->r.isAvailability() == true).
                    filter(r-> r.isOccupated() == false).
                    forEach(System.out::println);
        }
    }

}
