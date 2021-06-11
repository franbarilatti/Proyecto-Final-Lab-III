package model;



import java.io.Serial;
import java.io.Serializable;

public class Ticket implements Serializable {
    @Serial
    private static final long serialVersionUID = -5786522732862477983L;
    private String paxName;
    private String paxSurname;
    private String description;
    private double total;


    public Ticket() {
    }

    public Ticket(String paxName,String paxSurname,String description,double total) {
        this.paxName = paxName;
        this.paxSurname = paxSurname;
        this.description = description;
        this.total = total;
    }


    public String getPaxName() {
        return paxName;
    }

    public void setPaxName(String paxName) {
        this.paxName = paxName;
    }

    public String getPaxSurname() {
        return paxSurname;
    }

    public void setPaxSurname(String paxSurname) {
        this.paxSurname = paxSurname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.getPaxName() + " " + this.getPaxSurname() +
                "\nDetalle: " + this.getDescription() +
                "\nImporte=" + total;
    }
}
