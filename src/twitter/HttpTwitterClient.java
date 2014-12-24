package twitter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpTwitterClient {
	
	public Status getStatus (long statusId) throws IOException {
		
		String url = "http://twitter.com/*/status/" + statusId;
		
		Document doc = Jsoup.connect(url).get();
		
		Elements textLink = doc.getElementsByAttributeValue("property", "og:description");
		String text = textLink.attr("content");
		text = text.substring(1, (text.length()-1));
		
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
	
	public List <Status> getUserTimeline (String userName, int limit) throws IOException {
		
		String url = "http://twitter.com/" + userName;
		Document doc = Jsoup.connect(url).get();
		Elements link = doc.getElementsByAttributeValue("class", "StreamItem js-stream-item" );
		List <Element> elements = link.subList(0, limit);
		
		List <Status> statuses = new ArrayList<Status>();
		
		for (Element element: elements) {
			String idStr = element.id();
			String uns = "stream-item-tweet-";
			idStr = idStr.substring(uns.length());
			long statusId = new Long(idStr);
						
			Status status = getStatus(userName, statusId);
			statuses.add(status);
		}
		
		return statuses;
		
	}
	
	
	
	
	

}
