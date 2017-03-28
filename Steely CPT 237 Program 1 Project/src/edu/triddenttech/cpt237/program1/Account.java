//FILE: Account.java
//PROG: Marshall Chase Steely
//PURP: To create a parent class of a bank Account
//
package edu.triddenttech.cpt237.program1;

import java.util.ArrayList;

//Parent Class
public class Account {// BEGIN Account Class
	protected double balance;
	protected String accountNumber;
	protected ArrayList<Transaction> transaction = new ArrayList<Transaction>();

	public Account(String accNum, double minBal) {
		accountNumber = accNum;
		balance = minBal;
	}// END Account Constructor

	public double getBalance() {
		return balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public boolean deposit(double amount) {
		this.balance = this.balance + amount;
		Transaction t = new Transaction("Deposit", amount, this.balance);
		transaction.add(t);
		return true;
	}// END of deposit method

	public boolean withdraw(double amount) {
		double tempBal = this.balance;
		tempBal = tempBal - amount;
		if (tempBal < 0) {
			return false;
		}

		else {
			this.balance = this.balance - amount;
			return true;
		}
	}// END of withdraw method

	@Override
	public String toString() {
		return "Account Number: " + this.accountNumber + " Ending Balance: " + String.format("%.2f", this.balance);
	}

	public ArrayList<Transaction> getTransactionList() {
		return new ArrayList<Transaction>(transaction);// returns copy
	}

}// END Account Class
