package exceptions;

public class NotAvailableException extends Exception{

private String message;
	
	public NotAvailableException(String message){
		this.message = message;
	}
	
	public NotAvailableException() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage(){
		return message;
	}

}
