package app;

import enumn.BedType;
import enumn.TvType;
import model.Reservation;
import model.Superior;
import users.Pax;
import users.Recepcionist;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scan = new Scanner(System.in);
        Reservation reserve = new Reservation();
        hotel.rooms.add(new Superior(3,1, BedType.MATRIMONIAL,2500, TvType.TV_LED_42));
        hotel.showRooms();

        Recepcionist recepcionist1 = new Recepcionist("luchossj", "1234");
        Recepcionist recepcionist2 = new Recepcionist("ElDuko", "9821");
        Recepcionist recepcionist3 = new Recepcionist("L-Gante", "420");
        hotel.users.add(recepcionist1);
        hotel.users.add(recepcionist2);
        hotel.users.add(recepcionist3);hotel.addHistoryPax(new Pax("Pocho", "", "", "123232", ""));
        hotel.paxes.add(new Pax("Toto", "", "", "655475", ""));
        hotel.paxes.add(new Pax("Cholo", "", "", "998878", ""));
        hotel.paxes.add(new Pax("Tom", "", "", "221254", ""));
        hotel.paxes.add(new Pax("Moncho", "", "", "998785", ""));
        hotel.paxes.add(new Pax("Rodolfo", "", "", "554565", ""));
        hotel.paxes.add(new Pax("Otto", "", "", "778754", ""));
        hotel.paxes.add(new Pax("Pololo", "", "", "212446", ""));
        //hotel.showHistoryPax();
        hotel.addNewReserve(recepcionist1.makeReserve(hotel,scan,hotel.paxes.get(0)));
       // hotel.showAllReserves();
        hotel.paxes.get(0).userMenu(scan);
        System.out.println(hotel.paxes.get(0).getTickets());
    }


}
