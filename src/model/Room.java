package model;

import enumn.BedType;
import enumn.Condition;
import enumn.TvType;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Room implements Serializable {

    @Serial
    private static final long serialVersionUID = 7773573522139429469L;
    private int number;
    private BedType bedType;
    private Condition condition;
    private TvType tvType;

    public Room() {
    }

    public Room(int number, BedType bedType, Condition condition, TvType tvType) {
        this.number = number;
        this.bedType = bedType;
        this.condition = condition;
        this.tvType = tvType;
    }

    public BedType getBedType() {
        return bedType;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }


    public int getNumber() {
        return number;
    }

    public boolean isOcuped(List<Reservation> reservations, LocalDate ingress, LocalDate exit) {
        boolean bool = false;
        List<LocalDate> ocuppedDays = Stream.iterate(ingress, date -> date.plusDays(1)).limit(ChronoUnit.DAYS.between(ingress, exit)).collect(Collectors.toList());
        for (Reservation reservation : reservations) {
            for (LocalDate localDate : ocuppedDays) {
                if ((reservation.getCheckIn().equals(localDate) || reservation.getCheckOut().equals(localDate)) && (reservation.getRoom().getNumber() == this.number)) {
                    bool = true;
                    this.condition = Condition.OCUPPED;
                    break;
                }
            }
        }
        return bool || (this.condition != Condition.AVAILABLE && this.condition != Condition.UNCLEAN_AVAILABLE);
    }


    public String toString() {
        return "Numero de habitacion: " + number +
                "Tipo de cama " + bedType.getDescription() +
                "Tipo de Tv" + tvType.getDescription() +
                "Estado: " + condition.getState();
    }


}
