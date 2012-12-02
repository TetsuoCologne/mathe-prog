package CaesarStrichliste;
import java.util.Arrays;
import java.util.HashMap;


public class Testdriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Character,Integer>map=new HashMap<Character,Integer>();
		map.put('A', new Integer(647));
		map.put('B', new Integer(193));
		map.put('C', new Integer(268));
		map.put('D', new Integer(483));
		map.put('E', new Integer(1748));
		map.put('F', new Integer(165));
		map.put('G', new Integer(306));
		map.put('H', new Integer(423));
		map.put('I', new Integer(773));
		map.put('J', new Integer(27));
		map.put('K', new Integer(146));
		map.put('L', new Integer(349));
		map.put('M', new Integer(258));
		map.put('N', new Integer(984));
		map.put('O', new Integer(298));
		map.put('P', new Integer(96));
		map.put('Q', new Integer(2));
		map.put('R', new Integer(754));
		map.put('S', new Integer(683));
		map.put('T', new Integer(613));
		map.put('U', new Integer(417));
		map.put('V', new Integer(94));
		map.put('W', new Integer(148));
		map.put('X', new Integer(4));
		map.put('Y', new Integer(8));
		map.put('Z', new Integer(114));  
		
		
		//Einfügen der einzelnen elemente der chiffre char
		Strichliste<Character> test = new Strichliste<Character>();
		String a="ebtjtufjouftu";
		a = a.toUpperCase();
		for(int i=0; i < a.length(); i++){
			int b = a.charAt(i);
			test.addElem((char)b);
		}
		
		
		HashMap<Double,Integer>mapThree=new HashMap<Double,Integer>();
		
		int w=0;
		double[] Array = new double[26];
		for(int j=0;j<26;j++){
			//Aufbau der Strichliste die 26 mal rotiert um die chiffre Strichliste
			Strichliste<Character> deutsch = new Strichliste<Character>(map);
			HashMap<Character,Integer>mapTwo=new HashMap<Character,Integer>();
			
			for(int i:map.keySet()){
		
			int d=map.get((char)i);
			int f=i+1;
			if(f>90){
				f=65;
			}
			char h=(char)f;
			mapTwo.put(h,d);
		
		}
		map=mapTwo;
		double l =test.distance (deutsch);
		//System.out.println("test:"+l+"  "+map.entrySet()+"   "+mapTwo.entrySet()+"   "+w++);
		Array[j]=l;
	  // System.out.println(Array[j]+"  "+w++);
		
		}
	
		for(int i=0;i<26;i++){
	mapThree.put(Array[i], i);
		
		}
		//System.out.println(mapThree.entrySet());
		
		//Sortieren der wahrscheinlichkeiten
		double[] q=new double[26];
		  for(int i=0;i<26;i++){
			  Arrays.sort(Array);
			 // System.out.println(mapThree.get(Array[i]));  
			  q[i]=Array[i];  
		  }
		  a = a.toUpperCase();
		  for(int i=0;i<26;i++){
		  for(int i1=0; i1 < a.length(); i1++){
				double b = a.charAt(i1);
			
			//Einschranken der Char werte zum beispiel Z ist A und kleiner A ist Z also einen Alphabetischen Kreis erzeugen	
			b=b-mapThree.get(q[i]);
			if(b>=91){
				b=65+(b-91);			
			}
			if(b<=64){
				b=90-(65-b);
			}
			System.out.println((char)b);
		}}
		
	}

}
