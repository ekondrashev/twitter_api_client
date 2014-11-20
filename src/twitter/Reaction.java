package twitter;

import java.util.Set;

public class Reaction {
	private int favoriteCount;
	private Set<String> favorited;
	private int retweetCount;
	private Set<String> retvited;
	private Set<String> comments;
	
	public void setFavoriteCount (int favoriteCount) {
		if (favoriteCount>=0) { 
			this.favoriteCount=favoriteCount;} else {throw new IllegalArgumentException ("Illegal value of argument");}
	}
	
	public void setFavorited (Set<String> favorited) {
		this.favorited=favorited;
	}
	
	public void setRetweetCount (int retweetCount) {
		if (retweetCount>=0) { 
			this.retweetCount=retweetCount;} else {throw new IllegalArgumentException ("Illegal value of argument");}
	}
	
	public void setRetvited (Set<String> retvited) {
		this.retvited=retvited; 
	}
	
	public void setComments (Set<String> comments) {
		this.comments=comments;
	}
	
	public int getFavoriteCount () {
		return favoriteCount;
	}
	
	public Set<String> getFavorited () {
		return favorited;
	}
	
	public int getRetweetCount () {
		return retweetCount;
	}
	
	public Set<String> getRetvited () {
		return retvited;
	}
	
	public Set<String> getComments () {
		return comments; 
	}
	
}
