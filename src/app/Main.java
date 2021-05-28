package app;

import enumn.BedType;
import enumn.Condition;
import enumn.TvType;
import model.Reservation;
import model.Superior;
import users.Admin;
import users.Pax;
import users.Recepcionist;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scan = new Scanner(System.in);
        Reservation reserve = new Reservation();
        Admin recepcionist1 = new Admin("luchossj", "1234");
        Recepcionist recepcionist2 = new Recepcionist("ElDuko", "9821");
        Recepcionist recepcionist3 = new Recepcionist("L-Gante", "420");
        hotel.rooms.add(new Superior(101, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.rooms.add(new Superior(102, BedType.DOBLE_TWIN, Condition.AVAILABLE, TvType.TV_LED_32));
        hotel.rooms.add(new Superior(103, BedType.CUADRUPLE, Condition.MAINTENANCE, TvType.TV_LED_42));
        hotel.rooms.add(new Superior(104, BedType.MATRIMONIAL, Condition.UNCLEAN_OCUPPED, TvType.TV_LED_42));
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
        recepcionist2.makeReserve(hotel,scan);
        System.out.println(hotel.getRooms().get(0).getReservations());
        hotel.showDisponibledRooms(LocalDate.now(),LocalDate.now().plusDays(10));

    }


}
