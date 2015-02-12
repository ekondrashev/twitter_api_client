package HW;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

public class copyFiletoFile {
	private static final String filePath1 = "D:\\in.txt";
	private static final String filePath2 = "D:\\out.txt";
	public long crcIn;
	public long crcOut;

	public static void main(String[] args) throws IOException {

		copyFiletoFile(filePath1, filePath2);

	}

	public static void copyFiletoFile(String fileP1, String fileP2)
			throws IOException {
		FileInputStream in = null;

		FileOutputStream out = null;

		try {

			in = new FileInputStream(fileP1);

			out = new FileOutputStream(fileP2);

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

			}
		}
		System.out.println("We did it!");
	}


	public static long getCrc(String filepath){
		CRC32 crc = new CRC32();
		 InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(filepath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		 int cnt;
		   try {
			while ((cnt = inputStream.read()) != -1) {
			 crc.update(cnt);
			   }
		} catch (IOException e) {
			e.printStackTrace();
		}

		return crc.getValue();

	}

}
