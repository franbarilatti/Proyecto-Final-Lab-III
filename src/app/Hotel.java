package app;

import enumn.MiniBar;
import model.Reservation;
import model.Room;
import model.Ticket;
import repositories.RepositoryController;
import users.Admin;
import users.Pax;
import users.Recepcionist;
import users.User;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Hotel {
    //------ Attributes ------//
    private List<User> users;
    private List<Room> rooms;
    private List<Reservation> reserves;
    private List<Pax> paxes;
    private static final Scanner scan = new Scanner(System.in);
    private final RepositoryController<User> userRepository = new RepositoryController<>();
    private final RepositoryController<Pax> paxRepository = new RepositoryController<>();
    private final RepositoryController<Room> roomRepository = new RepositoryController<>();
    private final RepositoryController<Reservation> reserveRepository = new RepositoryController<>();
    private static final String userFile = "userFile.json";
    private static final String paxFile = "paxFile.json";
    private static final String roomFile = "roomFile.json";
    private static final String reserveFile = "reserveFile.json";


    //------ Constructors ------//
    public Hotel() {
        users = new ArrayList<>();
        rooms = new ArrayList<>();
        reserves = new ArrayList<>();
        paxes = new ArrayList<>();
    }

    //------ Getters ------//
    public List<User> getUsers() {
        return users;
    }

    public List<Pax> getPaxes() {
        return paxes;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Reservation> getReserves() {
        return reserves;
    }

    public void addHistoryPax(Pax pax) {
        paxes.add(pax);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //------ Setters ------//

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setReserves(List<Reservation> reserves) {
        this.reserves = reserves;
    }

    public void setPaxes(List<Pax> paxes) {
        this.paxes = paxes;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //------ Show Methods ------//
    public void spaces() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public void showHistoryPax() throws Exception {
        if (paxes != null)
            paxes.forEach(System.out::println);
        else
            throw new Exception("No hay pasajeros historicos en el sistema");
    }

    public void showTodayReserves() throws Exception {
        if (reserves != null)
            reserves.stream().
                    filter((Reservation r) -> r.getCheckIn().equals(LocalDate.now())).
                    forEach(System.out::println);
        else
            throw new Exception("No hay reservas cargadas en el sistema");
    }

    public void showDisponibledRooms(LocalDate ingress, LocalDate exit) {
        if (rooms == null) {
            System.out.println("No hay habitaciones disponibles");
        } else {
            rooms.stream().filter(room -> !room.isOcuped(reserves, ingress, exit)).forEach(System.out::println);
        }
    }

    public void showAllReserves() throws Exception {
        if (reserves != null)
            reserves.forEach(System.out::println);
        else
            throw new Exception("No hay reservas cargadas en el sistema");
    }

    public void showPaxReserves(Pax pax) {
        reserves.stream().filter((Reservation r) -> r.getPaxDni().equals(pax.getDni())).collect(Collectors.toList()).forEach(System.out::println);
    }

    public void showPaxTickets(Pax pax) {
        pax.getTickets().forEach(System.out::println);
    }

    public void showRooms() {
        if (rooms == null) {
            System.out.println("No hay habitaciones disponibles");
        } else {
            rooms.forEach(System.out::println);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //------ Search Methods ------//

    public Pax searchHistoryPax() {
        System.out.print("Ingrese el dni del pasajero que busca: ");
        String dni = scan.next();
        return this.getPaxes().stream().
                filter(pax -> pax.getDni().equals(dni))
                .findFirst()
                .orElse(null);
    }

    public Reservation searchPaxReserve() {
        Pax srchPax = searchHistoryPax();
        if(srchPax != null){
            System.out.println("Ingrese el numero de habitación: ");
            Room srchRoom = searchRoomByNumber(scan.nextInt());
            if(srchRoom != null){
                return reserves.stream().
                        filter((Reservation r) -> r.getRoom().equals(srchRoom)).
                        filter(r -> r.getPaxDni().equals(srchPax.getDni())).
                        findFirst().orElse(null);
            }else {
                System.out.println("Habitación no encontrada.");
            }
        }else {
            System.out.println("Pasajero no encontrado.");
        }
        return null;
    }

    public User searchUserByNickName(String nickname) {
        return users.stream().
                filter(user -> user.getNickName().equals(nickname)).
                findFirst().
                orElse(null);
    }

    public Room searchRoomByNumber(int roomNumber) {
        return rooms.stream().
                filter(room -> room.getNumber() == roomNumber).
                findFirst().
                orElse(null);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //------ Functional Methods ------//

    public void runHotel() throws ClassCastException {
        try{
            this.setUsers(userRepository.throwList(userFile));
            this.setRooms(roomRepository.throwList(roomFile));
            this.setPaxes(paxRepository.throwList(paxFile));
            this.setReserves(reserveRepository.throwList(reserveFile));
            System.out.println(this.getUsers().get(2).toString());
            firstMenu();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }


    }

    public void saveHotel() {
        userRepository.addList(userFile, this.users);
        roomRepository.addList(roomFile, this.rooms);
        reserveRepository.addList(reserveFile, this.reserves);
        paxRepository.addList(paxFile, this.paxes);
    }

    public void eliminateReserve(Reservation reserve) {
        reserves.remove(reserve);
    }


    public void logIn() {
        System.out.print("Ingrese su Nickname: ");
        String name = scan.next();
        User userAux = null;
        for (User user : this.getUsers()) {
            if (user.getNickName().equals(name)) {
                userAux = user;
                break;
            }
        }
        if (userAux != null) {
            System.out.print("Ingrese su contraseña: ");
            if (userAux.getPassword().equals(scan.next())) {
                userMenues(userAux);
            } else {
                System.out.println("Contraseña incorrecta");
            }
        } else {
            System.out.println("El usuario no se encuentra registrado");
        }
    }

    public Pax registerPax() {
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

    public void registerAdmin() {
        String nickName;
        User auxUser;
        Admin admin = new Admin();
        System.out.print("Ingrese su Nickname: ");
        nickName = scan.next();
        auxUser = searchUserByNickName(nickName);
        if (auxUser == null) {
            admin.setNickName(nickName);
            System.out.print("Ingrese una contraseña: ");
            admin.setPassword(scan.next());
            admin.setJobTitle("Administrador");
            users.add(admin);
            System.out.println("\nUsuario creado con exito");
        }

    }

    public void registerRecepcionist() {
        String nickName;
        User auxUser;
        Recepcionist recepcionist = new Recepcionist();
        System.out.print("Ingrese su Nickname: ");
        nickName = scan.next();
        auxUser = searchUserByNickName(nickName);
        if (auxUser == null) {
            recepcionist.setNickName(nickName);
            System.out.print("Ingrese una contraseña: ");
            recepcionist.setPassword(scan.next());
            recepcionist.setJobTitle("Recepcionista");
            users.add(recepcionist);
            System.out.println("\nUsuario creado con exito");
        }
    }

    public void chargue(Pax pax) {
        System.out.println("El total de su recibo en de: $" + pax.getTickets().stream().mapToDouble(Ticket::getTotal).sum());
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //------ Menu Methods ------//

    public void firstMenu() {
        spaces();
        System.out.println("========== BIENVENIDO A NUESTRO HOTEL ==========");
        int back = 0;
        while (back == 0) {
            System.out.println(
                    """
                            [1]- Ingresar
                            [2]- Registrarse
                            [0]- Salir""");
            System.out.print("Ingrese una opción: ");
            int opt = scan.nextInt();
            switch (opt) {
                case 1 -> logIn();
                case 2 -> {
                    registerMenu();
                    saveHotel();
                }
                case 0 -> back++;
                default -> System.out.println("Opcion incorrecta");
            }
        }
        saveHotel();
    }

    public void paxMenu(){
        spaces();
        int opt;
        int exit = 0;
        while (exit == 0) {
            System.out.println("\n\n[1]- Lista de pasajeros\n[2]- Nuevo Pasajero\n[3]- Buscar Pasajero\n[0]- Salir\n\nElija una opcion: ");
            opt = scan.nextInt();
            switch (opt) {
                case 1 -> {
                    try {
                        showHistoryPax();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    Pax newPax = registerPax();
                    addHistoryPax(newPax);
                }
                case 3 ->{
                    Pax srchPax = searchHistoryPax();
                    if (srchPax != null) {
                        paxMenuBis(srchPax);

                    } else {
                        System.out.println("Pasajero no encnontrado");
                    }
                }
                case 0 -> exit++;
                default -> System.out.println("opcion incorrecta");
            }
        }
    }

    public void paxMenuBis(Pax pax) {
        spaces();
        System.out.println("========== " + pax.getName() + " " + pax.getSurname() + " ==========");
        int back = 0;
        while (back == 0) {
            System.out.println(
                    """
                            [1]- Ver Reservas
                            [2]- Añadir consumo
                            [3]- Ver consumos
                            [4]- Cobrar
                            [0]- Salir""");
            System.out.print("Ingrese una opción: ");
            int opt = scan.nextInt();
            switch (opt) {
                case 1 -> showPaxReserves(pax);
                case 2 -> roomServiceMenu(scan, pax);
                case 3 -> showPaxTickets(pax);
                case 4 -> chargue(pax);
                case 0 -> back++;
                default -> System.out.println("Opcion incorrecta");
            }
        }

    }

    public void userMenues(User user) {

        if (user.getJobTitle().equals("Administrador")) {
            adminMenu(user);
        } else {
            recepcionistMenu(user);
        }
    }

    public void recepcionistMenu(User user) {
        spaces();
        Recepcionist recepcionist = new Recepcionist(user.getNickName(), user.getPassword(), user.getJobTitle());
        int opt;
        int back = 0;
        System.out.println("Bienvenido, " + recepcionist.getNickName());
        while (back == 0) {
            System.out.println(
                    """
                            [1]- Check in
                            [2]- Check out
                            [3]- Agregar Nueva Reserva
                            [4]- Reservas
                            [5]- Habitaciones
                            [6]- Pasajeros
                            [0]- Salir""");
            System.out.print("\n\nIngrese el numero de la opcion a la que quiere entrar: ");
            opt = scan.nextInt();
            switch (opt) {
                case 1:
                    recepcionist.checkIn(paxes, rooms, reserves);
                    break;
                case 2:
                    if (recepcionist.checkOut(paxes, rooms)) {
                        System.out.println("Check out exitoso.");
                    } else {
                        System.out.println("El pasajero todavia tiene cargos en su cuenta.");
                    }
                    break;
                case 3:
                    recepcionist.makeReserve(reserves, paxes, rooms, scan);
                    break;
                case 4:
                    reserveMenu();
                    break;
                case 5:
                    roomMenu();
                    break;
                case 6:
                    paxMenu();
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

    public void adminMenu(User user) {
        spaces();
        Admin admin = new Admin(user.getNickName(), user.getPassword(), user.getJobTitle());
        int opt;
        int back = 0;
        System.out.println("Bienvenido, " + admin.getNickName());
        while (back == 0) {
            System.out.println(
                    """
                            [1]- Añadir nuevo usuario
                            [2]- Check in
                            [3]- Check out
                            [4]- Añadir nueva reserva
                            [5]- Reservas
                            [6]- Habitaciones
                            [7]- Pasajeros
                            [8]- Cambiar estado de habitacion
                            [0]- Log out""");
            System.out.print("\n\nIngrese el numero de la opcion a la que quiere entrar: ");
            opt = scan.nextInt();
            switch (opt) {
                case 1:
                    registerMenu();
                    break;
                case 2:
                    admin.checkIn(paxes, rooms, reserves);
                    break;
                case 3:
                    if (admin.checkOut(paxes, rooms)) {
                        System.out.println("Check out exitoso.");
                    } else {
                        System.out.println("El pasajero todavia tiene cargos en su cuenta.");
                    }
                    break;
                case 4:
                    admin.makeReserve(reserves, paxes, rooms, scan);
                    break;
                case 5:
                    reserveMenu();
                    break;
                case 6:
                    roomMenu();
                    break;
                case 7:

                    break;
                case 8:
                    admin.changeRoomState(this.getRooms(), scan);
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

    public void roomServiceMenu(Scanner scan, Pax pax) {
        spaces();
        int op;
        int exit = 0;
        double total = 0;
        String detail = "";
        while (exit == 0) {
            System.out.println("Elija un producto, 0 para cancelar");
            for (MiniBar m : MiniBar.values()) {
                System.out.println(m.ordinal() + 1 + " " + m.getProduct() + ": $" + m.getPrice());
            }
            op = scan.nextInt();
            switch (op) {
                case 1 -> {
                    total = MiniBar.COCA_COLA.getPrice();
                    detail = MiniBar.COCA_COLA.getProduct();
                }
                case 2 -> {
                    total = MiniBar.SPRITE.getPrice();
                    detail = MiniBar.SPRITE.getProduct();
                }
                case 3 -> {
                    total = MiniBar.VINO_TINTO.getPrice();
                    detail = MiniBar.VINO_TINTO.getProduct();
                }
                case 4 -> {
                    total = MiniBar.VINO_BLANCO.getPrice();
                    detail = MiniBar.VINO_BLANCO.getProduct();
                }
                case 5 -> {
                    total = MiniBar.PAPAS_LAYS.getPrice();
                    detail = MiniBar.PAPAS_LAYS.getProduct();
                }
                case 6 -> {
                    total = MiniBar.TABLETA_CHOCOLATE.getPrice();
                    detail = MiniBar.TABLETA_CHOCOLATE.getProduct();
                }
                case 7 -> {
                    total = MiniBar.BOLSA_MANI.getPrice();
                    detail = MiniBar.BOLSA_MANI.getProduct();
                }
                case 0 -> exit++;
                default -> System.out.println("Opcion invalida, por favor elija una nueva");
            }
            pax.getTickets().add(new Ticket(pax.getName(), pax.getSurname(), detail, total));
        }
    }

    public void registerMenu() {
        spaces();
        int opt;
        int exit = 0;
        while (exit == 0) {
            System.out.println("\n\n[1]- Administrador\n[2]- Recepcionista\n[0]- Salir\n\nElija una opcion: ");
            opt = scan.nextInt();
            switch (opt) {
                case 1 -> registerAdmin();
                case 2 -> registerRecepcionist();
                case 0 -> exit++;
                default -> System.out.println("opcion incorrecta");
            }
        }

    }

    public void reserveMenu() {
        spaces();
        int opt;
        int exit = 0;
        while (exit == 0) {
            System.out.println("\n\n[1]- Todas las reservas\n[2]- Reservas del dia\n[3]- Reservas por pasajero\n[4]- Buscar Reserva\n[5]- Eliminar Reserva\n[0]- Salir\n\nElija una opcion: ");
            opt = scan.nextInt();
            switch (opt) {
                case 1 -> {
                    try {
                        showAllReserves();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    try {
                        showTodayReserves();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case 3 -> {
                    Pax srchPax = searchHistoryPax();
                    showPaxReserves(srchPax);
                }
                case 4 -> System.out.println(searchPaxReserve());
                case 5 -> {
                    Reservation reservation = searchPaxReserve();
                    eliminateReserve(reservation);
                }
                case 0 -> exit++;
                default -> System.out.println("opcion incorrecta.");
            }
        }
    }

    public void roomMenu() {
        spaces();
        int opt;
        int exit = 0;
        while (exit == 0) {
            System.out.println("\n\n[1]-Ver todas las Habitaciones\n[2]- Buscar Habitacion\n[3]- Habitaciones disponibles\n[4]-Habitaciones con Reserva\n[0]- Salir\n\nElija una opcion: ");
            opt = scan.nextInt();
            switch (opt) {
                case 1 -> showRooms();
                case 2 -> {
                    System.out.print("Ingrese el numero de habitación que busca: ");
                    Room srchRoom = searchRoomByNumber(scan.nextInt());
                    System.out.println(srchRoom);
                }
                case 3 -> {
                    System.out.print("Ingrese la fecha que busca: ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String date = scan.next();
                    LocalDate ingress = LocalDate.parse(date, formatter);
                    System.out.print("ingrese la cantidad de dias de disponibilidad: ");
                    LocalDate out = ingress.plusDays(scan.nextInt());
                    showDisponibledRooms(ingress, out);
                }
                case 0 -> exit++;
                default -> System.out.println("opcion incorrecta");
            }
        }
    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
