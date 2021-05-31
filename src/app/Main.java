package app;

import enumn.BedType;
import enumn.Condition;
import enumn.TvType;
import model.Reservation;
import model.Room;
import model.Standar;
import model.Superior;
import repositories.ReserveRepository;
import repositories.RoomRepository;
import users.Admin;
import users.Pax;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import mappers.Mapper;
import repositories.UserRepository;
import users.Recepcionist;
import users.User;
import com.google.gson.internal.LinkedTreeMap;

import java.util.Scanner;
import java.io.*;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scan = new Scanner(System.in);

        hotel.showRooms();
        Reservation reserve = new Reservation();
        hotel.showRooms();

        hotel.showRooms();


        Recepcionist recepcionist1 = new Recepcionist("luchossj", "1234");
        Recepcionist recepcionist2 = new Recepcionist("ElDuko", "9821");
        Admin recepcionist3 = new Admin("L-Gante", "420");
        hotel.rooms.add(new Superior(101, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.rooms.add(new Superior(102, BedType.DOBLE_TWIN, Condition.AVAILABLE, TvType.TV_LED_32));
        hotel.rooms.add(new Superior(103, BedType.CUADRUPLE, Condition.MAINTENANCE, TvType.TV_LED_42));
        hotel.rooms.add(new Superior(104, BedType.MATRIMONIAL, Condition.UNCLEAN_OCUPPED, TvType.TV_LED_42));
        hotel.users.add(recepcionist1);
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

        Pax pax1 = new Pax();
        Pax pax2 = new Pax();
        pax1.setName("pepe");
        pax2.setName("lolo");
        pax1.setSurname("pepe");
        pax2.setSurname("lolo");

        Room room1 = new Superior(101,BedType.DOBLE_TWIN,Condition.AVAILABLE,TvType.TV_LED_32);
        Room room2 = new Standar(101,BedType.MATRIMONIAL,Condition.AVAILABLE,TvType.TV_LED_32);

        Reservation reserve1 = new Reservation();
        Reservation reserve2 = new Reservation();

        reserve1.setPax(pax1);
        reserve2.setPax(pax2);
        reserve2.setRoom(room1);
        reserve2.setRoom(room2);

        List<Reservation> reservationList = new ArrayList<>();
        List<Room> roomList = new ArrayList<>();

        reservationList.add(reserve1);
        reservationList.add(reserve2);

        roomList.add(room1);
        roomList.add(room2);

        ReserveRepository reserveRepository = new ReserveRepository();
        RoomRepository roomRepository = new RoomRepository();

        reserveRepository.createFile();
        roomRepository.createFile();

        reserveRepository.addList(reservationList);
        roomRepository.addList(roomList);





    }
}
