package exceptions;

public class InvalidAmountException extends Throwable {
    public InvalidAmountException() {
        super("Invalid amount");
    }

    public InvalidAmountException(String message) {
        super(message);
    }
}
