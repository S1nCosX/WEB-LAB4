package server.database.domains.src.main.java.server.validators;

public class Converter {
    public float convertObjectToFloat(Object o){
        return Float.parseFloat(o.toString());
    }
}