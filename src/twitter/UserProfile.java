package twitter;

public class UserProfile {
	private int userID;
	private String userName;
	private String language;
	private int statusesCount;
	private String userDate; // Date of profile creation
	private String userTimezone;
	private int followersCount;
	private String folowing;
	private int friendsCount;
	
	public void setUserID (int userID) {
		if (userID>0) { 
			this.userID=userID;} else {System.out.println("invalid userID");}
	}
	
	public void setUserName (String userName) {
		if (userName instanceof String) {
			if (userName!=null) {this.userName=userName;}
		} else {System.out.println("invalid userName");} 
	}
	
	public void setLanguage (String language) {
		if (language instanceof String) {
			if (language!=null) {this.language=language;}
		} else {System.out.println("invalid language");} 
	}
	
	public void setStatusesCount (int statusesCount) {
		if (statusesCount>0) { 
			this.statusesCount=statusesCount;} else {System.out.println("invalid statusesCount");}
	}
	
	public void setUserDate (String userDate) {
		if (userDate instanceof String) {
			if (userDate!=null) {this.userDate=userDate;}
		} else {System.out.println("invalid userDate");} 
	}
	
	public void setUserTimezone (String userTimezone) {
		if (userTimezone instanceof String) {
			if (userTimezone!=null) {this.userTimezone=userTimezone;}
		} else {System.out.println("invalid userTimezone");} 
	}
	
	public void setFollowersCount (int followersCount) {
		if (followersCount>0) { 
			this.followersCount=followersCount;} else {System.out.println("invalid followersCount");}
	}
	
	public void setFolowing (String folowing) {
		if (folowing instanceof String) {
			if (folowing!=null) {this.folowing=folowing;}
		} else {System.out.println("invalid folowing");} 
	}
	
	public void setFriendsCount (int friendsCount) {
		if (friendsCount>0) { 
			this.friendsCount=friendsCount;} else {System.out.println("invalid friendsCount");}
	}
	
	public int getUserID () {
		return userID; 
	}
	
	public String getUserName () {
		return userName;
	}
	
	public String getLanguage () {
		return language;
	}
	
	public int getStatusesCount () {
		return statusesCount;
	}
	
	public String getUserDate () {
		return userDate; 
	}
	
	public String getUserTimezone () {
		return userTimezone; 
	}
	
	public int getFollowersCount () {
		return followersCount; 
	}
	
	public String getFolowing () {
		return folowing;
	}
	
	public int setFriendsCount () {
		return friendsCount; 
	}
		
}
