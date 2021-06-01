package app;

import model.Reservation;
import model.Room;
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
    private static Scanner scan = new Scanner(System.in);
    private RepositoryController repositoryController;
    private static final String userFile = "userFile.json";
    private static final String paxFile = "paxFile.json";
    private static final String roomFile = "roomFile.json";
    private static final String reserveFile = "reserveFile.json";


    //------ Constructors ------//
    public Hotel() {
        repositoryController = new RepositoryController();
        users = new ArrayList<>();
        rooms = new ArrayList<>();
        reserves = new ArrayList<>();
        paxes = new ArrayList<>();
    }

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

    public void showHistoryPax() throws Exception {
        if (paxes != null)
            paxes.stream().forEach(System.out::println);
        else
            throw new Exception("No hay pasajeros historicos en el sistema");
    }

    public Pax searchHistoryPax(String dni) {
        return paxes.stream().
                filter(pax -> pax.getDni().equals(dni))
                .findFirst()
                .orElse(null);
    }

    public void addNewReserve(Reservation newReserve) {
        this.reserves.add(newReserve);
    }

    public void showAllReserves() throws Exception {
        if (reserves != null)
            reserves.stream().forEach(System.out::println);
        else
            throw new Exception("No hay reservas cargadas en el sistema");
    }

    public void showTodayReserves() throws Exception {
        if (reserves != null)
            reserves.stream().
                    filter((Reservation r) -> r.getCheckIn().equals(LocalDate.now())).
                    forEach(System.out::println);
        else
            throw new Exception("No hay reservas cargadas en el sistema");
    }

    public Reservation searchReserve(Pax pax, Room room) {
        return reserves.stream().
                filter((Reservation r) -> r.getRoom().equals(room)).
                filter(r -> r.getPax().equals(pax)).
                findFirst().
                orElse(null);
    }

    public void eliminateReserve(Reservation reserve) {
        reserves.remove(reserve);
    }

    public void showRooms() {
        if (rooms == null) {
            System.out.println("No hay habitaciones disponibles");
        } else {
            rooms.stream().forEach(System.out::println);
        }
    }

    public void showDisponibledRooms(LocalDate ingress, LocalDate exit) {
        if (rooms == null) {
            System.out.println("No hay habitaciones disponibles");
        } else {
            rooms.stream().filter(room -> !room.isOcuped(reserves, ingress, exit)).forEach(System.out::println);
        }
    }

    public void register() {

        System.out.println(
                """

                        [1]- Administrador
                        [2]- Recepcionista""");
        System.out.print("\nIngresar opcion");
        int opt = scan.nextInt();
        if (opt == 1) {
            Admin admin = new Admin();
            System.out.print("Ingrese su Nickname: ");
            admin.setNickName(scan.next());
            System.out.print("Ingrese una contraseña: ");
            admin.setPassword(scan.next());
            users.add(admin);
        } else if (opt == 2) {
            Recepcionist recepcionist = new Recepcionist();
            System.out.print("Ingrese su Nickname: ");
            recepcionist.setNickName(scan.next());
            System.out.print("Ingrese una contraseña: ");
            recepcionist.setPassword(scan.next());
            users.add(recepcionist);
        }
    }

    public void runHotel() {
        //this.users = repositoryController.throwList(userFile);
        this.rooms = repositoryController.throwList(roomFile);
        this.paxes = repositoryController.throwList(paxFile);
        this.reserves = repositoryController.throwList(reserveFile);
        firstMenu();
    }

    public void closeHotel() {
        repositoryController.addList(userFile, this.users);
        repositoryController.addList(roomFile, this.rooms);
        repositoryController.addList(reserveFile, this.reserves);
        repositoryController.addList(paxFile, this.paxes);

    }

    public void logIn() {
        System.out.print("Ingrese su Nick Name: ");
        User userAux = users.stream().
                filter(user -> user.getNickName().equals(scan.next())).
                findFirst().
                orElse(null);
        if (userAux != null) {
            System.out.println("Ingrese su contrasña: ");
            if (userAux.getPassword().equals(scan.next())) {
                userMenues(userAux);
            } else {
                System.out.println("Contraseña incorrecta");
            }
        } else {
            System.out.println("El usuario registrado no se encuentra registrado");
        }
    }

    public void firstMenu() {
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
                case 2 -> register();
                case 0 -> back++;
                default -> System.out.println("Opcion incorrecta");
            }
        }
        closeHotel();
    }

    public void paxMenu(Pax pax, User user) {
        System.out.println("========== " + pax.getName() + " " + pax.getSurname() + " ==========");
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
                case 1:
                    break;
                case 2:
                    break;
                case 0:
                    back++;
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        }
        userMenues(user);
    }

    public void userMenues(User user) {
        if (user instanceof Admin) {
            adminMenu((Admin) user);
        } else {
            recepcionistMenu((Recepcionist) user);
        }
    }

    public void recepcionistMenu(Recepcionist recepcionist) {
        int opt;
        int back = 0;
        System.out.println("Bienvenido, " + recepcionist.getNickName());
        while (back == 0) {
            System.out.println(
                    """


                            [1]- Check in
                            [2]-Check out
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
}
