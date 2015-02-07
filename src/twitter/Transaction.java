package twitter;

public class Transaction {
	private boolean check(Account payer, int amount) {
		if (payer.getMoney() < amount) {
			return true;
		}
		return false;
	}

	public String transfer(Account payer, Account payee, int amount) {
		if (check(payer, amount))
			return "This transaction cannot be completed";
		int monPayer = payer.getMoney();
		int monPayee = payee.getMoney();
		monPayer -= amount;
		monPayee += amount;
		payer.setMoney(monPayer);
		payee.setMoney(monPayee);

		return "This transaction was successful";
	}

	public String putCash(Account account, int amount) {

		int monAcc = account.getMoney();
		monAcc += amount;
		account.setMoney(monAcc);
		return "This transaction was successful";

	}

}
