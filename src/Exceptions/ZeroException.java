package Exceptions;

public class ZeroException extends Exception{

	/**
	 * 	Diese Exception fängt die Eingabe zweier Nullen ab.
	 */
	private static final long serialVersionUID = 1L;
	
	public ZeroException(){
		super();
	}
	
	public ZeroException(String s){
		super(s);
	}

	
	
}
