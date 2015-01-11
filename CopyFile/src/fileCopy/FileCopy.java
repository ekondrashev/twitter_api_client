package fileCopy;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.text.DefaultEditorKit.CopyAction;

public class FileCopy {

	public static void main(String[] args) throws IOException {
		// create object file
		File file1 = new File("file1");

		// Create a file using the method
		FileOutputStream createFile = new FileOutputStream(file1);

		// write to a file string data
		DataOutputStream writeData = new DataOutputStream(createFile);

		// write data using the cycle
		int i;
		for (i = 0; i < 5; i++)
			writeData.writeBytes("Test");

		// close the output stream
		writeData.close();

		System.out.println("Absolute path: " + file1.getAbsolutePath());

		System.out.println("Canonical path: " + file1.getCanonicalPath());

		System.out.println("Length: " + file1.length());

		//  Copy the file by creating input and
		// output (byte by byte copy)
	
		// Create an input stream
		FileInputStream inputS = new FileInputStream("file1");

		// Create an output stream
		FileOutputStream outputS = new FileOutputStream("output.txt");

		int nLength;
		byte[] buf = new byte[8000];
		while (true) {
			nLength = inputS.read(buf);
			if (nLength < 0)
				break;
			outputS.write(buf, 0, nLength);
		}
		inputS.close();
		outputS.close();
		
	}
}
