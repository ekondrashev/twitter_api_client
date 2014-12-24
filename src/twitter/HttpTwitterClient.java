package twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpTwitterClient implements TwitterClient {
	
	HttpClientContext context = null;
	
	String authenticity_token = null;
	
	HttpTwitterClient (String userName, String password) throws ClientProtocolException, IOException {
		
		CloseableHttpClient clientToken = HttpClients.createDefault();
		HttpGet getToken = new HttpGet("http://twitter.com");
		context = HttpClientContext.create();
		CloseableHttpResponse resToken = clientToken.execute(getToken, context);
		HttpEntity entityToken = resToken.getEntity();
		
		
		InputStream instream = entityToken.getContent();
		BufferedReader in = new BufferedReader(new InputStreamReader(instream, "UTF-8"));
		String inputLine;
		StringBuffer htmlBuff = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			htmlBuff.append(inputLine);
		}
		
		in.close();
		
		String html = htmlBuff.toString();
		Document doc = Jsoup.parse(html);
		Elements link = doc.getElementsByAttributeValue("name", "authenticity_token");
		authenticity_token = link.attr("value");
					
		LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
		CloseableHttpClient clientAuthen = HttpClients.custom()
		        .setRedirectStrategy(redirectStrategy)
		        .build();
		
		HttpPost postCred = new HttpPost("https://twitter.com/sessions");
		List<NameValuePair> credentials = new ArrayList<NameValuePair>();
		credentials.add(new BasicNameValuePair("session[username_or_email]", userName));
		credentials.add(new BasicNameValuePair("session[password]", password));
		credentials.add(new BasicNameValuePair("authenticity_token", authenticity_token));
		postCred.setEntity(new UrlEncodedFormEntity(credentials));
		
		clientAuthen.execute(postCred, context);  
						
	}
	
	
	public Status getStatus (long statusId) throws Exception {
		
		String url = "http://twitter.com/*/status/" + statusId;
		
		Document doc = Jsoup.connect(url).get();
		
		Elements textLink = doc.getElementsByAttributeValue("property", "og:description");
		String text = textLink.attr("content");
		text = text.substring(1, (text.length()-1));
		
		Elements userNameLink = doc.getElementsByAttribute("data-screen-name");
		String userName = userNameLink.attr("data-screen-name");
		
		
		Elements dateLink = doc.getElementsByAttribute("data-time-ms");
		String dateStr = dateLink.attr("data-time-ms");
		Date statusDate = new Date (new Long (dateStr));
						
		Status status = new Status();
		status.setStatusId(statusId);
		status.setUserName(userName);
		status.setText(text);
		status.setStatusDate(statusDate);
		
		return status;
		
	}
	
	public List <Status> getUserTimeline (String userName, int limit) throws Exception {
		
		String url = "http://twitter.com/" + userName;
		Document doc = Jsoup.connect(url).get();
		Elements link = doc.getElementsByAttributeValue("class", "StreamItem js-stream-item" );
		List <Element> elements = link.subList(0, limit);
		
		List <Status> statuses = new ArrayList<Status>();
		
		for (Element element: elements) {
			String idStr = element.id();
			idStr = idStr.substring("stream-item-tweet-".length());
			long statusId = new Long(idStr);
						
			Status status = getStatus(statusId);
			statuses.add(status);
		}
		
		return statuses;
		
	}
	
	public long postStatus(String text) throws ClientProtocolException, IOException{
		
		CloseableHttpClient clientStatus = HttpClients.createDefault();
		HttpPost postStat = new HttpPost("https://twitter.com/i/tweet/create");
		List<NameValuePair> postEntity = new ArrayList<NameValuePair>();
		postEntity.add(new BasicNameValuePair("status", text));
		postEntity.add(new BasicNameValuePair("authenticity_token", authenticity_token));
		postStat.setEntity(new UrlEncodedFormEntity(postEntity));
		CloseableHttpResponse respStatus = clientStatus.execute(postStat, context);
		
		HttpEntity  getEntity = respStatus.getEntity();
		String entStr = EntityUtils.toString(getEntity);
		Matcher matcher = Pattern.compile("\"tweet_id\":\"(\\d+)\".*").matcher(entStr);
		matcher.find();
		Long statusId = new Long(new String(matcher.group(1)));
		
		return statusId;
		
	}
	
}
