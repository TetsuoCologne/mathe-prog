package ISBN;

public class FalscheEingabeException extends Exception {

	private static final long serialVersionUID = 1L;

	public FalscheEingabeException(){
		super();
	}
	
	public FalscheEingabeException(String message){
		super(message);
	}

}
