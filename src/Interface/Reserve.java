package Interface;

import model.Reservation;
import model.Room;
import users.Pax;

import java.time.LocalDate;
import java.util.List;

public interface Reserve {

    public Reservation makeReserve(Pax pax,List<Room> roomList, int roomNumber, LocalDate checkIn, LocalDate checkOut);
    public Pax NewPax();
    public void RoomAvailable(List<Room> roomList);
}
