package hw15_02_15;

//import java.io.ByteArrayOutputStream;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
import java.util.List;

public class InputFilter {

	public static void main(String[] args) throws IOException {

		// String a = "тестовое сообщение!";
		String a = "12345start678910 !!! +++";
		System.out.println(ConvertText(a));
	}

	public static String ConvertText(String str) throws IOException {

		// InputStream isr = ((InputStream) str.chars());
		StringBuilder br = new StringBuilder();
		FilterReader fr = null;
		Reader r = null;
		char[] cbuf = new char[5]; // special for test
		List<Character> arr = new ArrayList<Character>();

		int i = 0;

		try {
			// create new reader
			r = new StringReader(str);

			// create new filter reader
			fr = new FilterReader(r) {
			};

			while (i != -1) { //read and made list of all char
				i = fr.read(cbuf);
				for (int k =0; i!=-1 && k<i; k++)
					arr.add(new Character(cbuf[k]));
			}
			for (int m = arr.size() - 1; m >= 0; m--) {
				//System.out.print(arr.get(m));
				br.append(arr.get(m));
			}

		} catch (Exception e) {

			// if any I/O error occurs
			e.printStackTrace();
		} finally {

			// releases system resources associated with this stream
			if (r != null)
				r.close();
			if (fr != null)
				fr.close();
		}
		return br.toString();
	}
}
