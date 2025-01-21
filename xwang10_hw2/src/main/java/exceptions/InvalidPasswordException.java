package exceptions;

public class InvalidPasswordException extends Exception {
    private static final long serialVersionUID = 1L;
    public InvalidPasswordException() {
        super("Error: The password is invalid; enter 6-15 characters and at least\n" +
              "one letter, one number, and one special character (!@#$%^&).");
    }
}
