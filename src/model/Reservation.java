package model;

import users.Pax;

import java.time.LocalDate;

public class Reservation {
    private Pax pax;
    private Room room;
    private int cantDays;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public Reservation() {

    }

    public Reservation(Pax pax, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.pax = pax;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
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

    public int getCantDays() {
        return cantDays;
    }

    public void setCantDays(int cantDays) {
        this.cantDays = cantDays;
    }

    @Override
    public String toString() {
        return "\n-------------------------------\n"+
                "Pasajero: "+ pax.getName()+" "+ pax.getSurname()+
                "\nHabitacion: "+ room.getNumber()+
                "\nFecha de ingreso: "+checkIn+
                "\nFecha de retiro: "+checkOut+
                "\nCantidad de dias: " +cantDays+
                "\n---------------------------------";
    }
}
