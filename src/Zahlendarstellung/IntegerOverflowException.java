package Zahlendarstellung;

public class IntegerOverflowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	
	public IntegerOverflowException(){
		super();
	}
	
	public IntegerOverflowException(String s){
		super(s);
	}

}
