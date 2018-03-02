package ua.karavayev.myExceptions;

public class NotInDataBaseException extends Exception{

	public NotInDataBaseException() {
		super();
	}

	public NotInDataBaseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotInDataBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotInDataBaseException(String message) {
		super(message);
	}

	public NotInDataBaseException(Throwable cause) {
		super(cause);
	}
	
}
