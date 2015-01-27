import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Test;


public class BankTransferTest {

	@Test
	public void test() throws InterruptedException, ExecutionException {
			for (int i=0; i<100; i++){
					BankTransfer bank = new BankTransfer();
					List<BankClient> clients = bank.makeClients();
					long sumBefore =0;
					long sumAfter =0;
					
					for (int j =0; j<clients.size(); j++){
						BankClient bc = (BankClient) clients.get(j);
						sumBefore += bc.getSaldo();
					}
					bank.runMe();
					
					for (int j =0; j<clients.size(); j++){
						BankClient bc = (BankClient) clients.get(j);
						sumAfter += bc.getSaldo();
					}
						assertEquals(sumBefore, sumAfter);
					}
	}

	
}
