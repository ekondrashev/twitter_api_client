package twitter;

public class Reaction {
	private int favoriteCount;
	private String favorited;
	private int retweetCount;
	private String retvited;
	private String comments;
	
	public void setFavoriteCount (int favoriteCount) {
		if (favoriteCount>0) { 
			this.favoriteCount=favoriteCount;} else {System.out.println("invalid favoriteCount");}
	}
	
	public void setFavorited (String favorited) {
		if (favorited instanceof String) {
			if (favorited!=null) {this.favorited=favorited;}
		} else {System.out.println("invalid favorited");} 
	}
	
	public void setRetweetCount (int retweetCount) {
		if (retweetCount>0) { 
			this.retweetCount=retweetCount;} else {System.out.println("invalid retweetCount");}
	}
	
	public void setRetvited (String retvited) {
		if (retvited instanceof String) {
			if (retvited!=null) {this.retvited=retvited;}
		} else {System.out.println("invalid retvited");} 
	}
	
	public void setComments (String comments) {
		if (comments instanceof String) {
			if (comments!=null) {this.comments=comments;}
		} else {System.out.println("invalid comments");} 
	}
	
	public int getFavoriteCount () {
		return favoriteCount;
	}
	
	public String getFavorited () {
		return favorited;
	}
	
	public int getRetweetCount () {
		return retweetCount;
	}
	
	public String getRetvited () {
		return retvited;
	}
	
	public String getComments () {
		return comments; 
	}
	
}
