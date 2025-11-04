package model.exceptions;

// represents an exception when an entity is showing up as the wrong type in the JSON file
public class WrongEntityTypeException extends Exception {

    public WrongEntityTypeException(String msg) {
        super(msg);
    }
}
