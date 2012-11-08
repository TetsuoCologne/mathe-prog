package IsbnBerechnung;

public class isbnNummer {
	//Seite zum testen:http://www.arndt-bruenner.de/mathe/scripts/pruefziffern.htm#calc
		//ist ein prottyp algorithmus ist noch nicht ganz fertig muss noch den dreher von 5und 6 stelle ausbessern da es dort zu fehlern kommen kann
		//http://www.kurt-zelt.de/files/PZV.pdf
		public static void main(String[] args) {
			// TODO Auto-generated method stub
	String isbn="3866801920";
	String vollständigeIsbn="3866801920";

	    int b=BerechnungPrueffZiffer(isbn);
		System.out.println("1:"+b);
		
		String isbnX=prueffungAufKorrekthewit(isbn);
		System.out.println("2:"+isbnX);
		
		int c=BerechnungPrueffZiffer(isbnX);
		System.out.println("3:"+c);
		
		int e=BerechnungPrueffZiffer(vollständigeIsbn);
		System.out.println("4:"+e);
		
		boolean d=KorrekteIsbn(e);
		System.out.println("5:"+d);
		
		Fehlendeziffer("386680192x");
		
		}
		//Methode teilt zuerst den String in seine einzelnen ziffern und wandelt ihn in ein int um danach 
		//wird in der Zeile ergebnis=ergebnis+einzelziffer*(i+1); 1*isbn+2*isbn+3*isbn gerechnet
		//au�erhalb der schleife wird dann das endergbis der rechnung von innerhalb der schleife genommen und mod 11 gerechnet
		
		public static int BerechnungPrueffZiffer(String isbn){
			int ergebnis=0;
			int resultat=0;
			int einzelziffer=0;
		
			for(int i=0;i<=isbn.length()-1;i++){
				 einzelziffer=Integer.parseInt(""+isbn.charAt(i));
				
				 ergebnis=ergebnis+einzelziffer*(i+1);
			    
			}		
			//Resultat ist die Pr�fziffer
			resultat=ergebnis%11;
			return resultat;
		}
	   //macht eine konkordination von dem 9 ziffrigen String und der pr�fziffer(ist optional)
		public static String prueffungAufKorrekthewit(String isbn){
	    String neuerIsbnString;
	    String string = String.valueOf(BerechnungPrueffZiffer(isbn));
		
	    neuerIsbnString=isbn+string;
		return neuerIsbnString;
		
			    }
			   
		//pr�fung auf korrekte isbn
		
		public static boolean KorrekteIsbn(int vollständigeIsbn){
			    	
			    	
			    	boolean prüfung=true;
			    	if(vollständigeIsbn%11==0){
			    		
			    	}else{
			    		prüfung=false;
			    	}
			    	return prüfung;
			    }

		
		//wenn eine ziffer fehlt wird die zufehlende ziffer vom computer ermittelt
		public static void Fehlendeziffer(String isbn){
		int einzelziffer=0;	
		int indexSpeicher=0;
		int einzelArray[]=new int[isbn.length()];
		
		//hier wird geschaut wo die ziffer fehlt und wird vorerst mit null initialisiert sp�ter wird erst ab 1 weiter gez�hlt da muss noch verbessert werden
		for(int i=0;i<=isbn.length()-1;i++){
			
			String IndexLokalisiererChar=isbn.substring(0+i,i+1);
			if(isbn.charAt(i)=='x'){
			IndexLokalisiererChar="0";
			indexSpeicher=i;
		}  //ifschleife  
			einzelziffer=Integer.parseInt(IndexLokalisiererChar); 
	   	 einzelArray[i]=einzelziffer;
	   		
		}	//for schleife
		
		
			
			//Die whileschleife l�sst den IndexSpeicher(merkt sich den Index der fehlenden Ziffer) 9 mal durchlaufen also 1...9
			while(einzelArray[indexSpeicher]<9)	{
				
				int result=0;
			
			//die for schleife dient dazu jedes mal zu rechnen also fehlende ziffer ist gleich 1...9 wenn die while schleife den Indexspeicher neu setzt
				for(int j=0;j<=einzelArray.length-1;j++){
					
					 
					result=result+einzelArray[j]*(j+1);						 
			} //for schleife
			 
				
			    if(result%11==0){
			    	System.out.println(einzelArray[indexSpeicher]);
			    }//if Anweisung
			
				einzelArray[indexSpeicher]=einzelArray[indexSpeicher]+1;
			}//while schleife
	} //Methode
		


	}
