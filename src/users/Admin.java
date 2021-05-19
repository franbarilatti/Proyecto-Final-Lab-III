package users;

import Interface.Reserve;
import model.Reservation;
import model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Admin extends User implements Reserve{
    //------ Constructors ------//


    public Admin(String nickName, String password) {
        super(nickName, password);
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

    }

    @Override
    public String toString() {
        return "Admin: "+ nickName;
    }
}
