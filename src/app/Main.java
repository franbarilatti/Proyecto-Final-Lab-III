package app;

import users.Pax;
import users.Recepcionist;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        /*hotel.rooms.add(new Superior(3,1, BedType.MATRIMONIAL,2500,TvType.TV_LED_42));
        System.out.println(hotel.rooms.toString());

        Pax pax=new Pax();
        pax=lucho.NewPax();
        System.out.println(pax.toString());*/
        Recepcionist recepcionist = new Recepcionist("luchossj","1234");
        hotel.users.add(new Pax("Pocho","","","123232",""));
        hotel.users.add(new Pax("Toto","","","655475",""));
        hotel.users.add(new Pax("Cholo","","","998878",""));
        hotel.users.add(new Pax("Tom","","","221254",""));
        hotel.users.add(new Pax("Moncho","","","998785",""));
        hotel.users.add(new Pax("Rodolfo","","","554565",""));
        hotel.users.add(new Pax("Otto","","","778754",""));
        hotel.users.add(new Pax("Pololo","","","212446",""));
        hotel.showHistoryPax();

    }



}
