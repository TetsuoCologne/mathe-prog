package Zahlendarstellung;
import java.util.Observable;

public class ZahlenModel extends Observable{

	//Eingabe und Basen definieren
	private String eingabe, ausgabe;
	private int baseEingabe, baseAusgabe;
	private int [] eingabeArray;
	private char bases []= {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};



	public void setEingabe(String eingabe) throws FalscheEingabeException{

		if(check(eingabe)==false){
			throw new FalscheEingabeException("Der eingegebene String passt nicht zur Basis!");
		}
		else
			this.eingabe = eingabe;
	}



	public String getAusgabe(){
		return ausgabe;
	}

	public void setBaseEingabe(int baseEingabe) throws FalscheBasisException{
		if(baseEingabe<=1){
			throw new FalscheBasisException("Die Basis für die Eingabe sollte mindestens 2 sein!");
		}
		else if(baseEingabe>16){
			throw new FalscheBasisException("Die Basis für die Eingabe sollte nicht größer 16 sein!");
		}
		else
			this.baseEingabe = baseEingabe;
	}

	public void setBaseAusgabe(int baseAusgabe)throws FalscheBasisException{
		if(baseAusgabe<=1){
			throw new FalscheBasisException("Die Basis für die Ausgabe sollte mindestens 2 sein!");
		}
		else if(baseEingabe>16){
			throw new FalscheBasisException("Die Basis für die Ausgabe sollte nicht größer 16 sein!");
		}
		else		
			this.baseAusgabe = baseAusgabe;
	}

	public int getBaseEingabe(){
		return baseEingabe;
	}

	public int getBaseAusgabe(){
		return baseAusgabe;
	}


	private void vorbereiten(String eingabe){
		String sub3;
		eingabeArray= new int[eingabe.length()];


		for(int i=0; i<=(eingabe.length()-1);i++){
			sub3 =eingabe.substring(0+i,i+1);
			switch(eingabe.charAt(i)){
			case 'A':	sub3="10";
			break;
			case'B':    sub3="11";
			break;
			case'C':    sub3="12";
			break;
			case'D':    sub3="13";
			break;
			case'E':    sub3="14";
			break;
			case'F':    sub3="15";
			break;
			}
			int intzahl = Integer.parseInt(sub3);
			eingabeArray[i] =intzahl;
		}

	}

	/**
	 * Diese Methode rechnet nach dem Hornerschema ins 10er System um.
	 * @throws IntegerOverflowException 
	 */
	private int umrechnenDezimal() throws IntegerOverflowException{

		int ergebnis =eingabeArray[0];

		for(int i=1; i <=eingabeArray.length-1; i++) {
			if(ergebnis > ((Integer.MAX_VALUE - eingabeArray[i])/baseEingabe)){
				throw new IntegerOverflowException();
			}ergebnis = ergebnis *  baseEingabe + eingabeArray[i];

		}


		return ergebnis;
	}


	public void umrechnen() throws IntegerOverflowException{
		//ausgabe wieder zurücksetzen, sonst wird beim nochmaligen Klicken was rangehangen
		ausgabe = "";
		int output = 0;
		int mod = 0;
		char hilfsChar = ' ';

		vorbereiten(eingabe);
		output = umrechnenDezimal();
		//div mod
		if(baseAusgabe!=10){

			while(output!=0){

				mod = output%baseAusgabe;
				output = output/baseAusgabe;

				if(mod>9){
					switch(mod){
					case 10:	hilfsChar='A';
					break;
					case 11:    hilfsChar='B';
					break;
					case 12:    hilfsChar='C';
					break;
					case 13:    hilfsChar='D';
					break;
					case 14:    hilfsChar='E';
					break;
					case 15:    hilfsChar='F';
					break;
					}
					ausgabe+=hilfsChar;
				}
				else{
					ausgabe+=mod;
				}
			}
			ausgabe = reverse(ausgabe);
		}
		else ausgabe = String.valueOf(output);

		setChanged();
		notifyObservers();
	}

	private String reverse(String s){
		StringBuffer a = new StringBuffer(s);
		return a.reverse().toString();
	}

	//Default
	public ZahlenModel(){
		super();
		eingabe = "";
		ausgabe = "";
		baseEingabe = 0;
		baseAusgabe = 0;
	}


	private boolean isIn(char input){
		for(int i =0; i<baseEingabe;i++){
			if(input == bases[i]){
				return true;
			}

		}
		return false;
	}

	private boolean check(String eingabe) throws FalscheEingabeException{

		if(eingabe.charAt(0)=='-'){
			throw new FalscheEingabeException("Keine negativen Eingaben!");
		}
		else{	
			for(char c : eingabe.toCharArray()){

				if(isIn(c) == false)
					return false;
			}
		}
		return true;



	}

}
