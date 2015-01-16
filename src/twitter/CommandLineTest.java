package twitter;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;

public class CommandLineTest extends TestCase {
	
	 @Test
	public void testConstructor () {
		String [] test = {"--cmd=get_status", "--id=753951852654963741"};
		CommandLine cl = new CommandLine(test);
		Map <String, String> expected = new HashMap<String, String>();
		expected.put("cmd", "get_status");
		expected.put("id","753951852654963741");
		assertEquals(cl.commands, expected);
	}
	 @Test
	public void testValidate () {
		String [] test = {"--cmd=post_status", "--text=That is the great distinction between the sexes. Men see objects, women see the relationships between objects."};
		 CommandLine cl2 = new CommandLine(test);
		 Map <String, String> actual = cl2.validate();
		 Map <String, String> expected = new HashMap<String, String>();
		 expected.put("cmd", "post_status");
		 expected.put("text","That is the great distinction between the sexes. Men see objects, women see the relationships between objects.");
		 System.out.println(expected);
		 assertEquals(actual, expected);
	}
	 @Test
	 public void testValidate2 (){
		 String [] test = {"--cmd=get_user_timeline", "--user_name=s_vakarchuk"};
		 CommandLine cl3 = new CommandLine(test);
		 Map <String, String> actual = cl3.validate();
		 Map <String, String> expected = null;
		 assertEquals(actual, expected);
	 }
	 
	 @Test
	 public void testValidate3 (){
		 String [] test = {"--cmd=post_status", "--id=753951852654963741"};
		 CommandLine cl4 = new CommandLine(test);
		 Map <String, String> actual = cl4.validate();
		 Map <String, String> expected = null;
		 assertEquals(actual, expected);
	 }

}
