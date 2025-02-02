package exception;

/**
 * The class CourseNotFoundException and its subclasses are a form of Throwable 
 * that indicates conditions that a reasonable application might want to catch.
 * This exception is thrown when attempting to find a course that does not exist.
 * 
 * @author ohsh
 */
@SuppressWarnings("serial")
public class CourseNotFoundException extends Exception {

	public CourseNotFoundException(String msg) {
		super(msg);
	}
	
	public CourseNotFoundException() {
		this("Course was not found.");
	}
	
}
