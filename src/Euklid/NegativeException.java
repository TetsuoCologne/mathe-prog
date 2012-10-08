package Euklid;

public class NegativeException extends Exception{

	/**
	 * 	Diese Exception fängt die Eingabe von negativen Zahlen ab. 
	 */
	private static final long serialVersionUID = 1L;

	public NegativeException(){
		super();
	}
	
	public NegativeException(String s){
		super(s);
	}
}
