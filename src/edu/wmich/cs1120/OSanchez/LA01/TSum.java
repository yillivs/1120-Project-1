package edu.wmich.cs1120.OSanchez.LA01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * @author oliversanchez
 * CS1120
 * 1-27-2016
 * Program executes an input for users to interact with their bank accounts.
 * Users input their account number and then are asked how much money will be transfered.
 * After the users are finished they may press "0" to exit.
 *
 */
public class TSum {
/**
 * Reads data off text file, then performs a string split. After that organizes
 * the data into an array of accounts. The switch statement controls what the user does each time
 * he/she presses an option in the console. After that the proper decision is taken and
 * it is looped over again until
 * the user inputs "0".
 * @param args
 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Text Document named accountDatabase.txt
		Scanner userInput = new Scanner(System.in);
		String fileName = "accountDatabase.txt";
		String line = null;//empty line count for BufferedReader type variable
		boolean flag = true;//boolean to be used when looping back through the options once completed
		
		try {
			FileReader textReader = new FileReader(fileName);
			BufferedReader buffReader = new BufferedReader(textReader);//improves efficiency of file reader 
			Account[] cData = new Account[10];
			int index = 0;//counter for inside the while loop
			while((line = buffReader.readLine())!= null){
				String[] splitLine = line.split(" ");
				int accountNum = Integer.parseInt(splitLine[0]);
				double accountFund = Double.parseDouble(splitLine[1]);
				cData[index] = new Account(accountNum,accountFund);
				index++;
			}
			textReader.close();
			buffReader.close();
			while(flag){
			printMenu();
			int choiceList = getChoice(userInput);
			switch(choiceList){
			case 1:
				int depoAcc = getAccountNumber(userInput);
				for(int i = 0; i<cData.length;i++){
					if(depoAcc == cData[i].GetAccountNumber()){
						double depoAmount = getAmount(userInput);
						cData[i].deposit(depoAmount);
					}
				}
			break;
			
			case 2:
				int withAcc = getAccountNumber(userInput);
				for(int i = 0; i<cData.length;i++){
					if(withAcc ==cData[i].GetAccountNumber()){
						double withAmount = getAmount(userInput);
						cData[i].withdraw(withAmount);
					}
				}
				break;
				
			case 3:
				int[] accNums = getTransferAccountNumbers(userInput);
				double transAmount = getAmount(userInput);
				for(int i = 0; i<cData.length;i++){
					if(accNums[0] == cData[i].GetAccountNumber()){
						cData[i].withdraw(transAmount);
					}
				}
				for(int i = 0; i<cData.length; i++){
					if(accNums[1] == cData[i].GetAccountNumber()){
						cData[i].deposit(transAmount);
					}
				}
				break;
				
			case 0:
				System.exit(0);
				break;
				
			default:
				System.out.println("Not a correct option.");
				break;
			}
			updateAccountDatabase(cData);
			}
		}
		catch(FileNotFoundException e){
			System.err.println("No file of name accountDatabase.txt in folder");
		}
	}
	/**
	 * Prints option menu to the console, displaying options for input to all transactions
	 */
	public static void printMenu(){
		System.out.println("1. Deposit \n2. Withdraw \n3. Transfer \n0. Exit");
	}
	/**
	 * Allows user to input choice deciding which type of transaction he/she wants to perform
	 * @param keyboard allows user to input choice of bank transactions
	 * @return int with choices from 0-3, all linked to options for transactions
	 */
	public static int getChoice(Scanner keyboard){
		int choice = keyboard.nextInt();
		return choice;
	}
	/**
	 * Get's the account number from the user.
	 * @param keyboard allows user to input account number.
	 * @return the bank number the user enters.
	 */
	public static int getAccountNumber(Scanner keyboard){
		System.out.println("Please input the account number ");
		int accountNum = keyboard.nextInt();
		return accountNum;
		
	}
	/**
	 * Gets the transfer account numbers from the user.
	 * @param keyboard allows user to input account numbers.
	 * @return the bank numbers that you will return 
	 */
	public static int[] getTransferAccountNumbers(Scanner keyboard){
		System.out.println("Please input the transfer account to transfer from ");
		int from = keyboard.nextInt(); //user input for first transfer account
		System.out.println("Please input the tranfer account to transfer to ");
		int to = keyboard.nextInt();
		int [] accountNumbs = new int[2];
		for(int i = 0; i<accountNumbs.length; i++){
			if(i == 0){
				accountNumbs[i] = from;
			}
			if(i == 1){
				accountNumbs[i] = to;
			}
		}
		return accountNumbs;
		
	}
	/**
	 * Gets the amount that will be added or deducted from the total amount from the user
	 * @param keyboard allows user to input amount to be added or deducted
	 * @return amount that will change
	 */
	public static double getAmount(Scanner keyboard){
		System.out.println("Please input the amount");
		double amountChange = keyboard.nextDouble();
		return amountChange;
	}
	/**
	 * Updates the accountDatabase.txt file so that the proper amounts after transaction are included
	 * @param account array of accounts that will contain the 
	 * @throws IOException close function on txtWriter which will handle the method if something goes wrong 
	 */
	public static void updateAccountDatabase(Account[] account) throws IOException {
	File repoFile = new File("accountDatabase.txt");
	PrintWriter txtWriter = new PrintWriter(repoFile);
	for(int i = 0; i<account.length; i++){
		txtWriter.println(account[i].GetAccountNumber()+" "+account[i].getAccountBalance());
	}
	txtWriter.close();
	}
}
