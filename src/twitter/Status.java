package twitter;

import java.util.Date;

public class Status {
	
	private long statusId;
	private String text;
	private Date statusDate;
	private String userName;
		
	public void setStatusId (long statusId) {
		if (statusId>0) { 
			this.statusId=statusId;} else {throw new IllegalArgumentException ("Illegal value of argument");}
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
	
	public boolean equals(Object obj) {
		if (obj instanceof Status && obj!=null) {
			Status status = (Status) obj;
			return(this.statusId == status.statusId);
		}
		return false;
	}
	
//	public int hashCode(){
//		
//		return (new Long(this.statusId)).intValue();
//					
//	}
		
}