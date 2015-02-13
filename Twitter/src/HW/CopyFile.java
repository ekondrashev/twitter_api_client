package HW;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class CopyFile {

    public static void main(String[] args) throws IOException {


        FileInputStream in = null;

        FileOutputStream out = null;


        try {

            in = new FileInputStream("C:\\in.txt");

            out = new FileOutputStream("C:\\out.txt");

            int c;

			byte[] buffer = new byte[1024];

            while ((c = in.read(buffer)) != -1) {
            	out.write(buffer, 0, c);
 

            }

        } finally {

            if (in != null) {

                in.close();

            }

            if (out != null) {

                out.close();

            }}}

	public static void copyFiletoFile(String f1, String f2) {
		// TODO Auto-generated method stub
		
	}}

