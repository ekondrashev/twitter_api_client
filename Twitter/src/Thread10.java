
import java.util.concurrent.atomic.*;

public abstract class Thread10 implements Runnable {

	static final AtomicInteger count = new AtomicInteger(0);
	static Thread[] aTh= new Thread[10];
	
	static int startAllThreads() throws InterruptedException{
		for (int i = 0; i < 10; i++) {
			aTh[i] = new Thread(new Runnable() {

				public void run() // Этот метод будет выполняться в побочном потоке
				{
					//System.out.println(i);
				for (int k =0; k<1000000; k++) {
//					 increment();
					count.incrementAndGet();
				}
		 		
				}
			});
		}
		
		for(int i=0; i<10; i++){
			aTh[i].start(); 
		}
		for(int i=0; i<10; i++){
			aTh[i].join();
		}	
		return count.get();
		
	}
	
	public static void main(String[] args) throws InterruptedException  {
	
		System.out.println(startAllThreads());

	}

}
