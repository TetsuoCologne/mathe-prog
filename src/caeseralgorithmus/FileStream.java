package caeseralgorithmus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class FileStream {
	FileStream(){
		try{
			//FileReader filereader=new FileReader("Test.txt");
			BufferedReader br= new BufferedReader(new FileReader("Test.txt"));
			String b="";
			for(String s=br.readLine();s!=null;s=br.readLine()){
				
				s=s.replace(" ", "");
				s=s.replace("Ä", "AE");
				s=s.replace("Ö", "OE");
				s=s.replace("Ü", "UE");
				s=s.replace("ß", "SZ");
				
				b=b+s;
				//System.out.println(b);
				
				}
			//System.out.println(b);
			
		br.close();
		//filereader.close();
		} catch (FileNotFoundException ex){
			System.err.println("Datei nicht gefunden");
		}catch (IOException ex){
			System.err.println("Lesefehler:"+ex.getLocalizedMessage());
		}
		
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileStream filestream=new FileStream();
	}

}
