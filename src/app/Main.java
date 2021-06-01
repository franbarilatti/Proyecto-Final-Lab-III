package app;


import enumn.BedType;
import enumn.Condition;
import enumn.TvType;
import model.Standar;
import model.Superior;
import users.Admin;
import users.Pax;
import users.Recepcionist;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scan = new Scanner(System.in);

        Recepcionist recepcionist2 = new Recepcionist("ElDuko", "9821");
        Admin recepcionist3 = new Admin("L-Gante", "420");
        hotel.getRooms().add(new Superior(101, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.getRooms().add(new Standar(102, BedType.DOBLE_TWIN, Condition.AVAILABLE, TvType.TV_LED_32));
        hotel.getRooms().add(new Superior(103, BedType.CUADRUPLE, Condition.MAINTENANCE, TvType.TV_LED_42));
        hotel.getRooms().add(new Superior(104, BedType.MATRIMONIAL, Condition.UNCLEAN_OCUPPED, TvType.TV_LED_42));
        hotel.getUsers().add(recepcionist2);
        hotel.getUsers().add(recepcionist3);
        hotel.addHistoryPax(new Pax("Pocho", "", "", "123232", ""));
        hotel.getPaxes().add(new Pax("Toto", "", "", "655475", ""));
        hotel.getPaxes().add(new Pax("Cholo", "", "", "998878", ""));
        hotel.getPaxes().add(new Pax("Tom", "", "", "221254", ""));
        hotel.getPaxes().add(new Pax("Moncho", "", "", "998785", ""));
        hotel.getPaxes().add(new Pax("Rodolfo", "", "", "554565", ""));
        hotel.getPaxes().add(new Pax("Otto", "", "", "778754", ""));
        hotel.getPaxes().add(new Pax("Pololo", "", "", "212446", ""));
        /*recepcionist2.makeReserve(hotel, scan);
        System.out.println(hotel.getReserves());
        recepcionist2.makeReserve(hotel, scan);*/
//        recepcionist3.changegetRooms()tate(hotel, scan);
//        hotel.showgetRooms()();
      
    }
}
