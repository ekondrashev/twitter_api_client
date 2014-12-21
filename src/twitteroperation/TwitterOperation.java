package twitteroperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import org.apache.http.impl.client.HttpClients;

public class TwitterOperation implements TwitterClient {
	public static void main(String []args) throws ClientProtocolException
	{
		stringGetMetod();
		stringBuilderGetMetod();
	}
	public static void stringGetMetod() {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();;
			HttpGet getRequest = new HttpGet("https://twitter.com/KateVarlamova");
			HttpResponse response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output;
			System.out.println("============Output:============");
			while ((output = br.readLine()) != null) {
			System.out.println(output);
			}		 
			httpClient.close();		 
			} catch (ClientProtocolException e) {
			e.printStackTrace();		 
			} catch (IOException e) {
			e.printStackTrace();
			}	
	}
	public static void stringBuilderGetMetod() {
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();;
			HttpGet getRequest = new HttpGet("https://twitter.com/KateVarlamova");
			HttpResponse response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output;
			
			//add StringBuilder for concatenating Strings from BufferedReader;
			StringBuilder outhtml = new StringBuilder();
			
			System.out.println("============Output:============");
			while ((output = br.readLine()) != null) {
			outhtml.append(output);
			}		 
			httpClient.close();
			String page = outhtml.toString();
			System.out.println(page);
			} catch (ClientProtocolException e) {
			e.printStackTrace();		 
			} catch (IOException e) {
			e.printStackTrace();
			}	
	}
	}


