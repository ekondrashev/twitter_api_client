package twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Test3 {

	public static void main(String[] args) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();;
		HttpGet getRequest = new HttpGet("https://twitter.com/s_vakarchuk/status/547332446560473088");
		HttpResponse response = httpClient.execute(getRequest);
	
		HttpEntity entity = response.getEntity();
		
		//System.out.println(EntityUtils.toString(entity));
		
		InputStream instream = entity.getContent();
		BufferedReader in = new BufferedReader(new InputStreamReader(instream, "UTF-8"));
		String inputLine;
		StringBuffer html = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			html.append(inputLine);
		}
		in.close();
		String page = html.toString();
		System.out.println(page);

	}

}
