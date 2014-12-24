package twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class OneMorePost {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://twitter.com");
		
		HttpClientContext context = HttpClientContext.create();
				
		CloseableHttpResponse response = httpClient.execute(httpGet, context);
				
		HttpEntity entity = response.getEntity();
		InputStream instream = entity.getContent();
		BufferedReader in = new BufferedReader(new InputStreamReader(instream, "UTF-8"));
		String inputLine;
		StringBuffer html = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			html.append(inputLine);
		}
		in.close();
		String page = html.toString();
		
		Document doc = Jsoup.parse(page);
		
		Elements link = doc.getElementsByAttributeValue("name", "authenticity_token");
		String authenticity_token = link.attr("value");
		
		
		
		
		LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
		CloseableHttpClient httpClient2 = HttpClients.custom()
		        .setRedirectStrategy(redirectStrategy)
		        .build();
		
		HttpPost httpPost = new HttpPost("https://twitter.com/sessions");
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("session[username_or_email]", "silver.varlam@mail.ru"));
		nameValuePairs.add(new BasicNameValuePair("session[password]", "Katusha"));
		nameValuePairs.add(new BasicNameValuePair("authenticity_token", authenticity_token));
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		
		CloseableHttpResponse response2 = httpClient2.execute(httpPost, context);
				
		
		HttpPost httpPost2 = new HttpPost("https://twitter.com/i/tweet/create");
		List<NameValuePair> nameValuePairs2 = new ArrayList<NameValuePair>();
		nameValuePairs2.add(new BasicNameValuePair("status", "Hi!!!"));
		nameValuePairs2.add(new BasicNameValuePair("authenticity_token", authenticity_token));
		httpPost2.setEntity(new UrlEncodedFormEntity(nameValuePairs2));
		CloseableHttpResponse respStatus = httpClient2.execute(httpPost2, context);
		
		System.out.println(context.getCookieStore().getCookies());
		
		HttpEntity  entStat = respStatus.getEntity();
		String entStr = EntityUtils.toString(entStat);
		Matcher matcher = Pattern.compile("\"tweet_id\":\"(\\d+)\".*").matcher(entStr);
		matcher.find();
		Long statusId = new Long(new String(matcher.group(1)));
 		
		
		
		
		
		System.out.println(entStr);
		System.out.println(statusId);
		
		
		

	}

}
