package Interface;

import app.Hotel;
import model.Reservation;
import model.Room;
import users.Pax;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public interface Reserve {

    public Reservation makeReserve(Hotel hotel, Scanner scan,Pax pax);
    public Pax newPax();
    public void RoomAvailable(List<Room> roomList);
    public LocalDate ingressDate(Scanner scan);
}
