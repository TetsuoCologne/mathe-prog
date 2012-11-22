package CaesarViginere;

import java.util.Observable;

public class CaesarModel extends Observable{

	private char key;
	private String plain;
	private String chiffrat;
	
	public CaesarModel(){
		key =' ';
		plain="";
		chiffrat ="";
	
	}
	
	public void setKey(Character key){
		this.key =key;
	}
	
	public void setPlain(String plain){
		this.plain =plain;
	}
	
	public String getPlain(){
		return plain;
	}
	
	public void setChiffrat(String chiffrat){
		this.chiffrat=chiffrat;
	}
	
	public String getChiffrat(){
		return chiffrat.toUpperCase();
	}

}
