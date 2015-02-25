package IOandNIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialTransient {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileOutputStream f = new FileOutputStream("/tmp/1");
		ObjectOutputStream s = new ObjectOutputStream(f);
		s.writeObject(new A(1, 2f));
		s.flush(); s.close();
			    
		FileInputStream in = new FileInputStream("/tmp/1");
		ObjectInputStream ss = new ObjectInputStream(in);
		A a = (A) ss.readObject();
			    
		System.out.println(a.i);
		System.out.println(a.f);
		ss.close();


	}

}
