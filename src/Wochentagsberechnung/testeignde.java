package Wochentagsberechnung;

public class testeignde {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
// Seite zum testen: http://www.weltzeituhr.com/infos/wochentage_berechnung.shtml
	// die Ziffern sind eine zuweisung um einfacher zu rechnen
	//eingabe
	int tag=11;
	int monat=5;
	int jahre=00;
	int jahrhundert=20;
	//Methodenaufruf
	int a=Schaltjahr(2000,5); //für die Schalltjahr berechnung benötigt man das volle jahr sowie den monat z.b jan=1 und jahr =2000= Schaltjahr(2000,1)
    int b= Jahrhundert(jahrhundert);
	int c=Jahr(jahre);
	int d=Monat(monat);
	int e=Tag(tag);
	//Ergebnismethode
	gesamtrechnung(e,d,c,b,a);
	}
	
	//Methode zur berechnung des tages 
	public static int Tag(int tag){
	
	int day=tag%7;	
    return day;
	}
	//Methode zur berechnung des Monats Anmerkung im Array "monatsziffer" gibt die tage des monats an wobei null am anfang ein platzhalter ist da der index bei null beginnt für januar aber bei 1
	//Ablauf ist folgenderweise jedem Monat wird eine ziffer zugewiesen
	public static int  Monat(int monat){
		int[] monatsziffer={0,31,28,31,30,31,30,31,31,30,31,30,31};
		int addition=0;
	
	//int	monatrück=0;
	for(int i=0;i<monat;i++){
	 
    addition=(monatsziffer[i]+addition)%7;
	monatsziffer[i]= monatsziffer[i]%7;
		//monatrück=addition;
		
	}
		return addition;
		}
		
	
	//Methode Jahr hier wierd jedem jahr eine Ziffer zugewiesen 
	public static int Jahr(int jahr){
		int jahresziffer=0;
		//int jahrausgabe=0;
		
		jahresziffer=(jahr+(jahr/4))%7;	
		//jahrausgabe=jahresziffer;
		
		return jahresziffer;
		}
	
	// Methode für die Jahrundert Ziffer
	public static int Jahrhundert(int Jahrhundert){
		int Jahrhunderta=0;
	
	Jahrhunderta=(3-(Jahrhundert%4))*2;
	return Jahrhunderta;
		}
	
		
	//Methode zur bestimmung des schalltjahres dabei ist ein Schalltjahr  durch 4 teilbar aber nicht durch 100 außer es ist durch 400 teilbar die erste if Anweisung ist für die berechnung da immer richtig gerechnet wird solange es sich nicht um jan oder febr. des Schalltjahres handelt 
	public static int Schaltjahr(int jahr,int monat){
	int a=5;
		if(monat<=2){ //wenn monat kleiner gleich des 2 monats ist dann führe nachvollgendes aus
	if(jahr%4==0&&jahr%100!=0){ // bestimmt ob es sich um ein schalltjahr handelt
			 a=6;
		}else if(jahr%400==0){  // bestimmt ob es sich um ein schalltjahr handelt
		a=6;
		}else{ //else anweisung für die zweite if verzweigung sonst kein schalltjahr
			a=0;
		}
		
	}else{ //else Anweisung für die erste if Anweisung immer null wenn kein schaltjahr
		a=0;
	}
		return a;
	}
	//Ergebnismethode der gesamtrechnung hier werden sämtliche ziffern addiert wenn schalltjahr dann 6 sonst 0 auch null für die ersten zwei monate jan und febr die summe der ziffern wird mod 7 gerechnet und danach wird das ergebnis ausgegeben
	public static void gesamtrechnung(int tag,int monat,int jahr,int jahrhundert,int schaltjahr){
		String wochentag="";
	int	wochentagziffer=0;
		 wochentagziffer =(tag+monat+jahr+jahrhundert+schaltjahr)%7;
	switch(wochentagziffer){
	case 0:wochentag="Sonntag";
	break;
	case 1:wochentag="Montag";
	break;
	case 2:wochentag="Dienstag";
	break;
	case 3:wochentag="Mittwoch";
	break;
	case 4:wochentag="Donnerstag";
	break;
	case 5:wochentag="Freitag";
	break;
	case 6:wochentag="Samstag";
	break;
	}
	System.out.println("wochentagziffer:"+wochentagziffer+" wochentag:"+wochentag+ " Tag:"+tag+" monat:"+monat+" jahr:"+jahr+" jahrhundert:"+jahrhundert+" schaltjahr:"+schaltjahr);
	}
	
	
}
