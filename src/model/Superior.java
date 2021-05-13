package model;

import Enumn.TvType;

public class Superior extends Room{
    boolean ac;
    boolean safeBox;
    boolean oceanView;

    public Superior(int number, int cantBed, TvType tv, boolean disponible) {
        super(number, cantBed, tv, disponible);
        this.ac = true;
        this.safeBox = true;
        this.oceanView = true;
    }
}
