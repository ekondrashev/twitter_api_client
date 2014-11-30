package twitter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CallTwitterClient {

	public static void main(String[] args) {
		extractCommands(args);
	}
	
	public static  Map <String, String> extractCommands (String[] args) {
		
		Map <String, String> commands = new HashMap<String, String>();
		
		String usage = "Usage: 1)--cmd=\"getStatus\"--id=\"¬ведите id сообщени€\","
					+ "2) --cmd=\"postStatus\"--text=\"¬ведите текст сообщени€\","
					+ "3)--cmd=\"getUserTimeLine\"--id=\"¬ведите id пользовател€\"--limit=\"¬ведите количество необходимых сообщений\"";
		
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
		   result.append(args[i]);
		   	}
		String input = result.toString();
					
		if (Pattern.compile("(?=.*--cmd=(getstatus|get_status))(?=.*--id=(\\d+)).*").matcher(input.toLowerCase()).matches()) {
				
				Pattern pattern = Pattern.compile("id=(\\d+)");
				Matcher matcher = pattern.matcher(input.toLowerCase());
				matcher.find();
				String id = matcher.group(1);
				commands.put("cmd", "getStatus");
				commands.put("id",id);
						
				} else if (Pattern.compile("(?=.*--cmd=(poststatus|post_status))(?=.*--text=(.{1,140})).*").matcher(input.toLowerCase()).matches()) {
					
					commands.put("cmd", "postStatus");
					
						for (String arg: args) {
							if(Pattern.compile("--text=(.{1,140})").matcher(arg.toLowerCase()).matches()) {
								Pattern pattern = Pattern.compile("=(.{1,140})");
								Matcher matcher = pattern.matcher(arg);
								System.out.println(matcher.find());
								System.out.println(matcher.group(1));
								String text = matcher.group(1);
								commands.put("text", text);
								}
						}
			
					} else if (Pattern.compile("(?=.*--cmd=(getusertimeline|get_usertimeline))(?=.*--id=(\\d+))(?=.*--limit=(\\d{1,3}))?.*").matcher(input.toLowerCase()).matches()) {
				
						commands.put("cmd", "getUserTimeline");
						Pattern pattern = Pattern.compile("id=(\\d+)");
						Matcher matcher = pattern.matcher(input.toLowerCase());
						matcher.find();
						String id = matcher.group(1);
						commands.put("id",id);
				
							if (Pattern.compile("limit").matcher(input.toLowerCase()).find()) {
					
								Pattern patternL = Pattern.compile("limit=(\\d{1,3})");
								Matcher matcherL = patternL.matcher(input.toLowerCase());
								matcherL.find();
								String limit = matcherL.group(1);
								commands.put("limit", limit);
							}
					} 	else {System.out.println(usage);}
		
				
		return commands;
			
	}
}
