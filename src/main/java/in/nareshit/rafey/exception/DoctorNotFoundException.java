package in.nareshit.rafey.exception;

public class DoctorNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	
	public DoctorNotFoundException() {
		super();
	}
	
	public DoctorNotFoundException(String message) {
		super(message);
	}
}
