package enumn;

public enum BedType {
    MATRIMONIAL("Una cama matrimonial",1600),
    DOBLE_TWIN("Dos camas separadas de una plaza",1600),
    TRIPLE("Una cama matrimonial y dos individuales",2100),
    CUADRUPLE("Una cama matrimonial, una individual y una cucheta",2600);

    private final String description;
<<<<<<< HEAD
    private double price;

    BedType(String description) {

=======
    private final double price;

    BedType(String description, double price) {
>>>>>>> 62dd79e88b5a5959aed4e6126fc7ec26b7d41fc4
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
}
