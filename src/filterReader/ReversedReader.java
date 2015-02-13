package filterReader;

import java.io.CharArrayReader;
import java.io.FilterReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReversedReader extends FilterReader {

	protected ReversedReader(CharArrayReader in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	public String reverse() throws IOException {
		List<Character> chr = new ArrayList<Character>();

		int value = 0;
		while ((value = in.read()) != -1) {
			Character c = (char) value;
			chr.add(c);
		}

		int length = chr.size();
		char[] chars = new char[length];
		int j = 0;
		
		for (int i = length - 1; i >= 0; i--) {
			char c = chr.get(i);
			chars[j] = c;
			j++;
		}

		String result = new String(chars);
		return result;

	}

}
