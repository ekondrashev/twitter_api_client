package twitter;

import java.io.Console;
import java.util.List;
import java.util.Map;

import org.apache.http.auth.AuthenticationException;

public class Main {

	public static void main(String[] args) throws AuthenticationException{
		
		CommandLine cl = new CommandLine(args);
		Map <String, String> commands = cl.validate();
		
		if (commands != null) {
			try {
				switch (commands.get("cmd")) {
				case "get_status":
					System.out.println("Connecting to the Internet...");
					long statusId = new Long(commands.get("id"));
					TwitterClient forStatus = new HttpTwitterClient();
					Status status = forStatus.getStatus(statusId);
					DBClient dbStat = new DBClient();
					dbStat.insertData(status);
					dbStat.close();
					break;
				case "get_user_timeline":
					String userName = commands.get("user_name");
					int limit = Integer.parseInt(commands.get("limit"));
					TwitterClient forTL = new HttpTwitterClient();
					List <Status> userTL = forTL.getUserTimeline(userName, limit);
					DBClient dbTL = new DBClient();
					dbTL.insertData(userTL);
					dbTL.close();
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
					System.out.println("Status has been posted. Status ID = " + statusID);
					break;
					
				}
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
			}
		} else {System.out.println(USAGE);}

	}
	static final String USAGE = "Usage: 1)--cmd=\"get_status\"--id=\"Enter message ID\","
			+ "2) --cmd=\"post_status\"--text=\"Enter the message (up to 140 characters)\","
			+ "3)--cmd=\"get_user_timeline\"--user_name=\"Enter user name\"--limit=\"Enter the number of messages (up to 100 messages)\"";
}
