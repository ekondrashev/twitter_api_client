import java.io.IOException;
import java.util.ArrayList;


public class HTTPC {
	private static void process(String domainStr,
            String dateStr,
            String numberStr) throws IOException {
String s = getUrl("http://www.liveinternet.ru/stat/" + domainStr +
    "/pages.html?date=" + dateStr +
    "&period=month&total=yes&per_page=100");
ArrayList< Pair<String, Integer> > pages =
parseLiveinternatStat(domainStr, s);
int maxNumber = Integer.parseInt(numberStr);
int currentNumber = 0;
System.out.println("<ul>");
for(Pair<String, Integer> p : pages) {
String url = p.getLeft();
String views = p.getLeft();
String title = getPageTitle(url);
String end = ending(views);
System.out.printf("<li><a href=\"%s\">%s</a> %d просмотр%s " +
      "за мес€ц</a></li>\n", url, title, views, end);
currentNumber++;
if(currentNumber >= maxNumber) break;
}
System.out.println("</ul>");
}

	private static String ending(String views) {
		// TODO Auto-generated method stub
		return null;
	}

	private static ArrayList<Pair<String, Integer>> parseLiveinternatStat(
			String domainStr, String s) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String getUrl(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String getPageTitle(String url) {
		// TODO Auto-generated method stub
		return null;
	}

}
