package app;


import enumn.BedType;
import enumn.Condition;
import enumn.TvType;
import model.Standar;
import model.Superior;
import users.Admin;
import users.Pax;
import users.Recepcionist;
import users.User;


public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.runHotel();
        hotel.saveHotel();
    }
}
