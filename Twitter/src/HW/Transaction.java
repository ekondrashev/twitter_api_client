package HW;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Transaction {
	private static int numberTransaction;
	
	Transaction(Client cl1,  Client cl2, int summa) throws InterruptedException, ExecutionException{
		IncreaseNumberTransaction();
		ExecutorService ex = Executors.newFixedThreadPool(1);		
		Future<String> s=ex.submit(new MoneyTransfer(cl1, cl2, summa ));
		System.out.println(s.get());;
		}
		
	
	public static int getNumberTransaction() {
		return numberTransaction;
	}

	public static synchronized void IncreaseNumberTransaction() {
		numberTransaction ++;
	}
}

class MoneyTransfer implements Callable<String>{
		public Client cl1;
		public Client cl2;
		public int summa;
		
		MoneyTransfer(Client cl1, Client cl2, int summa){
			this.cl1 = cl1;
			this.cl2 = cl2;
			this.summa = summa;
		}
		
		public String call() {
			Account ac1 = cl1.getClientsAccount();
			Account ac2 = cl2.getClientsAccount();
			
			synchronized (ac1) {
				if (cl1.getSumma() <summa) {
					return "No many, no party!";
				} 
				ac1.decrSumma(summa); 
				synchronized (ac2) {
					ac2.incrSumma(summa); 
				}
			}
			
			return "Transfer from "+cl1.getName() + " to "+cl2.getName() +" in "+summa+"$ complete. ";

			}	
}


