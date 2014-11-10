package twitter;

import java.awt.List;

public interface TwitterClient {
	public Object getStatus();
	public void postStatus();
	public List getUserTimeline();

}
