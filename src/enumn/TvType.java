package enumn;

public enum TvType {
    TV_TUBO("Televisor de Tubo"), TV_LED_32("Televisor Led 32 Pulgadas"),TV_LED_42("Televisor Led 42 Pulgadas");

    private final String description;

    TvType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
