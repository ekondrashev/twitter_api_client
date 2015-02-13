package filterReader;

import static org.junit.Assert.*;

import java.io.CharArrayReader;
import java.io.IOException;

import org.junit.Test;

public class ReversedReaderTest {

	@Test
	public void testReversedReader() throws IOException {
		char[] input = { 'k', 'o', ' ', 's', 'i', ' ', 't', 's', 'e', 't' };
		CharArrayReader charReader = new CharArrayReader(input);
		ReversedReader revReader = new ReversedReader(charReader);
		String result = revReader.reverse();
		assertEquals(result, "test is ok");
	}
}
