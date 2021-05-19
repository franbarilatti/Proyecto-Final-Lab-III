package app;

import enumn.BedType;
import enumn.TvType;
import model.Hotel;
import model.Superior;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.rooms.add(new Superior(3,1, BedType.MATRIMONIAL,2500,TvType.TV_LED_42));
        System.out.println(hotel.rooms.toString());
    }

}
