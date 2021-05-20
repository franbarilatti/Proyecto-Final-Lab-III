package Interface;

import app.Hotel;
import model.Room;
import users.Pax;

import java.util.List;

public interface Ingress {
    public void checkIn(List<Pax> paxes, Room room, Hotel hotel);
    public void checkOut(Pax pax,Room room);
}
