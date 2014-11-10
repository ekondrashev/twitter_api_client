package twitter;

public interface TwitterClient {
	public Object getStatus();
	public void postStatus();
	public List getUserTimeline();

}
