package users;

import Interface.Ingress;
import Interface.Reserve;
import app.Hotel;
import enumn.Condition;
import model.Reservation;
import model.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Recepcionist extends User implements Reserve, Ingress {

    //------ Constructors ------//
    public Recepcionist() {

    }

    public Recepcionist(String nickName, String password) {
        super(nickName, password);
    }

    //------ Methods ------//
    @Override
    public void userMenu(Scanner scan,Hotel hotel) {
        int opt;
        int back = 0;
        System.out.println("Bienvenido, "+nickName);
        while (back == 0){
            System.out.println("\nIngrese el numero de la opcion a la que quiere entrar:\n[1]- Check in\n[2]-Check out\n[3]- Agregar Nueva Reserva\n[4]- Revisar Reservas\n[5]- Revisar Habitaciones\n[6]- Buscar Pasajero\n[0]- Log out");
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
<<<<<<< HEAD

=======
>>>>>>> e302e61d67ca2a7b27beb054dba3fa3a6fda487c
                    System.out.print("Ingrese el dni del pasajero que busca: ");
                    String dni = scan.next();
                    Pax srchPax = hotel.searchHistoryPax(dni);
                    srchPax.userMenu(scan,hotel);
                    break;
                case 0:
                    back++;
                    break;
                default:
                    System.out.println("opcion incorrecta");
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "Recepcionist: "+nickName;
    }


    @Override
    public Reservation makeReserve(Hotel hotel,Scanner scan) {
        System.out.print("Ingrese el dni del pasajero: ");
        String dni = scan.next();
        Pax pax = hotel.searchHistoryPax(dni);
        if (pax == null){
            System.out.println("El pasajero no esta dentro del historial del hotel.\n\nPor favor ingrese sus datos: \n\n-------------------------------------\n\n");
            pax = newPax();
        }
        System.out.print("\nFecha de ingreso(DD/MM/AAAA):");
        LocalDate checkIn = ingressDate(scan,LocalDate.now());
        System.out.print("Cantidad de noches que se queda: ");
        int cantDays=scan.nextInt();
        LocalDate checkOut = checkIn.plusDays(cantDays);
        System.out.print("\ningrese el numero de habitación disponible: ");
        System.out.println("--------------------------------------");
        hotel.showDisponibledRooms(checkIn,checkOut);
        System.out.println("--------------------------------------");
        int roomNumber = scan.nextInt();
        Room roomAux= hotel.getRooms().stream().filter(room -> room.getNumber()==roomNumber).findFirst().orElse(null);
        Reservation reservation = new Reservation(pax,roomAux,checkIn,checkOut,cantDays);
        pax.setReserve(reservation);
        assert roomAux != null;
        roomAux.reservations.add(reservation);
        hotel.addNewReserve(reservation);
        return reservation;
    }


    @Override
    public Pax newPax() {
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
            if (room.getCondition() == Condition.AVAILABLE || room.getCondition() == Condition.UNCLEAN_AVAILABLE){
                System.out.println(room.toString());
            }
        }
    }

    @Override
    public void checkIn(List<Pax> paxes, Room room, Hotel hotel) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Ingrese un DNI o Pasaporte: ");
        String dniAux=scanner.nextLine();
        Pax pax = paxes.stream().filter(pax1 -> pax1.getDni().equals(dniAux)).findFirst().orElse(null);
        if(pax==null){
            System.out.println("Pasajero no encontrado. Ingrese sus datos para continuar\n\n");
            pax=newPax();
        }
        paxes.add(pax);
        Reservation auxReserve = hotel.searchReserve(pax,room);
        pax.setIngress(true);
        pax.setReserve(auxReserve);
        hotel.addHistoryPax(pax);
        hotel.eliminateReserve(auxReserve);
        room.setCondition(Condition.OCUPPED);

    }

    @Override
    public void checkOut(Pax pax, Room room) {
        pax.setIngress(false);
        pax.setReserve(null);
        room.setCondition(Condition.UNCLEAN_AVAILABLE);
    }

    @Override
    public LocalDate ingressDate(Scanner scan, LocalDate today) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = scan.next();
        LocalDate localDate = LocalDate.parse(date,formatter);
        int exit=0;
        while (exit==0){
            if(!formatter.format(localDate).equals(date) || localDate.compareTo(today)<0){
                System.out.println("\nFecha invalida. Ingrese una nueva fecha");
                date = scan.next();
                localDate = LocalDate.parse(date,formatter);
            }else {
                exit++;
            }
        }
        return localDate;
    }
}
