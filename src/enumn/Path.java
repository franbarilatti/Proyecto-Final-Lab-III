package enumn;

public enum Path {
    PAX("C:\\FACU\\3 Cuatrimestre\\Labo III\\Proyecto-Final-Lab-III\\paxFile.json"),
    ROOM("C:\\FACU\\3 Cuatrimestre\\Labo III\\Proyecto-Final-Lab-III\\roomFile.json"),
    RESERVATION("C:\\FACU\\3 Cuatrimestre\\Labo III\\Proyecto-Final-Lab-III\\reserveFile.json"),
    USER("C:\\FACU\\3 Cuatrimestre\\Labo III\\Proyecto-Final-Lab-III\\userFile.json");

    String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
