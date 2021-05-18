package model;

import users.User;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<User> users;
    private List<Room> rooms;
    private List<Reservation> reserves;

    public Hotel() {
        this.users = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.reserves = new ArrayList<>();
    }

    public Hotel(List<User> users, List<Room> rooms, List<Reservation> reserves) {
        this.users = users;
        this.rooms = rooms;
        this.reserves = reserves;
    }


}
