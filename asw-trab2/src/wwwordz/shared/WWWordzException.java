package wwwordz.shared;

/**
 * A exception in WWWordz. All constructors delegate in the super class.
 * @author José Paulo Leal
 *
 */
public class WWWordzException extends java.lang.Exception {
	private static final long serialVersionUID = 1L;
	java.lang.String message;
	java.lang.Throwable cause;
	
	/**
	 * Empty constructor.
	 */
	public WWWordzException() {}
	
	/**
	 * Creates an exception with a message and a cause.
	 * @param message - of exception
	 * @param cause - of exception
	 */
	public WWWordzException(java.lang.String message, java.lang.Throwable cause) {
		this.message = message;
		this.cause = cause;
	}
	
	/**
	 * Creates an exception with a message.
	 * @param message - of exception
	 */
	public WWWordzException(java.lang.String message) {
		this.message = message;
	}
	
	/**
	 * Creates an exception with a cause.
	 * @param cause - of exception
	 */
	public WWWordzException(java.lang.Throwable cause) {
		this.cause = cause;
	}
}