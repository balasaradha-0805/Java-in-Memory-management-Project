package package1;

public class BankAccount {
private double balance = 1500; //prevents direct access to the bank account -> encapuslating the parameter
public double getbalance() {
	return balance;
}

public void add_amount(double amt) {
	balance+=amt;
}
public static void main(String args[]) {
	BankAccount account = new BankAccount();

	account.add_amount(500);
	System.out.println(account.getbalance());
}
}