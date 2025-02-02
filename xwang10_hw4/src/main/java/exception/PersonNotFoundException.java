package exception;

/**
 * The class PersonNotFoundException and its subclasses are a form of Throwable 
 * that indicates conditions that a reasonable application might want to catch.
 * This exception is thrown when attempting to find a person that does not exist.
 * 
 * @author ohsh
 */
@SuppressWarnings("serial")
public class PersonNotFoundException extends Exception {

	public PersonNotFoundException(String msg) {
		super(msg);
	}
	
	public PersonNotFoundException() {
		this("Person was not found.");
	}
	
}
