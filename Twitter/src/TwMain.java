import java.util.HashMap;
import java.util.Map;



public class TwMain implements TwInterface {
	static String ArgStr;

	public static void main(String[] args)   {
		String [] Nargs =  {"-UsID=IvaP", "-GtSt=Ok", "-PtSt= Begin text"};
		args = Nargs;
		if (args.length!=0) {
		ParcingArg(args);
		} else {
			System.out.println("Не указаны параметры при запуске программы!");
		}
	}
	
	public static  Map<String, String> ParcingArg(String[] args) {
		Map<String, String> commands = new HashMap<String, String>();
		String ArgUsID = "-UsID=";
		String ArgGtSt = "-GtSt=";
		String ArgPtSt = "-PtSt=";
		//int ToDo = 0; 
		for (String arg: args)
		{
			String cmd = null;
			if (arg.startsWith(ArgUsID))
			{
				cmd = arg.substring(ArgUsID.length(), arg.length());
			//	ToDo =1;
				commands.put("ArgUsID",cmd);
			}

			if (arg.startsWith(ArgGtSt))
			{
			//	ToDo =2;
				cmd = arg.substring(ArgGtSt.length(), arg.length());
				commands.put("ArgGtSt",cmd);
			}

			if (arg.startsWith(ArgPtSt))
			{
			//	ToDo =3;
				cmd = arg.substring(ArgPtSt.length(), arg.length());
				commands.put("ArgPtSt",cmd);
			}

/*			if (cmd != null)
			{
				RunCommand(ToDo,cmd);
			}*/
		}
		return commands;

	}
	
	
	
	public static void RunCommand(int ToDo, String cmd) {
		switch (ToDo) {
		case 1:
			System.out.println(cmd);
			break; /* пока других действий не делаем */
		case 2:
			System.out.println(cmd);
			break;
		case 3:
			System.out.println(cmd);
			break;
		default:
			System.out.println("Исключительная ситуация");
		}

	}

	public void readMessege() {
		System.out.println(ArgStr);
	}

	public void WriteMessege() {

	}

	public void getUderID() {

	}

	public void TestRead() {
		readMessege();

	}

}
