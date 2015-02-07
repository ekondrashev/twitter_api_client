package twitter;

import java.util.HashMap;
import java.util.Map;

public class Bank {
	Map<Integer, Account> accounts = new HashMap<Integer, Account>();

	public void addClient(int idClient) {
		Account account = new Account(idClient);
		accounts.put(idClient, account);
	}

	public void addClient(int idClient, int money) {
		Account account = new Account(idClient);
		clientRequest(account, money);
		accounts.put(idClient, account);
	}

	public String clientRequest(int idPayer, int idPayee, int amount) {
		Account payer = accounts.get(idPayer);
		Account payee = accounts.get(idPayee);
		Account lock1, lock2;
		if (idPayer < idPayee) {
			lock1 = payer;
			lock2 = payee;
		} else {
			lock1 = payee;
			lock2 = payer;
		}
		synchronized (lock1) {
			synchronized (lock2) {
				Transaction transaction = new Transaction();
				String result = transaction.transfer(payer, payee, amount);
				return result;
			}
		}
	}

	public String clientRequest(Account account, int amount) {

		synchronized (account) {
			Transaction transaction = new Transaction();
			String result = transaction.putCash(account, amount);
			return result;
		}
	}

	public int calculateAllMoney() {
		int sum = 0;
		for (Integer key : accounts.keySet()) {
			Account account = accounts.get(key);
			int value = account.getMoney();
			sum += value;
		}
		return sum;
	}
}
