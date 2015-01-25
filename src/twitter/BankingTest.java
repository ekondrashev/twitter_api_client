package twitter;

import junit.framework.TestCase;

public class BankingTest extends TestCase{
	int sumBT=0;
	int sumAT=0;
	public void testBanking(){
		for (int i=0; i<10000; i++){
			Banking bank = new Banking();
			sumBT = bank.calculateSum();
			bank.execute();
			sumAT = bank.calculateSum();
			assertEquals(sumBT,sumAT);
		}
	}

}
