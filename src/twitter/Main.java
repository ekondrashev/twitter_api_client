package twitter;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.auth.AuthenticationException;

public class Main {
	
	public static void main(String[] args) throws AuthenticationException {
		
		CommandLine cl = new CommandLine(args);
		Map <String, String> commands = cl.validate();
		
		Map <String, String> parameters = new HashMap<String, String>();
						
		if (commands == null) {
			System.out.println(USAGE);
			return;
		}
		
		if (commands.get("cmd") == "post_status") {
			Map <String, String> credentials = authenticate();
			parameters.putAll(credentials);
			}
		TwitterClient tClient = new HttpTwitterClient(parameters);
		DBClient dbClient = new DBClient();
		
		try {
			switch (commands.get("cmd")) {
			case "get_status":
				long statusId = new Long(commands.get("id"));
				Status status = tClient.getStatus(statusId);
				dbClient.insertData(status);
				break;
			case "get_user_timeline":
				String userName = commands.get("user_name");
				int limit = Integer.parseInt(commands.get("limit"));
				List <Status> userTL = tClient.getUserTimeline(userName, limit);
				dbClient.insertData(userTL);
				break;
			case "post_status":
				String text = commands.get("text");
				long statusID = tClient.postStatus(text);
				System.out.println("Status has been posted. Status ID = " + statusID);
				break;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
	}
	
	public static Map <String, String> authenticate() {
		
		Console console = System.console();
		if (console == null) {
	        System.err.println("No console.");
	        System.exit(1);
	    }
		console.printf("Please enter your login: ");
		String userName = console.readLine();
		char[] pw = console.readPassword("Please enter your password: ");
		String password = new String (pw);
		Map <String, String> credentials = new HashMap<String, String>();
		credentials.put("userName", userName);
		credentials.put("password",password);
		return credentials;
		
	}
	
	static final String USAGE = "Usage: 1)--cmd=\"get_status\"--id=\"Enter message ID\","
			+ "2) --cmd=\"post_status\"--text=\"Enter the message (up to 140 characters)\","
			+ "3)--cmd=\"get_user_timeline\"--user_name=\"Enter user name\"--limit=\"Enter the number of messages (up to 100 messages)\"";
}
