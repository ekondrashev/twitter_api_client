package twitter;

import java.util.Date;

public class Status {
	
	private long statusId;
	private String text;
	private Date statusDate;
	private String userName;
		
	public void setStatusId (long id) {
		if (id>0) { 
			this.statusId=id;} else {throw new IllegalArgumentException ("Illegal value of argument");}
	}
	
	public void setText (String text) {
		if (text!=null && !text.isEmpty()) {this.text=text;}
		else {throw new IllegalArgumentException ("Illegal value of argument");}
	}
	
	public void setStatusDate (Date statusDate) {
		if (statusDate!=null) {this.statusDate=statusDate;}
		else {throw new IllegalArgumentException ("Illegal value of argument");} 
	}
	
	public void setUserName (String userName) {
		if (userName!=null && !userName.isEmpty()) {this.userName=userName;}
		else {throw new IllegalArgumentException ("Illegal value of argument");} 
	}
	
	
			
	public long getStatusId() {
		return statusId;
	}
	
	public String getText() {
		return text;
	}
	
	public Date getStatusDate() {
		return statusDate;
	}
	
	public String getUserName () {
		return userName;
	}
	
		
}