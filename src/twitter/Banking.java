package twitter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Banking {
	Random random = new Random();
	List <Integer> clients = new ArrayList<Integer>();
	Lock lock = new ReentrantLock();
	List <Integer> sums = new ArrayList<Integer>();
	
	public List<Integer> execute () {
		
		int sumBT=0;
		int sumAT=0;
		
		for (int i=0; i<100; i++) {
			int value = random.nextInt(100000000);
			clients.add(value);
		}
		
		for(Integer account: clients){
			sumBT += account;
		}
		
		Set<Callable<String>> callables = new HashSet<Callable<String>>();
		
		for (int i=0; i<50; i++) {
			callables.add(new Callable<String>() {
			    public String call(){
			    	
			    	String result = transfer();
					return result;
			    	
			    }
			});	
		}
		ExecutorService executorService = Executors.newFixedThreadPool(50);
		
		List<Future<String>> futures = null;
		try {
			futures = executorService.invokeAll(callables);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Future<String> future : futures){
		    try {
				System.out.println("future.get = " + future.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		executorService.shutdown();
		
		for(Integer account: clients){
			sumAT += account;
		}
		
		sums.add(sumBT);
		sums.add(sumAT);
		
		return sums;

	}
	
	String transfer(){
		
		lock.lock();
		
		Integer nPayer = random.nextInt(100);
    	Integer nPayee = random.nextInt(100);
    	Integer amount = random.nextInt(10000000);
    	if (nPayer==nPayee) nPayee=nPayer+1;
    	Integer payer = clients.get(nPayer);
    	Integer payee = clients.get(nPayee);
    	if (amount<payer)
    	{
    		payer = payer - amount;
    		payee = payee + amount;
    	} else {
    		lock.unlock();
    		return "This transaction cannot be completed";}
    	
    	clients.set(nPayer, payer);
    	clients.set(nPayee, payee);
    	
    	lock.unlock();
    	
        return "This transaction was successful";
		
	}

}



//class Account {
//	Account (int account){
//		this.account=account;
//	}
//	int account;
//	void withdraw(int amount) {
//		
//			account = account - amount;
//		
//	}
//	
//	void put(int amount) {
//		
//			account = account + amount;
//		
//	}
//	
//	int getAccount(){
//		return account;
//	}
//	
//}