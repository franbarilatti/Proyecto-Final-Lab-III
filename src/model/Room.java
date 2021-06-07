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

public class Room implements Serializable {

    @Serial
    private static final long serialVersionUID = 7773573522139429469L;
    private int number;
    private BedType bedType;
    private Condition condition;
    private TvType tvType;
    private double extraPrice;

    public Room() {
        this.extraPrice = 0;
    }

    public Room(int number, BedType bedType, Condition condition, TvType tvType) {
        this.number = number;
        this.bedType = bedType;
        this.condition = condition;
        this.tvType = tvType;
    }

    public double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(double extraPrice) {
        this.extraPrice = extraPrice;
    }

    public BedType getBedType() {
        return bedType;
    }

    public Condition getCondition() {
        return condition;
    }

    public int getNumber() {
        return number;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTvType(TvType tvType) {
        this.tvType = tvType;
    }

    public boolean isOcuped(List<Reservation> reservations, LocalDate ingress, LocalDate exit) {
        boolean bool = false;
        List<LocalDate> ocuppedDays = Stream.iterate(ingress, date -> date.plusDays(1)).limit(ChronoUnit.DAYS.between(ingress, exit)).collect(Collectors.toList());
        for (Reservation reservation : reservations) {
            for (LocalDate localDate : ocuppedDays) {
                if ((reservation.getCheckIn().equals(localDate) || reservation.getCheckOut().equals(localDate)) && (reservation.getRoom().getNumber() == this.number)) {
                    bool = true;
                    break;
                }
            }
        }
        return bool || (this.getCondition() != Condition.AVAILABLE && this.getCondition() != Condition.UNCLEAN_AVAILABLE);
    }


    public String toString() {
        return "-------------------------------------------" +
                "\nNumero de habitacion: " + number +
                "\nTipo de cama " + bedType.getDescription() +
                "\nTipo de Tv" + tvType.getDescription() +
                "\nEstado: " + condition.getState() +
                "\n--------------------------------------------";
    }


}
