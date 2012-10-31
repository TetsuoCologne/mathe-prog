package Wochentagsberechnung;

import java.util.HashMap;
import java.util.Observable;

public class WochenModel extends Observable{


	// TODO Auto-generated method stub
	// Seite zum testen: http://www.weltzeituhr.com/infos/wochentage_berechnung.shtml
	// die Ziffern sind eine zuweisung um einfacher zu rechnen
	//eingabe

	private int tag;
	private int monat;
	private int jahr;
	//wird gebraucht bei führenden Nullen
	private String jahrMitNull;
	private int jahrhundert;
	private String wochentag;
	private int [] berechnungsArray;
	private HashMap<Integer, Integer> month = new HashMap<>();

	
	
	public void checkEingabe() throws KeinSchaltjahrException{
			
		// b = letzter tag des Monats
		//if(tag<1 || tag >= b)
		if(checkSchaltjahr() == false){
			if(monat ==2){
				if(tag<1 || tag >=29){
					throw new KeinSchaltjahrException("Kein 29. Februar für das Jahr " + String.valueOf(jahrhundert).concat(String.valueOf(jahrMitNull))+ "!" );
				}
			}
		}
	}
	public void setTag(int tag){

		this.tag = tag;
	}

	public void setMonat(int monat){
		this.monat = monat;

	}



	//Jahr und Jahrhundert werden in diesem Setter gesetzt
	public void setJahrKomplett(String jahr){
		char [] jahrChar = jahr.toCharArray();
		this.jahrhundert = Integer.valueOf(String.copyValueOf(jahrChar, 0, 2));
		this.jahrMitNull = String.copyValueOf(jahrChar,2,2);
		this.jahr = Integer.valueOf(String.copyValueOf(jahrChar, 2, 2));
		

	}

	private void setWochentag(String wochentag){
		this.wochentag = wochentag;
	}

	public String getWochentag(){
		return wochentag;
	}

	public WochenModel(){
		berechnungsArray = new int[5];
		tag = 0;
		monat = 0;
		jahr = 0;
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
		
	
		System.out.println(jahrMitNull);
		
		int komplettesJahr = Integer.valueOf(String.valueOf(jahrhundert).concat(jahrMitNull));

		if(monat<=2){
			if(komplettesJahr%4==0 && komplettesJahr%100!=0){
				schalt = true;
			}
			else if(komplettesJahr%400==0){
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

	public void wochenTagsBerechnung() throws KeinSchaltjahrException{
		checkEingabe();
		berechneTagesZiffer();
		berechneMonat();
		berechneJahr();
		berechneJahrhundert();
		if(checkSchaltjahr()==true){
			berechnungsArray[4] = 6;
		}else{
			berechnungsArray[4]=0;
		}

		int wochentagsziffer = 0;
		for(int i = 0;i<berechnungsArray.length;i++){
			wochentagsziffer+=berechnungsArray[i];
		}
		wochentagsziffer = wochentagsziffer%7;

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
