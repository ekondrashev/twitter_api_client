package twitter;

import java.util.HashSet;
import java.util.Set;

public class CallTwitterClient  {
		
	public static void main (String args[]) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
		   result.append(args[i]);
		   	}
		
		//String enter = result.toString();
		String enter = ("--cmd=getStatus--id=57687678");
		extractCommands(enter);
	}
	public static  Set<String> extractCommands(String enter) {
		Set<String> commands = new HashSet<String>();
			if (enter.indexOf("getStatus")>0) {
			String cmd = "getStatus";
			commands.add(cmd);
			int start = enter.indexOf("id")+3;
			// 3 is a magic number that means number of characters in phrase id=
			int end = enter.length();
			char buf[] = new char[end - start];
			enter.getChars(start, end, buf, 0);
			String id = new String(buf);
			commands.add(id);
				}
			else  if (enter.indexOf("postStatus")>0) {
				String cmd = "postStatus";
				commands.add(cmd);
				int start = enter.indexOf("text")+5;
				// 5 is a magic number that means number of characters in phrase text=
				int end = enter.length();
				char buf[] = new char[end - start];
				enter.getChars(start, end, buf, 0);
				String text = new String(buf);
				commands.add(text);
					}
				else if (enter.indexOf("getUserTimeline")>0) {
					String cmd = "getUserTimeline";
					commands.add(cmd);
					int start = enter.indexOf("id")+3;
					//  3 is a magic number that means number of characters in phrase id=
					int end = enter.indexOf("--limit");
					char buf[] = new char[end - start];
					enter.getChars(start, end, buf, 0);
					String id = new String(buf);
					commands.add(id);
					int start2 = enter.indexOf("limit")+6;
					//  6 is a magic number that means number of characters in phrase limit=
					int end2 = enter.length();
					char buf2[] = new char[end2 - start2];
					enter.getChars(start2, end2, buf2, 0);
					String limit = new String(buf2);
					commands.add(limit);
						}
						else { System.out.println("Usage: 1)--cmd=getStatus--id=, 2) --cmd=postStatus--text=,3)--cmd=getUserTimeLine--id=--limit=");}
		
				return commands;
		
			}

		
	}