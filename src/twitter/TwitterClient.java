package twitter;

public interface TwitterClient {
	public String getStatus();
	public void postStatus();
	public String getUserTimeline();

}
