package model;

import users.Pax;

public class Ticket {
    private Pax pax;
    private double total;


    public Ticket() {
    }

    public Ticket(Pax pax, double total) {
        this.pax = pax;
        this.total = total;
    }

    public Pax getPax() {
        return pax;
    }

    public void setPax(Pax pax) {
        this.pax = pax;
    }



    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "pax=" + pax.getName() +" " +pax.getSurname() +
                ", total=" + total;
    }
}
