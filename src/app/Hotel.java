package app;

import model.Reservation;
import model.Room;
import users.Pax;
import users.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
     protected List<User> users;
    protected List<Room> rooms;
    protected List<Reservation> reserves;
    protected List<Pax> paxes;

    public Hotel() {
        users = new ArrayList<>();
        rooms = new ArrayList<>();
        reserves = new ArrayList<>();
        paxes = new ArrayList<>();
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
        paxes.add(pax);
    }

    public void showHistoryPax(){

    }

    public Pax searchHistoryPax(String dni){
        return paxes.stream().
                filter(pax -> pax.getDni().equals(dni))
                .findFirst()
                .orElse(null);
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
            rooms.stream().filter(room -> room.isAvailability()).filter(room -> room.isOccupated()).forEach(System.out::println);
        }
    }

}
