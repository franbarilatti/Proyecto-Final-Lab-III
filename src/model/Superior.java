package model;

import enumn.BedType;
import enumn.Condition;
import enumn.TvType;

import java.io.Serial;
import java.io.Serializable;

public class Superior extends Room implements Serializable {
    @Serial
    private static final long serialVersionUID = 1706565279965930471L;


    public Superior() {
        this.setExtraPrice(1000);
    }

    public Superior(int number, BedType bedType, Condition condition, TvType tvType) {
        super(number, bedType, condition, tvType);

    }


    @Override
    public String toString() {
        return super.toString();
    }

}
