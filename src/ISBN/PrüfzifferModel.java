package ISBN;

import java.util.Observable;
import java.util.Observer;

public class PrüfzifferModel extends Observable{

	private String input;
	private int [] isbn;
	private String ergebnisZiffer;
	private int pruefziffer;

	public PrüfzifferModel(){
		input = "";
		isbn = new int[9];
		pruefziffer = 0;
	}

	public int getPruefziffer(){
		return this.pruefziffer;
	}


	public void setInput(String input){
		this.input = input;
	}

	public String getErgebnisZiffer(){
		return ergebnisZiffer;
	}


	public void berechnePruefziffer(){
		String isbnString = input.replaceAll("-", "");
		int counter  = 10;
		pruefziffer = 0;
		for(int i = 0;i<isbnString.length();i++){
			isbn[i] =Integer.valueOf(isbnString.charAt(i)+"");
		}
		
		for(int i = 0;i<isbn.length;i++){
			pruefziffer+=counter*isbn[i];
			counter--;
			
		}
		
		System.out.println(pruefziffer);
		pruefziffer = pruefziffer%11;
		pruefziffer = 11-pruefziffer;
		System.out.println(pruefziffer);
		if(pruefziffer == 10){
			ergebnisZiffer = "X";
			System.out.println("10!!!!");
		}
		if(pruefziffer == 11){
			ergebnisZiffer ="0";
		}
		if(pruefziffer <10){
			ergebnisZiffer = String.valueOf(pruefziffer);
		}
		
		setChanged();
		notifyObservers();
	}


}
