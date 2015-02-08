package HW;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Bank {
	
	private Map<Account, Client> ñlientsAndMoney =  new HashMap<Account, Client>();
	private List<Account> listAccounts =  new ArrayList <Account>();		
	private List<Client> listClients =  new ArrayList <Client>();
	static int idAccount = 10000;
//    static Random randomLong = new Random();
    static Random randomInt = new Random();	
	
   private synchronized int newId(){
	   idAccount++;
	   return idAccount;
   }
    
	public void madeNewClients() {
		
		Account ac= new Account(newId(),Math.abs(randomInt.nextInt(1000000)));
		Client cl =  new Client("Client ¹ "+idAccount, ac);
		listAccounts.add(ac);
		listClients.add(cl);
		ñlientsAndMoney.put(ac, cl);
		
		System.out.println(ac.accountNumber+" "+ac.getSumma());
		
	}

	
	public long balance(){
		long totalSumma =0;
		for (Account account : listAccounts) {
			totalSumma += account.summa;
		}
		return totalSumma;
		
	}
	
	public String startTransaction() throws InterruptedException, ExecutionException {
		Client cl1 = listClients.get(randomInt.nextInt(100)); //pay
		Client cl2 = listClients.get(randomInt.nextInt(100)); //get
		int summOfTrans = randomInt.nextInt(10000);
		new Transaction(cl1, cl2, summOfTrans);
		
		return "";
	}
	
	public Map<Account, Client> getClientAndMoney() {
		return ñlientsAndMoney;
	}
	
	public List<Account> getAccounts() {
		return listAccounts;
	}
	
	
	public List<Client> getClients() {
		return listClients;
	}
		
	public void addAccounts(Account acc) {
		listAccounts.add(acc);
	}

	public void addClients(Client cl) {
		listClients.add(cl);
	}

	public void putMoney(Client cl, int summa){
		cl.getClientsAccount().incrSumma(summa);
	}

	public void takeMoney(Client cl, int summa){
		cl.getClientsAccount().decrSumma(summa);
	}


	
}
