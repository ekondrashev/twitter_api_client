package twitter;

public class Account {
	int money = 0;
	int idClient;

	public Account(int idClient) {
		this.idClient = idClient;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getIdClient() {
		return idClient;
	}

}
