package twitter;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class AndOneMoreTest {

	public static void main(String[] args) throws IOException {
		
		String userName = "s_vakarchuk";
		long statusId = 547332446560473088L;
		
		String url = "http://twitter.com/" + userName + "/status/" + statusId;
		
		Document doc = Jsoup.connect(url).get();
		
		Elements textLink = doc.getElementsByAttributeValue("property", "og:description");
		String text = textLink.attr("content");
		text = text.substring(1, (text.length()-1));
		
		
		Elements dateLink = doc.getElementsByAttribute("data-time-ms");
		String dateStr = dateLink.attr("data-time-ms");
		
		
		
		
		//System.out.println(dateLink);
		System.out.println(dateStr);
		//System.out.println(text);

	}

}
