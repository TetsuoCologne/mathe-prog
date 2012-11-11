package ISBN;

import java.util.Observable;

public class KorrekteISBNModel extends Observable{

	private String input;
	private int[] isbn;
	private boolean check;


	public KorrekteISBNModel(){
		input = "";
		isbn = new int[10];
		check = false;

	}
	public void setInput(String input){
		this.input = input;

	}

	public boolean getCheck(){
		return check;
	}

	/**
	 * 	Diese Methode schiebt den ISBN-String (welcher noch Bindestriche enthält) in ein char Array.
	 * 
	 */
	private void isbnVorbereiten(){
		String isbnString = input.replaceAll("-", "");

		for(int i = 0;i<isbnString.length();i++){
			if(isbnString.charAt(i)!='X'){
				isbn[i] = Integer.valueOf(isbnString.charAt(i)+"");
			}
			else isbn[i] = 10;
		}
	}


	public void checkISBN(){
		int summe = 0;
		int counter = 10;
		isbnVorbereiten();
		for(int i = 0;i<isbn.length;i++){
			summe+=counter*isbn[i];
			counter--;
		}

		if(summe%11==0)
			check = true;
		else check =false;
		setChanged();
		notifyObservers();


	}


}
