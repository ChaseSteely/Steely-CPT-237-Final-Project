//FILE: Transaction.java
//PROG: Marshall Chase Steely
//PURP: To create a transaction to display later in an ArrayList.

package edu.triddenttech.cpt237.program1;

public class Transaction {

	protected String type;
	protected double amount;
	protected double balance;

	public Transaction(String t, double amt, double bal) {
		type = t;
		amount = amt;
		balance = bal;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction Type: " + this.type + " Amount: " + this.amount + " Current Balance: "
				+ String.format("%.2f", this.balance);
	}

}
