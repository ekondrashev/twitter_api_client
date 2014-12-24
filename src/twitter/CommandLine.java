package twitter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLine {

	public static void main(String[] args) {
		extractCommands(args);
	}
	
	public static  void extractCommands (String[] args) {
		
		Map <String, String> commands = new HashMap<String, String>();
		
		for (String arg: args) {
			arg = arg.toString().toLowerCase().replaceFirst("getstatus|get_status", "getStatus");
//			if (Pattern.compile("getstatus|get_status").matcher(arg.toLowerCase()).find()) {
//				commands.put("cmd", "getStatus");
//			} else if (Pattern.compile("poststatus|post_status").matcher(arg.toLowerCase()).find()) {
//				commands.put("cmd", "postStatus");
//			} else if (Pattern.compile("getusertimeline|get_usertimeline|get_user_timeline").matcher(arg.toLowerCase()).find()){
//				commands.put("cmd", "getUserTimeline");
//			}
		}
		
		
		for (String arg: args) {
			if (Pattern.compile("--id=(\\d+)").matcher(arg.toLowerCase()).find()) {
				Pattern pattern = Pattern.compile("id=(\\d+)");
				Matcher matcher = pattern.matcher(arg.toLowerCase());
				matcher.find();
				String id = matcher.group(1);
				commands.put("id",id);
			} else if (Pattern.compile("--text=(.{1,140})").matcher(arg.toLowerCase()).find()) {
				Pattern pattern = Pattern.compile("--text=(.{1,140})");
				Matcher matcher = pattern.matcher(arg.toLowerCase());
				matcher.find();
				String text = matcher.group(1);
				commands.put("text", text);
			} else if (Pattern.compile("--limit=(\\d{1,3})").matcher(arg.toLowerCase()).find()) {
				Pattern pattern = Pattern.compile("--limit=(\\d{1,3})");
				Matcher matcher = pattern.matcher(arg.toLowerCase());
				matcher.find();
				String limit = matcher.group(1);
				commands.put("limit", limit);
			}
		}
				
			Set <String> keysGetStatus = new HashSet <String>();
			keysGetStatus.add("cmd");
			keysGetStatus.add("id");
			
			Set <String> keysPostStatus = new HashSet <String>();
			keysPostStatus.add("cmd");
			keysPostStatus.add("text");
			
			Set <String> keysGetUserTL = new HashSet <String>();
			keysGetUserTL.add("cmd");
			keysGetUserTL.add("id");
			keysGetUserTL.add("text");
			
			String usage = "Usage: 1)--cmd=\"getStatus\"--id=\"Enter message ID\","
					+ "2) --cmd=\"postStatus\"--text=\"Enter the message (up to 140 characters)\","
					+ "3)--cmd=\"getUserTimeLine\"--id=\"Enter user ID\"--limit=\"Enter the number of messages (up to 100 messages)\"";
			
			if (commands.keySet().equals(keysGetStatus) && commands.get("cmd").equals("getStatus")) {
				returnCommands(commands);				
			} else if (commands.keySet().equals(keysPostStatus) && commands.get("cmd").equals("postStatus")) {
				returnCommands(commands);
			} else if (commands.keySet().equals(keysGetUserTL) && commands.get("cmd").equals("getUserTimeline")) {
				returnCommands(commands);
			} else { System.out.println(usage);}
			
				
	}
	
	public static  Map <String, String> returnCommands (Map <String, String> commands) {
		
		return commands;
		
	}
	
}
