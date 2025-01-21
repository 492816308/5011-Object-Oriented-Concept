package exceptions;

public class InvalidUsernameException extends Exception {
    private static final long serialVersionUID = 1L;
    public InvalidUsernameException() {
        super("Error: The username is invalid; enter 6-12 lower-case letters.");
    }
}
