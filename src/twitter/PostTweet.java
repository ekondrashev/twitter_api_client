package twitter;


import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.http.HttpResponse;


public class PostTweet {
	
	public static void main(String[] args) throws HttpException, IOException {
	
	
		  /*PostMethod filePost = new PostMethod("https://twitter.com/sessions");
		 // filePost.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
			
		  Part[] parts = {
		      new StringPart("session[username_or_email]", "KateVarlamova"),
		      new StringPart("session[password]", "Katusha"),
		     
		  };
		  filePost.setRequestEntity(
		      new MultipartRequestEntity(parts, filePost.getParams()));
		  
		  HttpClient client = new HttpClient();
		  //client.getParams().setAuthenticationPreemptive(false);
		 int status = client.executeMethod(filePost);
		  
		  
		  System.out.println(status);*/
		
		
		
		
		
		PostMethod method = new PostMethod("https://twitter.com/sessions");
	
	HttpConnectionManager connectionManager =  new MultiThreadedHttpConnectionManager();
	
	
	NameValuePair userName = new NameValuePair ("username_or_email", "KateVarlamova");
	NameValuePair password = new NameValuePair ("password", "Katusha");
	
	NameValuePair[] parametersBody = {userName, password};
	
	method.setRequestBody(parametersBody);
	method.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
	
	 HttpClient client = new HttpClient(connectionManager);
	 client.getParams().setAuthenticationPreemptive(false);
	 
	 int status = client.executeMethod(method);
	 
	 System.out.println(status);
	
	}
	

}





