package Interface;

import model.Reservation;
import model.Room;
import users.Pax;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public interface Reserve {

    void makeReserve(List<Reservation> reservations, List<Pax> paxes, List<Room> rooms, Scanner scan);

    Pax newPax();

    void RoomAvailable(List<Room> roomList);

    LocalDate ingressDate(Scanner scan, LocalDate today);

}
