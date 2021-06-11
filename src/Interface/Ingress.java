package Interface;

import model.Reservation;
import model.Room;
import users.Pax;

import java.util.List;


public interface Ingress {
    void checkIn(List<Pax> paxes, List<Room> rooms, List<Reservation> reservations);

    boolean checkOut(List<Pax> paxes, List<Room> rooms, List<Reservation> reservations);

}
