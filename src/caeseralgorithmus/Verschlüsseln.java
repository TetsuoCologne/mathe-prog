package caeseralgorithmus;
public class Verschlüsseln {
public static void main(String[] args){
	
	String orgT="ABERICHDOCHNICHT";
	String b=caesar(orgT,3);
System.out.println(b);

}
  
	
	public static String caesar(String klartext, int key){
    
    klartext = klartext.toUpperCase();

    
    String codierterText = "";

    
    for (int i = 0; i < klartext.length(); i++){
      
      char zeichen = klartext.charAt(i);

      
      if (zeichen == ' ');
      
      else codierterText = codierterText + (char)((zeichen + key-65)%26+65);
    }
    return  codierterText;
  }
}
