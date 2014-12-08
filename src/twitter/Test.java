package twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.omg.CORBA.portable.InputStream;

public class Test {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://twitter.com/KateVarlamova");
		CloseableHttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		
		
		java.io.InputStream instream = entity.getContent();
		BufferedReader in = new BufferedReader(new InputStreamReader(instream, "UTF-8"));
		String inputLine;
		StringBuffer html = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			html.append(inputLine);
		}
		in.close();
		
		
		
		String status = html.toString();
		//System.out.println(EntityUtils.toString(entity));
		System.out.println(status);   
		
		//Document doc = Jsoup.connect("https://twitter.com/xenia_sobchak/status/541931734305374208").get();
		
		//Document doc = Jsoup.parse(status);
		//Elements link = doc.getElementsByAttributeValue("property", "og:description");
		//System.out.println(link);
		//String linkHref = link.attr("content");
		//System.out.println(linkHref);
		
		//Pattern pattern = Pattern.compile("<meta  property=\"og:description\" content=\"“(.*?)”\">");
		//Matcher matcher = pattern.matcher(status);
		//matcher.find();
		//status = matcher.group(1);
		
		//status = status.replaceAll("&quot;", "\"");
		//System.out.println(status);
		//response.close();
	}

}
