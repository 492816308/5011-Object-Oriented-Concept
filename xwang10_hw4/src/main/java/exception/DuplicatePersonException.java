package exception;

/**
 * The class DuplicatePersonException and its subclasses are a form of Throwable 
 * that indicates conditions that a reasonable application might want to catch.
 * This exception is thrown when attempting to add a duplicate person.
 * 
 * @author ohsh
 */
@SuppressWarnings("serial")
public class DuplicatePersonException extends Exception {
	
	public DuplicatePersonException(String msg) {
		super(msg);
	}
	
	public DuplicatePersonException() {
		this("Person has already been added.");
	}
	
}
