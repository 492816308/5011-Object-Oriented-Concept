package exception;

@SuppressWarnings("serial")
public class SectionNotFoundException extends Exception {
	
	public SectionNotFoundException(String msg) {
		super(msg);
	}
	
	public SectionNotFoundException() {
		this("Section was not found.");
	}
}
