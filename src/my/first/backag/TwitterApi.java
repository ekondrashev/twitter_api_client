package my.first.backag;

interface TwitterApi {
	
	Object getStatus(String id);
	List<Object> getUserTimeLine(String userId);

}
