package twitter;

import java.util.HashSet;
import java.util.Set;

public class CallTwitterClient  {
		
	public static void main (String args[]) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
		   result.append(args[i]);
		   	}
		
		String enter = result.toString();
		//String enter = ("--cmd=getStatus--id=57687678");
		extractCommands(enter);
	}
	public static  Set<String> extractCommands(String enter) {
			Set<String> commands = new HashSet<String>();
			String usage = "Usage: 1)--cmd=getStatus--id=, 2) --cmd=postStatus--text=,3)--cmd=getUserTimeLine--id=--limit=";
			if (enter.indexOf("getStatus")>0) {
			String cmd = "getStatus";
			commands.add(cmd);
			int start = enter.indexOf("id")+3;
			// 3 is a magic number that means number of characters in phrase id=
			int end = enter.length();
			String id = enter.substring(start,end);
			commands.add(id);
			System.out.println(commands);
				}
			else  if (enter.indexOf("postStatus")>0) {
				String cmd = "postStatus";
				commands.add(cmd);
				int start = enter.indexOf("text")+5;
				// 5 is a magic number that means number of characters in phrase text=
				int end = enter.length();
				String text = enter.substring(start,end);
				commands.add(text);
					}
				else if (enter.indexOf("getUserTimeline")>0) {
					String cmd = "getUserTimeline";
					commands.add(cmd);
					int start = enter.indexOf("id")+3;
					//  3 is a magic number that means number of characters in phrase id=
					int end = enter.indexOf("--limit");
					String id = enter.substring(start,end);
					commands.add(id);
					int start2 = enter.indexOf("limit")+6;
					//  6 is a magic number that means number of characters in phrase limit=
					int end2 = enter.length();
					String limit = enter.substring(start2,end2);;
					commands.add(limit);
						}
						else { System.out.println(usage);}
		
				return commands;
		
			}

		
	}