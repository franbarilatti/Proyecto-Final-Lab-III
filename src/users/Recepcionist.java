package users;

import Interface.Reserve;
import model.Reservation;
import model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Recepcionist extends User implements Reserve {
    //------ Constructors ------//
    public Recepcionist() {
    }

    public Recepcionist(String nickName, String password) {
        super(nickName, password);
    }

    //------ Methods ------//
    @Override
    public void userMenu(Scanner scan) {
        int opt;
        int back = 0;
        System.out.println("Welcome, "+nickName);
        while (back == 0){
            System.out.println("\nSelect one option:\n[1]- Check in\n[2]-Check out\n[3]- Add new reserve\n[4]- Check reserves\n[5]- Check Rooms\n[6]- Room service\n[0]- Log out");
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
    public Reservation makeReserve(Pax pax, List<Room> roomList, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        Room roomAux= roomList.stream().filter(room -> room.getNumber()==roomNumber).findFirst().orElse(null);
        if(roomAux!=null){
            pax.setIngress(true);
            roomAux.setAvailability(false);
        }
        return new Reservation(pax,roomAux,checkIn,checkOut);
    }

    @Override
    public Pax NewPax() {
        Pax pax=new Pax();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre: ");
        pax.setName(scanner.nextLine());
        System.out.print("Apellido: ");
        pax.setSurname(scanner.nextLine());
        System.out.print("Direccion: ");
        pax.setAddress(scanner.nextLine());
        System.out.print("DNI o Pasaporte: ");
        pax.setDni(scanner.nextLine());
        System.out.print("Nacionalidad: ");
        pax.setNationality(scanner.nextLine());
        return pax;
    }

    @Override
    public void RoomAvailable(List<Room> roomList) {
        for (Room room:roomList){
            if (room.isAvailability()){
                System.out.println(room.toString());
            }
        }

    }
}
