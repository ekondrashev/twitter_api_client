package HW;

public class Account {
	int accountNumber;
	int summa = 0;
	
	Account(int accountNumber, int Summa){
		this.accountNumber = accountNumber;
		this.summa = Summa;
	}
	
	public void setSumma(int summa) {
		this.summa = summa;
	}
	public long getSumma() {
		return summa;
	}

	public void incrSumma(int summa) {
		this.summa += summa;
	}
	
	public void decrSumma(int summa) {
		if (summa <= this.summa){
			this.summa -= summa;
		}
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
}
