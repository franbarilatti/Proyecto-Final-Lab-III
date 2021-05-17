package app;

import users.Pax;
import users.User;

public class Main {
    public static void main(String[] args) {
        User user1 = new Pax("pepe","pepe2","pepe","pompin","lolo 123","8978785","arg");
        System.out.println(user1.toString());
    }
}
