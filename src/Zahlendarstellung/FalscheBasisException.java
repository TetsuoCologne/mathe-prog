package Zahlendarstellung;

public class FalscheBasisException extends Exception{

	/**
	 * @param args
	 * Diese Klasse fängt die Eingabe von Basen ab, die entweder <=0 oder >16 sind.
	 */
	
	public FalscheBasisException() {
		
		super();
	}
	
	public FalscheBasisException(String s){
		super(s);
	}

}
