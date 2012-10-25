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

	public void setExponent(int expo) throws FalscheEingabeException{
		
		if(expo<=0){
			throw new FalscheEingabeException("Der Exponent soll mindestens 1 betragen!");
		}
		else this.exponent=expo;
	}
	public void setFaktor(int faktor) throws FalscheEingabeException {
		if(faktor<=0){
			throw new FalscheEingabeException("Der Faktor soll mindestens 1 betragen!");
		}
		else this.faktor = faktor;
	}

	public void setMod(int mod) throws FalscheEingabeException {
		if(mod<=0){
			throw new FalscheEingabeException("Das m soll mindestens 1 betragen!");
		}
		this.mod = mod;
	}

	
	public int getErgebnis(){
		return erg;
	}

	

	/* Methode, um den Exponenten in eines binäres Array mit führenden Nullen
	 * umzuwandeln
	 * 
	 */

	private int[] exp2bin(){
	
		String exp = Integer.toBinaryString(Integer.valueOf(exponent));
		String bin[] = exp.split("");

		binExpoArray = new int[bin.length-1];
		
		
		for(int i =0;i<binExpoArray.length;i++){
			binExpoArray[i] = Integer.valueOf(bin[i+1]); 
		}

		return binExpoArray;
	}

	public void modularesPotenzieren(){
	
		exp2bin();
		int ergebnis = 1;
		int erg = 1;


		for(int i = 0;i<binExpoArray.length;i++){
			if(binExpoArray[i]==1){
				ergebnis=ergebnis*faktor%mod;
				faktor=faktor*faktor%mod;
				erg = ergebnis;
			}
			else if(binExpoArray[i]==0){
				ergebnis = erg;
				faktor = faktor*faktor%mod;
			}
		}
		this.erg = ergebnis;
		setChanged();
		notifyObservers();
	}


}
