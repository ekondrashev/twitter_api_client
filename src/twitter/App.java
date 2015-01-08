package twitter;

import java.util.ArrayList;
import java.util.List;
 
class UseList {
	
	List list = new ArrayList();
       
    public void execute(){
    	                
        Thread addValue = new Thread (new Runnable(){
        	
        	public void run() {
        		
        		for(int i=0; i<100; i++) {
        			
        			list.add(i);
        		}
            
                list.add("Stop!");
             }
        });
       
        addValue.start();
       
        Thread readValue = new Thread (new Runnable(){
        	
        	public void run() {
        		
        		int i = 0;
                       
                while (list.size()>0 && list.get(i) != "Stop!"){
                	
                	System.out.println("Read new item: " + list.get(i));
                    list.remove(i);
                        
                }
                    
             }
                
        });
       
        readValue.start();
        
        try {
        	addValue.join();
        	readValue.join();
        }
        
       catch (InterruptedException ignore) {/*NOP*/}
       
    }
         
}
 
public class App {
	
	public static void main(String[] args) {
		
		UseList newList = new UseList();
        newList.execute();
               
     }
 
}