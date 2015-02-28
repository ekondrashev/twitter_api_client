package Serialization;
import java.io.Serializable;

public class A extends B implements Serializable {
	int i;
	float f;

	public A(int i, float f, double d) {
		super(d);
		this.i = i;
		this.f = f;
	}

	public boolean equals(Object obj)
	  {
	    if(obj == this)
	    return true;


	    if(obj == null)
	    return false;

	    if(!(getClass() == obj.getClass()))
	    	return false;
	    else
	    {
	      A tmp = (A)obj;
	      if((tmp.i == this.i) && (tmp.f == this.f) && (tmp.d == this.d)) 
	       return true;
	      else
	       return false;
	    }
	  }
}
