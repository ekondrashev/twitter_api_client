package twitter;

import java.util.HashMap;
import java.util.Map;

public class CallTwitterClient  {
		
	public static void main (String args[]) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
		   result.append(args[i]);
		   	}
		
		String enter = result.toString();
		//String enter = ("--cmd=getStatus--id=57687678");
		extractCommands(enter);
		//System.out.println(enter);
	}
	public static  Map <String, String> extractCommands(String enter) {
		
			
			Map <String, String> commands = new HashMap<String, String>();
			String usage = "Usage: 1)--cmd=getStatus--id=, 2) --cmd=postStatus--text=,3)--cmd=getUserTimeLine--id=--limit=";
			
			
			if (enter.indexOf("getStatus")>0) {
			commands.put("cmd", "getStatus");
			int start = enter.indexOf("id")+3;
			// 3 is a magic number that means number of characters in phrase id=
			int end = enter.length();
			String id = enter.substring(start,end);
			commands.put("id", id);
			System.out.println(commands);
				}
			else  if (enter.indexOf("postStatus")>0) {
				commands.put("cmd", "postStatus");
				int start = enter.indexOf("text")+5;
				// 5 is a magic number that means number of characters in phrase text=
				int end = enter.length();
				String text = enter.substring(start,end);
				commands.put("text", text);
					}
				else if (enter.indexOf("getUserTimeline")>0) {
					commands.put("cmd", "getUserTimeline");
					int start = enter.indexOf("id")+3;
					//  3 is a magic number that means number of characters in phrase id=
					int end = enter.indexOf("--limit");
					String id = enter.substring(start,end);
					commands.put("id", id);
					int start2 = enter.indexOf("limit")+6;
					//  6 is a magic number that means number of characters in phrase limit=
					int end2 = enter.length();
					String limit = enter.substring(start2,end2);;
					commands.put("limit", limit);
						}
						else { System.out.println(usage);}
		
				return commands;
		
			}

		
	}