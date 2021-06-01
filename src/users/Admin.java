package users;

import Interface.Ingress;
import Interface.Reserve;
import app.Hotel;
import enumn.Condition;
import model.Reservation;
import model.Room;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Admin extends User implements Reserve, Ingress, Serializable {
    //------ Constructors ------//


    public Admin(String nickName, String password) {
        super(nickName, password);
    }

    //------ Methods ------//
    @Override
    public void userMenu(Scanner scan, Hotel hotel) {
        int opt;
        int back = 0;
        System.out.println("Welcome, " + nickName);
        while (back == 0) {
            System.out.println("\nSelect one option:\n[1]- Add new user\n[2]- Check in\n[3]-Check out\n[4]- Add new reserve\n[5]- Check reserves\n[6]- Check Rooms[7]- Room service\n[0]- Log out");
            opt = scan.nextInt();
            switch (opt) {
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
    public void makeReserve(List<Reservation> reservations, List<Pax> paxes, List<Room> rooms, Scanner scan) {
        System.out.print("Ingrese el dni del pasajero: ");
        String dni = scan.next();
        Pax pax = paxes.stream().filter(pax1 -> pax1.getDni().equals(dni)).findFirst().orElse(null);
        if (pax == null) {
            System.out.println("El pasajero no esta dentro del historial del hotel.\n\nPor favor ingrese sus datos: \n\n-------------------------------------\n\n");
            pax = newPax();
        }
        System.out.print("\nFecha de ingreso(DD/MM/AAAA):");
        LocalDate checkIn = ingressDate(scan, LocalDate.now());
        System.out.print("Cantidad de noches que se queda: ");
        int cantDays = scan.nextInt();
        LocalDate checkOut = checkIn.plusDays(cantDays);
        System.out.print("\ningrese el numero de habitación disponible: ");
        System.out.println("--------------------------------------");
        for (Room room : rooms) {
            if (!room.isOcuped(reservations,checkIn, checkOut)) {
                System.out.println(room.toString());
            }
        }
        System.out.println("--------------------------------------");
        int roomNumber = scan.nextInt();
        Room roomAux = rooms.stream().filter(room -> room.getNumber() == roomNumber).findFirst().orElse(null);
        Reservation reservation = new Reservation(pax, roomAux, checkIn, checkOut, cantDays);
        reservations.add(reservation);
    }


    @Override
    public Pax newPax() {
        Pax pax = new Pax();
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
        for (Room room : roomList) {
            if (room.getCondition() == Condition.AVAILABLE || room.getCondition() == Condition.UNCLEAN_AVAILABLE) {
                System.out.println(room.toString());
            }
        }
    }

    @Override
    public void checkIn(List<Pax> paxes, Room room, Hotel hotel) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un DNI o Pasaporte: ");
        String dniAux = scanner.nextLine();
        Pax pax = paxes.stream().filter(pax1 -> pax1.getDni().equals(dniAux)).findFirst().orElse(null);
        if (pax == null) {
            System.out.println("Pasajero no encontrado. Ingrese sus datos para continuar\n\n");
            pax = newPax();
        }
        paxes.add(pax);
        Reservation auxReserve = hotel.searchReserve(pax, room);
        pax.setIngress(true);
        hotel.addHistoryPax(pax);
        hotel.eliminateReserve(auxReserve);
        room.setCondition(Condition.OCUPPED);

    }

    @Override
    public void checkOut(Pax pax, Room room) {
        pax.setIngress(false);
        room.setCondition(Condition.UNCLEAN_AVAILABLE);
    }

    @Override
    public LocalDate ingressDate(Scanner scan, LocalDate today) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = scan.next();
        LocalDate localDate = LocalDate.parse(date, formatter);
        int exit = 0;
        while (exit == 0) {
            if (!formatter.format(localDate).equals(date) || localDate.compareTo(today) < 0) {
                System.out.println("\nFecha invalida. Ingrese una nueva fecha");
                date = scan.next();
                localDate = LocalDate.parse(date, formatter);
            } else {
                exit++;
            }
        }
        return localDate;
    }

    public void changeRoomState(Hotel hotel, Scanner scan) {
        System.out.println("Elija un numero de habitacion");
        Room room = this.searchRoomByNumber(hotel, scan.nextInt());
        if (room != null) {
            System.out.println(room.toString());
            System.out.println("Elija el nuevo estado de la habitacion");
            for (Condition condition : Condition.values()) {
                System.out.println(condition.ordinal() + 1 + "- " + condition.name());
            }
            String s = Condition.searchByOrdinal(scan.nextInt());
            if (!s.equals("")) {
                room.setCondition(Condition.valueOf(s));
            } else {
                System.out.println("Condicion no encontrada");
            }
        } else {
            System.out.println("Habitacion no encontrada");
        }
    }
}
