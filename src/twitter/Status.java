package twitter;

import java.util.Date;
import java.util.Set;

public class Status {
	private long id;//Maybe better to do it as String
	private String text;
	private Date statusDate;//Date and time of status creation
	private boolean media;//Presence or absence of picture, video or audio
	private Set<String> hashtag;
	private Set<Float> coordinates;
	
	public void setId (long id) {
		if (id>0) { 
			this.id=id;} else {throw new IllegalArgumentException ("Illegal value of argument");}
	}
	
	public void setText (String text) {
		if (text!=null && !text.isEmpty()) {this.text=text;}
		else {throw new IllegalArgumentException ("Illegal value of argument");}
	}
	
	public void setStatusDate (Date statusDate) {
		if (statusDate!=null) {this.statusDate=statusDate;}
		else {throw new IllegalArgumentException ("Illegal value of argument");} 
	}
	
	public void setMedia (boolean media) {
		this.media=media;
	}
	
	public void setHashtag (Set<String> hashtag) {
		this.hashtag=hashtag;
	}
	
	public void setCoordinates (Set<Float> coordinates) {
		this.coordinates=coordinates;
	}
	
	public long getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}
	
	public Date getStatusDate() {
		return statusDate;
	}
	
	public boolean getMedia () {
		return media;
	}
	
	public Set<String> getHashtag() {
		return hashtag;
	}
	
	public Set<Float> getCoordinates() {
		return coordinates;
	}
	
}