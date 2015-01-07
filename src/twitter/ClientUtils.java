package twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
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
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ClientUtils {
	
	public static boolean checkLogPas(String userName, String password) {
		
		HttpClientContext context = null;
		
		String authenticity_token = null;
	
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
		
			CloseableHttpClient checkClient = HttpClients.createDefault();
		
			HttpPost postCred = new HttpPost("https://twitter.com/sessions");
			
			List<NameValuePair> credentials = new ArrayList<NameValuePair>();
			credentials.add(new BasicNameValuePair("session[username_or_email]", userName));
			credentials.add(new BasicNameValuePair("session[password]", password));
			credentials.add(new BasicNameValuePair("authenticity_token", authenticity_token));
			postCred.setEntity(new UrlEncodedFormEntity(credentials));
			
			CloseableHttpResponse checkResp = checkClient.execute(postCred,context);
			Header[] headers = checkResp.getHeaders("location");
			checkClient.close();
			checkResp.close();
			
			for (Header header: headers) {
				String location = header.getValue();
				System.out.println(location);
				if (location.equals("https://twitter.com/")) {
					return true;
				}
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();	
		}
		
		
		return false;
		
	
		}

}
