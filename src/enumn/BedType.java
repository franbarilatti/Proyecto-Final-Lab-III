package enumn;

import java.io.Serializable;

public enum BedType implements Serializable {
    MATRIMONIAL("Una cama matrimonial",1600),
    DOBLE_TWIN("Dos camas separadas de una plaza",1600),
    TRIPLE("Una cama matrimonial y dos individuales",2100),
    CUADRUPLE("Una cama matrimonial, una individual y una cucheta",2600);

    private final String description;
    private final double price;

<<<<<<< HEAD
=======

>>>>>>> 79116f8c28c0981f0c7faed17b4696bdf5d9fd27
    BedType(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
