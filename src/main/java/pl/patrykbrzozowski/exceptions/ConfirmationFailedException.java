package pl.patrykbrzozowski.exceptions;

public class ConfirmationFailedException extends RuntimeException{

    public ConfirmationFailedException(String message) {
        super(message);
    }
}
