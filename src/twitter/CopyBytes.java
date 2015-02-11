package twitter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyBytes {
	public static void copy(String input, String output) throws IOException {

		try (FileInputStream in = new FileInputStream(input);
				FileOutputStream out = new FileOutputStream(output);) {
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);

			}
		}
	}

}
