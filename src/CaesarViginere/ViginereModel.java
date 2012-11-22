package CaesarViginere;

import java.util.ArrayList;
import java.util.Observable;

public class ViginereModel extends Observable{

	private ArrayList<Character> alphabet;
	private String chiffrat;
	private String plain;
	private String key;

	public ViginereModel(){
		
		chiffrat ="";
		key = "";
		plain ="";

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

	public void setChiffrat(String chiffrat) throws EingabeException{
		if(chiffrat.isEmpty())throw new EingabeException("Bitte geben Sie einen chiffrierten Text ein!");
		this.chiffrat = normalizeInput(chiffrat.toLowerCase());
	}

	public String getChiffrat(){
		return chiffrat.toUpperCase();
	}

	public void setPlain(String plain) throws EingabeException{
		if(plain.isEmpty()) throw new EingabeException("Bitte geben Sie einen Klartext ein!");
		else this.plain = normalizeInput(plain.toLowerCase());
		setChanged();
		notifyObservers();

	}

	public String getPlain(){
		return plain;
	}
	
	public void setKey(String key) throws EingabeException{
		if(key.isEmpty()) throw new EingabeException("Bitte geben Sie einen Key ein!");
		else this.key = normalizeInput(key.toLowerCase());
	}
	
	public String getKey(){
		return key;
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

	public void chiffrieren(){

		int [] chiffre = new int[plain.length()];
		char [] plainArray = plain.toCharArray();
		char [] keyArray = key.toCharArray();
		StringBuilder chiffrat = new StringBuilder();
		
		for(int i = 0;i<chiffre.length;i++){
			
			chiffre[i] = (alphabet.indexOf(plainArray[i]) + alphabet.indexOf(keyArray[i%key.length()]))%26;
		}

		for(int i = 0;i<chiffre.length;i++){
			chiffrat.append(alphabet.get(chiffre[i]));
		}
		this.chiffrat = chiffrat.toString();
	
		setChanged();
		notifyObservers();

	}

	public void dechiffrieren(){
		int [] plainArray = new int[chiffrat.length()];
		char [] chiffratArray = chiffrat.toCharArray();
		char [] keyArray = key.toCharArray();
		StringBuilder plain = new StringBuilder();
		
		for(int i = 0;i<plainArray.length;i++){
			plainArray[i] = (alphabet.indexOf(chiffratArray[i])) - alphabet.indexOf(keyArray[i%key.length()]);
			if(plainArray[i]<0)plainArray[i] = plainArray[i] + 26;
		}
		
		for(int i = 0;i<plainArray.length;i++){
			plain.append(alphabet.get(plainArray[i]));
		}
		this.plain = plain.toString();
		System.out.println(this.plain);
		setChanged();
		notifyObservers();
	}
}

