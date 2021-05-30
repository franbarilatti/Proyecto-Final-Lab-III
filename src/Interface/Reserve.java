package Interface;

import app.Hotel;
import model.Reservation;
import model.Room;
import users.Pax;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public interface Reserve {

     Reservation makeReserve(Hotel hotel, Scanner scan);
     Pax newPax();
     void RoomAvailable(List<Room> roomList);
     LocalDate ingressDate(Scanner scan, LocalDate today);

}
