package model;

import enumn.BedType;
import enumn.Condition;
import enumn.TvType;

import java.io.Serial;
import java.io.Serializable;

public class Standar extends Room implements Serializable {

    @Serial
    private static final long serialVersionUID = 6545602839674728519L;

    public Standar() {
    }

    public Standar(int number, BedType bedType, Condition condition, TvType tvType) {
        super(number, bedType, condition, tvType);
    }


    @Override
    public String toString() {
        return super.toString() + "\nTipo de habitacion: Standar";
    }

}
