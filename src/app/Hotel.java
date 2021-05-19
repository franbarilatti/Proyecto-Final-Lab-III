package app;

import model.Reservation;
import model.Room;
import users.User;

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
}
