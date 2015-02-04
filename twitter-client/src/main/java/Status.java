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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (statusId ^ (statusId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Status other = (Status) obj;
		if (statusId != other.statusId)
			return false;
		return true;
	}
	
			
}