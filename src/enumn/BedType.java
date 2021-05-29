package enumn;

public enum BedType {
    MATRIMONIAL("Una cama matrimonial",1600),
    DOBLE_TWIN("Dos camas separadas de una plaza",1600),
    TRIPLE("Una cama matrimonial y dos individuales",2100),
    CUADRUPLE("Una cama matrimonial, una individual y una cucheta",2600);

    private final String description;
    private final double price;

<<<<<<< HEAD
=======

>>>>>>> be43b06b6127ae35b840b8413d76982e10536f98
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
