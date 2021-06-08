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
       /*User recepcionist1 = new Admin("L-Gante", "Keloke", "Administrador");
        User recepcionist2 = new Recepcionist("ElDuko", "9821", "Recepcionista");
        User recepcionist3 = new Admin("ELDoctor", "LaMasPiola", "Administrador");
        User recepcionist4 = new Recepcionist("Dillom", "247", "Recepcionista");
        hotel.getRooms().add(new Superior(101, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.getRooms().add(new Standar(102, BedType.DOBLE_TWIN, Condition.AVAILABLE, TvType.TV_TUBO));
        hotel.getRooms().add(new Standar(103, BedType.CUADRUPLE, Condition.AVAILABLE, TvType.TV_TUBO));
        hotel.getRooms().add(new Superior(104, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.getUsers().add(recepcionist1);
        hotel.getUsers().add(recepcionist2);
        hotel.getUsers().add(recepcionist3);
        hotel.getUsers().add(recepcionist4);
        hotel.getPaxes().add(new Pax("Pocho", "", "", "123232", ""));
        hotel.getPaxes().add(new Pax("Toto", "", "", "655475", ""));
        hotel.getPaxes().add(new Pax("Cholo", "", "", "998878", ""));
        hotel.getPaxes().add(new Pax("Tom", "", "", "221254", ""));
        hotel.getPaxes().add(new Pax("Moncho", "", "", "998785", ""));
        hotel.getPaxes().add(new Pax("Rodolfo", "", "", "554565", ""));
        hotel.getPaxes().add(new Pax("Otto", "", "", "778754", ""));
        hotel.getPaxes().add(new Pax("Pololo", "", "", "212446", ""));
        hotel.saveHotel();*/
        hotel.runHotel();
        hotel.saveHotel();
    }
}
