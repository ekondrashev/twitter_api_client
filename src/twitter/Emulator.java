package twitter;

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

public class Emulator {
	Bank bank = new Bank();
	Random random = new Random();

	public void generateClients() {
		for (int i = 0; i < 20; i++) {
			int j = random.nextInt(10000);
			bank.addClient(i, j);
		}
	}

	public void execute() {
		Set<Callable<String>> callables = new HashSet<Callable<String>>();

		for (int i = 0; i < 100; i++) {
			callables.add(new Callable<String>() {
				public String call() {
					Integer nPayer = random.nextInt(20);
					Integer nPayee = random.nextInt(20);
					Integer amount = random.nextInt(10000000);
					if (nPayer == nPayee) {
						nPayer = random.nextInt(10);
						nPayee = random.nextInt(10) + 10;
					}

					String result = bank.clientRequest(nPayer, nPayee, amount);
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
			if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
				if (!executorService.awaitTermination(60, TimeUnit.SECONDS))
					System.err.println("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			executorService.shutdownNow();
			Thread.currentThread().interrupt();
		}

	}

}
