package ISBN;

import java.util.Observable;

public class FehlendeZifferModel extends Observable{

	private String input;
	private int ziffer;
	private String result;
	private int zifferIndex;
	private int [] isbn;

	public FehlendeZifferModel(){
		input =" ";
		ziffer = 0;
		zifferIndex = 0;
		isbn = new int[10];
	}

	public void setInput(String input){
		this.input =input;
	}

	public String getResult(){
		return this.result;
	}


	/**
	 * 	Diese Methode iteriert den eingegeben String durch und erlaubt nur ein '*' als Eingabe.
	 * 	Weiterhin darf nur an der letzten Stelle ein 'X' eingegeben werden.
	 * @return
	 * @throws FalscheEingabeException 
	 */
	public void checkEingabe() throws FalscheEingabeException{
		boolean correctStar =true;
		boolean correctXPosition = true;
		int counterStar = 0;
		for(int i = 0;i<input.length();i++){
			if(input.charAt(i) == '*'){
				counterStar++;	
			}
		}

		for(int i = 0;i<input.length()-1;i++){
			if(input.charAt(i) == 'X'){
				correctXPosition = false;
			}
		}
		if(counterStar>=2) throw new FalscheEingabeException("Bitte nur ein * !"); 
		if(!correctStar || !correctXPosition) throw new FalscheEingabeException("Ein X ist nur an der letzten Stelle erlaubt!");
	}

	private void isbnVorbereiten(){
		String isbnString = input.replaceAll("-", "");

		for(int i= 0;i<isbnString.length();i++){
			if(isbnString.charAt(i)!= '*' && isbnString.charAt(i)!= 'X'){
				isbn[i] = Integer.valueOf(isbnString.charAt(i) +"");
			}else if(isbnString.charAt(i) == '*'){
				isbn[i]= 0;
				zifferIndex = 10-i;
			}else if(isbnString.charAt(i) =='X'){
				isbn[i] = 10;
			}
		}

	}

	public void computeMissingDigit(){
		isbnVorbereiten();
		int summe = 0;
		int counter = 10;
		isbnVorbereiten();
		

		for(int i = 0;i<isbn.length;i++){
			summe+=counter*isbn[i];
			counter--;
		}
		
		for(int i = 0;i<11;i++){
			if((summe+(zifferIndex*i))%11==0){
				ziffer= i;
			}
		}
		if(ziffer == 10){
			result = "X";
		}
		else result = String.valueOf(ziffer);
		setChanged();
		notifyObservers();
		
	}

}
