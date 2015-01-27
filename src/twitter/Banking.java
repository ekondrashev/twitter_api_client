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
import java.util.concurrent.TimeUnit;

public class Banking {

	List<Account> accounts = new ArrayList<Account>();
	Random random = new Random();

	Banking() {
		for (int i = 0; i < 20; i++) {
			int value = random.nextInt(100000000);
			Account account = new Account(value);
			accounts.add(account);
		}

	}

	int calculateSum() {
		int sum = 0;
		for (Account account : accounts) {
			int value = account.getAccount();
			sum += value;
		}
		return sum;
	}

	String transfer() {
		Integer lock1 = 0;
		Integer lock2 = 0;
		Integer nPayer = random.nextInt(20);
		Integer nPayee = random.nextInt(20);
		Integer amount = random.nextInt(10000000);
		if (nPayer == nPayee) {
			nPayer = random.nextInt(10);
			nPayee = random.nextInt(10) + 10;
		}
		Account payer = accounts.get(nPayer);
		Account payee = accounts.get(nPayee);
		if (nPayer < nPayee) {
			lock1 = nPayer;
			lock2 = nPayee;
		} else {
			lock1 = nPayee;
			lock2 = nPayer;
		}

		synchronized (lock1) {
			synchronized (lock2) {
				if (amount > payer.getAccount())
					return "This transaction cannot be completed";
				payer.withdraw(amount);
				payee.put(amount);
			}

		}

		return "This transaction was successful";
	}

	void execute() {
		Set<Callable<String>> callables = new HashSet<Callable<String>>();

		for (int i = 0; i < 100; i++) {
			callables.add(new Callable<String>() {
				public String call() {
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

		for (Future<String> future : futures) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(300, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
				if (!executorService.awaitTermination(300, TimeUnit.SECONDS))
					System.err.println("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			executorService.shutdownNow();
			Thread.currentThread().interrupt();
		}

	}

}

class Account {
	private int account;

	Account(int account) {
		this.account = account;
	}

	void withdraw(int amount) {
		account = account - amount;
	}

	void put(int amount) {
		account = account + amount;
	}

	int getAccount() {
		return account;
	}

}