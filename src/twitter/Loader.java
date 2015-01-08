package twitter;

import java.io.Console;
import java.util.List;
import java.util.Map;

import org.apache.http.auth.AuthenticationException;

public class Loader {
	
void callTwitterClient(Map<String, String> commands) throws AuthenticationException {
	
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

}
