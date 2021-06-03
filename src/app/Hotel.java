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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void showPaxReserve(Pax pax) {
        System.out.println(reserves.stream().filter((Reservation r) -> r.getPax().equals(pax)).findFirst());
    }

    public void showPaxTickets(Pax pax) {
        pax.getTickets().forEach(System.out::println);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //------ Search Methods ------//

    public Pax searchHistoryPax(String dni) {
        return paxes.stream().
                filter(pax -> pax.getDni().equals(dni))
                .findFirst()
                .orElse(null);
    }

    public Reservation searchReserve(Pax pax, Room room) {
        return reserves.stream().
                filter((Reservation r) -> r.getRoom().equals(room)).
                filter(r -> r.getPax().equals(pax)).
                findFirst().
                orElse(null);
    }

    public User searchUserByNickName(String nickname) {
        return users.stream().
                filter((User u) -> u.getNickName().equals(nickname)).
                findFirst().
                orElse(null);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //------ Functional Methods ------//

    public void runHotel() throws ClassCastException {
        userRepository.throwList(userFile, users);
        /*roomRepository.throwList(roomFile, rooms);
        paxRepository.throwList(paxFile, paxes);
        reserveRepository.throwList(reserveFile, reserves);*/
        System.out.println(this.getUsers().getClass());
        firstMenu();
    }

    public void saveHotel() {
        userRepository.addList(userFile, this.users);
        roomRepository.addList(roomFile, this.rooms);
        reserveRepository.addList(reserveFile, this.reserves);
        paxRepository.addList(paxFile, this.paxes);
    }

    public void addNewReserve(Reservation newReserve) {
        this.reserves.add(newReserve);
    }

    public void eliminateReserve(Reservation reserve) {
        reserves.remove(reserve);
    }

    public void showRooms() {
        if (rooms == null) {
            System.out.println("No hay habitaciones disponibles");
        } else {
            rooms.forEach(System.out::println);
        }
    }

    public void logIn() {
        System.out.print("Ingrese su Nickname: ");
        String name = scan.next();
        User userAux = null;
        for (User user : users) {
            if (user.getNickName().equals(name)) {
                userAux = user;
                break;
            }
        }
        if (userAux != null) {
            System.out.println("Ingrese su contraseña: ");
            if (userAux.getPassword().equals(scan.next())) {
                userMenues(userAux);
            } else {
                System.out.println("Contraseña incorrecta");
            }
        } else {
            System.out.println("El usuario registrado no se encuentra registrado");
        }
    }

    public void register() {
        String nickName;
        String password;
        User auxUser;
        System.out.println(
                "[1]- Administrador\n" +
                        "[2]- Recepcionista");
        System.out.print("\nIngresar opcion");
        int opt = scan.nextInt();
        if (opt == 1) {
            Admin admin = new Admin();
            System.out.print("Ingrese su Nickname: ");
            nickName = scan.next();
            auxUser = searchUserByNickName(nickName);
            if (auxUser == null) {
                admin.setNickName(nickName);
                System.out.print("Ingrese una contraseña: ");
                admin.setPassword(scan.next());
                users.add(admin);
            }
        } else if (opt == 2) {
            Recepcionist recepcionist = new Recepcionist();
            System.out.print("Ingrese su Nickname: ");
            nickName = scan.next();
            auxUser = searchUserByNickName(nickName);
            if (auxUser == null) {
                recepcionist.setNickName(nickName);
                System.out.print("Ingrese una contraseña: ");
                recepcionist.setPassword(scan.next());
                users.add(recepcionist);
            }
            System.out.print("Ingrese una contraseña: ");
            recepcionist.setPassword(scan.next());
            users.add(recepcionist);
        }
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
                    register();
                    saveHotel();
                }
                case 0 -> {
                    back++;
                    break;
                }
                default -> System.out.println("Opcion incorrecta");
            }
        }
        saveHotel();
    }

    public void paxMenu(Pax pax, User user) {
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
                case 1 -> {
                    showPaxReserve(pax);
                    break;
                }
                case 2 -> {
                    roomServiceMenu(scan, pax);
                    break;
                }
                case 3 -> {
                    showPaxTickets(pax);
                    break;
                }
                case 4 -> {
                    break;
                }
                case 0 -> back++;
                default -> System.out.println("Opcion incorrecta");

            }
        }
        userMenues(user);
    }

    public void userMenues(User user) {
        spaces();
        if (user instanceof Admin) {
            adminMenu((Admin) user);
        } else {
            recepcionistMenu((Recepcionist) user);
        }
    }

    public void recepcionistMenu(Recepcionist recepcionist) {
        spaces();
        int opt;
        int back = 0;
        System.out.println("Bienvenido, " + recepcionist.getNickName());
        while (back == 0) {
            System.out.println(
                    """
                            [1]- Check in
                            [2]- Check out
                            [3]- Agregar Nueva Reserva
                            [4]- Revisar Reservas
                            [5]- Revisar Habitaciones
                            [6]- Buscar Pasajero
                            [0]- Salir""");
            System.out.print("\n\nIngrese el numero de la opcion a la que quiere entrar: ");
            opt = scan.nextInt();
            switch (opt) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    recepcionist.makeReserve(reserves, paxes, rooms, scan);
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.print("Ingrese el dni del pasajero que busca: ");
                    String dni = scan.next();
                    Pax srchPax = searchHistoryPax(dni);
                    paxMenu(srchPax, recepcionist);
                    break;
                case 0:

                    back++;
                    break;
                default:
                    System.out.println("opcion incorrecta");
                    break;
            }
        }
        firstMenu();
    }

    public void adminMenu(Admin admin) {
        spaces();
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
                            [5]- Ver Reservas
                            [6]- Ver Habitaciones
                            [7]- Servicio a la Habitacion
                            [0]- Log out""");
            System.out.print("\n\nIngrese el numero de la opcion a la que quiere entrar: ");
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
        firstMenu();
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
                    total += MiniBar.COCA_COLA.getPrice();
                    detail = MiniBar.COCA_COLA.getProduct();
                }
                case 2 -> {
                    total += MiniBar.SPRITE.getPrice();
                    detail = MiniBar.SPRITE.getProduct();
                }
                case 3 -> {
                    total += MiniBar.VINO_TINTO.getPrice();
                    detail = MiniBar.VINO_TINTO.getProduct();
                }
                case 4 -> {
                    total += MiniBar.VINO_BLANCO.getPrice();
                    detail = MiniBar.VINO_BLANCO.getProduct();
                }
                case 5 -> {
                    total += MiniBar.PAPAS_LAYS.getPrice();
                    detail = MiniBar.PAPAS_LAYS.getProduct();
                }
                case 6 -> {
                    total += MiniBar.TABLETA_CHOCOLATE.getPrice();
                    detail = MiniBar.TABLETA_CHOCOLATE.getProduct();
                }
                case 7 -> {
                    total += MiniBar.BOLSA_MANI.getPrice();
                    detail = MiniBar.BOLSA_MANI.getProduct();
                }
                case 0 -> exit++;
                default -> System.out.println("Opcion invalida, por favor elija una nueva");
            }
            pax.getTickets().add(new Ticket(pax.getName(), pax.getSurname(), detail, total));
        }
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
