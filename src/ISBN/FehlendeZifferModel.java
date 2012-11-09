package ISBN;

import java.util.Observable;

public class FehlendeZifferModel extends Observable{

	private String input;
	private int ziffer;
	
	public void setInput(String input){
		this.input =input;
	}
	public String getZiffer(){
		return String.valueOf(this.ziffer);
	}
	
}
