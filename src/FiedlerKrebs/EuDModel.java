package FiedlerKrebs;
import java.util.Observable;
/**
 * 	Diese Klasse berechnet den größten gemeinsamen Teiler und die Bezout Koeffizienten nach dem Erweiterten
 * 	Euklidischen Algorithmus.
 * 	@author Marc Fiedler, Franziska Krebs
 *
 */
public class EuDModel extends Observable {

	private int a, b, ggt,bezoutA, bezoutB;

	public int getBezoutA() {
		return bezoutA;
	}

	public int getBezoutB() {
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

	// Methode zur Berechnung
	/**
	 * 
	 * @throws ZeroException Bei der Eingabe zweier Nullen wird eine Exception geworfen.
	 * @throws NegativeEingabeException Ist eine der eingegebenen Zahl kleiner als 0, wird ebenfalls eine Exception
	 * geworfen. Die Festlegung des Fehlertextes erfolgt in der Klasse EuDView.
	 */
	public void berechneGgT() throws ZeroException, NegativeEingabeException{

		int q, r, s, t;
		q=0;
		r=0;
		bezoutA=t=1;
		bezoutB=s=0;

		if(a==0 && b==0) throw new ZeroException();
		else if(a<0 || b<0) throw new NegativeEingabeException();
		else{
			while(b>0)
			{
				q=a/b;
				r=a-q*b;
				a=b;
				b=r;
				r=bezoutA-q*s;
				bezoutA=s;
				s=r;
				r=bezoutB-q*t;
				bezoutB=t;
				t=r;
			}
			ggt=a;
		}

	
		setChanged();
		notifyObservers();
	}



}
