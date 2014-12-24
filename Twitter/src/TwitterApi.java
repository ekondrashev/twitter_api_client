import java.io.IOException;

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

public class TwitterApi {
	
	private static String consumerKey="mb5yNKsX0OVr1GRmhfDfcGBgX";
	private static String consumerSecret ="2dQ0Wsbz8x03v8HwTmPt8CMeEPg9rMe3Oxws9GHrSqUVyO2jXj";
	private static String accessToken ="2909023707-FAIlWfTw2v8ziqZxP46AMSsq1dqSY29Boo11qCt";
	private static String accessTokenSecret ="qLxbRDgnLDTvnSx41Y8MWc9x0P80EjmFx21JQiw6IZhvt";

	private static String urlAuth="https://api.twitter.com/oauth2/token";
	//private static String owner ="a__kot";
	//private static String ownerID ="2909023707";
	
	//private static String signatureBaseString ="GET&https%3A%2F%2Fapi.twitter.com%2F1.1%2F&oauth_consumer_key%3Dmb5yNKsX0OVr1GRmhfDfcGBgX%26oauth_nonce%3Dd57233d49e1590a471ea9d7403371eb4%26oauth_signature_method%3DHMAC-SHA1%26oauth_timestamp%3D1418161737%26oauth_token%3D2909023707-FAIlWfTw2v8ziqZxP46AMSsq1dqSY29Boo11qCt%26oauth_version%3D1.0";
	private static String authorizationHeader ="Authorization: OAuth oauth_consumer_key=\"mb5yNKsX0OVr1GRmhfDfcGBgX\", oauth_nonce=\"d57233d49e1590a471ea9d7403371eb4\", oauth_signature=\"0FDw4AFYuSavzvAx%2BXjL7rjJQgs%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1418161737\", oauth_token=\"2909023707-FAIlWfTw2v8ziqZxP46AMSsq1dqSY29Boo11qCt\", oauth_version=\"1.0\"";
	//private static String cURLCommand = "url --get 'https://api.twitter.com/1.1/' --header 'Authorization: OAuth oauth_consumer_key=\"mb5yNKsX0OVr1GRmhfDfcGBgX\", oauth_nonce=\"d57233d49e1590a471ea9d7403371eb4\", oauth_signature=\"0FDw4AFYuSavzvAx%2BXjL7rjJQgs%3D\", oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"1418161737\", oauth_token=\"2909023707-FAIlWfTw2v8ziqZxP46AMSsq1dqSY29Boo11qCt\", oauth_version=\"1.0\"' --verbose";
	
	public static void main(String[] args) throws ClientProtocolException, IOException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
				consumerKey,
				consumerSecret);
        String userName = "RidusNews";
        consumer.setTokenWithSecret(accessToken, accessTokenSecret);
        HttpGet request = new HttpGet("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=".concat(userName));
        consumer.sign( request);
 
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(request);
 
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode + ":" + response.getStatusLine().getReasonPhrase());
        System.out.println(IOUtils.toString(response.getEntity().getContent()));
	}

}
