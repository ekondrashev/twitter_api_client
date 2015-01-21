package twitter;

public class CounterMult {
	
	int counter = 0;
	CounterRun countRun = new CounterRun();
	
	public synchronized void increment() {
		counter++;
	 }
     
	 public int execute(){
		 
		 Thread thread1 = new Thread (countRun);
		 Thread thread2 = new Thread (countRun);
		 Thread thread3 = new Thread (countRun);
		 Thread thread4 = new Thread (countRun);
		 Thread thread5 = new Thread (countRun);
		 Thread thread6 = new Thread (countRun);
		 Thread thread7 = new Thread (countRun);
		 Thread thread8 = new Thread (countRun);
		 Thread thread9 = new Thread (countRun);
		 Thread thread10 = new Thread (countRun);
		 
		 thread1.start();
		 thread2.start();
		 thread3.start();
		 thread4.start();
		 thread5.start();
		 thread6.start();
		 thread7.start();
		 thread8.start();
		 thread9.start();
		 thread10.start();
		 
		 
		 try {			 
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
			thread5.join();
			thread6.join();
			thread7.join();
			thread8.join();
			thread9.join();
			thread10.join();
						
		 } catch (InterruptedException ignore) {/*NOP*/}
		 
		 return counter;
		 
	 }
	 class CounterRun implements Runnable{

			@Override
			public void run() {
				
				for (int i = 0; i < 1000000; i++)
                {
                    increment();
                }
				
			}       
}
}
