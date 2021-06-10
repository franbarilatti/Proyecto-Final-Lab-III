package app;

import enumn.BedType;
import enumn.Condition;
import enumn.MiniBar;
import enumn.TvType;
import model.*;
import repositories.RepositoryController;
import users.Admin;
import users.Pax;
import users.Recepcionist;
import users.User;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
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
        System.out.println("\n\n\n\n\n\n\n");
    }

    public void showHistoryPax() throws Exception {
        if (paxes != null)
            getPaxes().forEach(System.out::println);
        else
            throw new Exception("No hay pasajeros historicos en el sistema");
    }

    public void showTodayReserves() throws Exception {
        if (getReserves() != null)
            getReserves().stream().
                    filter((Reservation r) -> r.getCheckIn().equals(LocalDate.now())).
                    forEach(System.out::println);
        else
            throw new Exception("No hay reservas cargadas en el sistema");
    }

    public void showDisponibledRooms(LocalDate ingress, LocalDate exit) {
        if (getRooms().isEmpty()) {
            System.out.println("No hay habitaciones disponibles");
        } else {
            getRooms().stream().filter(room -> !room.isOcuped(reserves, ingress, exit)).forEach(System.out::println);
        }
    }

    public void showAllReserves() {
        if (!getReserves().isEmpty()) {
            getReserves().forEach(System.out::println);
        } else {
            System.out.println("No hay reservas cargadas en el sistema");
        }
    }

    public void showPaxReserves(Pax pax) {
        if (!getReserves().isEmpty()) {
            getReserves().stream().filter((Reservation r) -> r.getPaxDni().equals(pax.getDni())).collect(Collectors.toList()).forEach(System.out::println);
        } else {
            System.out.println("No hay reservas cargadas en el sistema");
        }
    }

    public void showPaxTickets(Pax pax) {
        if (!pax.getTickets().isEmpty()) {
            pax.getTickets().forEach(System.out::println);
        } else {
            System.out.println("El pasajero no tiene tickets cargados");
        }
    }

    public void showRooms() {
        if (getRooms() == null) {
            System.out.println("No hay habitaciones disponibles");
        } else {
            getRooms().stream().sorted(Comparator.comparingInt(Room::getNumber)).forEach(System.out::println);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //------ Search Methods ------//

    public Pax searchHistoryPax() {
        System.out.print("Ingrese el DNI del pasajero que busca: ");
        String dni = scan.next();
        return this.getPaxes().stream().
                filter(pax -> pax.getDni().equals(dni))
                .findFirst()
                .orElse(null);
    }

    public Reservation searchPaxReserve() {
        Pax srchPax = searchHistoryPax();
        if (srchPax != null) {
            System.out.println("Ingrese el numero de habitación: ");
            Room srchRoom = searchRoomByNumber(scan.nextInt());
            if (srchRoom != null) {
                return getReserves().stream().
                        filter((Reservation r) -> r.getRoom().equals(srchRoom)).
                        filter(r -> r.getPaxDni().equals(srchPax.getDni())).
                        findFirst().orElse(null);
            } else {
                System.out.println("Habitación no encontrada.");
            }
        } else {
            System.out.println("Pasajero no encontrado.");
        }
        return null;
    }

    public User searchUserByNickName(String nickname) {
        return getUsers().stream().
                filter(user -> user.getNickName().equals(nickname)).
                findFirst().
                orElse(null);
    }

    public Room searchRoomByNumber(int roomNumber) {
        return getRooms().stream().
                filter(room -> room.getNumber() == roomNumber).
                findFirst().
                orElse(null);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //------ Functional Methods ------//

    public void runHotel() throws ClassCastException {
        try {
            this.setUsers(userRepository.throwList(userFile));
            this.setRooms(roomRepository.throwList(roomFile));
            this.setPaxes(paxRepository.throwList(paxFile));
            this.setReserves(reserveRepository.throwList(reserveFile));
            firstMenu();
        } catch (FileNotFoundException e) {
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

    private void addNewRoom() {

        System.out.print("\nIngrese el numero de la habitación: ");
        int number = scan.nextInt();
        Room auxRoom = searchRoomByNumber(number);
        if (auxRoom == null) {
            Room newRoom = new Room();
            System.out.println("ingrese el tipo de Habitacion: \n[1]- Superior\n[2]- Estandar\n");
            switch (scan.nextInt()) {
                case 1 -> newRoom = new Superior();
                case 2 -> newRoom = new Standar();
            }
            System.out.println("ingrese el tipo de cama: \n[1]- Matrimonial\n[2]- Doble Twin\n[3]- Triple\n[4]- Cuadruple\n");
            switch (scan.nextInt()) {
                case 1 -> newRoom.setBedType(BedType.MATRIMONIAL);
                case 2 -> newRoom.setBedType(BedType.DOBLE_TWIN);
                case 3 -> newRoom.setBedType(BedType.TRIPLE);
                case 4 -> newRoom.setBedType(BedType.CUADRUPLE);
                default -> System.out.println("Opcion Incorrecta. ");
            }
            System.out.println("ingrese el tipo de TV: \n[1]- TV de Tubo\n[2]- TV 32'\n[3]- TV 42'\n");
            switch (scan.nextInt()) {
                case 1 -> newRoom.setTvType(TvType.TV_TUBO);
                case 2 -> newRoom.setTvType(TvType.TV_LED_32);
                case 3 -> newRoom.setTvType(TvType.TV_LED_42);
                default -> System.out.println("Opcion Incorrecta. ");
            }
            newRoom.setCondition(Condition.AVAILABLE);
            newRoom.setNumber(number);
            getRooms().add(newRoom);
        } else {
            System.out.println("La habistacion con este numero ya esta registrada. ");
        }

    }

    private void eliminateRoom() {
        System.out.print("Ingrese el numero de habitacion: ");
        int number = scan.nextInt();
        Room eliminatedRoom = getRooms().stream().filter(room -> room.getNumber() == number).findFirst().orElse(null);
        if (eliminatedRoom != null) {
            System.out.println(eliminatedRoom);
            System.out.println("Esta seguro que desea eliminar esta habitacion?\n[1]- Si\n[2]- No\n");
            switch (scan.nextInt()) {
                case 1 -> {
                    getRooms().remove(eliminatedRoom);
                    System.out.println("Habitacion Eliminada");
                }
                case 2 -> System.out.println("Gracias por salvar esta pequeña habitacion :)");
                default -> System.out.println("Opcion incorrecta.");
            }
        } else {
            System.out.println("Habitacion no encontrada.");
        }

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
        System.out.print("Nombre: ");
        pax.setName(scan.nextLine());
        System.out.print("Apellido: ");
        pax.setSurname(scan.nextLine());
        System.out.print("Direccion: ");
        pax.setAddress(scan.nextLine());
        System.out.print("DNI o Pasaporte: ");
        pax.setDni(scan.nextLine());
        System.out.print("Nacionalidad: ");
        pax.setNationality(scan.nextLine());
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
            getUsers().add(admin);
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
        System.out.println("\033[33m"+"========== BIENVENIDO A HOTEL TRANSILVANIA ==========");
        int back = 0;
        while (back == 0) {
            System.out.println("""
                    \033[32m
                    [1]- Ingresar
                    [2]- Registrarse
                    [0]- Salir""");
            System.out.print("Ingrese una opción: ");
            switch (scan.nextInt()) {
                case 1 -> logIn();
                case 2 -> {
                    registerMenu();
                    saveHotel();
                }
                case 0 -> back++;
                default -> System.out.println("\033[31m"+"Opcion incorrecta");
            }
        }
        saveHotel();
    }

    public void paxMenu() {
        spaces();
        System.out.println("\033[36m"+"========== MENU DE PASAJEROS ==========");
        int exit = 0;
        while (exit == 0) {
            System.out.println("""
                    \033[32m
                    [1]- Lista de pasajeros
                    [2]- Nuevo Pasajero
                    [3]- Buscar Pasajero
                    [0]- Salir

                    Elija una opcion:\s""");

            switch (scan.nextInt()) {
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
                case 3 -> {
                    Pax srchPax = searchHistoryPax();
                    if (srchPax != null) {
                        paxMenuBis(srchPax);

                    } else {
                        System.out.println("\033[31m"+"Pasajero no encnontrado");
                    }
                }
                case 0 -> exit++;
                default -> System.out.println("\033[31m"+"opcion incorrecta");
            }
        }
    }

    public void paxMenuBis(Pax pax) {
        spaces();
        System.out.println("\033[36m"+"========== MENU DEL PASAJERO " + pax.getName() + " " + pax.getSurname() + " ==========");
        int back = 0;
        while (back == 0) {
            System.out.println("""
                    \033[32
                    [1]- Ver Reservas
                    [2]- Añadir consumo
                    [3]- Ver consumos
                    [4]- Cobrar
                    [0]- Salir""");
            System.out.print("Ingrese una opción: ");
            switch (scan.nextInt()) {
                case 1 -> showPaxReserves(pax);
                case 2 -> roomServiceMenu(scan, pax);
                case 3 -> showPaxTickets(pax);
                case 4 -> chargue(pax);
                case 0 -> back++;
                default -> System.out.println("\033[31m"+"Opcion incorrecta");
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
        System.out.println("\033[36m"+"========== MENU DE RECEPCIONISTAS ==========");

        Recepcionist recepcionist = new Recepcionist(user.getNickName(), user.getPassword(), user.getJobTitle());
        int back = 0;
        System.out.println("Bienvenido, " + recepcionist.getNickName());
        while (back == 0) {
            System.out.println(
                    """
                            \033[32m
                            [1]- Check in
                            [2]- Check out
                            [3]- Agregar Nueva Reserva
                            [4]- Reservas
                            [5]- Habitaciones
                            [6]- Pasajeros
                            [0]- Salir""");
            System.out.print("\n\nIngrese el numero de la opcion a la que quiere entrar: ");
            switch (scan.nextInt()) {
                case 1:
                    recepcionist.checkIn(paxes, rooms, reserves);
                    break;
                case 2:
                    if (recepcionist.checkOut(paxes, rooms, reserves)) {
                        System.out.println("\033[34m"+"Check out exitoso.");
                    } else {
                        System.out.println("\033[31m"+"El pasajero todavia tiene cargos en su cuenta.");
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
                    System.out.println("\033[31m"+"Opción incorrecta");
                    break;
            }
        }
    }

    public void adminMenu(User user) {
        spaces();
        System.out.println("""
                \033[36m
                ========== MENU DE ADMINISTRADORES ==========
                """);
        Admin admin = new Admin(user.getNickName(), user.getPassword(), user.getJobTitle());
        int back = 0;
        System.out.println("Bienvenido, " + admin.getNickName());
        while (back == 0) {
            System.out.println(
                    """
                            \033[32m
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
            switch (scan.nextInt()) {
                case 1:
                    registerMenu();
                    break;
                case 2:
                    admin.checkIn(paxes, rooms, reserves);
                    break;
                case 3:
                    if (admin.checkOut(paxes, rooms, reserves)) {
                        System.out.println("\033[33m"+"Check out exitoso.");
                    } else {
                        System.out.println("\033[31m"+"El pasajero todavia tiene cargos en su cuenta.");
                    }
                    break;
                case 4:
                    admin.makeReserve(reserves, paxes, rooms, scan);
                    break;
                case 5:
                    reserveMenu();
                    break;
                case 6:
                    roomMenuAdmin();
                    break;
                case 7:
                    paxMenu();
                    break;
                case 8:
                    admin.changeRoomState(this.getRooms(), scan);
                    break;
                case 0:
                    back++;
                    break;
                default:
                    System.out.println("\033[31m"+"opción incorrecta");
                    break;
            }
        }
    }

    public void roomServiceMenu(Scanner scan, Pax pax) {
        spaces();
        System.out.println("""
                \033[36m
                ========== ROOM SERVICE MENU ==========
                """);
        int exit = 0;
        double total = 0;
        String detail = "";
        while (exit == 0) {
            System.out.println("Elija un producto, 0 para cancelar");
            for (MiniBar m : MiniBar.values()) {
                System.out.println(m.ordinal() + 1 + " " + m.getProduct() + ": $" + m.getPrice());
            }
            switch (scan.nextInt()) {
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
        System.out.println("""
                \033[36m
                ========== MENU DE REGISTRO ==========
                """);
        int exit = 0;
        while (exit == 0) {
            System.out.println("""
                    \033[32m
                    [1]- Administrador
                    [2]- Recepcionista
                    [0]- Salir

                    Elija una opción:\s""");
            switch (scan.nextInt()) {
                case 1 -> {
                    registerAdmin();
                    exit++;
                }
                case 2 -> {
                    registerRecepcionist();
                    exit++;
                }
                case 0 -> exit++;
                default -> System.out.println("opción incorrecta");
            }
        }
    }

    public void reserveMenu() {
        spaces();
        System.out.println("""
                \033[36m
                ========== MENU DE RESERVAS ==========
                """);
        int exit = 0;
        while (exit == 0) {
            System.out.println("""
                    \033[32m
                    [1]- Todas las reservas
                    [2]- Reservas del dia
                    [3]- Reservas por pasajero
                    [4]- Buscar Reserva
                    [5]- Eliminar Reserva
                    [0]- Salir

                    Elija una opción:\s""");
            switch (scan.nextInt()) {
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
                    if (srchPax!=null)
                    showPaxReserves(srchPax);
                    else
                        System.out.println("El pasajero ingresado no esta cargado en el sistema");
                }
                case 4 -> System.out.println(searchPaxReserve());
                case 5 -> {
                    Reservation reservation = searchPaxReserve();
                    if (reservation != null)
                        eliminateReserve(reservation);
                    else
                        System.out.println("El pasajero no tiene una reserva para esta habitacion");
                }
                case 0 -> exit++;
                default -> System.out.println("opción incorrecta.");
            }
        }
    }

    public void roomMenu() {
        spaces();
        System.out.println("""
                \033[36m
                ========== MENU DE HABITACIONES ==========\s
                """);
        int exit = 0;
        while (exit == 0) {
            System.out.println("""
                    \033[32m
                    [1]- Ver todas las Habitaciones
                    [2]- Buscar Habitacion
                    [3]- Habitaciones disponibles
                    [4]- Habitaciones con Reserva
                    [0]- Salir

                    Elija una opción:\s""");
            switch (scan.nextInt()) {
                case 1 -> showRooms();
                case 2 -> {
                    System.out.print("Ingrese el numero de habitación que busca: ");
                    Room srchRoom = searchRoomByNumber(scan.nextInt());
                    if (srchRoom != null)
                        System.out.println(srchRoom);
                    else
                        System.out.println("Habitacion no encontrada");
                }
                case 3 -> {
                    System.out.print("Ingrese la fecha que busca: ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String date = scan.next();
                    LocalDate ingress = LocalDate.parse(date, formatter);
                    System.out.print("ingrese la cantidad de días de disponibilidad: ");
                    LocalDate out = ingress.plusDays(scan.nextInt());
                    showDisponibledRooms(ingress, out);
                }
                case 4 -> {
                    List<Room> listAux = getRooms().stream().filter(room -> room.getCondition().equals(Condition.RESERVED)).collect(Collectors.toList());
                    if (!listAux.isEmpty())
                        System.out.println(listAux);
                    else System.out.println("No hay habitaciones con reservas");
                }
                case 0 -> exit++;
                default -> System.out.println("opción incorrecta");
            }
        }
    }

    public void roomMenuAdmin() {
        spaces();
        System.out.println("""
                \033[36m
                ========== MENU DE HABITACIONES ==========\s
                """);
        int exit = 0;
        while (exit == 0) {
            System.out.println("""
                    \033[32m
                    [1]-Ver todas las Habitaciones
                    [2]- Buscar Habitacion
                    [3]- Habitaciones disponibles
                    [4]- Habitaciones con Reserva
                    [5]- Agregar habitacion
                    [6]- Eliminar Habitacion
                    [0]- Salir

                    Elija una opción:\s""");
            switch (scan.nextInt()) {
                case 1 -> showRooms();
                case 2 -> {
                    System.out.print("Ingrese el numero de habitación que busca: ");
                    Room srchRoom = searchRoomByNumber(scan.nextInt());
                    if (srchRoom != null)
                        System.out.println(srchRoom);
                    else
                        System.out.println("Habitacion no encontrada");
                }
                case 3 -> {
                    System.out.print("Ingrese la fecha que busca: ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String date = scan.next();
                    LocalDate ingress = LocalDate.parse(date, formatter);
                    System.out.print("ingrese la cantidad de días de disponibilidad: ");
                    LocalDate out = ingress.plusDays(scan.nextInt());
                    showDisponibledRooms(ingress, out);
                }
                case 4 -> {
                    List<Room> listAux = getRooms().stream().filter(room -> room.getCondition().equals(Condition.RESERVED)).collect(Collectors.toList());
                    if (!listAux.isEmpty())
                        System.out.println(listAux);
                    else System.out.println("No hay habitaciones con reservas");
                }
                case 5 -> addNewRoom();
                case 6 -> eliminateRoom();
                case 0 -> exit++;
                default -> System.out.println("opción incorrecta");
            }
        }
    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
