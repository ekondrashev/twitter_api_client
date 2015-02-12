package HW;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class copyFileTest {

	@Test
	public void test() {
		String f1 = "D:\\in.txt";
		String f2 = "D:\\out.txt";
		for (int i = 0; i < 100; i++) {

			try {
				CopyFile.copyFiletoFile(f1, f2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			long crc1 = CopyFile.getCrc(f1);
			long crc2 = CopyFile.getCrc(f2);

			assertEquals(crc1, crc2);
		}
	}

}
