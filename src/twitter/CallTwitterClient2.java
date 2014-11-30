package twitter;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CallTwitterClient2 {

	public static void main(String[] args) {
					
		Arrays.sort(args);
				
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < args.length; i++) {
		   result.append(args[i]);
		   	}
		String input = result.toString();
		
		input = input.toLowerCase();
		
		
		String regex = 	"(\\--cmd=(getstatus|get_status)\\--id=\\d+)|"
						+ "(\\--cmd=(poststatus|post_status)\\--text=.+)|"
						+ "(\\--cmd=(getusertimeline|get_usertimeline)\\--id=\\d+(\\--limit=\\d+)?)";
		
		Pattern pattern = Pattern.compile (regex);
		
		Matcher matcher = pattern.matcher(input);
                
        String usage = "Usage: 1)--cmd=\"getStatus\"--id=\"¬ведите id сообщени€\","
        					+ "2) --cmd=\"postStatus\"--text=\"¬ведите текст сообщени€\","
        					+ "3)--cmd=\"getUserTimeLine\"--id=\"¬ведите id пользовател€\"--limit=\"¬ведите количество необходимых сообщений\"";
                     
        if (matcher.matches()) {
        	extractCommands(args);
        } else {System.out.println(usage);}
        
	}
	
	public static  Map <String, String> extractCommands (String[] args) {
		
		Arrays.sort(args);
        		
		Map <String, String> commands = new HashMap<String, String>();
		
		String cmdSignature = "--cmd=";
		String idSignature = "--id=";
		String textSignature = "--text=";
		String limitSignature = "--limit=";
				
		String cmd = args[0].toLowerCase();
		cmd= cmd.substring(cmdSignature.length());
				
		switch (cmd) {
			
		case "getstatus":	
		case "get_status":			commands.put("cmd", "getStatus");
									String id = args[1].substring(idSignature.length());
									commands.put("id", id);
									break;
		
		case "poststatus":	
		case "post_status":			commands.put("cmd", "postStatus");
									String text = args[1].substring(textSignature.length());
									commands.put("text", text);
									break;
		
		case "getusertimeline":	
		case "get_usertimeline":	commands.put("cmd", "getUserTimeline");
									String userId = args[1].substring(idSignature.length());
									commands.put("id", userId);
									if (!args[2].isEmpty()) {
									String limit = args[2].substring(limitSignature.length());
									commands.put("limit", limit);
									}
									break;
		}
		
		
		return commands;
	
	}

}
