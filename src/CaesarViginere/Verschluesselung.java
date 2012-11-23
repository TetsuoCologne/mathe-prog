package CaesarViginere;

import java.util.ArrayList;
import java.util.Observable;

public class Verschluesselung extends Observable{

	private String keyViginere;
	private String plain;
	private String chiffrat;
	private char keyCaesar;
	private ArrayList<Character> alphabet;

	public Verschluesselung(){
		keyViginere = "";
		plain ="";
		chiffrat="";
		keyCaesar=0;
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

	/**
	 * 	Überprüft, ob ein Key eingegeben wurde und normalisiert diesen.
	 * @param key
	 * @throws EingabeException 
	 */
	public void setKeyViginere(String key) throws EingabeException{
		if(key.equals("")){
			throw new EingabeException("Bitte geben Sie einen Schlüssel ein!");
		}
		else this.keyViginere= normalizeInput(key);
	}

	public String getKeyViginere(){
		return keyViginere;
	}

	public void setChiffrat(String chiffrat) throws EingabeException{
		if(chiffrat.equals("")){
			throw new EingabeException("Bitte geben Sie einen chiffrierten Text ein");
		}
		else this.chiffrat =normalizeInput(chiffrat);
	}

	public String getChiffrat(){
		return chiffrat.toUpperCase();
	}

	public void setPlain(String plain) throws EingabeException{
		if(plain.equals("")){
			throw new EingabeException("Bitte geben Sie einen Klartext ein!");
		}
		else this.plain = normalizeInput(plain);
	}

	public String getPlain(){
		return plain;
	}

	public void setKeyCaesar(String s) throws EingabeException{
		if(s.equals("")){
			throw new EingabeException("Bitte geben Sie einen Buchstaben ein!");
		}
		if(s.length()==1 && Character.isLetter(s.charAt(0))){
			char key = s.charAt(0);
			if((key=='ä' || key=='ö' || key =='ü' || key =='ß')) throw new EingabeException("Bitte kein "+key + " als Eingabe benutzen!"); 
			else this.keyCaesar = key;
		}
		else{
			throw new EingabeException("Die Eingabe darf nur ein Zeichen sein!");
		}
	}

	public char getKeyCaesar(){
		return keyCaesar;
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
		return output.toString().toLowerCase();
	}

	public void chiffrierenCaesar(){
		int zeichen = alphabet.indexOf(keyCaesar);	
		chiffrat = "";
		
		for(int i =0;i<plain.length();i++){
			chiffrat += String.valueOf(alphabet.get((alphabet.indexOf(plain.charAt(i)) + zeichen)%26));
		}
		setChanged();
		notifyObservers();
	}

	public void dechiffrierenCaesar(){
		int zeichen = alphabet.indexOf(keyCaesar);
		int plainArray[] = new int[chiffrat.length()];
		char chiffratArray[] = chiffrat.toCharArray();

		plain ="";
		for(int i = 0;i<plainArray.length;i++){
			plainArray[i] = (alphabet.indexOf(chiffratArray[i]) - zeichen);
			if(plainArray[i]<0){
				plainArray[i] = plainArray[i] +26;
			}
			else{
				plain+=alphabet.get(plainArray[i]);
			}
		}
		setChanged();
		notifyObservers();
	}

	public void chiffrierenViginere(){
		char [] keyArray = keyViginere.toCharArray();	
		chiffrat ="";

		for(int i = 0;i<plain.length();i++){
			chiffrat+= alphabet.get((alphabet.indexOf(plain.charAt(i)) + alphabet.indexOf(keyArray[i%keyViginere.length()]))%26);
		}

		setChanged();
		notifyObservers();
	}

	public void dechiffrierenViginere(){
		int [] plainArray = new int[chiffrat.length()];
		char [] chiffratArray = chiffrat.toCharArray();
		char [] keyArray = keyViginere.toCharArray();
		plain =" ";
		for(int i = 0;i<plainArray.length;i++){
			plainArray[i] = (alphabet.indexOf(chiffratArray[i])) - alphabet.indexOf(keyArray[i%keyViginere.length()]);
			if(plainArray[i]<0){
				plainArray[i] = plainArray[i] + 26;
			}
			else{
				plain+=(alphabet.get(plainArray[i]));
			}
		}
		setChanged();
		notifyObservers();
	}



}
