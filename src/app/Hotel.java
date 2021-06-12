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
import java.util.*;
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
    public void showPaxInHouse(){
        this.getPaxes().stream().filter(Pax::isIngress).forEach(System.out::println);
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
                default -> System.out.println("\033[33m" + "\nOpcion Incorrecta. ");
            }
            newRoom.setCondition(Condition.AVAILABLE);
            newRoom.setNumber(number);
            getRooms().add(newRoom);
        } else {
            System.out.println("\033[33m" + "\nLa habitacion con este numero ya esta registrada. ");
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
                default -> System.out.println("\033[33m" + "\nOpcion incorrecta.");
            }
        } else {
            System.out.println("\033[33m" + "\nHabitacion no encontrada.");
        }

    }

    public void logIn() {
        spaces();
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
                System.out.println("\033[33m" + "\nContraseña incorrecta");
            }
        } else {
            System.out.println("\033[33m" + "\nEl usuario no se encuentra registrado");
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
        System.out.println("El total de su recibo es de: $" + pax.getTickets().stream().mapToDouble(Ticket::getTotal).sum());
        pax.getTickets().clear();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //------ Menu Methods ------//

    public void firstMenu() {

        int back = 0;
        while (back == 0) {
            spaces();
            System.out.println("\033[33m" + "========== BIENVENIDO A HOTEL TRANSILVANIA ==========");
            try {
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
                    default -> System.out.println("\033[31m" + "Opcion incorrecta");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[31m" + "Solo se puede ingresar numeros.");
                scan.next();
            }
        }
        saveHotel();
    }

    public void paxMenu() throws InputMismatchException {

        int exit = 0;
        while (exit == 0) {
            spaces();
            System.out.println("\033[36m" + "========== MENU DE PASAJEROS ==========");
            try {
                System.out.println("""
                        \033[32m
                        [1]- Lista de pasajeros
                        [2]- Nuevo Pasajero
                        [3]- Buscar Pasajero
                        [4]- Mostrar pasajeros in house
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
                            System.out.println("\033[31m" + "Pasajero no encnontrado");
                        }
                    }
                    case 4-> showPaxInHouse();
                    case 0 -> exit++;
                    default -> System.out.println("\033[31m" + "opcion incorrecta");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[31m" + "Solo se puede ingresar numeros.");
                scan.next();
            }
        }
    }

    public void paxMenuBis(Pax pax) throws InputMismatchException {

        int back = 0;
        while (back == 0) {
            spaces();
            System.out.println("\033[36m" + "========== MENU DEL PASAJERO " + pax.getName() + " " + pax.getSurname() + " ==========");
            try {
                System.out.println("""
                        \033[32m
                        [1]- Ver Reservas
                        [2]- Añadir consumo
                        [3]- Ver consumos
                        [4]- Cobrar
                        [0]- Salir""");
                System.out.print("Ingrese una opción: ");
                switch (scan.nextInt()) {
                    case 1 -> showPaxReserves(pax);
                    case 2 -> {
                        if (pax.isIngress())
                            roomServiceMenu(scan, pax);
                        else
                            System.out.println("\033[31m\nEl pasajero no se encuentra en el hotel");
                    }
                    case 3 -> showPaxTickets(pax);
                    case 4 -> {
                        if (pax.isIngress())
                            chargue(pax);
                        else
                            System.out.println("\033[31m\nEl pasajero no se encuentra en el hotel");
                    }
                    case 0 -> back++;
                    default -> System.out.println("\033[31m" + "\nOpcion incorrecta");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[31m" + "\nSolo se puede ingresar numeros.");
                scan.next();
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

    public void recepcionistMenu(User user) throws InputMismatchException {
        Recepcionist recepcionist = new Recepcionist(user.getNickName(), user.getPassword(), user.getJobTitle());
        int back = 0;
        while (back == 0) {
            spaces();
            System.out.println("\033[36m" + "========== MENU DE RECEPCIONISTAS ==========");
            System.out.println("\n\nBienvenido, " + recepcionist.getNickName());

            try {
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
                            System.out.println("\033[34m" + "\nCheck out exitoso.");
                        } else {
                            System.out.println("\033[31m\nHubo un error en el Checkout, vuelva a intentarlo.");
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
                        System.out.println("\033[31m" + "\nOpción incorrecta");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[31m" + "\nSolo se puede ingresar numeros.");
                scan.next();
            }
        }
    }

    public void adminMenu(User user) throws InputMismatchException {

        Admin admin = new Admin(user.getNickName(), user.getPassword(), user.getJobTitle());
        int back = 0;

        while (back == 0) {
            spaces();
            System.out.println("""
                \033[36m
                ========== MENU DE ADMINISTRADORES ==========
                """);
            System.out.println("\n\nBienvenido, " + admin.getNickName());
            try {
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
                            System.out.println("\033[33m" + "\nCheck out exitoso.");
                        } else {
                            System.out.println("\033[31m \nHubo un error en el Checkout, vuelva a intentarlo.");
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
                        System.out.println("\033[31m" + "\nOpción incorrecta");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[31m" + "\nSolo se puede ingresar numeros.");
                scan.next();
            }
        }
    }

    public void roomServiceMenu(Scanner scan, Pax pax) throws InputMismatchException {

        int exit = 0;
        double total = 0;
        String detail = "";
        while (exit == 0) {
            spaces();
            System.out.println("""
                \033[36m
                ========== ROOM SERVICE MENU ==========
                """);
            try {
                System.out.println("Elija un producto, 0 para cancelar");
                for (MiniBar m : MiniBar.values()) {
                    System.out.println(m.ordinal() + 1 + " " + m.getProduct() + ": $" + m.getPrice());
                }
                switch (scan.nextInt()) {
                    case 1 -> {
                        total += MiniBar.COCA_COLA.getPrice();
                        detail += MiniBar.COCA_COLA.getProduct() + ", ";
                    }
                    case 2 -> {
                        total += MiniBar.SPRITE.getPrice();
                        detail += MiniBar.SPRITE.getProduct() + ", ";
                    }
                    case 3 -> {
                        total += MiniBar.VINO_TINTO.getPrice();
                        detail += MiniBar.VINO_TINTO.getProduct() + ", ";
                    }
                    case 4 -> {
                        total += MiniBar.VINO_BLANCO.getPrice();
                        detail += MiniBar.VINO_BLANCO.getProduct() + ", ";
                    }
                    case 5 -> {
                        total += MiniBar.PAPAS_LAYS.getPrice();
                        detail += MiniBar.PAPAS_LAYS.getProduct() + ", ";
                    }
                    case 6 -> {
                        total += MiniBar.TABLETA_CHOCOLATE.getPrice();
                        detail += MiniBar.TABLETA_CHOCOLATE.getProduct() + ", ";
                    }
                    case 7 -> {
                        total += MiniBar.BOLSA_MANI.getPrice();
                        detail += MiniBar.BOLSA_MANI.getProduct() + ", ";
                    }
                    case 0 -> exit++;
                    default -> System.out.println("\033[31m" + "\nOpcion invalida, por favor elija una nueva");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[31m" + "\nSolo se puede ingresar numeros.");
                scan.next();
            }
        }
        if (total != 0) {
            pax.getTickets().add(new Ticket(pax.getName(), pax.getSurname(), detail, total));
            System.out.println("\nRoom service exitoso");
        }
    }

    public void registerMenu() throws InputMismatchException {

        int exit = 0;
        while (exit == 0) {
            spaces();
            System.out.println("""
                \033[36m
                ========== MENU DE REGISTRO ==========
                """);
            try {
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
                    default -> System.out.println("\033[31m" + "\nOpción incorrecta");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[31m" + "\nSolo se puede ingresar numeros.");
                scan.next();
            }
        }
    }

    public void reserveMenu() throws InputMismatchException {

        int exit = 0;
        while (exit == 0) {
            spaces();
            System.out.println("""
                \033[36m
                ========== MENU DE RESERVAS ==========
                """);
            try {
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
                        if (srchPax != null)
                            showPaxReserves(srchPax);
                        else
                            System.out.println("\\033[31m" + "\nEl pasajero ingresado no esta cargado en el sistema");
                    }
                    case 4 -> {
                        Reservation reservation = searchPaxReserve();
                        if (reservation != null)
                            System.out.println(reservation);
                        else
                            System.out.println("\033[31m" + "\nNo hay reserva encontrada");
                    }
                    case 5 -> {
                        Reservation reservation = searchPaxReserve();
                        if (reservation != null) {
                            eliminateReserve(reservation);
                            System.out.println("\nReserva eliminada con exito");
                        } else
                            System.out.println("\033[31m" + "\nEl pasajero no tiene una reserva para esta habitacion");
                    }
                    case 0 -> exit++;
                    default -> System.out.println("\033[31m" + "\nOpción incorrecta.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[31m" + "\nSolo se puede ingresar numeros.");
                scan.next();
            }
        }
    }

    public void roomMenu() throws InputMismatchException {
        spaces();
        System.out.println("""
                \033[36m
                ========== MENU DE HABITACIONES ==========\s
                """);
        int exit = 0;
        while (exit == 0) {
            try {
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
                            System.out.println("\033[31m" + "\nHabitacion no encontrada");
                    }
                    case 3 -> {
                        System.out.print("Ingrese la fecha que busca: ");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String date = scan.next();
                        LocalDate ingress = LocalDate.parse(date, formatter);
                        System.out.print("Ingrese la cantidad de días de disponibilidad: ");
                        LocalDate out = ingress.plusDays(scan.nextInt());
                        showDisponibledRooms(ingress, out);
                    }
                    case 4 -> {
                        List<Room> listAux = getRooms().stream().filter(room -> room.getCondition().equals(Condition.RESERVED)).collect(Collectors.toList());
                        if (!listAux.isEmpty())
                            System.out.println(listAux);
                        else System.out.println("\nNo hay habitaciones con reservas");
                    }
                    case 0 -> exit++;
                    default -> System.out.println("\033[31m" + "\nOpción incorrecta");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[31m" + "\nSolo se puede ingresar numeros.");
                scan.next();
            }
        }
    }

    public void roomMenuAdmin() throws InputMismatchException {
        spaces();
        System.out.println("""
                \033[36m
                ========== MENU DE HABITACIONES ==========\s
                """);
        int exit = 0;
        while (exit == 0) {
            try {
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
                            System.out.println("\033[31m" + "\nHabitacion no encontrada");
                    }
                    case 3 -> {
                        System.out.print("Ingrese la fecha que busca: ");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        String date = scan.next();
                        LocalDate ingress = LocalDate.parse(date, formatter);
                        System.out.print("Ingrese la cantidad de días de disponibilidad: ");
                        LocalDate out = ingress.plusDays(scan.nextInt());
                        showDisponibledRooms(ingress, out);
                    }
                    case 4 -> {
                        List<Room> listAux = getRooms().stream().filter(room -> room.getCondition().equals(Condition.RESERVED)).collect(Collectors.toList());
                        if (!listAux.isEmpty())
                            System.out.println(listAux);
                        else System.out.println("\033[31m" + "\nNo hay habitaciones con reservas");
                    }
                    case 5 -> addNewRoom();
                    case 6 -> eliminateRoom();
                    case 0 -> exit++;
                    default -> System.out.println("\033[31m" + "\nOpción incorrecta");
                }
            } catch (InputMismatchException e) {
                System.out.println("\033[31m" + "\nSolo se puede ingresar numeros.");
                scan.next();
            }
        }
    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
