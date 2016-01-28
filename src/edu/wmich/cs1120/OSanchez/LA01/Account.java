package edu.wmich.cs1120.OSanchez.LA01;
/**
 * 
 * @author oliversanchez
 *
 */
public class Account {
private int accountNumber;
private double accountBalance;
/**
 * 
 * @param accountNum constructor for account, which contains the account identification number
 * @param accountBal constructor for account, which contains the account balance
 */
public Account(int accountNum, double accountBal){
	accountNumber = accountNum;
	accountBalance = accountBal;
}
/**
 * getter for accountNumber of the user
 * @return accountNumber - user account number
 */
public int GetAccountNumber(){
	return accountNumber;
}
/**
 * getter for accountBalance attribute.
 * @return accountBalance - user account balance
 */
public double getAccountBalance(){
	return accountBalance;
}
/**
 * Calculates the accountBalance after depositing funds.
 * @param depositAmount amount deposited in the account
 */
public void deposit(double depositAmount){
	accountBalance += depositAmount;
}
/**
 * Calculates the accountBalance after withdrawing funds.
 * @param withdrawAmount amount withdrawn from account
 */
public void withdraw(double withdrawAmount){
	accountBalance -= withdrawAmount;
}
}
