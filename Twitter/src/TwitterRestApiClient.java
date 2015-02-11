import java.io.IOException;
import java.util.List;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class TwitterRestApiClient implements TwitterClient{
	private static String consumerKey="mb5yNKsX0OVr1GRmhfDfcGBgX";
	private static String consumerSecret ="2dQ0Wsbz8x03v8HwTmPt8CMeEPg9rMe3Oxws9GHrSqUVyO2jXj";
	private static String accessToken ="2909023707-FAIlWfTw2v8ziqZxP46AMSsq1dqSY29Boo11qCt";
	private static String accessTokenSecret ="qLxbRDgnLDTvnSx41Y8MWc9x0P80EjmFx21JQiw6IZhvt";
	private OAuthConsumer consumer;
	
	public TwitterRestApiClient() {
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
				consumerKey,
				consumerSecret);
        consumer.setTokenWithSecret(accessToken, accessTokenSecret);
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public Status getStatus(long statusId) throws Exception { 
        String stringstatusId = Long.toString(statusId);
        HttpGet request = new HttpGet("https://api.twitter.com/1.1/statuses/show.json?id=".concat(stringstatusId));
        try {
			consumer.sign(request);
		} catch (OAuthMessageSignerException e) {
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			e.printStackTrace();
		}
 
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(request);
 
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode + ":" + response.getStatusLine().getReasonPhrase());
        System.out.println(IOUtils.toString(response.getEntity().getContent()));
		return null;
	}

	public boolean postStatus(String text) {
 //       consumer.setTokenWithSecret(accessToken, accessTokenSecret);
		HttpGet request = new HttpGet("https://api.twitter.com/1.1/direct_messages/new.json".concat(text));
        consumer.sign(request);
		return true;
	}

	public List<Status> getUserTimeline(String userName, int limit) throws Exception {
		
 //       consumer.setTokenWithSecret(accessToken, accessTokenSecret);
        String sLimit = Integer.toString(limit);
        HttpGet request = new HttpGet("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=".concat(userName)+"&count=".concat(sLimit));
        consumer.sign(request);
 
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(request);
 
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode + ":" + response.getStatusLine().getReasonPhrase());
        System.out.println(IOUtils.toString(response.getEntity().getContent()));
		return null;
	}

}
