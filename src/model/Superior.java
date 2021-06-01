package model;

import enumn.BedType;
import enumn.Condition;
import enumn.TvType;

<<<<<<< HEAD
import java.time.LocalDate;

public class Superior extends Room{
=======
import java.io.Serializable;

public class Superior extends Room implements Serializable {
>>>>>>> 79116f8c28c0981f0c7faed17b4696bdf5d9fd27
    private final double extraPrice;

    public Superior() {
        this.extraPrice=1000;
    }

    public Superior(int number, BedType bedType, Condition condition, TvType tvType) {
        super(number, bedType, condition, tvType);
        this.extraPrice=1000;
    }


    @Override
    public String toString() {
        return super.toString();
    }

}
