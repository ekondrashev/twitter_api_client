package twitter;

import java.util.HashSet;
import java.util.Set;

public class Test {
	public static void main (String args[]) {
		
		String test  = ("--cmd=getStatus--id=57687678");
		Set <String> actual = CallTwitterClient.extractCommands(test);
		
		Set<String> expected = new HashSet<String>();
		expected.add("getStatus");
		expected.add("57687678");
		if (actual.equals(expected)) {
			System.out.println("Yes");
		} else {System.out.println("No");}
		
	}
}
