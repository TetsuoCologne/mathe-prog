package Zahlendarstellung;

import java.util.Observable;
/**
 * @param args
 * Diese Klasse stellt das Model für die modulare Potenz dar. 
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



	/** Methode, um den Exponenten in eines binäres Array umzuwandeln.
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

	/**
	 * Führt den Algorithmus zur Berechnung aus.
	 * @throws IntegerOverflowException Wird geworfen, wenn bei der Quadrierung der Faktoren der Integer überläuft.
	 */

	public void modularesPotenzieren() throws IntegerOverflowException{

		exp2bin();
		int ergebnis = 1;
		
		if(faktor%mod==0){
			ergebnis = 0;
		}
		else{
			//erster Durchlauf
			if(binExpoArray[0]==1){
				ergebnis=ergebnis*faktor%mod;
			}
			//restlichen Durchläufe
			for(int i = 1;i<binExpoArray.length;i++){

				if(faktor ==0){
					ergebnis = 0;
				} 
				else{
					if(faktor>Integer.MAX_VALUE/faktor){
						throw new IntegerOverflowException("Der Faktor ist zu groß!");
					}

					if(binExpoArray[i]==1){	
						faktor=faktor*faktor%mod;
						ergebnis=ergebnis*faktor%mod;
					}
					else {
						faktor = faktor*faktor%mod;
					}
				}
			}
		}
		this.erg = ergebnis;
		setChanged();
		notifyObservers();
	}


}
