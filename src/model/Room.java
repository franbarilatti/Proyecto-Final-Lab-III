package model;

import enumn.BedType;
import enumn.Condition;
import enumn.TvType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Room {
    private int number;
    private BedType bedType;
    private Condition condition;
    private TvType tvType;
    public List<Reservation> reservations;
    public Room() {
        this.reservations= new ArrayList<>();
    }

    public Room(int number, BedType bedType, Condition condition, TvType tvType) {
        this.number = number;
        this.bedType = bedType;
        this.condition = condition;
        this.tvType = tvType;
        this.reservations= new ArrayList<>();
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public int getNumber() {
        return number;
    }

    public boolean isOcuped(LocalDate ingress, LocalDate exit){
        Reservation reservation= reservations.stream().filter(reservation1 -> (reservation1.getCheckIn().compareTo(ingress)<=0 && reservation1.getCheckOut().compareTo(exit)>=0)).findFirst().orElse(null);

        return reservation != null;
    }

    public String toString() {
        return "Numero de habitacio: " + number +
                "Tipo de cama " + bedType.getDescription() +
                "Tipo de Tv" + tvType.getDescription()+
                "Estado: " + condition.getState();
    }
}
