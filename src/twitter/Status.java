package twitter;

public class Status {
	private int id;
	private String text;
	private String statusDate;//Date and time of status creation
	private Boolean media;//Presence or absence of picture, video or audio
	private String hashtag;
	
	public void setId (int id) {
		if (id>0) { 
			this.id=id;} else {System.out.println("invalid id");}
	}
	
	public void setText (String text) {
		if (text instanceof String) {
			if (text!=null) {this.text=text;}
		} else {System.out.println("invalid text");} 
	}
	
	public void setStatusDate (String statusDate) {
		if (statusDate instanceof String) {
			if (statusDate!=null) {this.statusDate=statusDate;}
		} else {System.out.println("invalid statusDate");} 
	}
	
	public void setMedia (Boolean media) {
		if (media instanceof Boolean) {
			this.media=media;} else {System.out.println("invalid media");} 
	}
	
	public void setHashtag (String hashtag) {
		if (hashtag instanceof String) {
			this.hashtag=hashtag;} else {System.out.println("invalid hashtag");} 
	}
	
	public int getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}
	
	public String getStatusDate() {
		return statusDate;
	}
	
	public Boolean getMedia () {
		return media;
	}
	
	public String getHashtag() {
		return hashtag;
	}
	
}