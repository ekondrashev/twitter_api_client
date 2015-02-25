package IOandNIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Date;

public class Serialization {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
			FileOutputStream f = new FileOutputStream("tmp");
			ObjectOutput s = new ObjectOutputStream(f);
			s.writeObject("Today");
			s.writeObject(new Date());
			s.flush();
			s.close();
	

		FileInputStream in = new FileInputStream("tmp");
		ObjectInputStream z= new ObjectInputStream(in);
		String today = (String)z.readObject();
		Date date = (Date)z.readObject();
		z.close();
		System.out.println(today);
		System.out.println(date);

	}

}
