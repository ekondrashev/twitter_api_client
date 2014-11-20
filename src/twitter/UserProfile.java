package twitter;

import java.util.Date;
import java.util.Set;
import java.util.SimpleTimeZone;

public class UserProfile {
	private int userID;
	private String userName;
	private String language;
	private int statusesCount;
	private Date userDate; // Date of profile creation
	private SimpleTimeZone userTimezone;
	private int followersCount;
	private Set<String> folowing;
	private int friendsCount;
		
	public void setUserID (int userID) {
		if (userID>0) { 
			this.userID=userID;} else {throw new IllegalArgumentException ("Illegal value of argument");}
	}
	
	public void setUserName (String userName) {
		if (userName!=null && !userName.isEmpty()) {this.userName=userName;}
		else {throw new IllegalArgumentException ("Illegal value of argument");} 
	}
	
	public void setLanguage (String language){
		this.language=language;
	}
	
	public void setStatusesCount (int statusesCount) {
		this.statusesCount=statusesCount;
	}
	
	public void setUserDate (Date userDate) {
		if (userDate!=null) {this.userDate=userDate;}
		else {throw new IllegalArgumentException ("Illegal value of argument");} 
	}
	
	public void setUserTimezone (SimpleTimeZone userTimezone) {
		this.userTimezone=userTimezone;
	}
	
	public void setFollowersCount (int followersCount) {
		if (followersCount>=0) { 
			this.followersCount=followersCount;} else {throw new IllegalArgumentException ("Illegal value of argument");}
	}
	
	public void setFolowing (Set<String> folowing) {
		this.folowing=folowing;
	}
	
	public void setFriendsCount (int friendsCount) {
		if (friendsCount>=0) { 
			this.friendsCount=friendsCount;} else {throw new IllegalArgumentException ("Illegal value of argument");}
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
	
	public Date getUserDate () {
		return userDate; 
	}
	
	public SimpleTimeZone getUserTimezone () {
		return userTimezone; 
	}
	
	public int getFollowersCount () {
		return followersCount; 
	}
	
	public Set<String> getFolowing () {
		return folowing;
	}
	
	public int setFriendsCount () {
		return friendsCount; 
	}
		
}
