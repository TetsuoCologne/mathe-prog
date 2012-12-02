package CaesarStrichliste;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 
 * @author Socher
 *
 * @param <K>
 * Objekte vom Typ K sollen gez�hlt werden. 
 */


public class Strichliste<K> {
	
	HashMap<K,Integer> sl;
	int total;
	
	/**
	 * Konstruktor
	 */
	
	public Strichliste(){
		 sl = new HashMap<K,Integer>();
		 total = 0;
	 }
	 
	 /**
	  * Konstruktor
	  * @param liste eine Hashtable mit vorgebenen Objekth�ufigkeiten
	  *         Verwendung: f�r die Eingabe der H�ufigkeitstabelle der deutschen Sprache
	  */
	 
	public Strichliste(HashMap<K,Integer> liste){
sl = new HashMap<K,Integer>();
		
		this.sl=liste;
		this.total=0;
		for(K elem:sl.keySet()){
			int a=sl.get(elem);
			this.total+=a;
		}
		 
	 }


	/**
	 * f�gt einen Strich f�r elem hinzu
	 * @param elem ein zu z�hlendes Objekt vom Typ K
	 */
	public void addElem(K elem) {
		if (!sl.containsKey(elem)) sl.put(elem, 1);
		else sl.put(elem, sl.get(elem)+1);
		total++;
	}

	/**
	 * @param elem ein zu z�hlendes Objekt vom Typ K
	 * @return absolute H�ufigkeit von elem
	 */
	public int getFreqAbs(K elem) {
		return (sl.containsKey(elem) ? sl.get(elem) : 0);
	}

	/**
	 * @param elem ein zu z�hlendes Objekt vom Typ K
	 * @return relative H�ufigkeit von elem
	 */
	public double getFreqRel(K elem) {
		return (sl.containsKey(elem) && total != 0 ? sl.get(elem)*1.0/total : 0);
	}
	
	/**
	 * 
	 * @param other eine zweite Strichliste
	 * @return distance die Distanz der beiden Strichlisten (this & other)
	 */
  	public double distance(Strichliste<K> other) {
  		double result=0.0;
 	   for(K i:sl.keySet())
 		    result+= Math.abs(sl.get(i)*1.0/total-other.sl.get(i)*1.0/other.total);
 	   
 	            return result;
  	}
  	

}