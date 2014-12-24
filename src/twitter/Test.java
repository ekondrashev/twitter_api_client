package twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

public class Test {
	
		String authenticity_token = null;
		
		HttpClientContext context = null;
		
		public void authenticateUser (String userName, String password) throws ClientProtocolException, IOException {
			
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
			
			CloseableHttpResponse resCred = clientAuthen.execute(postCred, context);
			
			
						
		}
		
		
		
		
	

	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		
//		String userName = "s_vakarchuk";
//		long statusId = 547332446560473088L;
//		
//		String url = "http://twitter.com/" + userName + "/status/" + statusId;
//		
//		Document doc = Jsoup.connect(url).get();
//		
//		Elements textLink = doc.getElementsByAttributeValue("property", "og:description");
//		String text = textLink.attr("content");
//		text = text.substring(1, (text.length()-1));
//		
//		//It should be overwritten.
//		Elements dateLink = doc.getElementsByAttributeValue("class", "tweet-timestamp js-permalink js-nav js-tooltip");
//		String dateStr = dateLink.attr("title");
//		//String dateStr = "Wed, Jul 4, '01";
		
		
		//dateStr = dateStr.replace(" ", "");
		
		//ParsePosition pp = new ParsePosition(1); 
		//SimpleDateFormat date = new SimpleDateFormat("EEE, MMM d, ''yy");
		//System.out.println(date);
		//format.applyPattern("K:mma-ddMMYYYY");
		//format.setLenient(false);
		//try {
		//Date docDate= date.parse(dateStr);
		//System.out.println(docDate);
		
		// } catch (ParseException e) { 
	   //       System.out.println("Unparseable using "); 
	   //   }
		//Date now = new Date();
		//System.out.println(docDate);
		
//		Long statDate = new Long(dateStr);
//		Date statusDate = new Date(statDate);
//				
//		Status status = new Status();
//		status.setStatusId(statusId);
//		status.setUserName(userName);
//		status.setText(text);
//		status.setStatusDate(statusDate);
		
	//	System.out.println(dateStr);
		
//		HttpTwitterClient userTL = new HttpTwitterClient();
//		Status status = userTL.getStatus(userName, id);
//		System.out.println(status.getText());
//		System.out.println(status.getStatusDate());
		
//		int limit = 5;
//		String userName = "KateVarlamova";
//		
//		HttpTwitterClient userTL = new HttpTwitterClient();
//		
//		List <Status> statuses = userTL.getUserTimeline(userName, limit);
//		Status lastStatus = statuses.get(1);
//		String text = lastStatus.getText();
//		System.out.println(text);
		
		
//		String url = "http://twitter.com/" + userName;
//		Document doc = Jsoup.connect(url).get();
//		Elements link = doc.getElementsByAttributeValue("class", "StreamItem js-stream-item" );
//		List <Element> elements = link.subList(0, limit);
//		List <Status> statuses = new ArrayList<Status>();
//		for (Element element: elements) {
//			String idStr = element.id();
//			String uns = "stream-item-tweet-";
//			idStr = idStr.substring(uns.length());
//			//long statusId = new Long(idStr);
//			
		
//			statuses.add(status);
//			
//			
//		}
//		
//		Status lastStatus = statuses.get(1);
//		String text = lastStatus.getText();
//		
//		System.out.println(statuses);

		
		
		
		
		
		
		
		
		
		
//		String userName = "KateVarlamova";
//		long StatusId = 540146266324602880L;
//		
//		String url = "http://twitter.com/" + userName + "/status/" + StatusId;
//		
//		Document doc = Jsoup.connect(url).get();
//		
//		Elements textLink = doc.getElementsByAttributeValue("property", "og:description");
//		String text = textLink.attr("content");
//		text = text.substring(1, (text.length()-1));
//		
//		//It should be overwritten.
//		Elements dateLink = doc.getElementsByAttributeValue("data-aria-label-part","last");
//		String dateStr = dateLink.attr("data-time-ms");
//		
//		Date statusDate = new Date(new Long(dateStr));
//				
//		Status status = new Status();
//		status.setStatusId(StatusId);
//		status.setUserName(userName);
//		status.setText(text);
//		status.setStatusDate(statusDate);
//		
//		return status;
//		
//			
//		
//		
//		
//		
//		
//		String strLong = Long.toString(StatusId);
//		System.out.println(strLong);
//		
//		
//		CloseableHttpClient httpClient = HttpClients.createDefault();;
//		HttpGet getRequest = new HttpGet("https://twitter.com/s_vakarchuk/status/547332446560473088");
//		HttpResponse response = httpClient.execute(getRequest);
//	
//		HttpEntity entity = response.getEntity();
//		
//		//System.out.println(EntityUtils.toString(entity));
//		
//		InputStream instream = entity.getContent();
//		BufferedReader in = new BufferedReader(new InputStreamReader(instream, "UTF-8"));
//		String inputLine;
//		StringBuffer html = new StringBuffer();
// 
//		while ((inputLine = in.readLine()) != null) {
//			html.append(inputLine);
//		}
//		in.close();
//		String page = html.toString();
//		System.out.println(page);
		
		
		
		
//		Elements dateLink = doc.getElementsByAttributeValue("class", "tweet-timestamp js-permalink js-nav js-tooltip");
//		String dateStr = dateLink.attr("title");
//		SimpleDateFormat datePat = new SimpleDateFormat("K:mm a - dd MMM yyyy");
//		Date statusDate = null;
//		try{
//			statusDate = datePat.parse(dateStr);			
//		}
//		catch (ParseException e) {
//			 System.out.println("Unparseable date"); 
//		}

	

}
