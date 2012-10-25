package Zahlendarstellung;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		int exp = 84;
		String expo = Integer.toBinaryString(exp);
		//System.out.print(expo);
		String bin[] = expo.split("");
		for( String a : bin){
			System.out.print(a);
		}
		System.out.println(bin[0]);
		int []binExpoArray = new int[bin.length-1];
		
		
		for(int i =0;i<binExpoArray.length;i++){
			binExpoArray[i] = Integer.valueOf(bin[i+1]); 
		}
		
		System.out.println(2*2%3);
		System.out.println((2*2)%3);

		
//		for(int j : binExpoArray){
//			System.out.println(j);
//		}


	}




}
