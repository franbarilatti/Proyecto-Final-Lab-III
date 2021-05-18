package users;

import Interface.Reserve;
import model.Reservation;
import model.Room;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Recepcionist extends User implements Reserve {
    //------ Constructors ------//
    public Recepcionist() {
        id = UUID.randomUUID();
        nickName = "";
        password = "";
    }

    public Recepcionist(String nickName, String password) {
        id = UUID.randomUUID();
        this.nickName = nickName;
        this.password = password;
    }
    //------ Methods ------//
    @Override
    public void userMenu(Scanner scan) {
        int opt;
        int back = 0;
        System.out.println("Welcome, "+nickName);
        while (back == 0){
            System.out.println("\nSelect one option:\n[1]- Check in\n[2]-Check out\n[3]- Add new reserve\n[4]- Check reserves\n[5]- Check Rooms[6]- Room service\n[0]- Log out");
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
    public String toString() {
        return "Recepcionist: "+nickName;
    }


    @Override
    public Reservation makeReserve(Pax pax, Room room, LocalDate checkIn, LocalDate checkOut) {
        pax.setIngress(true);
        room.setAvailability(false);
        Reservation newReserve = new Reservation(pax,room,checkIn,checkOut);
        return newReserve;
    }
}
