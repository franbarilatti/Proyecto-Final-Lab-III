package app;


import enumn.BedType;
import enumn.Condition;
import enumn.TvType;
import model.Reservation;
import model.Superior;
import repositories.RoomRepository;
import users.Admin;
import users.Pax;
import users.Recepcionist;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scan = new Scanner(System.in);

        hotel.showRooms();
        Reservation reserve = new Reservation();
        hotel.showRooms();

        hotel.showRooms();

        Recepcionist recepcionist2 = new Recepcionist("ElDuko", "9821");
        Admin recepcionist3 = new Admin("L-Gante", "420");
        hotel.rooms.add(new Superior(101, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.rooms.add(new Superior(102, BedType.DOBLE_TWIN, Condition.AVAILABLE, TvType.TV_LED_32));
        hotel.rooms.add(new Superior(103, BedType.CUADRUPLE, Condition.MAINTENANCE, TvType.TV_LED_42));
        hotel.rooms.add(new Superior(104, BedType.MATRIMONIAL, Condition.UNCLEAN_OCUPPED, TvType.TV_LED_42));
        hotel.users.add(recepcionist2);
        hotel.users.add(recepcionist3);
        hotel.addHistoryPax(new Pax("Pocho", "", "", "123232", ""));
        hotel.paxes.add(new Pax("Toto", "", "", "655475", ""));
        hotel.paxes.add(new Pax("Cholo", "", "", "998878", ""));
        hotel.paxes.add(new Pax("Tom", "", "", "221254", ""));
        hotel.paxes.add(new Pax("Moncho", "", "", "998785", ""));
        hotel.paxes.add(new Pax("Rodolfo", "", "", "554565", ""));
        hotel.paxes.add(new Pax("Otto", "", "", "778754", ""));
        hotel.paxes.add(new Pax("Pololo", "", "", "212446", ""));
        /*recepcionist2.makeReserve(hotel, scan);
        System.out.println(hotel.getReserves());
        recepcionist2.makeReserve(hotel, scan);*/
//        recepcionist3.changeRoomState(hotel,scan);
//        hotel.showRooms();
//        recepcionist2.makeReserve(hotel,scan);
        RoomRepository roomRepository= new RoomRepository();
       // roomRepository.createFile();
        roomRepository.addObjet(hotel.rooms.get(0));
    }
}
