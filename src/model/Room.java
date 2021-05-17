package model;

import Enumn.TvType;

public abstract class Room {
    int number;
    int cantBed;
    double price;
    TvType tv;
    boolean disponible;
    boolean miniFridge;

    public Room() {
    }

    public Room(int number, int cantBed, TvType tv, boolean disponible) {
        this.number = number;
        this.cantBed = cantBed;
        this.tv = tv;
        this.disponible = disponible;
        this.miniFridge=true;
    }
}
