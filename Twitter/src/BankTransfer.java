import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BankTransfer {

	private static int clientAmount;
	static long totalSum = 0;
	static long totalSumAfter = 0;
    static Random randomLong = new Random();
    static Random randomInt = new Random();
    static List<BankClient> listOfClient;
	

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		makeNewClients();
		runMe();
	}

	public static void makeNewClients() {
		clientAmount = 20; //sc.nextInt();
		 
		List<BankClient> listOfClient = makesClient();
		for (int i=0; i<clientAmount; i++) {
			System.out.println(listOfClient.get(i));
		}
		System.out.println("All done. We have "+totalSum+"$.");

	}

	public static void runMe() throws InterruptedException, ExecutionException {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Put amount of clients:");

		int nTrans = 50; //sc.nextInt();
	//	sc.close();
		ExecutorService ex = Executors.newFixedThreadPool(nTrans);		
		for (int i = 0; i < nTrans; i++) {
			
		int c1 =0;
		int c2 =0;
		while(c1==c2 || c1 > clientAmount || c2 > clientAmount){
		c1 = randomInt.nextInt(clientAmount);
		c2 = randomInt.nextInt(clientAmount);
		}
		
		Future<String> s=ex.submit(new MoneyTransfer(listOfClient.get(c1), listOfClient.get(c2), randomInt.nextInt(1000) ));
		System.out.println(s.get());;
		}
		
		for (int k=0; k<clientAmount; k++) {
			totalSumAfter = totalSumAfter + listOfClient.get(k).saldo; 
		}
		System.out.println(" We have "+totalSumAfter+"$");

	}
	
	
	private static List<BankClient> makesClient() {
		
		List<BankClient> list = new ArrayList<BankClient>();
	     randomLong.setSeed(1000000000);
	     for (int i = 0; i < clientAmount; i++) {
	    	long clNumber = Math.abs(randomLong.nextLong()); 
	    	int saldo = randomInt.nextInt(1000000);
	    	totalSum = totalSum + saldo; 
	    	list.add(new BankClient(clNumber, saldo));
			
		}
		
		return list;
	
	}

}

class BankClient {
	static int clientNumber;
	String name;
	private long orderNumber;
	int saldo;
	BankClient(long orderNumber, int saldo){
		clientNumber++;
	
		name = "Client CityBank "+ clientNumber;
		this.orderNumber = orderNumber;
		this.saldo = saldo;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name);
		sb.append(" ");
		sb.append(orderNumber);
		sb.append(" ");
		sb.append(saldo);
			
		return sb.toString();
	}
}

class MoneyTransfer implements Callable<String>{
	public BankClient cl1;
	public BankClient cl2;
	public int summa;
	
	MoneyTransfer(BankClient cl1, BankClient cl2, int summa){
		this.cl1 = cl1;
		this.cl2 = cl2;
		this.summa = summa;
	}
	
	public String call() {
		
		synchronized (cl1) {
			if (cl1.saldo <summa) {
				return "No many, no party!";
			} 
			cl1.saldo -= summa;
			synchronized (cl2) {
				cl2.saldo += summa;
			}
		}
		
		return "Transfer from "+cl1.name + " to "+cl2.name +" in "+summa+"$ complete. ";

		}

	
}


