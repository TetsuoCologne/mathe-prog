package Wochentagsberechnung;

public class TagNichtImMonatException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TagNichtImMonatException(){
		super();
	}
	public TagNichtImMonatException(String s){
		super(s);
	}

}
