//FILE: CheckingAccount.java
//PROG: Marshall Chase Steely
//PURP: To create an inherited class of the Account
//for checking

package edu.triddenttech.cpt237.program1;

public class CheckingAccount extends Account {

	private double MIN_BALANCE;

	public CheckingAccount(String accNum, double iBal) {
		super(accNum, iBal);
		Transaction o = new Transaction("Open", 0, this.balance);
		transaction.add(o);
	}// END CheckingAccount constructor

	public boolean withdraw(double amount) {
		double tempBal = this.balance;
		tempBal = tempBal - amount;
		if (tempBal < MIN_BALANCE) {
			if (super.withdraw(.13))
			{
				Transaction s = new Transaction("Service Fee", .13, this.balance);
				transaction.add(s);
				if(super.withdraw(amount))
				{
					Transaction w = new Transaction("Withdrawal", amount, this.balance);
					transaction.add(w);
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}

		else {
			Transaction w = new Transaction("Withdrawal", amount, this.balance);
			transaction.add(w);
			return super.withdraw(amount);
		}
	}// END of withdraw method

	public double getMIN_BALANCE() {
		return MIN_BALANCE;
	}// END getMin_Balance method

	public void setMIN_BALANCE(double mIN_BALANCE) {
		MIN_BALANCE = mIN_BALANCE;
	}

}// END of CheckingAccount class
