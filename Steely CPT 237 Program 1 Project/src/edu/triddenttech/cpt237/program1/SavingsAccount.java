//FILE: SavingAccount.java
//PROG: Marshall Chase Steely
//PURP: To create an inherited class of the Account
//for savings.

package edu.triddenttech.cpt237.program1;

public class SavingsAccount extends Account {

	private int MAX_WITHDRAWALS;
	private int numWithdrawals;
	private final double serviceFee = .23;

	public SavingsAccount(String accNum, double iBal) {
		super(accNum, iBal);
		Transaction o = new Transaction("Open", 0, this.balance);
		transaction.add(o);

	}// END SavingsAccount constructor

	public boolean withdraw(double amount) {
		if (numWithdrawals > MAX_WITHDRAWALS) {
			numWithdrawals++;
			super.withdraw(serviceFee);// service fee transaction
			Transaction s = new Transaction("Service Fee", serviceFee, this.balance);
			transaction.add(s);
			Transaction w = new Transaction("Withdrawal", amount, this.balance);
			transaction.add(w);
			return super.withdraw(amount);
		}

		else {
			numWithdrawals++;
			Transaction w = new Transaction("Withdrawal", amount, this.balance);
			transaction.add(w);
			return super.withdraw(amount);
		}
	}// END of withdraw method

	public void resetWithdrawalCount() {
		numWithdrawals = 0;
	}// END reset method

	public int getNumWithdrawls() {
		return numWithdrawals;
	}// END getNumWithdrawals method

	public int getMAX_WITHDRAWLS() {
		return MAX_WITHDRAWALS;
	}// END get MAX method

	public void setMAX_WITHDRAWALS(int mAX_WITHDRAWALS) {
		MAX_WITHDRAWALS = mAX_WITHDRAWALS;
	}

	public double getServiceFee() {
		return serviceFee;
	}// END get fee method

}// END SavingsAccount class
