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
	private int jahrhundert;
	private String wochentag;
	private int [] berechnungsArray;
	
	private HashMap<Integer, Integer> month = new HashMap<>();

	public void setTag(int tag){
		this.tag = tag;
	}

	public void setMonat(int monat){
		this.monat = monat;
	}


	//Jahr und Jahrhundert werden in diesem Setter gesetzt
	public void setJahrKomplett(String jahr){
		char [] jahrChar = jahr.toCharArray();
		this.jahr = Integer.valueOf(String.copyValueOf(jahrChar, 0, 2));
		this.jahrhundert = Integer.valueOf(String.copyValueOf(jahrChar, 2, 2));

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


	//int tag=11;
	//int monat=5;
	//int jahre=00;
	//int jahrhundert=20;
	//Methodenaufruf
	//int a=Schaltjahr(2000,5); //f�r die Schalltjahr berechnung ben�tigt man das volle jahr sowie den monat z.b jan=1 und jahr =2000= Schaltjahr(2000,1)
	//int b= Jahrhundert(jahrhundert);
	//int c=Jahr(jahre);
	//int d=Monat(monat);
	//int e=Tag(tag);
	//Ergebnismethode
	//gesamtrechnung(e,d,c,b,a);


	//Methode zur Berechnung des Tages 
	private void berechneTagesZiffer(){
		berechnungsArray[0] = tag%7;
	}

	//	
	//	public static int Tag(int tag){
	//	
	//	int day=tag%7;	
	//    return day;
	//	}

	//Methode zur berechnung des Monats Anmerkung im Array "monatsziffer" gibt die tage des monats an wobei null am anfang ein platzhalter ist da der index bei null beginnt f�r januar aber bei 1
	//Ablauf ist folgenderweise jedem Monat wird eine ziffer zugewiesen
	
	private void berechneMonat(){
		berechnungsArray[1] = month.get(monat);
		  
	}
//	public static int  Monat(int monat){
//		int[] monatsziffer={0,31,28,31,30,31,30,31,31,30,31,30,31};
//		int addition=0;
//
//		//int	monatr�ck=0;
//		for(int i=0;i<monat;i++){
//
//			addition=(monatsziffer[i]+addition)%7;
//			monatsziffer[i]= monatsziffer[i]%7;
//			//monatr�ck=addition;
//
//		}
//		return addition;
//	}


	//Methode Jahr hier wierd jedem jahr eine Ziffer zugewiesen 
	private void berechneJahr(){
		berechnungsArray[2] = (jahr+(jahr/4))%7;

	}

	//	public static int Jahr(int jahr){
	//		int jahresziffer=0;
	//		//int jahrausgabe=0;
	//		
	//		jahresziffer=(jahr+(jahr/4))%7;	
	//		//jahrausgabe=jahresziffer;
	//		
	//		return jahresziffer;
	//		}



	// Methode für die Jahrundert Ziffer
	private void berechneJahrhundert(){
		berechnungsArray[3] = (3-(jahrhundert%4))*2;
	}

	//	
	//	public static int Jahrhundert(int Jahrhundert){
	//		int Jahrhunderta=0;
	//	
	//	Jahrhunderta=(3-(Jahrhundert%4))*2;
	//	return Jahrhunderta;
	//		}
	//	


	private void checkSchaltjahr(){
		int komplettesJahr = Integer.valueOf(String.valueOf(jahr).concat(String.valueOf(jahrhundert)));
		if(monat == 1 ||monat ==0){
			if(komplettesJahr%4==0 && komplettesJahr%100!=0){
				if(komplettesJahr%400==0){
					berechnungsArray[4]=6;
				}
				else{
					berechnungsArray[4]=0;
				}
			}
			else{
				berechnungsArray[4]=0;
			}
			
		}
	}
	//Methode zur bestimmung des schalltjahres dabei ist ein Schalltjahr  durch 4 teilbar aber nicht durch 100 au�er es ist durch 400 teilbar die erste if Anweisung ist f�r die berechnung da immer richtig gerechnet wird solange es sich nicht um jan oder febr. des Schalltjahres handelt 
//	public static int Schaltjahr(int jahr,int monat){
//		int a=5;
//		if(monat<=2){ //wenn monat kleiner gleich des 2 monats ist dann f�hre nachvollgendes aus
//			if(jahr%4==0&&jahr%100!=0){ // bestimmt ob es sich um ein schalltjahr handelt
//				a=6;
//			}else if(jahr%400==0){  // bestimmt ob es sich um ein schalltjahr handelt
//				a=6;
//			}else{ //else anweisung f�r die zweite if verzweigung sonst kein schalltjahr
//				a=0;
//			}
//
//		}else{ //else Anweisung f�r die erste if Anweisung immer null wenn kein schaltjahr
//			a=0;
//		}
//		return a;
//	}
	//Ergebnismethode der gesamtrechnung hier werden s�mtliche ziffern addiert wenn schalltjahr dann 6 sonst 0 auch null f�r die ersten zwei monate jan und febr die summe der ziffern wird mod 7 gerechnet und danach wird das ergebnis ausgegeben

	public void wochenTagsBerechnung(){
		berechneTagesZiffer();
		berechneMonat();
		berechneJahr();
		berechneJahrhundert();
		checkSchaltjahr();
		
		int wochentagsziffer = 0;
		for(int i = 0;i<berechnungsArray.length;i++){
			wochentagsziffer+=berechnungsArray[i];
		}
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
	
//	public static void gesamtrechnung(int tag,int monat,int jahr,int jahrhundert,int schaltjahr){
//		String wochentag="";
//		int	wochentagziffer=0;
//		wochentagziffer =(tag+monat+jahr+jahrhundert+schaltjahr)%7;
//		
//		//System.out.println("wochentagziffer:"+wochentagziffer+" wochentag:"+wochentag+ " Tag:"+tag+" monat:"+monat+" jahr:"+jahr+" jahrhundert:"+jahrhundert+" schaltjahr:"+schaltjahr);
//	}


}
