package caeseralgorithmus;
import java.util.*;
 

public class CaesarCodeHaeufigkeit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	

String geheimWort="LFKZRKQHDXIHLQHULQVHOLPSDCLILN";
int b[]=chiffre(geheimWort);
int a =maxEqual(b);
String c=Berechnung(a,geheimWort);
System.out.println(c);




  /*  HashMap<String,Double> map=new HashMap<String,Double>();
	map.put("A", new Double(6.47));
    map.put("B", new Double(1.93));
    map.put("C", new Double(2.68));
    map.put("D", new Double(4.83));
    map.put("E", new Double(17.48));
    map.put("F", new Double(1.65));
    map.put("G", new Double(3.06));
    map.put("H", new Double(4.23));
    map.put("I", new Double(7.73));
    map.put("J", new Double(0.27));
    map.put("K", new Double(1.46));
    map.put("L", new Double(3.49));
    map.put("M", new Double(2.58));
    map.put("N", new Double(9.84));
    map.put("O", new Double(2.98));
    map.put("P", new Double(0.96));
    map.put("Q", new Double(0.02));
    map.put("R", new Double(7.54));
    map.put("S", new Double(6.83));
    map.put("T", new Double(6.13));
    map.put("U", new Double(4.17));
    map.put("V", new Double(0.94));
    map.put("W", new Double(1.48));
    map.put("X", new Double(0.04));
    map.put("Y", new Double(0.08));
    map.put("Z", new Double(1.14));   
	 */
	}
public static int [] chiffre(String geheimCode){
	char einzelChar;
	int einzelArray[]=new int[geheimCode.length()];
	int einzelInt;
	int einzelArrayZwei[]=new int[geheimCode.length()];
	for(int i=0;i<geheimCode.length();i++){
	
	einzelChar=geheimCode.charAt(i);
	 einzelInt=(int)einzelChar;
	
	einzelArray[i]=einzelInt;
	}
	Arrays.sort(einzelArray);
for(int n=0;n<einzelArray.length;n++){
	einzelArrayZwei[n]=einzelArray[n];
}
return einzelArrayZwei;
}
	
public static int  maxEqual (int [] a){
	//int j=2;  
	int zaehler = 0;
	//int merkerarray[]=new int[j];  
	int max = 0;
		  int merker;
		 int wertMerker=0;
		if(a.length>0)                  //bei einem leeren Array kommt hier 0 raus, bei deiner Implementierung eine Fehlermeldung
		{
		    merker=a[0];                //Erste Zahl merken
		 
		    for(int i=0; i<a.length; i++)
		    {
		        if(a[i]==merker)        //Zahl[i] mit Merker vergleichen
		        {
		            zaehler++;      //Z�hler erh�hen
		            if(zaehler>max)     //Ist ein neues Maximum erreicht?
		            {		            	
		            		max=zaehler;
		            		//j++;
		            	    //neues Maximum setzten
		          
		                   wertMerker=a[i];	           		           
		           // merkerarray[j]=wertMerker;
		            
		            
		            }
		        }else               //Die gleiche Zahlenreihe h�rt auf
		        {
		            //j=1;
		        	merker=a[i];        //Die Aktuelle Zahl merken
		            zaehler=1;      //Z�hler zur�cksetzten
		        }
		    }
		}
		return wertMerker;   
}
	
public static  String Berechnung(int wert,String CodeWort){
	String Alphabet="ENIRSATDHULGOCMBFWKZPVJYXQ";
	char einzelChar;
	int einzelArray[]=new int[Alphabet.length()];
	int einzelInt;
	int einzelArrayZwei[]=new int[Alphabet.length()];
	int ergebnis=0;
	int resultat=0;
	String originalText="";
	int i=2;
	
	einzelChar=Alphabet.charAt(i);
	 einzelInt=(int)einzelChar;
	
	einzelArray[i]=einzelInt;
	if(wert>=einzelArray[i]){
		ergebnis=wert-65;
		resultat=einzelArray[i]-65;
		resultat=ergebnis-resultat;
	}
	if(wert<einzelArray[i]){
		ergebnis=wert-65;
		resultat=einzelArray[i]-65;
		resultat=resultat-ergebnis;
	}
	
	
	for (int j = 0; j < CodeWort.length(); j++){
	     
		originalText =originalText+  (char)((CodeWort.charAt(j) + 26-resultat-65)%26+65);
	    
	}
	
	
	return originalText;
	
	
}
	
}
