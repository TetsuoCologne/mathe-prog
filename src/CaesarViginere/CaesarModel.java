package CaesarViginere;

import java.util.ArrayList;
import java.util.Observable;

public class CaesarModel extends Observable{

	private char key;
	private String plain;
	private String chiffrat;
	private ArrayList<Character> alphabet;
	
	public CaesarModel(){
		key =' ';
		plain="";
		chiffrat ="";
		
		alphabet = new ArrayList<>();
		alphabet.add('a');
		alphabet.add('b');
		alphabet.add('c');
		alphabet.add('d');
		alphabet.add('e');
		alphabet.add('f');
		alphabet.add('g');
		alphabet.add('h');
		alphabet.add('i');
		alphabet.add('j');
		alphabet.add('k');
		alphabet.add('l');
		alphabet.add('m');
		alphabet.add('n');
		alphabet.add('o');
		alphabet.add('p');
		alphabet.add('q');
		alphabet.add('r');
		alphabet.add('s');
		alphabet.add('t');
		alphabet.add('u');
		alphabet.add('v');
		alphabet.add('w');
		alphabet.add('x');
		alphabet.add('y');
		alphabet.add('z');
	
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

	public void chiffrierenCaesar(){
		int zeichen = alphabet.indexOf(key);		
		for(int i =0;i<plain.length();i++){
			chiffrat += String.valueOf(alphabet.get((alphabet.indexOf(plain.charAt(i)) + zeichen)%26));
		}
		System.out.println(chiffrat);
	}
}
