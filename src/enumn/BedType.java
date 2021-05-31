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
>>>>>>> 9278ab9886280d59b2c4c597af67f35104fa91a4
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
