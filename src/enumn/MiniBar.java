package enumn;

public enum MiniBar {
    COCA_COLA("Coca Cola 500ml", 150), SPRITE("Sprite 500ml", 150), VINO_TINTO("Vino Tinto 750ml", 400), VINO_BLANCO("Vino Blanco 750ml",400),
    PAPAS_LAYS("Papas Lays 145grs", 200), TABLETA_CHOCOLATE("Tableta de Chocolae 100grs", 175), BOLSA_MANI("Bolsa de Mani 50grs", 90);
    private String product;
    private double price;

    MiniBar(String product, double price) {
        this.product = product;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }
}
