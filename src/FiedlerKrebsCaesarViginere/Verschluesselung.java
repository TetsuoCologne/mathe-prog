package FiedlerKrebsCaesarViginere;
/**
 * 	Diese Klasse beinhaltet sowohl die Ver- und Entschlüsselung nach Viginere, als auch nach Caesar.
 */
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
	 * @throws EingabeException Wird geworfen, wenn der Schlüssel nicht eingegeben wurde.
	 */
	public void setKeyViginere(String key) throws EingabeException{
		if(normalizeInput(key).equals("")){
			throw new EingabeException("Bitte geben Sie für den Schlüssel mindestens einen Buchstaben ein!");
		}
		else this.keyViginere= normalizeInput(key);
	}

	public String getKeyViginere(){
		return keyViginere;
	}

	/**
	 * 	Überprüft, ob ein Chiffrat-Text eingegeben wurde. Wenn das Feld nicht leer ist, wird das Chiffrat 
	 * 	normalisiert und gesetzt.
	 * @param chiffrat
	 * @throws EingabeException Wird geworfen, wenn die Eingabe leer ist.
	 */
	public void setChiffrat(String chiffrat) throws EingabeException{
		if(chiffrat.length()==0)throw new EingabeException("Bitte geben Sie einen chiffrierten Text ein");
		else if(normalizeInput(chiffrat).equals("")){
			throw new EingabeException("Die Zeichenkette \""+ chiffrat + "\" ist keine gültige Eingabe!");
		}
		else this.chiffrat =normalizeInput(chiffrat);
	}

	public String getChiffrat(){
		return chiffrat.toUpperCase();
	}

	/**
	 * 	Überprüft, ob ein Klartext eingegeben wurde und normalisiert diesen.
	 * @param plain
	 * @throws EingabeException Wird geworfen, wenn das Feld leer ist.
	 */
	public void setPlain(String plain) throws EingabeException{
		if(plain.length()==0)throw new EingabeException("Bitte geben Sie einen Klartext ein!");
		else if(normalizeInput(plain).equals("")){
			throw new EingabeException("Die Zeichenkette \"" + plain + " \" ist keine gültige Eingabe!");
		}
		else this.plain = normalizeInput(plain);
	}

	public String getPlain(){
		return plain;
	}


	/**
	 * 	Der Setter überprüft zunächst, ob überhaupt eine Eingabe erfolgt ist. Danach wird überprüft, ob nach der
	 * 	Normalisierung noch Zeichen übrig sind. 
	 *  
	 * @param s
	 * @throws EingabeException Wird geworfen bei einer leeren oder ungültigen Eingabe(wenn der String infolge
	 * 	der Normalisierung leer werden würde).
	 */
	public void setKeyCaesar(String s) throws EingabeException{
		String key = normalizeInput(s);
		if(key.length()<1){
			throw new EingabeException("Bitte geben Sie für den Schlüssel genau einen Buchstaben ein!");
		}	
		else keyCaesar = key.charAt(0);
	}


	public char getKeyCaesar(){
		return keyCaesar;
	}

	/**
	 * 	Diese Methode normalisiert den übergebenen Text. Es werden ausschließlich Buchstaben erlaubt.
	 * 	Leerzeichen, Sonderzeichen und Ziffern werden gelöscht.
	 * @param input
	 * @return
	 */
	public String normalizeInput(String input){

		char [] zeichen = input.toLowerCase().toCharArray();
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

	/**
	 * 	Vorgehen bei der Chiffrierung von Caesar:
	 * 	- Index des eingegeben Schlüssels speichern
	 * 	- den Klartext buchstabenweise durchlaufen und dabei den Index des jeweiligen Buchstaben zum Schlüsselindex addieren
	 *  - für den Fall eines Überlaufs %26 ausführen
	 *  - anschließend den Buchstaben zum errechneten Index ermitteln und an den String anfügen
	 */
	public void chiffrierenCaesar(){
		int zeichen = alphabet.indexOf(keyCaesar);	
		chiffrat = "";

		for(int i =0;i<plain.length();i++){
			chiffrat += String.valueOf(alphabet.get((alphabet.indexOf(plain.charAt(i)) + zeichen)%26));
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * 	Der Ablauf der Dechiffrieren entspricht der umgekehrten Chiffrierung.
	 * 	Für den Fall, dass als Ergebnis der Subtraktion ein negativer Wert geliefert wird, wird 26 addiert. 
	 * 
	 */
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

			plain+=alphabet.get(plainArray[i]);

		}
		setChanged();
		notifyObservers();
	}

	/**
	 * 	Diese Methode setzt die Chiffrierung nach Viginere um.
	 * 	Dabei wird jeweils der Index des aktuellen Buchstabens des Klartextes auf den Index des aktuellen Buchstabens
	 * 	des Schlüsselwortes addiert.  
	 * 	Anschließend wird der entsprechende Buchstabe ermittelt und an den Ausgabestring gehangen.
	 */
	public void chiffrierenViginere(){
		char [] keyArray = keyViginere.toCharArray();	
		chiffrat ="";

		for(int i = 0;i<plain.length();i++){
			chiffrat+= alphabet.get((alphabet.indexOf(plain.charAt(i)) + alphabet.indexOf(keyArray[i%keyViginere.length()]))%26);
		}

		setChanged();
		notifyObservers();
	}

	/**
	 * 	Diese Methode implementiert die Dechiffrierung nach Viginere.
	 * 	Sie wird analog zur Chiffrierung ausgeführt (lediglich umgekehrt).
	 */
	public void dechiffrierenViginere(){
		int [] plainArray = new int[chiffrat.length()];
		char [] chiffratArray = chiffrat.toCharArray();
		char [] keyArray = keyViginere.toCharArray();
		plain ="";
		for(int i = 0;i<plainArray.length;i++){
			plainArray[i] = (alphabet.indexOf(chiffratArray[i])) - alphabet.indexOf(keyArray[i%keyViginere.length()]);
			if(plainArray[i]<0){
				plainArray[i] = plainArray[i] + 26;
			}

			plain+=(alphabet.get(plainArray[i]));

		}
		setChanged();
		notifyObservers();
	}



}
