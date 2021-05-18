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
    //------ Getters ------//
    public int getNumber() {
        return number;
    }

    public int getCantBed() {
        return cantBed;
    }

    public double getPrice() {
        return price;
    }

    public TvType getTv() {
        return tv;
    }

    public boolean isAvailability() {
        return availability;
    }

    public boolean isMiniFridge() {
        return miniFridge;
    }

    //------ Setters ------//
    public void setNumber(int number) {
        this.number = number;
    }

    public void setCantBed(int cantBed) {
        this.cantBed = cantBed;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTv(TvType tv) {
        this.tv = tv;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void setMiniFridge(boolean miniFridge) {
        this.miniFridge = miniFridge;
    }

    //------ Methods ------//
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
