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
	
	public void setKey(char key) throws EingabeException{
		if(key =='ä' || key=='ö' || key =='ü' || key =='ß') throw new EingabeException("Bitte kein "+key + " als Eingabe benutzen!"); 
		else this.key =key;
	}
	
	public char getKey(){
		return key;
	}
	
	public void setPlain(String plain) throws EingabeException{
		if(plain.isEmpty()) throw new EingabeException("Bitte geben sie einen Klartext ein!");
		else this.plain =normalizeInput(plain);
	}
	
	public String getPlain(){
		return plain;
	}
	
	public void setChiffrat(String chiffrat) throws EingabeException{
		if(chiffrat.isEmpty()) throw new EingabeException("Bitte geben Sie einen chiffrierten Text ein!");
		else this.chiffrat=normalizeInput(chiffrat);
	}
	
	public String getChiffrat(){
		return chiffrat.toUpperCase();
	}

	public String normalizeInput(String input){
		char [] zeichen = input.toCharArray();
		StringBuilder output = new StringBuilder();

		for(int i = 0;i<zeichen.length;i++){
			if(Character.isLetter(zeichen[i])){

				switch(zeichen[i]){
				case 'ß':output.append("ss");
				break;
				case 'ä': 	output.append("ae");
				break;
				case 'ö': 	output.append("oe");
				break;
				case 'ü':	output.append("ue");
				break;
				default:output.append(zeichen[i]);
				}
			}
		}
		return output.toString();
	}
	
	public void chiffrierenCaesar(){
		int zeichen = alphabet.indexOf(key);	
		chiffrat = "";
		
		for(int i =0;i<plain.length();i++){
			chiffrat += String.valueOf(alphabet.get((alphabet.indexOf(plain.charAt(i)) + zeichen)%26));
		}
		setChanged();
		notifyObservers();
		
	}
}
