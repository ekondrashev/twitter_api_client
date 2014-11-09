package twitter;


import java.util.List;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterIO implements Twitterclient {

	

	public static void main(String[] args) throws TwitterException   {
		
		
		
		ConfigurationBuilder cb= new ConfigurationBuilder();
		
		 cb.setDebugEnabled(true)
		  .setOAuthConsumerKey ("lUovo3BZqnUZtHZ93bMswt3Eo")
		  .setOAuthConsumerSecret ("fjOLhctYpofr67r3leuRYc80fs7wv0HWbGVaPPgXDMpoaQ18bq")
		  .setOAuthAccessToken ("2859592708-wYYGmguzOaImRPjxirc1TZj3EkNdWWHOJDCNskq")
		  .setOAuthAccessTokenSecret ("0r61bdwmrYEXlYheU2lNEGyvEMhJQTd7deAFCzYcuRaNk");
		 
		 TwitterFactory tf= new TwitterFactory(cb.build());
		 twitter4j.Twitter tw= tf.getInstance();
		 
		 
		 
		 
			//POSTING MESSAGE************
		 
			
			//Status stat = tw.updateStatus("Hello");
		
	   // System.out.println("Posted!!!");
	

	
		
		//Reading****(USERNAME,TEXT,STAT ID,USER ID************
		
		List<Status> statuses = tw.getHomeTimeline();
		
		for (Status status1: statuses){
			long userId=status1.getUser().getId();
			System.out.println(status1.getUser().getName()+":"+status1.getText()+"  "+"STATUS ID:"+"  "+status1.getId()+"  "+"USER ID:"+userId);
		}
			
	//sample reading userId*********************
		 //ResponseList<Status> a = tw.getHomeTimeline(new Paging(1,1000));

	            //for (Status b: a){
	                //long userId = b.getUser().getId();// user Id
	                //String userName = b.getUser().getName(); // user name
	                

	                //System.out.println(userId+" "+userName);
	            
	        
		 //}
	}

	@Override
	public void postStatus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getUserTimeline() {
		// TODO Auto-generated method stub
		
	}
}

