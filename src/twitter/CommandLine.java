package twitter;

import java.io.Console;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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
		
		if (commands != null) {
			callTwitterClient (commands);
		} else {System.out.println(usage);}
		
		return null;
								
	}
	
	static void callTwitterClient (Map <String, String> commands) throws AuthenticationException {
		
		try {
			switch (commands.get("cmd")) {
			case "get_status":
				long statusId = new Long(commands.get("id"));
				TwitterClient forStatus = new HttpTwitterClient();
				Status status = forStatus.getStatus(statusId);
				System.out.println(status.getText());
				break;
			case "get_user_timeline":
				String userName = commands.get("user_name");
				int limit = Integer.parseInt(commands.get("limit"));
				TwitterClient forTL = new HttpTwitterClient();
				List <Status> userTL = forTL.getUserTimeline(userName, limit);
				Status status2 = userTL.get(2);
				System.out.println(status2.getText());
				break;
			case "post_status":
				Console console = System.console();
				if (console == null) {
			        System.err.println("No console.");
			        System.exit(1);
			    }
				console.printf("Please enter your login: ");
				String login = console.readLine();
				char[] pw = console.readPassword("Please enter your password: ");
				String password = new String (pw);
				String text = commands.get("text");
				TwitterClient forPost = new HttpTwitterClient(login,password);
				long statusID = forPost.postStatus(text);
				System.out.println(statusID);
				break;
				
			}
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}
		
	}
	
	static String usage = "Usage: 1)--cmd=\"get_status\"--id=\"Enter message ID\","
			+ "2) --cmd=\"post_status\"--text=\"Enter the message (up to 140 characters)\","
			+ "3)--cmd=\"get_user_timeline\"--user_name=\"Enter user name\"--limit=\"Enter the number of messages (up to 100 messages)\"";
}
