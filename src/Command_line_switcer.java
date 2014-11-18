import java.util.Date;
import java.util.Scanner;


public class Command_line_switcer {

	// Help
		public static final String HELP_LINE []= {
			"Usage:",
			"Ч cmd = getStatus ID",
			"Ч cmd = post_status TEXT ID COMMENT DATA LIKES SHARES  ",
			"Ч cmd = getUserTimeline ID",	
			"Example:",
			"Ч cmd = getStatus ID"
		};
		
		
		//Available_commands
		public static final String COMMAND_PREFIX = "--CMD=";
		
							public final static String COMMAND_GET_STATUS = "getstatus";
							public final static String COMMAND_GET_STATUS1 = "get_status";
							
							public final static String COMMAND_POST_STATUS = "poststatus";
							public final static String COMMAND_POST_STATUS1 = "post_status";
							
							public static final String COMMAND_USER_TIMELINE = "getusertimeline";
							public static final String COMMAND_USER_TIMELINE1 = "get_user_timeline";
		
	
	
	
	
	public Command_line_switcer() {
		// TODO Auto-generated constructor stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length>1 && args[0].equalsIgnoreCase(COMMAND_PREFIX))
		{
			
			 System.out.println("switching command");	
		switch (args [1].toLowerCase())
		 {
         case COMMAND_GET_STATUS:
         case COMMAND_GET_STATUS1:     	 
        	 System.out.println(COMMAND_GET_STATUS);
        	 GetStatus (args);
        	 break;
       
         case COMMAND_POST_STATUS:
         case COMMAND_POST_STATUS1:  	 
        	 System.out.println(COMMAND_POST_STATUS);
        	 PostStatus (args);
             break;
             
         case COMMAND_USER_TIMELINE:
         case COMMAND_USER_TIMELINE1:
        	 System.out.println(COMMAND_USER_TIMELINE);
        	 GetUserTimeline (args);
             break;
             
          
         default:
        	 PrintHelp ();
             
		 }
		}
		
		else {
			 System.out.println("No command line args");
			 PrintHelp ();		
		}
		
		
	}




	public static void PrintHelp ()
	{
		for(String s: HELP_LINE)
		{
		System.out.println(s);			
		}		
	}


	public static void GetStatus (String args[])
	{
		int id;
		id=GetId(2, args);
		System.out.println("GetStatus ID="+id);
	}

	
	public static void PostStatus (String args[])
	{
		int id;
		String text;
		Date date; 
	//	Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		int likes;
		int shares;
		
		
		id=GetId(2, args);
//		text=args[3];
		text=GetText(3, args, "Text");
		//date=SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(args[4]);
		likes=GetInt(5, args, "Likes");
		shares=GetInt(6, args, "Shares");
		
		System.out.println("PostStatus ID="+id);
		System.out.println("PostStatus Text="+text);
		//System.out.println("PostStatus date"+date);
		System.out.println("PostStatus likes="+likes);
		System.out.println("PostStatus shares="+shares);
	}

	public static void GetUserTimeline (String args[])
	{
		int id;
		id=GetId(2, args);
		System.out.println("GetUserTimeline ID="+id);	
	}
	
	
	
	private static int GetInt(int position, String[] args, String name) {
		int id=0;
		String s;
		if (args.length<(position+1))
			s = getStringSystemIn("Enter "+name);
		else s = args [position];
	
		
		id = strToInt(s);
		System.out.println(name+id);
		
		return id;
	}
	
	private static String GetText(int position, String[] args, String name) {
		
		String s;
		if (args.length<(position+1))
			s = getStringSystemIn("Enter "+name);
		else s = args [position];
	
		return s;
	}
	
	
	private static int GetId(int position, String[] args) {
		int id=0;
		String s;
		if (args.length<(position+1))
			s = getStringSystemIn("Enter ID");
		else s = args [position];
		
		//System.out.println("ID="+s);
		
		
		id = strToInt(s);
		System.out.println("ID="+id);
		
		return id;
	}

	
	private static int strToInt(String s) {
		int id=0;
		boolean error;
		
do {
	error=false;	
		try {
		      id = Integer.parseInt(s);
		} 
		catch (NumberFormatException e) 
		{
			s = getStringSystemIn("WRONG ID Enter ID");
			error=true;
		}
		
}while (error) ; 
		
		
		return id;
	}

	
	
	
/*	private static int getIntSystemIn(String k)
	{
		int i=0;
		System.out.println(k);
		Scanner sc = new Scanner(System.in);

		 if(sc.hasNextInt()) { // возвращает истинну если с потока ввода можно считать целое число
	          i = sc.nextInt(); // считывает целое число с потока ввода и сохран€ем в переменную
	          System.out.println(i*2);
	        } else {
	          System.out.println("¬ы ввели не целое число");
	        }
		
		 return i;
		
	}*/
	
	
	
	
	
	
	
	
private static String getStringSystemIn(String k) {
		String s;
		{
			System.out.println(k);
			{
			Scanner sc = new Scanner(System.in);
			s = sc.nextLine();
			}
		}
		System.out.println("recieved "+s);
		return s;
	}


	

}
