package enumn;

public enum Path {
    PAX("C:\\FACU\\3 Cuatrimestre\\Labo III\\Proyecto-Final-Lab-III\\paxFile.jsen"),
    ROOM("C:\\FACU\\3 Cuatrimestre\\Labo III\\Proyecto-Final-Lab-III\\roomFile.jsen"),
    RESERVATION("C:\\FACU\\3 Cuatrimestre\\Labo III\\Proyecto-Final-Lab-III\\reserveFile.jsen"),
    USER("C:\\FACU\\3 Cuatrimestre\\Labo III\\Proyecto-Final-Lab-III\\userFile.jsen");

    String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
