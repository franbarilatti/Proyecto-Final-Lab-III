package app;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import enumn.BedType;
import enumn.Condition;
import enumn.TvType;
import model.Reservation;
import model.Superior;
import users.Admin;
import users.Pax;
import users.Recepcionist;

<<<<<<< HEAD
=======
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
>>>>>>> be43b06b6127ae35b840b8413d76982e10536f98
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*Hotel hotel = new Hotel();
        Scanner scan = new Scanner(System.in);
        Reservation reserve = new Reservation();
        Admin recepcionist1 = new Admin("luchossj", "1234");
<<<<<<< HEAD
        hotel.showRooms();

=======
        hotel.rooms.add(new Superior(3,1, BedType.MATRIMONIAL,2500, TvType.TV_LED_42));
        hotel.showRooms();*/


        Recepcionist recepcionist1 = new Recepcionist("luchossj", "1234");
>>>>>>> be43b06b6127ae35b840b8413d76982e10536f98
        Recepcionist recepcionist2 = new Recepcionist("ElDuko", "9821");
        Recepcionist recepcionist3 = new Recepcionist("L-Gante", "420");
        /*hotel.rooms.add(new Superior(101, BedType.MATRIMONIAL, Condition.AVAILABLE, TvType.TV_LED_42));
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
        System.out.println(hotel.getReserves());
        recepcionist2.makeReserve(hotel,scan);

<<<<<<< HEAD

=======
        //hotel.showHistoryPax();
        //hotel.addNewReserve(recepcionist1.makeReserve(hotel,scan));
       // hotel.showAllReserves();

        recepcionist1.userMenu(scan,hotel
         */

        String json = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("archivito.json"));

            String line;
            while ((line = br.readLine()) != null){
                json += line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        System.out.println(json);

        Gson gson = new Gson();
        Type type = new TypeToken<List<Recepcionist>>(){}.getType();

        ArrayList<Recepcionist> rList = gson.fromJson(json,type);
        System.out.println(rList);

>>>>>>> be43b06b6127ae35b840b8413d76982e10536f98
    }


}
