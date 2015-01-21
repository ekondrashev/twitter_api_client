package twitter;

public class Counter {
	int counter = 0;
	
	 public synchronized void increment() {
		
		 counter++;
				 
	 }
    
	 public int execute(){
		 Thread thread = new Thread (new Runnable(){
			 public void run()
            {
                    for (int i = 0; i < 10000000; i++)
                    {
                            increment();
                    }
            }
		 }
				 );
		 thread.start();
		 try {
			thread.join();
		} catch (InterruptedException ignore) {/*NOP*/}
		 
		 return counter;
		 
	 }
}
