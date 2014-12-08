package twitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTTPclientUTL {

	public static void main(String[] args) throws IOException {
		
				int limit = 5;
				String userName = "KateVarlamova";
				String url = "http://twitter.com/" + userName;
				Document doc = Jsoup.connect(url).get();
				Elements link = doc.getElementsByAttributeValue("class", "ProfileTweet-text js-tweet-text u-dir");
				List <Element> elements = link.subList(0, limit);
				List <String> statuses = new ArrayList <String>();
				for (Element elment: elements) {
					String status = elment.text();
					statuses.add(status);
				}
				
				System.out.println(statuses);
		
	}

}
