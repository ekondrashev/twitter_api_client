package twitter;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class HTTPclient {
	
	public static void main(String[] args) throws IOException {
		
		String userName = "euromaidan";
		long id = 546486598448447488L;
		String url = "http://twitter.com/" + userName + "/status/" + id;
				
		Document doc = Jsoup.connect(url).get();
		
		
		Elements link = doc.getElementsByAttributeValue("property", "og:description");
		String linkContent = link.attr("content");
		linkContent = linkContent.substring(1, (linkContent.length()-1));
		
		System.out.println(link.toString());
			
		
		
	}
		
	
}
