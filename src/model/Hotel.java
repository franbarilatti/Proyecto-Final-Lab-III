package model;

import users.User;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
     public List<User> users;
     public List<Room> rooms;
     public List<Reservation> reserves;

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
