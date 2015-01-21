import static org.junit.Assert.*;

import org.junit.Test;



public class Thread10Test {

	@Test
	public void teststartAllThreads() throws InterruptedException {
		 for (int i= 0; i<100; i++){
			 Thread10.count.set(0);;
			 int actual = Thread10.startAllThreads();
			 int expected = 10000000;
			 assertEquals(actual,expected);
			 }
	}

}
