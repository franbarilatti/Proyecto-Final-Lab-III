package Interface;

import model.Reservation;
import model.Room;
import users.Pax;

import java.time.LocalDate;

public interface Reserve {

    public Reservation makeReserve(Pax pax, Room room, LocalDate checkIn, LocalDate checkOut);
}
