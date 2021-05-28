package enumn;

public enum Condition {
    AVAILABLE("Disponible"), OCUPPED("Ocupada"), MAINTENANCE("En Mantenimiento"), UNCLEAN_AVAILABLE("Sucia Disponible"),
    UNCLEAN_OCUPPED("Sucia Ocupada");
    private String state;

    Condition(String status) {
        this.state = status;
    }

    public String getState() {
        return state;
    }
}
