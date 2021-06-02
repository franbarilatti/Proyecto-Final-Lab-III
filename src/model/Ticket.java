package model;

import users.Pax;

import java.io.Serial;
import java.io.Serializable;

public class Ticket implements Serializable {
    @Serial
    private static final long serialVersionUID = -5786522732862477983L;
    private String paxName;
    private String paxSurname;
    private double total;


    public Ticket() {
    }

    public Ticket(String paxName,String paxSurname ,double total) {
        this.paxName = paxName;
        this.paxSurname = paxSurname;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "pax=" + paxName +" " +paxSurname+
                ", total=" + total;
    }
}
