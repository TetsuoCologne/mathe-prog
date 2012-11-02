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


	// TODO Auto-generated method stub
	// Seite zum testen: http://www.weltzeituhr.com/infos/wochentage_berechnung.shtml
	// die Ziffern sind eine zuweisung um einfacher zu rechnen
	//eingabe

	private String datumKomplett;
	private int tag;
	private int monat;
	private int jahrKomplett;
	//wird gebraucht bei führenden Nullen
	private String jahrMitNull;
	private int jahrhundert;
	private int jahr;
	private String wochentag;
	private int [] berechnungsArray;
	private HashMap<Integer, Integer> month = new HashMap<>();

	
	

	public void setEingabeKomplett(String eingabeKomplett) {
		this.datumKomplett = eingabeKomplett;
	}
	
	public void setTag(int tag){
		this.tag = tag;
	}

	public void setMonat(int monat){
		this.monat = monat;

	}

	private void setWochentag(String wochentag){
		this.wochentag = wochentag;
	}

	public String getWochentag(){
		return wochentag;
	}



	/**
	 * 	Konstruktor
	 * 	HashMap für Monate initialisieren; zu jedem Monat die Merkziffer
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

	}
	
	/**
	 * Splittet den eingehenden String in der Form dd.mm.jjjj in die einzelnen Komponenten.
	 * Anschließend wird das Jahr nochmals geteilt in die ersten beiden Ziffern (=Jahrhundert) und die letzten beiden 
	 * Ziffern (=Jahr).
	 * 
	 * 
	 * @throws FalschesJahrException Wird geworfen, wenn das Jahr vor 1700 oder nach 1200 eingegeben wird.
	 * @throws FalscherMonatException Wird geworfen, wenn der Monat nicht zwischen 1 und 12 liegt.
	 * Für den Fall, dass ein Jahr der Form 1900 oder 1902 eingegeben wird, wird der letzte Teil der Eingabe zusätzlich als 
	 * String gescpeichert. Dies spielt insbesondere bei der 
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
		
		System.out.println(jahrKomplett + " " + jahr);
	}
	
	/**
	 * 	
	 *  @throws TagNichtImMonatException Wird geworfen, wenn der Tag nicht im Monat enthalten ist.
	 */
	
	public void checkEingabe() throws TagNichtImMonatException{
		int tagesAnzahl = 0;
		if(monat%2!=0&&monat<=7||monat>=7&&monat%2==0){
			tagesAnzahl = 31;
		}
		else if(monat%2==0&&monat<=7||monat>7&&monat%2!=0){
			tagesAnzahl = 30;
		}
		if(monat ==2 &&checkSchaltjahr()==false){
			tagesAnzahl  =28;
		}
		else if(monat==2 &&checkSchaltjahr() ==true){
			tagesAnzahl = 29;
		}
		
		if(tag<1 || tag >tagesAnzahl){
			throw new TagNichtImMonatException("Den Tag " + tag + " gibt es nicht im Monat " + monat +".");
		}
		
	}

	//Methode zur Berechnung des Tages 
	private void berechneTagesZiffer(){
		berechnungsArray[0] = tag%7;
	}


	//Methode zur berechnung des Monats Anmerkung im Array "monatsziffer" gibt die tage des monats an wobei null am anfang ein platzhalter ist da der index bei null beginnt f�r januar aber bei 1
	//Ablauf ist folgenderweise jedem Monat wird eine ziffer zugewiesen

	private void berechneMonat(){
		berechnungsArray[1] = month.get(monat);;

	}


	//Methode Jahr hier wierd jedem jahr eine Ziffer zugewiesen 
	private void berechneJahr(){
		berechnungsArray[2] = (jahr+(jahr/4))%7;

	}


	// Methode für die Jahrundert Ziffer
	private void berechneJahrhundert(){
		berechnungsArray[3] = (3-(jahrhundert%4))*2;
	}



	private boolean checkSchaltjahr(){
		boolean schalt = false;
	
		if(monat<=2){
			if(jahrKomplett%4==0 && jahrKomplett%100!=0){
				schalt = true;
			}
			else if(jahrKomplett%400==0){
				schalt = true;
			}
			else{
				schalt =false;
			}
		}

		else{
			schalt = false;
		}
		return schalt;
	}

	//Ergebnismethode der gesamtrechnung hier werden s�mtliche ziffern addiert wenn schalltjahr dann 6 sonst 0 auch null f�r die ersten zwei monate jan und febr die summe der ziffern wird mod 7 gerechnet und danach wird das ergebnis ausgegeben

	public void wochenTagsBerechnung() throws TagNichtImMonatException, FalschesJahrException, FalscherMonatException{
		splitEingabe();
		checkEingabe();
		berechneTagesZiffer();
		berechneMonat();
		berechneJahr();
		berechneJahrhundert();
		System.out.println(jahrKomplett);
		System.out.println(checkSchaltjahr());
		if(checkSchaltjahr()==true){
			berechnungsArray[4] = 6;
		}else{
			berechnungsArray[4]=0;
		}

		int wochentagsziffer = 0;
		for(int a : berechnungsArray){
			System.out.println(a);
		}
		for(int i = 0;i<berechnungsArray.length;i++){
			wochentagsziffer+=berechnungsArray[i];
		}
		wochentagsziffer = wochentagsziffer%7;
		System.out.println(wochentagsziffer);
		switch(wochentagsziffer){
		case 0:setWochentag("Sonntag");
		break;
		case 1:setWochentag("Montag");
		break;
		case 2:setWochentag("Dienstag");
		break;
		case 3:setWochentag("Mittwoch");
		break;
		case 4:setWochentag("Donnerstag");
		break;
		case 5:setWochentag("Freitag");
		break;
		case 6:setWochentag("Samstag");
		break;


		}

		setChanged();
		notifyObservers();
	}




}
