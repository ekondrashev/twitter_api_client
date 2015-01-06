package twitter;

import java.util.List;


public interface TwitterClient {
	public Status getStatus(long statusId);
	public long postStatus(String text);
	public List <Status> getUserTimeline(String userName, int limit);

}
