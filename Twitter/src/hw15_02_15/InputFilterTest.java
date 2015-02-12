package hw15_02_15;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class InputFilterTest {

	@Test
	public void testMain() throws IOException {
		String sample="12345start678910 !!! +++";
		String orig="+++ !!! 019876trats54321";
		String res = InputFilter.ConvertText(sample);

		assertEquals(res, orig);
		
	}

}
