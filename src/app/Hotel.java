package app;

import model.Reservation;
import model.Room;
import users.Pax;
import users.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public List<Pax> getPaxes() {
        return paxes;
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

    public void showHistoryPax() throws Exception {
        if (paxes != null)
            paxes.stream().forEach(System.out::println);
        else
            throw new Exception("No hay pasajeros historicos en el sistema");
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

    public void showAllReserves() throws Exception {
        if (reserves != null)
            reserves.stream().forEach(System.out::println);
        else
            throw new Exception("No hay reservas cargadas en el sistema");
    }

    public void showTodayReserves() throws Exception {
        if (reserves != null)
            reserves.stream().
                    filter((Reservation r)-> r.getCheckIn().equals(LocalDate.now())).
                    forEach(System.out::println);
        else
            throw new Exception("No hay reservas cargadas en el sistema");
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

    public void showDisponibledRooms(LocalDate ingress, LocalDate exit){
        if (rooms == null) {
            System.out.println("No hay habitaciones disponibles");
        } else {
           rooms.stream().filter(room -> !room.isOcuped(ingress,exit)).forEach(System.out::println);
        }
    }

    public Room searchRoomByNumber(int number){
        return rooms.stream().
                filter(room1 -> room1.getNumber()==number).
                findFirst().
                orElse(null);
    }

    public void logIn(Scanner scan){
        System.out.println("Ingrese su Nick Name");

        User userAux = users.stream().
                filter(user -> user.getNickName().equals(scan.nextLine())).
                findFirst().
                orElse(null);
        if (userAux != null){
            System.out.println("Ingrese su contrasña");
            if (userAux.getPassword().equals(scan.nextLine())){
                userAux.userMenu(scan,this);
            }else {
                System.out.println("Contraseña incorrecta");
            }
        }else {
            System.out.println("El usuario registrado no se encuentra registrado");
        }
    }


}
