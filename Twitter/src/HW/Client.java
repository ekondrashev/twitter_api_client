package HW;

public class Client {
	private String name;
	private Account clientsAccount;
	
	Client(String name){
		this.name = name;
	}

	Client(String name, Account clientsAccount){
		this.name = name;
		this.clientsAccount = clientsAccount;
	}
	
	public String getName(){
		return name;
	}
	
	public long getSumma(){
		return clientsAccount.getSumma();
	}

	public void setClientsAccount(Account clientsAccount) {
		this.clientsAccount = clientsAccount;
	}

	public Account getClientsAccount() {
		return clientsAccount;
	}
	
	
}
