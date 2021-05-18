package model;

import users.Pax;

import java.time.LocalDate;

public class Reservation {
    private Pax pax;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation(Pax pax, Room room, LocalDate checkIn, LocalDate checkOut) {
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

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }
}
