package Zahlendarstellung;

public class FalscheEingabeException extends Exception{

	/**
	 * @param args
	 */
	public FalscheEingabeException(){
		super();
	}
	
	public FalscheEingabeException(String s){
		super(s);
	}

}
