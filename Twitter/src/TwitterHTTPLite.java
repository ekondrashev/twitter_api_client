import java.io.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
	
public class TwitterHTTPLite {
	//http://apache.cp.if.ua//httpcomponents/httpclient/binary/httpcomponents-client-4.3.6-bin.zip

	
	public static void main(String[] args) throws ClientProtocolException, IOException {
	    HttpClient httpClient = new DefaultHttpClient();
	      HttpGet httpGetRequest = new HttpGet("http://twitter.com/euromaidan/status/540146266324602880");
	 
	      // Execute HTTP request
	      HttpResponse httpResponse = httpClient.execute(httpGetRequest);
	 
	      System.out.println("----------------------------------------");
	      System.out.println(httpResponse.getStatusLine());
	      System.out.println("----------------------------------------");
	
	      // Get hold of the response entity
	      HttpEntity entity = httpResponse.getEntity();
		      
	      DataOutputStream os;
		        os = new DataOutputStream(
				         new FileOutputStream("C:\\testfile.html"));
		        entity.writeTo(os);
		        os.flush();
		        os.close();

	      httpClient.getConnectionManager().shutdown();
	}
}
