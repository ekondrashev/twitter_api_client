package twitter;

import junit.framework.TestCase;

public class EmulatorTest extends TestCase {
	
	public void testEmulator() {
		for (int i = 0; i < 100; i++) {
			int sumBT = 0;
			int sumAT = 0;
			Emulator emulator = new Emulator();
			emulator.generateClients();
			sumBT = emulator.bank.calculateAllMoney();
			emulator.execute();
			sumAT = emulator.bank.calculateAllMoney();
			assertEquals(sumBT, sumAT);
		}
	}

}
