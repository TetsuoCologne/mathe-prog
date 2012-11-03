package Wochentagsberechnung;

import java.util.HashMap;
import java.util.Observable;

/**
 * 
 * @author Marc Fielder, Franziska Krebs
 *  Diese Klasse enthält die Berechnung des Wochentags.
 *
 */
public class WochenModel extends Observable{


	private String datumKomplett;
	private int tag;
	private int monat;
	private int jahr;
	private int jahrKomplett;
	private int jahrhundert;
	private String wochentag;
	private int [] berechnungsArray;
	private String [] wochentagsArray;
	private HashMap<Integer, Integer> month = new HashMap<>();

	
	

	public void setEingabeKomplett(String eingabeKomplett) {
		this.datumKomplett = eingabeKomplett;
	}
	

	private void setWochentag(String wochentag){
		this.wochentag = wochentag;
	}

	public String getWochentag(){
		return wochentag;
	}



	/**
	 * 	Konstruktor
	 * 	HashMap für Monate initialisieren; zu jedem Monat die Merkziffer hinzufügen.
	 */
	public WochenModel(){
		berechnungsArray = new int[5];
		tag = 0;
		monat = 0;
		jahr = 0;
		jahrKomplett = 0;
		jahrhundert = 0;
		wochentag = "";
		month.put(1, 0);
		month.put(2, 3);
		month.put(3, 3);
		month.put(4, 6);
		month.put(5, 1);
		month.put(6, 4);
		month.put(7, 6);
		month.put(8, 2);
		month.put(9, 5);
		month.put(10, 0);
		month.put(11, 3);
		month.put(12, 5);
		wochentagsArray = new String[7];
		wochentagsArray[0] ="Sonntag";
		wochentagsArray[1] ="Montag";
		wochentagsArray[2] ="Dienstag";
		wochentagsArray[3] ="Mittwoch";
		wochentagsArray[4] ="Donnerstag";
		wochentagsArray[5] ="Freitag";
		wochentagsArray[6] ="Samstag";
	
	}
	
	/**
	 * Splittet den eingehenden String in der Form dd.mm.jjjj in die einzelnen Komponenten.
	 * Anschließend wird das Jahr nochmals geteilt in die ersten beiden Ziffern (=Jahrhundert) und die letzten beiden 
	 * Ziffern (=Jahr).
	 * 
	 * 
	 * @throws FalschesJahrException Wird geworfen, wenn das Jahr vor 1700 oder nach 1200 eingegeben wird.
	 * @throws FalscherMonatException Wird geworfen, wenn der Monat nicht zwischen 1 und 12 liegt.
	 * 
	 */
	private void splitEingabe() throws FalschesJahrException, FalscherMonatException{
		String [] datum = (datumKomplett.split("\\."));
		this.tag = Integer.valueOf(datum[0]);
		this.monat = Integer.valueOf(datum[1]);
		this.jahrKomplett = Integer.valueOf(datum[2]);
		
		if(jahrKomplett <1700 || jahrKomplett >2100){
			throw new FalschesJahrException("Das Jahr sollte zwischen 1700 und 2100 liegen!");
		} else 	if(monat <1 || monat >12){
			throw new FalscherMonatException("Der Monat sollte zwischen 1 und 12 liegen");
		}
		char [] jahrChar = String.valueOf(jahrKomplett).toCharArray();
		this.jahrhundert = Integer.valueOf(String.copyValueOf(jahrChar, 0, 2));
		this.jahr = Integer.valueOf(String.copyValueOf(jahrChar, 2, 2));
	
	}
	
	/**
	 * 	Diese Methode checkt, ob die Eingabe korrekt ist. 
	 *  @throws TagNichtImMonatException Wird geworfen, wenn der Tag nicht im Monat enthalten ist.
	 */
	
	private void checkEingabe() throws TagNichtImMonatException{
		int tagesAnzahl = 0;
		if(monat%2!=0&&monat<=7||monat>=7&&monat%2==0){
			tagesAnzahl = 31;
		}
		else if(monat%2==0&&monat<=7||monat>7&&monat%2!=0){
			tagesAnzahl = 30;
		}
		if(!(monat ==2 &&checkSchaltjahr())){
			tagesAnzahl  =28;
		}
		else if(monat==2 &&checkSchaltjahr()){
			tagesAnzahl = 29;
		}
		
		if(tag<1 || tag >tagesAnzahl){
			throw new TagNichtImMonatException("Den Tag " + tag + " gibt es nicht im Monat " + monat +".");
		}
		
	}

	/**
	 * 	Diese Methode berechnet die Tagesziffer und schiebt sie ins Array.
	 */
	private void berechneTagesZiffer(){
		berechnungsArray[0] = tag%7;
	}


	/**
	 * 	Diese Methode holt die Merkziffer aus der HashMap und schiebt sie ins Array.
	 */
	private void berechneMonat(){
		berechnungsArray[1] = month.get(monat);

	}


	/**
	 * 	Diese Methode berechnet das Jahr(die letzten beiden Ziffern) und schiebt dieses ins Array.
	 */
	private void berechneJahr(){
		berechnungsArray[2] = (jahr+(jahr/4))%7;

	}


	/**
	 * 	Diese Methode berechnet die Ziffer für das Jahrhundert und schiebt dieses ins Array.
	 */
	private void berechneJahrhundert(){
		berechnungsArray[3] = (3-(jahrhundert%4))*2;
	}



	/**
	 *  Diese Methode überprüft, ob das eingegebene Jahr ein Schaltjahr ist.
	 * @return
	 */
	private boolean checkSchaltjahr(){
		boolean schalt = false;
		
		schalt = ((jahrKomplett%4 == 0 && jahrKomplett%100 != 0) || jahrKomplett%400 == 0  );

		return schalt;
	}

	/**
	 * 
	 * 	Diese Methode führt die eigentliche Berechnung des Tages aus.
	 * @throws TagNichtImMonatException Wird hochpropagiert von der Methode "checkEingabe".
	 * @throws FalschesJahrException Wird hochpropagiert von der Methode "splitEingabe".
	 * @throws FalscherMonatException Wird hochpropagiert von der Methode "splitEingabe"
	 */

	public void wochenTagsBerechnung() throws TagNichtImMonatException, FalschesJahrException, FalscherMonatException{
		splitEingabe();
		checkEingabe();
		berechneTagesZiffer();
		berechneMonat();
		berechneJahr();
		berechneJahrhundert();

		if(checkSchaltjahr() && monat<=2){
			berechnungsArray[4] = 6;
		}else{
			berechnungsArray[4]=0;
		}

		int wochentagsziffer = 0;
	
		for(int i = 0;i<berechnungsArray.length;i++){
			wochentagsziffer+=berechnungsArray[i];
		}
		wochentagsziffer = wochentagsziffer%7;
		setWochentag(wochentagsArray[wochentagsziffer]);
		

		setChanged();
		notifyObservers();
	}




}
