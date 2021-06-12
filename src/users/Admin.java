package users;

import Interface.Ingress;
import Interface.Reserve;
import enumn.Condition;
import model.Reservation;
import model.Room;
import model.Ticket;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
        System.out.println("""
                \033[36m
                ========== GENERADOR DE RESERVAS ==========
                """);
        System.out.print("\n\nIngrese el dni del pasajero: ");
        String dni = scan.next();
        Pax pax = paxes.stream().filter(pax1 -> pax1.getDni().equals(dni)).findFirst().orElse(null);
        if (pax == null) {
            System.out.println("El pasajero no esta dentro del historial del hotel.\n\nPor favor ingrese sus datos: \n\n-------------------------------------\n\n");
            pax = newPax();
            paxes.add(pax);
        }
        System.out.print("\nFecha de ingreso(DD/MM/AAAA):");
        LocalDate checkIn = ingressDate(scan, LocalDate.now());
        System.out.print("\nCantidad de noches que se queda: ");
        int cantDays = scan.nextInt();
        LocalDate checkOut = checkIn.plusDays(cantDays);
        System.out.println("\nIngrese el numero de habitación disponible: ");
        System.out.println("--------------------------------------");
        for (Room room : rooms) {
            if (!room.isOcuped(reservations, checkIn, checkOut)) {
                System.out.println(room);
            }
        }
        System.out.println("--------------------------------------");
        int roomNumber = scan.nextInt();
        Room roomAux = rooms.stream().filter(room -> room.getNumber() == roomNumber).findFirst().orElse(null);
        if (roomAux != null) {
            roomAux.setCondition(Condition.RESERVED);
            Reservation reservation = new Reservation(pax.getName(), pax.getDni(), roomAux, cantDays, checkIn, checkOut);
            reservations.add(reservation);
            System.out.println("\033[33m"+"\nReserva creada con exito");
        } else {
            System.out.println("\033[31m" +"\nHabitacion no encontrada");
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
        System.out.println("Pasajero creado con exito");
        return pax;
    }

    @Override
    public void RoomAvailable(List<Room> roomList) {
        for (Room room : roomList) {
            if (room.getCondition() == Condition.AVAILABLE || room.getCondition() == Condition.UNCLEAN_AVAILABLE) {
                System.out.println(room);
            }
        }
    }

    @Override
    public void checkIn(List<Pax> paxes, List<Room> rooms, List<Reservation> reservations) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un DNI o Pasaporte del pasajero: ");
        String dniAux = scanner.nextLine();
        Pax pax = paxes.stream().filter(pax1 -> pax1.getDni().equals(dniAux)).findFirst().orElse(null);
        if (pax == null) {
            System.out.println("Pasajero no encontrado. Ingrese sus datos para continuar\n\n");
            pax = newPax();
            paxes.add(pax);
            this.makeReserve(reservations, paxes, rooms, scanner);
        }
        List<Reservation> reservationList = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getPaxDni().equals(pax.getDni()) && reservation.getCheckIn().equals(LocalDate.now())) {
                reservationList.add(reservation);
            }
        }
        if (!reservationList.isEmpty()) {
            System.out.println(reservationList);
            System.out.println("Ingrese el numero de habitacion que quiere hacer el checkin");
            int roomNumAux = scanner.nextInt();
            Reservation reservation = reservationList.stream().filter(reservation1 -> reservation1.getRoom().getNumber() == roomNumAux).findFirst().orElse(null);
            if (reservation != null) {
                Room roomAux = searchRoomByNumber(rooms, roomNumAux);
                Reservation reservationAux = searchReserve(pax, roomAux, reservations);
                pax.getTickets().add(new Ticket(pax.getName(), pax.getSurname(), "Alojamiento", (roomAux.getBedType().getPrice() + roomAux.getExtraPrice()) * reservationAux.getCantDays()));
                pax.setIngress(true);
                roomAux.setCondition(Condition.OCUPPED);
                System.out.println("\033[33m"+"Checkin exitoso");
            } else {
                System.out.println(pax.getName() + " no tiene reserva para esta habitacion");
            }

        } else {
            System.out.println(pax.getName() + " no tiene reservas hechas para este dia");
        }
    }

    @Override
    public boolean checkOut(List<Pax> paxes, List<Room> rooms, List<Reservation> reservations) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el dni del pasajero: ");
        String dni = scanner.next();
        Pax pax = paxes.stream().filter(pax1 -> pax1.getDni().equals(dni)).findFirst().orElse(null);
        if (pax == null) {
            System.out.println("\033[31m" + "\nEl dni ingresado no esta registrado en el sistema.");
        } else {
            System.out.print("Ingrese el numero de habitacion: ");
            Room room = searchRoomByNumber(rooms, scanner.nextInt());
            Reservation srchReserve = reservations.stream().filter(reservation -> reservation.getRoom().equals(room)).filter(reservation -> reservation.getPaxDni().equals(pax.getDni())).findFirst().orElse(null);
            if (srchReserve != null && srchReserve.getRoom().getCondition().equals(Condition.OCUPPED)) {
                if ((pax.getTickets().stream().mapToDouble(Ticket::getTotal).sum()) == 0) {
                    pax.setIngress(false);
                    room.setCondition(Condition.UNCLEAN_AVAILABLE);
                    return true;
                } else {
                    System.out.println("\033[31m" + "\nEl pasajero tiene cuentas inpagas.");
                }
            }
        }
        return false;
    }

    @Override
    public LocalDate ingressDate(Scanner scan, LocalDate today) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = scan.next();
        LocalDate localDate = null;
        int exit = 0;
        while (exit == 0) {
            try {
                localDate = LocalDate.parse(date, formatter);
                if (!formatter.format(localDate).equals(date) || localDate.compareTo(today) < 0) {
                    System.out.println("\033[31m" +"\nFecha invalida. Ingrese una nueva fecha");
                    date = scan.next();
                    localDate = LocalDate.parse(date, formatter);
                } else {
                    exit++;
                }
            } catch (DateTimeParseException e) {
                System.out.println("\033[31m" +"\nFormato de fecha invalido. Ingrese la fecha nuevamente");
                date = scan.next();
            }
        }
        return localDate;
    }

    public void changeRoomState(List<Room> rooms, Scanner scan) {
        System.out.println("Elija un numero de habitacion");
        Room room = this.searchRoomByNumber(rooms, scan.nextInt());
        if (room != null) {
            System.out.println(room);
            System.out.println("Elija el nuevo estado de la habitacion");
            for (Condition condition : Condition.values()) {
                System.out.println(condition.ordinal() + 1 + "- " + condition.getState());
            }
            String s = Condition.searchByOrdinal(scan.nextInt());
            if (!s.equals("")) {
                room.setCondition(Condition.valueOf(s));
            } else {
                System.out.println("\033[31m" +"\nCondicion no encontrada");
            }
        } else {
            System.out.println("\033[31m" +"\nHabitacion no encontrada");
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
