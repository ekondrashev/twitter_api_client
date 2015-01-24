import static org.junit.Assert.*;

import org.junit.Test;


public class BankTransferTest {

	@Test
	public void test() {
			for (int i=0; i<100; i++){
				BankTransfer bank = new BankTransfer();
					bank.runMe();
					assertEquals(bank.totalSum, bank.totalSumAfter);
					}
	}

	

}
