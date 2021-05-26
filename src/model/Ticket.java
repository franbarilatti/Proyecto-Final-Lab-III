package model;

import users.Pax;

public class Ticket {
    private Pax pax;
    private Room room;
    private double total;


    public Ticket() {
    }

    public Ticket(Pax pax, Room room, double total) {
        this.pax = pax;
        this.room = room;
        this.total = total;
    }

    public Pax getPax() {
        return pax;
    }

    public void setPax(Pax pax) {
        this.pax = pax;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "pax=" + pax +
                ", room=" + room.getNumber() +
                ", total=" + total +
                '}';
    }
}
