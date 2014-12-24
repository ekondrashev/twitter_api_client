package twitter;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;



public interface TwitterClient {
	public Status getStatus(long statusId) throws Exception;
	public long postStatus(String text) throws ClientProtocolException, IOException;
	public List <Status> getUserTimeline(String userName, int limit) throws IOException, Exception;

}
