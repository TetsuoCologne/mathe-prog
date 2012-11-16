package ISBN;

import java.util.Observable;

/**
 * 	Diese Klasse stellt eine Methode zur Verfügung, die die Prüfziffer einer ISBN-10 berechnet.
 * @author Marc Fiedler, Franziska Krebs
 *
 */
public class PruefzifferModel extends Observable{

	private String input;
	private int [] isbn;
	private String ergebnisZiffer;
	private int pruefziffer;

	public PruefzifferModel(){
		input = "";
		isbn = new int[9];
		pruefziffer = 0;
	}

	public int getPruefziffer(){
		return this.pruefziffer;
	}


	public void setInput(String input){
		this.input = input;
	}

	public String getErgebnisZiffer(){
		return ergebnisZiffer;
	}

/**
 * 	Diese Methode ersetzt erst alle Bindestriche durch Leerzeichen und schiebt dann die einzelnen Chars in ein Integer-Array.
 * 	Schließend werden alle Werte einzeln mit 10 absteigend multipliziert und aufsummiert. Dann folgt Summe%11. Das Ergebnis wird anschließend von 11 
 * 	abgezogen. 
 * 	  
 */
	public void berechnePruefziffer(){
		String isbnString = input.replaceAll("-", "");
		int counter  = 10;
		pruefziffer = 0;
		for(int i = 0;i<isbnString.length();i++){
			isbn[i] =Integer.valueOf(isbnString.charAt(i)+"");
		}
		
		for(int i = 0;i<isbn.length;i++){
			pruefziffer+=counter*isbn[i];
			counter--;
			
		}
		
		pruefziffer = pruefziffer%11;
		pruefziffer = 11-pruefziffer;
	
		if(pruefziffer == 10){
			ergebnisZiffer = "X";

		}
		if(pruefziffer == 11){
			ergebnisZiffer ="0";
		}
		if(pruefziffer <10){
			ergebnisZiffer = String.valueOf(pruefziffer);
		}
		
		setChanged();
		notifyObservers();
	}


}
