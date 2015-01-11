import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class copyFile {

	public static void main(String[] args)throws IOException {
		File source = new File("C:\\text.txt");
		// check the existence of the file text.txt (created)
		if (source.isFile()) {
			System.out.println("file path: " + source.getPath() + "file name : "
					+ source.getName());
		}
		// create a file object destinated
		File dest = new File("D:\\dest.txt");

		// Create an input stream
		FileInputStream is = new FileInputStream(source);
		try {
			// Create an output stream
			FileOutputStream os = new FileOutputStream(dest);
			// copy the file byte by byte
			try {
				byte[] buffer = new byte[4096];
				int length;
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
			} finally {
				os.close();
			}
		} finally {
			is.close();
		}
		if (dest.isFile()) {
			System.out.print("file path: " + dest.getPath() + " file name: "
					+ dest.getName());
		}
	}
}
