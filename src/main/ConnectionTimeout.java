package main;

public class ConnectionTimeout extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String message;
	
	public ConnectionTimeout() {
		message = "Connection timed out";
	}
	
	public String getMessage() {
		return message;
	}

}
