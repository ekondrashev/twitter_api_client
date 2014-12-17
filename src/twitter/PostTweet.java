package twitter;


import java.io.IOException;



import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;





public class PostTweet {
	
	public static void main(String[] args) throws ParseException, IOException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpHost targetHost = new HttpHost("twitter.com", 443, "https");
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
		        new AuthScope(targetHost.getHostName(), targetHost.getPort()),
		        new UsernamePasswordCredentials("KateVarlamova", "Katusha"));

		
		AuthCache authCache = new BasicAuthCache();
		
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(targetHost, basicAuth);
		
		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		context.setAuthCache(authCache);

		HttpGet httpget = new HttpGet("/");
		CloseableHttpResponse response = httpclient .execute(targetHost, httpget, context);
		Header [] headers = response.getAllHeaders();
		for (Header header: headers) {
			System.out.println(header.getName() + " : " + header.getValue());
			}
		
		response.close();
		}
	}
	







