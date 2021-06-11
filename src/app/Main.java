package app;


import enumn.BedType;
import enumn.Condition;
import enumn.TvType;
import model.Standar;
import model.Superior;
import users.Admin;
import users.Pax;
import users.Recepcionist;
import users.User;


public class Main {
    public static void main(String[] args)  {
        Hotel hotel = new Hotel();
        User recepcionist1 = new Admin("L-Gante", "Keloke", "Administrador");
        User recepcionist2 = new Recepcionist("ElDuko", "9821", "Recepcionista");
        User recepcionist3 = new Admin("ELDoctor", "LaMasPiola", "Administrador");
        User recepcionist4 = new Recepcionist("Dillom", "247", "Recepcionista");
        hotel.getRooms().add(new Superior(101, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.getRooms().add(new Standar(102, BedType.DOBLE_TWIN, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.getRooms().add(new Standar(103, BedType.CUADRUPLE, Condition.AVAILABLE, TvType.TV_LED_32));
        hotel.getRooms().add(new Superior(104, BedType.TRIPLE, Condition.AVAILABLE, TvType.TV_LED_32));
        hotel.getRooms().add(new Standar(105, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_TUBO));
        hotel.getRooms().add(new Standar(106, BedType.DOBLE_TWIN, Condition.AVAILABLE, TvType.TV_TUBO));
        hotel.getRooms().add(new Standar(107, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_TUBO));
        hotel.getRooms().add(new Superior(201, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.getRooms().add(new Superior(202, BedType.DOBLE_TWIN, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.getRooms().add(new Superior(203, BedType.CUADRUPLE, Condition.AVAILABLE, TvType.TV_LED_32));
        hotel.getRooms().add(new Superior(204, BedType.TRIPLE, Condition.AVAILABLE, TvType.TV_LED_32));
        hotel.getRooms().add(new Standar(205, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_TUBO));
        hotel.getRooms().add(new Standar(206, BedType.DOBLE_TWIN, Condition.AVAILABLE, TvType.TV_TUBO));
        hotel.getRooms().add(new Standar(207, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_TUBO));
        hotel.getRooms().add(new Superior(301, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.getRooms().add(new Superior(302, BedType.DOBLE_TWIN, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.getRooms().add(new Superior(303, BedType.CUADRUPLE, Condition.AVAILABLE, TvType.TV_LED_32));
        hotel.getRooms().add(new Superior(304, BedType.TRIPLE, Condition.AVAILABLE, TvType.TV_LED_32));
        hotel.getRooms().add(new Standar(305, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_TUBO));
        hotel.getRooms().add(new Standar(306, BedType.DOBLE_TWIN, Condition.AVAILABLE, TvType.TV_TUBO));
        hotel.getRooms().add(new Standar(307, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_TUBO));
        hotel.getRooms().add(new Superior(401, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.getRooms().add(new Superior(402, BedType.DOBLE_TWIN, Condition.AVAILABLE, TvType.TV_LED_42));
        hotel.getRooms().add(new Superior(403, BedType.CUADRUPLE, Condition.AVAILABLE, TvType.TV_LED_32));
        hotel.getRooms().add(new Superior(404, BedType.TRIPLE, Condition.AVAILABLE, TvType.TV_LED_32));
        hotel.getRooms().add(new Standar(405, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_TUBO));
        hotel.getRooms().add(new Standar(406, BedType.DOBLE_TWIN, Condition.AVAILABLE, TvType.TV_TUBO));
        hotel.getRooms().add(new Standar(407, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_TUBO));
        hotel.getUsers().add(recepcionist1);
        hotel.getUsers().add(recepcionist2);
        hotel.getUsers().add(recepcionist3);
        hotel.getUsers().add(recepcionist4);
        hotel.getPaxes().add(new Pax("Franky", "Stain", "Berg 123", "938568", "Aleman"));
        hotel.getPaxes().add(new Pax("Tutan", "Kamon", "Giza 3000 A.c ", "A17734243EGY68144M", "Egipcio"));
        hotel.getPaxes().add(new Pax("Vlad", "Dracula", "Castel 2757 ", "14281477", "Rumano"));
        hotel.getPaxes().add(new Pax("Jonathan", "Loughran", "California", "03005988", "EEUU"));
        hotel.getPaxes().add(new Pax("Were", "Wolf", "28is Oktovriou 116", "AM0432504", "Griego"));
        hotel.getPaxes().add(new Pax("Van", "Hellsing", "Damstraat 46", "XR1001R58", "Holandes"));
        hotel.saveHotel();
        //hotel.getRooms().forEach(System.out::println);
        // hotel.runHotel();
       // hotel.saveHotel();
    }
}
