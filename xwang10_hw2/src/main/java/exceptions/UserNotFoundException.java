package exceptions;

public class UserNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public UserNotFoundException() {
        super("fError: Username does not exist.");
    }
}
