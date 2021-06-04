package users;

import Interface.Ingress;
import Interface.Reserve;
import app.Hotel;
import enumn.Condition;
import model.Reservation;
import model.Room;
import model.Ticket;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Admin extends User implements Reserve, Ingress, Serializable {
    @Serial
    private static final long serialVersionUID = -2416890722001641006L;
    //------ Constructors ------//


    public Admin() {
    }

    public Admin(String nickName, String password, String jobTitle) {
        super(nickName, password, jobTitle);
    }

    //------ Methods ------//

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
            if (!room.isOcuped(reservations, checkIn, checkOut)) {
                System.out.println(room.toString());
            }
        }
        System.out.println("--------------------------------------");
        int roomNumber = scan.nextInt();
        Room roomAux = rooms.stream().filter(room -> room.getNumber() == roomNumber).findFirst().orElse(null);
        if(roomAux!=null){
            roomAux.setCondition(Condition.RESERVED);
            Reservation reservation = new Reservation(pax.getName(), pax.getDni(), roomAux, cantDays, checkIn, checkOut);
            reservations.add(reservation);
        }else {
            System.out.println("Habitacion no encontrada");
        }

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
    public void checkIn(List<Pax> paxes, List<Room> rooms, List<Reservation> reservations) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un DNI o Pasaporte: ");
        String dniAux = scanner.nextLine();
        Pax pax = paxes.stream().filter(pax1 -> pax1.getDni().equals(dniAux)).findFirst().orElse(null);
        if (pax == null) {
            System.out.println("Pasajero no encontrado. Ingrese sus datos para continuar\n\n");
            pax = newPax();
        }
        paxes.add(pax);
        System.out.print("Ingrese el numero de habitacion: ");
        int roomNumber = scanner.nextInt();
        Room room = rooms.stream().filter((Room r) -> ((Integer) r.getNumber()).equals(roomNumber)).findFirst().orElse(null);
        Reservation auxReserve = searchReserve(pax, room, reservations);
        if (auxReserve != null) {
            pax.setIngress(true);
            paxes.add(pax);
            eliminateReserve(reservations, auxReserve);
            room.setCondition(Condition.OCUPPED);
        } else {
            pax.setIngress(true);
            paxes.add(pax);
            room.setCondition(Condition.OCUPPED);
        }
    }

    @Override
    public boolean checkOut(List<Pax> paxes, List<Room> rooms) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el dni del pasajero: ");
        Pax pax = paxes.stream().filter(pax1 -> pax1.getDni().equals(scanner.next())).findFirst().orElse(null);
        System.out.print("Ingrese el numero de habitacion: ");
        Room room = searchRoomByNumber(rooms, scanner.nextInt());
        if (pax == null) {
            System.out.println("El dni ingresado no esta registrado en el sistema.");
        } else {
            if ((pax.getTickets().stream().mapToDouble((Ticket t) -> t.getTotal()).sum()) == 0) {
                pax.setIngress(false);
                room.setCondition(Condition.UNCLEAN_AVAILABLE);
                return true;
            }
        }
        return false;
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

    public void changeRoomState(List<Room> rooms, Scanner scan) {
        System.out.println("Elija un numero de habitacion");
        Room room = this.searchRoomByNumber(rooms, scan.nextInt());
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

    @Override
    public Reservation searchReserve(Pax pax, Room room, List<Reservation> reserves) {
        return reserves.stream().
                filter((Reservation r) -> r.getRoom().equals(room)).
                filter(r -> r.getPaxDni().equals(pax.getDni())).
                findFirst().
                orElse(null);

    }

    @Override
    public void eliminateReserve(List<Reservation> reserves, Reservation reserve) {
        reserves.remove(reserve);
    }
}
