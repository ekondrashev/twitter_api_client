package twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
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
	
	HttpTwitterClient () {}
	
	HttpTwitterClient (String userName, String password) {
		
		CloseableHttpClient clientToken = HttpClients.createDefault();
		HttpGet getToken = new HttpGet("http://twitter.com");
		context = HttpClientContext.create();
		
		CloseableHttpResponse resToken = null;
		try {
			resToken = clientToken.execute(getToken, context);
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();}
		catch (IOException e) {
		System.err.println("Please check your internet connection.");}
			
		HttpEntity entityToken = resToken.getEntity();
				
		try {
			InputStream instream = entityToken.getContent();
		
			BufferedReader in = null;
			StringBuffer htmlBuff = new StringBuffer();
			try {
				in = new BufferedReader(new InputStreamReader(instream));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					htmlBuff.append(inputLine);
				}
			}
		
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				in.close();
			}
		
		
			String html = htmlBuff.toString();
			Document doc = Jsoup.parse(html);
			Elements link = doc.getElementsByAttributeValue("name", "authenticity_token");
			authenticity_token = link.attr("value");
			clientToken.close();
			resToken.close();
		
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
			clientAuthen.close();
		}
		catch (IOException e) {
			e.printStackTrace();	
		}
				
	}
	
	
	public Status getStatus (long statusId) {
		
		String url = "http://twitter.com/*/status/" + statusId;
		Status status = new Status();
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		}
		catch (SocketTimeoutException e) {
			System.err.println("The connection time has expired");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
			
		Elements textLink = doc.getElementsByAttributeValue("property", "og:description");
		String text = textLink.attr("content");
		text = text.substring(1, (text.length()-1));
			
		Elements userNameLink = doc.getElementsByAttribute("data-screen-name");
		String userName = userNameLink.attr("data-screen-name");
			
			
		Elements dateLink = doc.getElementsByAttribute("data-time-ms");
		String dateStr = dateLink.attr("data-time-ms");
		Date statusDate = new Date (new Long (dateStr));
							
		status.setStatusId(statusId);
		status.setUserName(userName);
		status.setText(text);
		status.setStatusDate(statusDate);
				
		return status;
		
	}
	
	public List <Status> getUserTimeline (String userName, int limit) {
		
		String url = "http://twitter.com/" + userName;
		List <Status> statuses = new ArrayList<Status>();
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		}
		catch (SocketTimeoutException e) {
			System.err.println("The connection time has expired");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements link = doc.getElementsByAttributeValue("class", "StreamItem js-stream-item" );
		List <Element> elements = link.subList(0, limit);
					
		for (Element element: elements) {
				
			Elements linkStatId = element.getElementsByAttribute("data-tweet-id");
			String idStr = linkStatId.attr("data-tweet-id");
			long statusId = new Long(idStr);
				
			String name = element.attr("data-screen-name");
				
			Elements dateLink = element.getElementsByAttribute("data-time");
			String dateStr = dateLink.attr("data-time");
			Date date = new Date(new Long(dateStr)*1000);
				
			//It should be overwritten
			Elements textLink = element.getElementsByClass("ProfileTweet-contents");
			Element textLink2 = textLink.get(0);
			Elements textLink3 = textLink2.getElementsByAttribute("lang");
			String text = textLink3.text();
				
			Status status = new Status();
			status.setStatusId(statusId);
			status.setUserName(name);
			status.setStatusDate(date);
			status.setText(text);
			statuses.add(status);
		}
		
		
		return statuses;
		
	}
	
	public long postStatus(String text) {
		long statusId = 0L;
		CloseableHttpClient clientStatus = HttpClients.createDefault();
		HttpPost postStat = new HttpPost("https://twitter.com/i/tweet/create");
		List<NameValuePair> postEntity = new ArrayList<NameValuePair>();
		postEntity.add(new BasicNameValuePair("status", text));
		postEntity.add(new BasicNameValuePair("authenticity_token", authenticity_token));
		try{
			postStat.setEntity(new UrlEncodedFormEntity(postEntity));
			CloseableHttpResponse respStatus = null;
			try {
			respStatus = clientStatus.execute(postStat, context);
			}
			catch (SocketTimeoutException e) {
				System.err.println("The connection time has expired");
			}
			catch (IOException e) {
				e.printStackTrace();
			}
						
			HttpEntity  getEntity = respStatus.getEntity();
			String entStr = EntityUtils.toString(getEntity);
			Matcher matcher = Pattern.compile("\"tweet_id\":\"(\\d+)\".*").matcher(entStr);
			
			if (matcher.find()) {
			statusId = new Long(new String(matcher.group(1)));}
			clientStatus.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return statusId;
				
	}
	
}
