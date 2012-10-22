package Zahlendarstellung;

public class FalscheEingabeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
