package Zahlendarstellung;

import java.util.Observable;

public class ZahlenModel extends Observable{

	//Eingabe und Basen definieren
	private String eingabe, ausgabe;
	private int baseEingabe, baseAusgabe;
	private char bases []= {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};



	public void setEingabe(String eingabe) throws FalscheEingabeException{
		if(check()==false){
			throw new FalscheEingabeException();
		}
		else
		this.eingabe = eingabe;
	}

	public String getAusgabe(){
		return ausgabe;
	}

	public void setBaseEingabe(int baseEingabe){
		this.baseEingabe = baseEingabe;
	}

	public void setBaseAusgabe(int baseAusgabe){
		this.baseAusgabe = baseAusgabe;
	}

	public int getBaseEingabe(){
		return baseEingabe;
	}

	public int getBaseAusgabe(){
		return baseAusgabe;
	}


	public void umrechnen(){ 

	}


	//Default
	public ZahlenModel(){
		super();
		eingabe = "0";
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

	private boolean check(){
	
		for(char c : eingabe.toCharArray()){
			if(isIn(c) == false)
				return false;
		}
			return true;
		
		
		
	}

}
