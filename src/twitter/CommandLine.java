package twitter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class CommandLine {
	
	Map <String, String> commands = new HashMap<String, String>();
						
	CommandLine (String[] args) {
				
		for (String arg: args) {
			Matcher matcher = Pattern.compile("--(\\w+)=(.+)").matcher(arg);
			if (matcher.find()){
			commands.put(matcher.group(1), matcher.group(2));}
		}
				
	}
	
		
	Map <String, String> validate (){
				
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
			return commands;				
		} else if (commands.keySet().equals(keysPostStatus) && commands.get("cmd").equals("post_status")) {
			return commands;
		} else if (commands.keySet().equals(keysGetUserTL) && commands.get("cmd").equals("get_user_timeline")) {
			return commands; }
			
		return null;
				
	}
		
}
