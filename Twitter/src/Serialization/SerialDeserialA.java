package Serialization;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialDeserialA {
	static File file = new File("store.bin");

	public static void main(String[] args) throws Exception {
		A a = new A(5, 15, 1.7);
		System.out.print(a.i+" ");
		System.out.print(a.f+" ");
		System.out.print(a.d+" ");
		serA(a);
		A a1 = deserA();
		System.out.println();
		System.out.print(a.i+" ");
		System.out.print(a.f+" ");
		System.out.print(a.d+" ");
	

	}

	protected static A deserA() throws IOException, ClassNotFoundException {
		try(FileInputStream is = new FileInputStream(file);
		ObjectInputStream io = new ObjectInputStream(is);){
		A a1 = (A) io.readObject();

		return a1;}
	}

	protected static void serA(A a) throws IOException {
		try(FileOutputStream fo = new FileOutputStream(file);
		ObjectOutputStream  so = new ObjectOutputStream(fo);){
		so.writeObject(a);}
		//so.close();
		
	}

}
