package ISBNentwurf;

public class isbnNummer {
	//Seite zum testen:http://www.arndt-bruenner.de/mathe/scripts/pruefziffern.htm#calc
		//ist ein prottyp algorithmus ist noch nicht ganz fertig muss noch den dreher von 5und 6 stelle ausbessern da es dort zu fehlern kommen kann
		//http://www.kurt-zelt.de/files/PZV.pdf
		public static void main(String[] args) {
			// TODO Auto-generated method stub
	String isbn="386680192";
	String vollst‰ndigeIsbn="3423168803";

	    int b=BerechnungPrueffZiffer(isbn);
		System.out.println("1:"+b);
		
		String isbnX=Stringzusammensetzen(isbn);
		System.out.println("2:"+isbnX);
		
		int c=BerechnungPrueffZiffer(isbnX);
		System.out.println("3:"+c);
		
		int e=BerechnungPrueffZiffer(vollst‰ndigeIsbn);
		System.out.println("4:"+e);
		
		//boolean d=
		KorrekteIsbn(vollst‰ndigeIsbn);
		//System.out.println("5:"+d);
		
		Fehlendeziffer("386680192x");
		
		}
		//Methode teilt zuerst den String in seine einzelnen ziffern und wandelt ihn in ein int um danach 
		//wird in der Zeile ergebnis=ergebnis+einzelziffer*(i+1); 1*isbn+2*isbn+3*isbn gerechnet
		//auﬂerhalb der schleife wird dann das endergbis der rechnung von innerhalb der schleife genommen und mod 11 gerechnet
		
		public static int BerechnungPrueffZiffer(String isbn){
			int ergebnis=0;
			int resultat=0;
			int einzelziffer=0;
		
			for(int i=0;i<=isbn.length()-1;i++){
				 einzelziffer=Integer.parseInt(""+isbn.charAt(i));
				
				 ergebnis=ergebnis+einzelziffer*((isbn.length()+1)-i);
			    
			}		
			//Resultat ist die Pr¸fziffer
			ergebnis=ergebnis%11;
			resultat=11-ergebnis;
			//problem bei zehn da hier x hin m¸sste
			if(ergebnis==0){
				resultat=0;
			}
			return resultat;
		}
	   //macht eine konkordination von dem 9 ziffrigen String und der pr¸fziffer(ist optional)
		public static String Stringzusammensetzen(String isbn){
	    String neuerIsbnString;
	    String string = String.valueOf(BerechnungPrueffZiffer(isbn));
		
	    neuerIsbnString=isbn+string;
		return neuerIsbnString;
		
			    }
			   
		//pr¸fung auf korrekte isbn
		
		/*public static void KorrekteIsbn(String vollst‰ndigeIsbn){
			int einzelziffer=0;  	
			int[]IndexZaehler=new int[10];
			int ergebnis=0;
			int resultat=0;
			boolean pr¸fung;
			
			for(int i=0;i<=vollst‰ndigeIsbn.length()-1;i++){
			
				einzelziffer=Integer.parseInt(""+vollst‰ndigeIsbn.charAt(i));	
				IndexZaehler[i]=einzelziffer;
			//System.out.println("indexz‰hler: "+IndexZaehler[i]);
			}
			
			
			for(int j=0;j<=IndexZaehler.length-2;j++){
			
				
				 ergebnis=ergebnis+IndexZaehler[j]*((IndexZaehler.length)-j);
			    //System.out.println("indexz‰hler2:"+ergebnis+"  "+j+" "+IndexZaehler[j]);
			}	
			//Resultat ist die Pr¸fziffer
			ergebnis=ergebnis%11;
			resultat=11-ergebnis;
			//System.out.println("resultat"+resultat+"  "+ergebnis);
			//problem bei zehn da hier x hin m¸sste
			if(ergebnis==0){
				resultat=0;		    	
			}
			
			
			    	if(resultat==IndexZaehler[9]){
			    		pr¸fung=true;
			    	}else{
			    		pr¸fung=false;
			    	
			    	}
			    	System.out.println("iz3: "+IndexZaehler[9]+"resultat: "+resultat+ "pr¸fung: "+pr¸fung);
			  }      
			*/
		
		//alternative
		public static void KorrekteIsbn(String vollst‰ndigeIsbn){
				int stringKonnverter=0;
				String teilstring=vollst‰ndigeIsbn.substring(9,10);

				if (teilstring.equals("X") | teilstring.equals("x"))
					stringKonnverter=10;
				else
					stringKonnverter=Integer.parseInt(teilstring);

				for (int i=1; i<10 ; i++){ 
					teilstring=vollst‰ndigeIsbn.substring(i-1,i);
					stringKonnverter=stringKonnverter+Integer.parseInt(teilstring)*(11-i);
				}

				int rest= stringKonnverter % 11;

				if (rest != 0 | stringKonnverter==0) 
					System.out.println("false");
				else
					System.out.println("true");
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		//wenn eine ziffer fehlt wird die zufehlende ziffer vom computer ermittelt
		public static void Fehlendeziffer(String isbn){
		int einzelziffer=0;	
		int indexSpeicher=0;
		int einzelArray[]=new int[isbn.length()];
		
		//hier wird geschaut wo die ziffer fehlt und wird vorerst mit null initialisiert sp‰ter wird erst ab 1 weiter gez‰hlt da muss noch verbessert werden
		for(int i=0;i<=isbn.length()-1;i++){
			
			String IndexLokalisiererChar=isbn.substring(0+i,i+1);
			if(isbn.charAt(i)=='x'){
			IndexLokalisiererChar="0";
			indexSpeicher=i;
		}
			 
		//ifschleife 
			einzelziffer=Integer.parseInt(IndexLokalisiererChar); 
	   	 einzelArray[i]=einzelziffer;
	   		
		}	//for schleife
		
		
			
			//Die whileschleife l‰sst den IndexSpeicher(merkt sich den Index der fehlenden Ziffer) 9 mal durchlaufen also 1...9
			while(einzelArray[indexSpeicher]<9)	{	
				int result=0;
			//die for schleife dient dazu jedes mal zu rechnen also fehlende ziffer ist gleich 1...9 wenn die while schleife den Indexspeicher neu setzt
				for(int j=0;j<=einzelArray.length-1;j++){
					
					 
					result=result+einzelArray[j]*((einzelArray.length)-j);						 
			
					
					//System.out.println("7:"+result+"  "+j+"  "+einzelArray[j]);
				
				} //for schleife
			 // hier werden die ergebnisse in ein array gesteckt da das einzige ergebnis das richtig sein kann 0 ist wird der indexspeicher auf diesen eintrag gesetzt und ausgegeben
				  //for schleife Anfang 1
				
			    if(result%11==0){
			    	
			    	System.out.println(einzelArray[indexSpeicher]);
			    
			    
			    }//if Anweisung
			  //for schleife ende 1
			    einzelArray[indexSpeicher]=einzelArray[indexSpeicher]+1;
			}//while schleife
	} //Methode
		


	}

