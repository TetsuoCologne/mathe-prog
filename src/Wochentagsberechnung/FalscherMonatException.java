package Wochentagsberechnung;

public class FalscherMonatException extends Exception {

	private static final long serialVersionUID = 1L;

	public FalscherMonatException(){
		super();
	}
	
	public FalscherMonatException(String s){
		super(s);
	}
	

}
