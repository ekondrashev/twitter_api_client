
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.*;

public abstract class Thread10 implements Runnable {

	static final AtomicInteger count = new AtomicInteger(0);
	static Thread[] aTh= new Thread[10];
	
	static int startAllThreads() throws InterruptedException{
		CountDownLatch latch = new CountDownLatch(10);
		for (int i = 0; i < 10; i++) {
			aTh[i] = new Tread10Worker(latch);
			aTh[i].start();
			}
		latch.await();
		return count.get();
		
	}
	
	public static void main(String[] args) throws InterruptedException  {
	
		System.out.println(startAllThreads());

	}

}
