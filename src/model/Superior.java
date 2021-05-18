package model;

import enumn.TvType;

public class Superior extends Room{

    boolean ac;
    boolean safeBox;
    boolean oceanView;

    public Superior(int number, int cantBed, TvType tv) {
        super(number, cantBed, tv);
        this.ac = true;
        this.safeBox = true;
        this.oceanView = true;
    }

    @Override
    public String showDescription() {
        return "Nuestras habitaciones están diseñadas para que usted disfrute de las mayores comodidades," +
                " encontrándose en ellas, la superior calidad en servicios de hotelería. LCD 32 pulgadas, wifi en todo el hotel" +
                ", aire acondicionado frío-calor, secadores de cabello, pisos alfombrados, frigobar, cofres de seguridad, room service las 24hs.";
    }
}
