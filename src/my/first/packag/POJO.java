package my.first.packag;

public class POJO {

	static String SID;
	final static double PI = 3.14;

	private int id;
	
	private String sid;
	//"ABC", "UDC"
	
	private String firstName;
	private String lastName;
	
	public POJO() {
		this.sid = ""; 
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setId(String id) {
		
		this.id = Integer.parseInt(id);
	}
	
	public String getFullName() {
//		return firstName + " " + lastName;
		return String.format("%s %s", firstName, lastName);
	}
	
	
	
	
	public String getSID() {
		
		return sid;
	}
	
	
	

	//method parameter
	public void setSID(String sid) {
		
		//local variable
		String temp;

		if (sid.length() > 3) {
			throw new IllegalArgumentException("Invalid SID"); 
		}
		//instance variable
		this.sid = sid;
		
		//class variable
		POJO.SID = sid;
		
		//warning 
		//The static field POJO.SID should be accessed in a static way
		this.SID = sid;
		
		//invalid
//		POJO.PI = 3.141;
		
	}
}