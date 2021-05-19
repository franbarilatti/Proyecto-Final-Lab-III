package model;

import enumn.BedType;
import enumn.TvType;

public class Standard extends Room {
    boolean fan;

    public Standard(int number, int cantBed, BedType bedType, double price, TvType tv, boolean fan) {
        super(number, cantBed,bedType, price, tv);
        this.fan = fan;
    }

    @Override
    public String showDescription() {
        return "Habitación Single : Nuestras habitaciones están diseñadas para que usted disfrute de las mayores comodidades, " +
                "encontrándose en ellas, la superior calidad en servicios de hotelería. Tv de tubo, wifi en todo el hotel" +
                ", Calefaccion central, secadores de cabello, pisos alfombrados, frigobar, room service las 24hs.";
    }
}
