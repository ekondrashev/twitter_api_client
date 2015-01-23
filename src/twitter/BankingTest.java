package twitter;

import junit.framework.TestCase;

public class BankingTest extends TestCase{
	
	public void testBanking(){
		for (int i=0; i<100; i++){
		Banking bank = new Banking();
		bank.execute();
		assertEquals(bank.sums.get(0),bank.sums.get(1));
		}
	}

}
