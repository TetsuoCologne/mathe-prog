package Zahlendarstellung;

import java.util.Observable;

public class ZahlenModel extends Observable{

	
	private int dual, dez;
	
	public void setDual(int dual){
		this.dual = dual;
	}
	
	public void setDez(int dez){
		this.dez = dez;
	}
	
	public int getDual(){
		return dual;
	}
	
	public int getDez(){
		return dez;
	}
	
	
	
	public void dez2dual(){
		
	}
	
	public void dual2dez(){
		
	}
	
	//Default
	public ZahlenModel(){
		super();
		dez = 0;
		dual = 0;
	}
	
	
	
}
