package app;

import enumn.BedType;
import enumn.TvType;
import model.Superior;
import users.Pax;
import users.Recepcionist;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.rooms.add(new Superior(3,1, BedType.MATRIMONIAL,2500,TvType.TV_LED_42));
        System.out.println(hotel.rooms.toString());
        Recepcionist lucho= new Recepcionist("luchossj","1234");
        Pax pax=new Pax();
        pax=lucho.NewPax();
        System.out.println(pax.toString());
    }

}
