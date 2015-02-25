package IOandNIO;

import java.io.Serializable;

public class A implements Serializable {

	int i;
	transient float f;
	
	public A(int i, float f) {
		this.i = i;
		this.f = f;
	}
}
