package twitter;

import org.junit.Test;

import junit.framework.TestCase;

public class CounterTest extends TestCase {
	
	 @Test
	public void testCounter(){
		Counter counter = new Counter();
		int actual = counter.execute();
		int expected = 10000000;
		assertEquals(actual,expected);
	}
	
	 @Test
		public void testCounterMult(){
		 for (int i= 0; i<100; i++){
			 CounterMult counter = new CounterMult();
			 int actual = counter.execute();
			 int expected = 10000000;
			 assertEquals(actual,expected);
	 }
	 }

}
