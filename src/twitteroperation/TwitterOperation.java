package twitteroperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;

public class TwitterOperation implements TwitterClient {
	public static void main(String []args) throws ClientProtocolException
	{
		 try {
			 URL restServiceURL = new URL("https://twitter.com/dmitry_gordon");
			 HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
			 httpConnection.setRequestMethod("GET");
			 httpConnection.setRequestProperty("Accept", "text/html;charset=UTF-8");
			 if (httpConnection.getResponseCode() != 200) {
				 throw new RuntimeException("HTTP GET Request Failed with Error code : " + httpConnection.getResponseCode());
				 }
			 BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream())));
			 String output;
			 System.out.println("Output from Server:  \n");
			 while ((output = responseBuffer.readLine()) != null) {
				 System.out.println(output);
				 }
			 httpConnection.disconnect();
			 } catch (MalformedURLException e) {
				 e.printStackTrace();
				 } catch (IOException e) {
					 e.printStackTrace();
					 }
		
//	public getStatus ()
//	{
//		try {
//			CloseableHttpClient httpClient = HttpClients.createDefault();;
//			HttpGet getRequest = new HttpGet("https://twitter.com/KateVarlamova");
//			//getRequest.addHeader("accept", "application/html");
//			HttpResponse response = httpClient.execute(getRequest);
//			if (response.getStatusLine().getStatusCode() != 200) {
//			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
//			}
//			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
//			String output;
//			System.out.println("============Output:============");
//			while ((output = br.readLine()) != null) {
//			System.out.println(output);
//			}		 
//			httpClient.getConnectionManager().shutdown();		 
//			} catch (ClientProtocolException e) {
//			e.printStackTrace();		 
//			} catch (IOException e) {
//			e.printStackTrace();
//			}
			
		
	}
	}


