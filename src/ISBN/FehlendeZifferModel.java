package ISBN;

import java.util.Observable;
/**
 * 	Diese Klasse beinhaltet das Einlesen einer unvollständigen ISBN Nummer und die Berechnung der fehlenden Ziffer.
 * 	
 * @author Marc Fiedler, Franziska Krebs
 *
 */
public class FehlendeZifferModel extends Observable{

	private String input;
	private int ziffer;
	private String result;
	private int zifferIndex;
	private int [] isbn;

	public FehlendeZifferModel(){
		input =" ";
		ziffer = 0;
		zifferIndex = 0;
		isbn = new int[10];
	}

	public void setInput(String input){
		this.input =input;
	}

	public String getResult(){
		return this.result;
	}


	/**
	 * 	Diese Methode iteriert den eingegeben String durch und erlaubt genau ein '*' als Eingabe.
	 * 	Weiterhin darf nur an der letzten Stelle ein 'X' eingegeben werden.
	 * @return
	 * @throws FalscheEingabeException Wird geworfen, wenn
	 * 		- ein * kein- oder mehrmals vorkommt
	 * 		- ein X an den ersten 9 Stellen vorkommt
	 */
	public void checkEingabe() throws FalscheEingabeException{
		boolean correctStar =true;
		boolean correctXPosition = true;
		int counterStar = 0;
		for(int i = 0;i<input.length();i++){
			if(input.charAt(i) == '*'){
				counterStar++;	
			}
		}

		for(int i = 0;i<input.length()-1;i++){
			if(input.charAt(i) == 'X'){
				correctXPosition = false;
			}
		}
		if(counterStar== 0)throw new FalscheEingabeException("Bitte geben Sie ein * für die fehlende Ziffer ein!");
		if(counterStar>=2) throw new FalscheEingabeException("Bitte nur ein * !"); 
		if(!correctStar || !correctXPosition) throw new FalscheEingabeException("Ein X ist nur an der letzten Stelle erlaubt!");
	}

	/** 
	 * 	Diese Methode löscht aus dem eingegeben String alle Bindestriche raus und castet den String in einzelne Integer. Das X an der letzten
	 * 	Stelle wird dabei zur 10. Für die fehlende ziffer wird eine Null eingetragen und der Index dieser Ziffer wird vermerkt.
	 */
	private void isbnVorbereiten(){
		String isbnString = input.replaceAll("-", "");

		for(int i= 0;i<isbnString.length();i++){
			if(isbnString.charAt(i)!= '*' && isbnString.charAt(i)!= 'X'){
				isbn[i] = Integer.valueOf(isbnString.charAt(i) +"");
			}else if(isbnString.charAt(i) == '*'){
				isbn[i]= 0;
				zifferIndex = 10-i;
			}else if(isbnString.charAt(i) =='X'){
				isbn[i] = 10;
			}
		}

	}

	/**
	 * 	Diese Methode berechnet die fehlende Ziffer. Dabei wird zunächst die Summe nach dem Prüfziffer-Algorithmus berechnet.
	 * 	Anschließend wird mittels einer Schleife die Ziffer eingesetzt und überprüft, wann das Ergebnis%11 == 0 ergibt. 
	 * 	Zuletzt wird für eine 10 an der letzten Stelle ein X eingesetzt.
	 * @throws UndefinierteISBNException Wird geworfen, wenn die fehlende Ziffer eine 10 ergibt und nicht an der letzten Stelle fehlt! 
	 */
	public void computeMissingDigit() throws UndefinierteISBNException{
		isbnVorbereiten();
		int summe = 0;
		int counter = 10;

		for(int i = 0;i<isbn.length;i++){
			summe+=counter*isbn[i];
			counter--;
		}
		
		for(int i = 0;i<11;i++){
			if((summe+(zifferIndex*i))%11==0){
				ziffer= i;
			}
		}
		if(ziffer == 10){
			if(zifferIndex==1){
			result = "X";
			}
			else throw new 	UndefinierteISBNException("Die fehlende Ziffer wäre eine 10, ist so nicht erlaubt!"); 
		}
		else result = String.valueOf(ziffer);
		setChanged();
		notifyObservers();
		
	}

}
