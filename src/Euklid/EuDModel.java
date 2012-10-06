package Euklid;
import java.util.Observable;

import Exceptions.ZeroException;

public class EuDModel extends Observable {
		
	int a, b, ggt;	
	
	
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

	public  int ggt() throws ZeroException {
		
		ggt = 0;
		if(a== 0 && b == 0) throw new ZeroException();
		
		
	      setChanged();
	      notifyObservers();
		return ggt;
	}
}
