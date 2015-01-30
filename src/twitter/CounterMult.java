package twitter;

import java.util.concurrent.CountDownLatch;

public class CounterMult {

	int counter = 0;
	CountDownLatch latch = new CountDownLatch(10);
	CounterRun countRun = new CounterRun(latch);

	public synchronized void increment() {
		counter++;
	}

	public int execute() {

		for (int i = 0; i < 10; i++) {
			new Thread(countRun).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return counter;

	}

	class CounterRun implements Runnable {

		CountDownLatch latch = null;

		public CounterRun(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {

			for (int i = 0; i < 1000000; i++) {
				increment();
			}

			latch.countDown();

		}
	}
}
