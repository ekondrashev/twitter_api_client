package twitter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.auth.AuthenticationException;

class CommandLine {
						
	static Map <String, String> extract (String[] args) throws AuthenticationException {
		
		Map <String, String> commands = new HashMap<String, String>();			
		
		for (String arg: args) {
			Matcher matcher = Pattern.compile("--(\\w+)=(\\w+)").matcher(arg);
			matcher.find();
			commands.put(matcher.group(1), matcher.group(2));
			
		}
		
		return validate(commands);

	}
	
		
	static Map <String, String> validate (Map <String, String> commands) throws AuthenticationException{
				
		Set <String> keysGetStatus = new HashSet <String>();
		keysGetStatus.add("cmd");
		keysGetStatus.add("id");
				
		Set <String> keysPostStatus = new HashSet <String>();
		keysPostStatus.add("cmd");
		keysPostStatus.add("text");
		
		Set <String> keysGetUserTL = new HashSet <String>();
		keysGetUserTL.add("cmd");
		keysGetUserTL.add("user_name");
		keysGetUserTL.add("limit");
		
		if (commands.keySet().equals(keysGetStatus) && commands.get("cmd").equals("get_status")) {
			return forTwitterClient (commands);				
		} else if (commands.keySet().equals(keysPostStatus) && commands.get("cmd").equals("post_status")) {
			return forTwitterClient (commands);
		} else if (commands.keySet().equals(keysGetUserTL) && commands.get("cmd").equals("get_user_timeline")) {
			return forTwitterClient (commands); }
		
	
		return forTwitterClient (null);
				
	}
	
	static Map<String, String> forTwitterClient (Map <String, String> commands) throws AuthenticationException{
		
		Loader loader = new Loader();
		
		if (commands != null) {
			loader.callTwitterClient(commands);
		} else {System.out.println(usage);}
		
		return null;
								
	}
	
	static String usage = "Usage: 1)--cmd=\"get_status\"--id=\"Enter message ID\","
			+ "2) --cmd=\"post_status\"--text=\"Enter the message (up to 140 characters)\","
			+ "3)--cmd=\"get_user_timeline\"--user_name=\"Enter user name\"--limit=\"Enter the number of messages (up to 100 messages)\"";
}
