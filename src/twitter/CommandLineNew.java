package twitter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineNew {
	
	public static void main(String[] args) {
		
		extract(args);
		
	}
		
	public static Map <String, String> extract (String[] args) {
		
		Map <String, String> commands = new HashMap<String, String>();
			
		for (String arg: args) {
				
			Matcher matcher = Pattern.compile("--(\\w+)=(\\w+)").matcher(arg);
			matcher.find();
			commands.put(matcher.group(1), matcher.group(2));
			
		}
		
		return validate(commands);

	}
	
		
	public static Map <String, String> validate (Map <String, String> commands) {
				
		Set <String> keysGetStatus = new HashSet <String>();
		keysGetStatus.add("cmd");
		keysGetStatus.add("id");
		keysGetStatus.add("user_name");
		
		Set <String> keysPostStatus = new HashSet <String>();
		keysPostStatus.add("cmd");
		keysPostStatus.add("text");
		
		Set <String> keysGetUserTL = new HashSet <String>();
		keysGetUserTL.add("cmd");
		keysGetUserTL.add("id");
		keysGetUserTL.add("limit");
		
		if (commands.keySet().equals(keysGetStatus) && commands.get("cmd").equals("get_status")) {
			return commands;				
		} else if (commands.keySet().equals(keysPostStatus) && commands.get("cmd").equals("post_status")) {
			return commands;
		} else if (commands.keySet().equals(keysGetUserTL) && commands.get("cmd").equals("get_user_timeline")) {
			return commands;
		} else { System.out.println(usage);}
		
	
		return commands;
				
	}
	
	static String usage = "Usage: 1)--cmd=\"get_status\"--user_name=\"Enter user name\"--id=\"Enter message ID\","
			+ "2) --cmd=\"post_status\"--text=\"Enter the message (up to 140 characters)\","
			+ "3)--cmd=\"get_user_timeLine\"--id=\"Enter user ID\"--limit=\"Enter the number of messages (up to 100 messages)\"";

}
