package exception;

@SuppressWarnings("serial")
public class DuplicateSectionException extends Exception {

	public DuplicateSectionException(String msg) {
		super(msg);
	}
	
	public DuplicateSectionException() {
		this("Section has already been added.");
	}
	
}
