package users;

import Interface.Reserve;
import model.Reservation;
import model.Room;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Admin extends User implements Reserve{
    //------ Constructors ------//

    public Admin() {
        nickName = "";
        password = "";
        id = UUID.randomUUID();
    }

    //------ Methods ------//
    @Override
    public void userMenu(Scanner scan) {
        int opt;
        int back = 0;
        System.out.println("Welcome, "+nickName);
        while (back == 0){
            System.out.println("\nSelect one option:\n[1]- Add new user\n[2]- Check in\n[3]-Check out\n[4]- Add new reserve\n[5]- Check reserves\n[6]- Check Rooms[7]- Room service\n[0]- Log out");
            opt = scan.nextInt();
            switch (opt){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 0:
                    back++;
                    break;
                default:
                    System.out.println("Incorrect option");
                    break;
            }
        }
    }

    @Override
    public Reservation makeReserve(Pax pax, Room room, LocalDate checkIn, LocalDate checkOut) {
        pax.setIngress(true);
        room.setAvailability(false);
        Reservation newReserve = new Reservation(pax,room,checkIn,checkOut);
        return newReserve;
    }

    @Override
    public String toString() {
        return "Admin: "+ nickName;
    }
}
