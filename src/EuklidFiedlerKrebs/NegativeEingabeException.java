package EuklidFiedlerKrebs;

public class NegativeEingabeException extends Exception {

	/**
	 * 	Diese Exception fängt negative Eingaben ab.
	 */
	private static final long serialVersionUID = 1L;

	public NegativeEingabeException(){
		super();
	}

	public NegativeEingabeException(String s){
		super(s);
	}
}
