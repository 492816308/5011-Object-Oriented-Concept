package exception;

/**
 * The class DuplicateSubjectException and its subclasses are a form of Throwable 
 * that indicates conditions that a reasonable application might want to catch.
 * This exception is thrown when attempting to add a duplicate subject.
 * 
 * @author ohsh
 */
@SuppressWarnings("serial")
public class DuplicateSubjectException extends Exception {

	public DuplicateSubjectException(String msg) {
		super(msg);
	}
	
	public DuplicateSubjectException() {
		this("Subject has already been added.");
	}
	
}
