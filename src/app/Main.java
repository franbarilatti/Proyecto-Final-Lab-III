package app;


import enumn.BedType;
import enumn.Condition;
import enumn.TvType;
import model.Reservation;
import model.Standar;
import model.Superior;
import repositories.RepositoryController;
import users.Admin;
import users.Pax;
import users.Recepcionist;
import users.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Hotel hotel = new Hotel();
        /*User recepcionist1 = new Admin("LGante", "420", "Administrador");
        User recepcionist2 = new Recepcionist("ElDuko", "9821", "Recepcionista");
        hotel.getRooms().add(new Superior(101, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.getRooms().add(new Standar(102, BedType.DOBLE_TWIN, Condition.AVAILABLE, TvType.TV_LED_32));
        hotel.getRooms().add(new Superior(103, BedType.CUADRUPLE, Condition.MAINTENANCE, TvType.TV_LED_42));
        hotel.getRooms().add(new Superior(104, BedType.MATRIMONIAL, Condition.UNCLEAN_OCUPPED, TvType.TV_LED_42));
        hotel.getUsers().add(recepcionist1);
        hotel.getUsers().add(recepcionist2);
        hotel.getPaxes().add(new Pax("Pocho", "", "", "123232", ""));
        hotel.getPaxes().add(new Pax("Toto", "", "", "655475", ""));
        hotel.getPaxes().add(new Pax("Cholo", "", "", "998878", ""));
        hotel.getPaxes().add(new Pax("Tom", "", "", "221254", ""));
        hotel.getPaxes().add(new Pax("Moncho", "", "", "998785", ""));
        hotel.getPaxes().add(new Pax("Rodolfo", "", "", "554565", ""));
        hotel.getPaxes().add(new Pax("Otto", "", "", "778754", ""));
        hotel.getPaxes().add(new Pax("Pololo", "", "", "212446", ""));
        Reservation reservation1 = new Reservation(hotel.getPaxes().get(0).getName(),hotel.getPaxes().get(0).getDni(), hotel.getRooms().get(0),5, LocalDate.now(), LocalDate.now().plusDays(5));
        Reservation reservation2 = new Reservation(hotel.getPaxes().get(1).getName(),hotel.getPaxes().get(1).getDni(), hotel.getRooms().get(1),5, LocalDate.now().plusDays(25), LocalDate.now().plusDays(5));
        Reservation reservation3 = new Reservation(hotel.getPaxes().get(2).getName(),hotel.getPaxes().get(2).getDni(), hotel.getRooms().get(2),5, LocalDate.now(), LocalDate.now().plusDays(10).plusDays(5));
        hotel.getReserves().add(reservation1);
        hotel.getReserves().add(reservation2);
        hotel.getReserves().add(reservation3);
        hotel.saveHotel();*/
        hotel.runHotel();

    }
}
