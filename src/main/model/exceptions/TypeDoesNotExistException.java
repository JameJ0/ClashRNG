package model.exceptions;

public class TypeDoesNotExistException extends Exception {
    public TypeDoesNotExistException() {
        super();
    }

    public TypeDoesNotExistException(String msg) {
        super(msg);
    }
}
