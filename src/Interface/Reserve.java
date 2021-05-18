package Interface;

import model.Reservation;
import model.Room;

public interface Reserve {

    public Reservation makeReserve(Pax pax, Room room);
}
