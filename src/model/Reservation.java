package model;

import users.Pax;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {
    @Serial
    private static final long serialVersionUID = 214941093807371581L;
    private String paxName;
    private String paxDni;
    private Room room;
    private int cantDays;
    private  LocalDate checkIn;
    private  LocalDate checkOut;

    public Reservation() {

    }

    public Reservation(String paxName, String paxDni, Room room, int cantDays, LocalDate checkIn, LocalDate checkOut) {
        this.paxName = paxName;
        this.paxDni = paxDni;
        this.room = room;
        this.cantDays = cantDays;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getPaxName() {
        return paxName;
    }

    public void setPaxName(String paxName) {
        this.paxName = paxName;
    }

    public String getPaxDni() {
        return paxDni;
    }

    public void setPaxDni(String paxDni) {
        this.paxDni = paxDni;
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
        return "\n-------------------------------\n" +
                "Pasajero: " + getPaxName() +
                "\nHabitacion: " + room.getNumber() +
                "\nFecha de ingreso: " + checkIn +
                "\nFecha de retiro: " + checkOut +
                "\nCantidad de dias: " + cantDays +
                "\n---------------------------------";
    }
}
