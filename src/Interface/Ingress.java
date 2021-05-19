package Interface;

import app.Hotel;
import model.Reservation;
import model.Room;
import users.Pax;

public interface Ingress {
    public void checkIn(Pax pax,Room room, Hotel hotel);
    public void checkOut(Pax pax,Room room);
}
