package ISBN;

import java.util.Observable;

public class KorrekteISBNModel extends Observable{

	private String input;
	private boolean check;
	
	
	public void setInput(String input){
		this.input = input;
	}
	
	public boolean getCheck(){
		return check;
	}
	public void checkISBN(){
		check = false;
		
		setChanged();
		notifyObservers();
		
		
	}


}
