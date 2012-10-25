package Zahlendarstellung;

import java.util.Observable;
/**
 * @param args
 */

public class PotenzModel extends Observable{

	private int faktor;	//basis
	private int mod;	//modulo
	private int exponent; //exponent
	private int erg;
	private int[] binExpoArray; // gefüllt mit Exponent in Binärform

	public PotenzModel(){
		faktor = 0;
		mod = 0;
		exponent = 0;
		erg = 0;
		
	}
	
	/**
	 * 
	 * @param expo
	 * @throws FalscheEingabeException Fängt die Eingabe von Werten, die kleiner gleich 0 sind, ab.
	 */
	public void setExponent(int expo) throws FalscheEingabeException{
		
		if(expo<=0){
			throw new FalscheEingabeException("Der Exponent soll mindestens 1 betragen!");
		}
		else this.exponent=expo;
	}
	
	/**
	 * 
	 * @param faktor
	 * @throws FalscheEingabeException Fängt die Eingabe von Werten <=0 ab.
	 */
	public void setFaktor(int faktor) throws FalscheEingabeException {
		if(faktor<=0){
			throw new FalscheEingabeException("Der Faktor soll mindestens 1 betragen!");
		}
		else this.faktor = faktor;
	}

	/**
	 * 
	 * @param mod
	 * @throws FalscheEingabeException Fängt die Eingabe von Werten <=0 ab.
	 */
	public void setMod(int mod) throws FalscheEingabeException {
		if(mod<=0){
			throw new FalscheEingabeException("Das m soll mindestens 1 betragen!");
		}
		this.mod = mod;
	}

	
	public int getErgebnis(){
		return erg;
	}

	

	/* Methode, um den Exponenten in eines binäres Array umzuwandeln.
	 * 
	 */
	private int[] exp2bin(){
	
		String exp = Integer.toBinaryString(Integer.valueOf(exponent));
		//String umdrehen für die Berechnung
		StringBuffer expo = new StringBuffer(exp).reverse();
		
		String bin[] = expo.toString().split("");
		
		binExpoArray = new int[bin.length-1];	
		for(int i =0;i<binExpoArray.length;i++){
			binExpoArray[i] = Integer.valueOf(bin[i+1]); 
		}

		return binExpoArray;
	}

	/*	Führt den Algorithmus zur Berechnung des Ergebnisses aus. 
	 * 
	 */
	public void modularesPotenzieren(){
	
		exp2bin();
		int ergebnis = 1;
		int auxErg = 1;
	
		if(binExpoArray[0]==1){
			ergebnis=ergebnis*faktor%mod;
		}

		for(int i = 1;i<binExpoArray.length;i++){
		
			if(binExpoArray[i]==1){	
				faktor=faktor*faktor%mod;
				ergebnis=ergebnis*faktor%mod;
				auxErg = ergebnis;

			}
			else {
				ergebnis = auxErg;
				faktor = faktor*faktor%mod;
	
			}
		}
		this.erg = ergebnis;
		setChanged();
		notifyObservers();
	}


}
