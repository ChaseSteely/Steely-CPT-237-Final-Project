//FILE: MainCLass.java
//PROG: Marshall Chase Steely
//PURP: To read ArrayLists and print out info
//and let the user interact and input info and print out report.
package edu.triddenttech.cpt237.program1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainClass {

	public static void main(String args[]) throws FileNotFoundException {
		String line;
		String accntNum;
		String type;
		Scanner input;
		double initialBalance;
		// double minBalance;
		double amount;
		String[] fields;
		input = new Scanner(new File("C:/Users/Marshall/workspace/Steely CPT 237 Program 1 Project/Transactions.csv"));
		Bank bank = Bank.getInstance();
		while (input.hasNext()) {
			line = input.nextLine();
			fields = line.split(",");
			switch (fields[0].charAt(0)) {
			case 'O': {
				accntNum = (fields[1]);
				type = (fields[2]);
				initialBalance = Double.parseDouble(fields[3]);
				// minBalance = Double.parseDouble(fields[4]); There are only 4
				// fields in the .csv file. This asks for 5 fields???? And there
				// is not a rate referenced anywhere. What is rate????
				if (type == "S") {
					bank.openSavingsAccount(accntNum, initialBalance, 0);
				}

				else {
					bank.openCheckingAccount(accntNum, initialBalance, 0);// set
																			// minBalance
																			// manually
																			// which
																			// throws
																			// off
																			// output.
																			// Instructions
																			// show
																			// 5
																			// fields
																			// in
																			// the
																			// csv
																			// file.
				}

			}
				break;

			case 'D': {
				accntNum = (fields[1]);
				type = (fields[2]);
				amount = Double.parseDouble(fields[3]);
				if (type == "S") {
					SavingsAccount save = bank.getSavingsAccountByNum(accntNum);
					save.deposit(amount);
				}

				else {
					CheckingAccount check = bank.getCheckingAccountByNum(accntNum);
					check.deposit(amount);
				}
			}
				break;

			case 'W': {
				accntNum = (fields[1]);
				type = (fields[2]);
				amount = Double.parseDouble(fields[3]);
				if (type == "S") {
					SavingsAccount save = bank.getSavingsAccountByNum(accntNum);
					save.withdraw(amount);
				}

				else {
					CheckingAccount check = bank.getCheckingAccountByNum(accntNum);
					check.withdraw(amount);
				}
			}
				break;

			default: {
				System.out.println("ERROR");
			}
			}// END switch

		} // END While
		input.close();

		for (SavingsAccount save : bank.getSavings())
			System.out.println(save.toString());

		for (CheckingAccount check : bank.getChecking())
			System.out.println(check.toString());

		Scanner userInput = new Scanner(System.in);
		String aNum;

		char quit = 'n';

		while (quit != 'y') {
			System.out.println("Please enter Account Number?");
			aNum = userInput.nextLine();
			Account search = bank.getCheckingAccountByNum(aNum);
			if (search == null)// if not found in Checking it will search
								// Savings
				search = bank.getSavingsAccountByNum(aNum);

			if (search == null)
				System.out.println("Account Number Not Found.");

			System.out.println("(D)eposit\n(W)ithdrawal\n(S)how Balance");

			String action;
			System.out.println("Please enter 'D' 'W' or 'S'");
			action = userInput.nextLine();

			switch (action) {
			case "d":
			case "D": {
				double amt;
				System.out.println("Enter Deposit amount");
				amt = userInput.nextDouble();
				userInput.nextLine();
				search.deposit(amt);
				System.out.printf("Balance: %.2f%n", search.getBalance());
			}
				break;
			case "w":
			case "W": {
				double amt;
				System.out.println("Enter Withdrawal amount");
				amt = userInput.nextDouble();
				userInput.nextLine();
				search.withdraw(amt);
				System.out.printf("Balance: %.2f%n", search.getBalance());
			}
				break;

			case "s":
			case "S": {
				System.out.printf("Balance: %.2f%n", search.getBalance());
			}
				break;

			default: {
				System.out.println("Please enter 'D' 'W' or 'S'");
			}

			}// END switch

			System.out.println("Do you want to quit? [y/n]");
			quit = userInput.nextLine().charAt(0);

		} // END while

		for (SavingsAccount save : bank.getSavings()) {
			System.out.printf("\nSavings Account: %s\nEnding Balance: %.2f", save.getAccountNumber(),
					save.getBalance());
			System.out.printf("\n%-5s%10s%10s\n", "Transaction Type", "Amount", "Balance");
			for (Transaction t : save.getTransactionList()) {
				System.out.printf("%-15s%10.2f%10.2f\n", t.getType(), t.getAmount(), t.getBalance());
			}
		}

		for (CheckingAccount check : bank.getChecking()) {
			System.out.printf("\nChecking Account: %s\nEnding Balance: %.2f", check.getAccountNumber(),
					check.getBalance());
			System.out.printf("\n%-5s%10s%10s\n", "Transaction Type", "Amount", "Balance");
			for (Transaction t : check.getTransactionList()) {
				System.out.printf("%-15s%10.2f%10.2f\n", t.getType(), t.getAmount(), t.getBalance());
			}
		}
		userInput.close();
	}// END of main method

}// END of MainClass
