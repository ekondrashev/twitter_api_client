package twitter;

import java.io.IOException;


import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class StatusPost {

	public static void main(String[] args) throws ParseException, IOException {
		
		 CredentialsProvider credsProvider = new BasicCredentialsProvider();
	        credsProvider.setCredentials(
	                new AuthScope("twitter.com", 80),
	                new UsernamePasswordCredentials("KateVarlamova", "Katusha"));
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setDefaultCredentialsProvider(credsProvider)
	                .build();
	   
		
		StringEntity myEntity = new StringEntity("УРА! Это работает!", ContentType.create("text/plain", "UTF-8"));
			
		HttpPost httppost = new HttpPost("https://twitter.com/");
		httppost.setEntity(myEntity);
		 
		CloseableHttpResponse response = httpclient.execute(httppost);
		
		
		
		System.out.println(response.getStatusLine().toString());
		
		response.close();
		
			

	}

}

