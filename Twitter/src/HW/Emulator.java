package HW;
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
    static Random randomLong = new Random();
    static Random randomInt = new Random();
    private static Bank javaBank = new Bank();
    
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		beginStart();
		
		System.out.println(javaBank.balance());
		
		beginTransaction();

		System.out.println(javaBank.balance());
		
	}

	private static void beginTransaction() throws InterruptedException, ExecutionException {
		for (int i = 0; i < 100; i++) {
			javaBank.startTransaction();
		
		}
		
	}

	private static void beginStart() {

		for (int i = 0; i < 100; i++) {// made  new accounts and clients
			javaBank.madeNewClients();
		
		}
		
	}


}
