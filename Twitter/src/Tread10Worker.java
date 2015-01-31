import java.util.concurrent.CountDownLatch;

public class Tread10Worker extends Thread{
  
	private CountDownLatch _cdl;

    public Tread10Worker (CountDownLatch cdl) {
        _cdl = cdl;
 
    }

    @Override
    public void run() {
    	
		for (int k =0; k<1000000; k++) {

			Thread10.count.incrementAndGet();
		}
        _cdl.countDown();
    }

}
