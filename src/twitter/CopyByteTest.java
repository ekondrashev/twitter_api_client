package twitter;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;

public class CopyByteTest extends TestCase {
	public void testCopy() throws IOException {
		CopyBytes.copy("F:/xanadu.txt", "F:/outagain.txt");
		File source = new File("F:/xanadu.txt");
		File finite = new File("F:/outagain.txt");
		String f1 = Long.toString(FileUtils.checksumCRC32(source));
		String f2 = Long.toString(FileUtils.checksumCRC32(finite));

		assertEquals(f1, f2);

	}

}
