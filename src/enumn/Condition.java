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

    public void setState(String state) {
        this.state = state;
    }

    public static String searchByOrdinal(int num){
        String s="";
        for (Condition c: Condition.values()){
            if (c.ordinal() == num-1){
                s = c.name();
                break;
            }
        }
        return s;
    }
}
