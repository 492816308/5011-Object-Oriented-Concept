package exceptions;

public class PasswordMismatchException extends Exception {
    private static final long serialVersionUID = 1L;
    public PasswordMismatchException() {
        super("Error: Attempted to login with incorrect credentials.");
    }
}
