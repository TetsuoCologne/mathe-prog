package Euklid;
import java.util.Observable;
/**
 * 	Diese Klasse führt die Berechnung des ggT und der Bezout Koeffizienten nach dem Erweiterten Euklidischen 
 * 	Algorithmus durch. 
 * @author franzi
 *
 */

public class EuDModel extends Observable {

	private int a, b, ggt, bezoutA, bezoutB;	

	public int getBezoutA(){
		return bezoutA;
	}

	public int getBezoutB(){
		return bezoutB;
	}


	public void setA(int a) {
		this.a = a;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getGgt() {
		return ggt;
	}

	public EuDModel(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public EuDModel() {
		super();
		a = 0;
		b = 0;
	}

	//Berechnung des ggTs
	public  int ggt() throws ZeroException, NegativeException {

		ggt = 0;

		if(a== 0 && b == 0) throw new ZeroException();

		else if(a<0 || b<0) throw new NegativeException();
		else
		{
			while(b>0){
				int abbruch = a%b;
				a = b;
				b = abbruch;

			}
			ggt = a;

		}

		setChanged();
		notifyObservers();
		return ggt;
	}
}
