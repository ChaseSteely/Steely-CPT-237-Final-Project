//FILE: Bank.java
//PROG: Marshall Chase Steely
//PURP: TO create a Bank instance
//Opening accounts by type.

package edu.triddenttech.cpt237.program1;

import java.util.ArrayList;

public class Bank {

	private ArrayList<SavingsAccount> savings = new ArrayList<SavingsAccount>();
	private ArrayList<CheckingAccount> checking = new ArrayList<CheckingAccount>();
	static private Bank instance = new Bank();

	private Bank() {
	}// Empty Constructor

	public static Bank getInstance() {
		return instance;
	}// END getInstance

	public SavingsAccount getSavingsAccountByNum(String accntNum) {
		for (SavingsAccount save : savings) {
			String acctNum = save.getAccountNumber();
			if (acctNum.equals(accntNum)) {
				return save;
			}
		}
		return null;
	}// END getSavings

	public CheckingAccount getCheckingAccountByNum(String accntNum) {
		for (CheckingAccount check : checking) {
			String acctNum = check.getAccountNumber();
			if (acctNum.equals(accntNum)) {
				return check;
			}
		}
		return null;
	}

	public boolean openSavingsAccount(String accntNum, double initialBal, int rate) {
		if (accntNum == null)
			return false;
		SavingsAccount save = new SavingsAccount(accntNum, initialBal);
		save.setMAX_WITHDRAWALS(rate);
		savings.add(save);
		return true;
	}

	public boolean openCheckingAccount(String accntNum, double initialBal, double minBalance) {
		if (accntNum == null)
			return false;
		CheckingAccount check = new CheckingAccount(accntNum, initialBal);
		check.setMIN_BALANCE(minBalance);
		checking.add(check);
		return true;
	}

	public ArrayList<SavingsAccount> getSavings() {
		return savings;
	}

	public ArrayList<CheckingAccount> getChecking() {
		return checking;
	}

	public void transfer(String fromAcc, String toAcc, double amount) {
		Account from = getCheckingAccountByNum(fromAcc);
		if (from == null)// if not found in Checking it will search Savings
			from = getSavingsAccountByNum(fromAcc);

		Account to = getCheckingAccountByNum(toAcc);
		if (to == null)
			to = getSavingsAccountByNum(toAcc);

		if (from.withdraw(amount)) {
			to.deposit(amount);
		}
	}// END transfer method

}// END Bank class
