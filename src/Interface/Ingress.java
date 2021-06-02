package Interface;

import model.Reservation;
import model.Room;
import users.Pax;

import java.util.List;


public interface Ingress {
    void checkIn(List<Pax> paxes, Room room, List<Reservation> reservations);

    void checkOut(Pax pax, Room room);

}
