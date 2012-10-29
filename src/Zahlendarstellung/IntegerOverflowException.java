package Zahlendarstellung;

public class IntegerOverflowException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 	@param args
	 * 	Diese Exception fängt die Eingabe von zu großen Werten ab.
	 */
	
	public IntegerOverflowException(){
		super();
	}
	
	public IntegerOverflowException(String s){
		super(s);
	}

}
