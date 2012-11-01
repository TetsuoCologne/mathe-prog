package Wochentagsberechnung;

import java.util.HashMap;
import java.util.HashSet;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		String a = "01.01.1700";
		String[] b  = a.split("\\.");
		int c = Integer.valueOf(b[0]);
		System.out.println(c);
	}

}
