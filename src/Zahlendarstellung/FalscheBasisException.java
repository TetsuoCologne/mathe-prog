package Zahlendarstellung;

public class FalscheBasisException extends Exception{

	/**
	 * @param args
	 * Diese Klasse fängt die Eingabe von Basen ab, die entweder <=0 oder >16 sind.
	 */
	
	private static final long serialVersionUID = 1L;

	
	public FalscheBasisException() {
		
		super();
	}
	
	public FalscheBasisException(String s){
		super(s);
	}

}
