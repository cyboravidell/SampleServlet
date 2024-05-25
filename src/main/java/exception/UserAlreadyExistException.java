package exception;

@SuppressWarnings("serial")
public class UserAlreadyExistException extends Exception {
	
	public UserAlreadyExistException(String message) {
		super(message);
	}

	UserAlreadyExistException() {
		super();
		
	}

	UserAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	UserAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
		
	}

	UserAlreadyExistException(Throwable cause) {
		super(cause);
		
	}
	
	
}
