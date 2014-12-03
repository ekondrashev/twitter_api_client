
public class Main {
	
 private static final String USAGE = "Usage: Main --cmd"
	public static void main(String args[]){
		String cmd = null;
		for (String arg: args)
		{
			if (arg.startsWith("--cmd=")) {
				cmd = arg.substring("--cmd=".length(), arg.length());
			}
			}
			if (cmd i= null) {
				System.out.println(cmd);
			} else {
			System.out.println(USAGE);
		}
	}

}
