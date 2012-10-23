package Zahlendarstellung;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	
		int exp = 75;
		String ex = Integer.toBinaryString(exp);
		String l[] = ex.split(""); 
		System.out.println(l.length);
		for(int i = 0;i<l.length;i++){
			System.out.println(l[i]);
		}

		int [] k = new int [31];
		//alles auf 0 setzen
		for(int i = 0;i<k.length;i++){
			k[i] = 0;
		}
		System.out.println();

		int counter = 1;
		for(int i = ((k.length-l.length)+1);i<k.length;i++){
			k[i] = Integer.valueOf(l[counter]);
			counter++;
		}



		for( int b : k){
			System.out.println(b);
		}




	}




}
