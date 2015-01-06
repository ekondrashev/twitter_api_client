package twitter;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;


public class CallTwitterClientTest extends TestCase {
	
		public void testCallTwitterClient () {
		String test  = ("--cmd=getStatus--id=57687678");
		
		//Set <String> actual = CallTwitterClient.extractCommands(test);
	 
		Set<String> expected = new HashSet<String>();
		expected.add("getStatus");
		expected.add("57687678");
		
		//assertEquals(actual, expected);
		
				
	}

	
}
