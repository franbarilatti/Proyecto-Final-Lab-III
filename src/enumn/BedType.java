package enumn;

public enum BedType {
    MATRIMONIAL("Una cama matrimonial"),
    DOBLE_TWIN("Dos camas separadas de una plaza"),
    TRIPLE("Una cama matrimonial y dos individuales"),
    CUADRUPLE("Una cama matrimonial, una individual y una cucheta");

    private final String description;
    private double price;

    BedType(String description) {

        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
