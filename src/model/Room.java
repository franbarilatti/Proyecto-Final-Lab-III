package model;

import enumn.TvType;

public abstract class Room {
    private int number;
    private int cantBed;
    private double price;
    private TvType tv;
    private boolean availability;
    private boolean miniFridge;

    public Room() {
    }

    public Room(int number, int cantBed, TvType tv) {
        this.number = number;
        this.cantBed = cantBed;
        this.tv = tv;
        this.availability = true;
        this.miniFridge=true;
    }

    public abstract String showDescription();

    @Override
    public String toString() {
        return "Detalles de la habitacion:" +
                "\nNumero de habitacion: " + number +
                "\nCantidad de camas: " + cantBed+
                "\nDisponibilidad: " + showAvailability();
    }

    private String showAvailability(){
        if(this.availability){
            return "Si";
        }else {
            return "No";
        }
    }
}
