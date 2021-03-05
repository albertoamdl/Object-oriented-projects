import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Account class is a parent of all other classes such as Checking, Savings, and Credit.
 * Methods are included in this class as well as getters and setters that will allow for the function of entire program.
 *
 * @author Federico Marin
 * @since September 29,2020
 */

public abstract class Account {
    //attributes
    private int accountNum;
    private double initialBalance;
    private int creditMax;

    /**
     * Default constructor
     */
    public Account(){

    }

    /**
     * Constructor
     * @param accountNumIn will work with any child class such as Checking, Savings, Credit account numbers
     * @param initialBalanceIn will work with any child class initial balances
     */

    public Account(int accountNumIn, double initialBalanceIn){
        this.accountNum = accountNumIn;
        this.initialBalance = initialBalanceIn;

    }

    /**
     * getter Method to obtain account number
     * @return account number
     */

    public int getAccountNum(){
        return this.accountNum;
    }

    /**
     * setter method for account number
     * @param accountNumIn account number
     */
    public void setAccountNum(int accountNumIn) {
        this.accountNum = accountNumIn;
    }

    /**
     * getter method for balance
     * @return balance
     */
    public double getInitialBalance() {
        return this.initialBalance;
    }

    /**
     * Setter method for balance
     * @param initialBalanceIn balance
     *
     */
    public void setInitialBalance(double initialBalanceIn) {
        this.initialBalance = initialBalanceIn;
    }



    /**
     * Method to acquire balance of designated account.
     * @param accountArrList receives designated account array list.
     * @param i index in array
     *
     */

    public double acquireBalance(ArrayList<Account> accountArrList, int i){
        return accountArrList.get(i).getInitialBalance();
    }

    /**
     * Searching index of the ArrayList of designated account number
     * @param accountArrList receives designated account array list
     * @param accountNum account number received
     * @return array list index
     */
    public int accountSearch(ArrayList<Account> accountArrList, int accountNum){
        int i;

        for(i = 0; i <accountArrList.size(); i++){
            if (accountNum == accountArrList.get(i).getAccountNum()){
                break;
            }
        }
        return i;
    }

    public void transactionAction(ArrayList<Client> clientArrayList, ArrayList<Account> fromAccountList, ArrayList< Account> receivingAccountList, String fileName){
        int fromFirstNameIndex, fromLastNameIndex, fromWhereIndex, actionIndex, toFirstNameIndex, toLastNameIndex, toWhereIndex, actionAmountIndex;
        fromFirstNameIndex =fromLastNameIndex = fromWhereIndex = actionIndex = toFirstNameIndex = toLastNameIndex = toWhereIndex = actionAmountIndex = 0;

        File transactionActions = new File("Transaction Actions(3).csv");
        Scanner scnr = null;

        try{
            scnr = new Scanner(transactionActions);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }

        String header = scnr.nextLine();
        String[] headerArray = header.split(",");

        int i;
        for(i = 0; i < headerArray.length; i++){
            if (headerArray[i].equals("From First Name")) fromFirstNameIndex = i;
            if (headerArray[i].equals("From Last Name")) fromLastNameIndex = i;
            if (headerArray[i].equals("From Where")) fromWhereIndex = i;
            if (headerArray[i].equals("Action")) actionIndex = i;
            if (headerArray[i].equals("To First Name")) toFirstNameIndex = i;
            if (headerArray[i].equals("To Last Name")) toLastNameIndex = i;
            if (headerArray[i].equals("To Where")) toWhereIndex = i;
            if (headerArray[i].equals("Action Amount")) actionAmountIndex = i;
        }

        while (scnr.hasNextLine()){
            String nextLine = scnr.nextLine();
            String[] newLine = nextLine.split(",");

            String fromFirstName = null;
            String fromLastName = null;
            String fromWhere = null;
            String toFirstName = null;
            String toLastName = null;
            String toWhere = null;
            double actionAmount = 0;

            String action = newLine[actionIndex];
            if (!action.equals("deposits")) {
                fromFirstName = newLine[fromFirstNameIndex];
                fromLastName = newLine[fromLastNameIndex];
                fromWhere = newLine[fromWhereIndex];
            }
            if (!action.equals("inquires")) {
                if (!action.equals("withdraws")) {
                    toFirstName = newLine[toFirstNameIndex];
                    toLastName = newLine[toLastNameIndex];
                    toWhere = newLine[toWhereIndex];
                }
                String actionAmountString = newLine[actionAmountIndex];
                actionAmount = Double.parseDouble(actionAmountString);
            }

            Client fromUser = new Client();
            Client toUser = new Client();
            Checking checkingObject = new Checking();

            int fromUserIndex = 0;
            int toUserIndex = 0;

            if(fromFirstName != null){
                fromUserIndex = fromUser.accountSearch(clientArrayList, fromFirstName, fromLastName);
            }
            if (toFirstName != null) {
                toUserIndex = toUser.accountSearch(clientArrayList, toFirstName, toLastName);
            }
            if(action.equals("pays")){
                wireTransfer(clientArrayList,fromAccountList,receivingAccountList,fromUserIndex,toUserIndex);
            }
            int fromWhereInt;
            if (action.equals("transfers")){
                if (fromWhere.equals("Checking")) {
                     fromWhereInt = 1;
                }
                else {
                     fromWhereInt = 2;
                }
                transfer(clientArrayList,fromAccountList,receivingAccountList,fromUserIndex, fromWhereInt, actionAmount);
            }
            if (action.equals("inquires")) {
                checkingObject.acquireBalance(fromAccountList, fromUserIndex);
            }
            if (action.equals("withdraws")) {
                withdraw(clientArrayList, fromAccountList, fromUserIndex, fromWhere);
            }
            if (action.equals("deposits")) {
                deposit(clientArrayList, receivingAccountList, toUserIndex, actionAmount, toWhere);
            }

        }
    }//end of transactionAction method

    public void transactionLog(String transactionDone, String senderName, String receiverName, double amount, String fromAccount, String receivingAccount){
        try{
            FileWriter transactionLogWrite = new FileWriter("transactionLog.txt", true);
            if(transactionDone.equals("deposit")){
                transactionLogWrite.write(senderName + "deposited $" + amount + "into" + fromAccount + "account. \n");
            }

            if(transactionDone.equals("withdraw")){
                transactionLogWrite.write(senderName + "withdrew $" + amount + "from" + fromAccount + "account. \n");
            }

            if(transactionDone.equals("transfer")){
                transactionLogWrite.write(senderName + "Transferred $" + amount + "from" + fromAccount + "account to" + receivingAccount + "\n");
            }

            if(transactionDone.equals("wireTransfer")){
                transactionLogWrite.write(senderName + "wire transferred $" + amount + "to" + receivingAccount + "account. \n");
            }
            transactionLogWrite.close();
        }
        catch (IOException e){
            System.out.println("error");
        }
    }

    /**
     * Deposit method is used to add funds into designated account
     * @param clientArrayList clients array list
     * @param accountArrList account array list
     * @param i index from array lists
     * @param depositTotal amount to deposit
     * @param account choice of account type
     * @return access granted or not
     */

    public String deposit(ArrayList<Client> clientArrayList, ArrayList<Account> accountArrList, int i, double depositTotal, String account){

        Scanner userInput = new Scanner(System.in);

        if(account.equals("credit") && (depositTotal + accountArrList.get(i).getInitialBalance() > 0)){
            return "Transaction not valid, current balance $" + accountArrList.get(i).getInitialBalance() + "\n" + "Main Menu";
        }

        if(depositTotal<0) {
            return "Transaction not valid, non-acceptable amount. Returning to main menu";
        }

        double afterBalance = accountArrList.get(i).getInitialBalance() + depositTotal;
        accountArrList.get(i).setInitialBalance(afterBalance);

        String clientFullName = clientArrayList.get(i).getFirstName() + " " + clientArrayList.get(i).getLastName();
        transactionLog("deposit", clientFullName, "", depositTotal, account, "");

        return "Deposit of $" + depositTotal + " completed.";
    }

    /**
     * Withdraw Method is used to remove money from designated account.
     * @param clientArrayList client array list
     * @param accountArrList account array list
     * @param i index from array list
     * @param account type of account
     */

    public void withdraw(ArrayList<Client> clientArrayList, ArrayList<Account> accountArrList, int i, String account){
        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter amount of money to withdraw: ");
        double withdrawTotal = userInput.nextDouble();

        if(withdrawTotal < 0){
            System.out.println("Amount was not valid.");
            return;
        }

        if (accountArrList.get(i).getInitialBalance() - withdrawTotal < 0){
            System.out.println("Insufficient funds.");
            return;
        }

        double afterAccountBalance = accountArrList.get(i).getInitialBalance() - withdrawTotal;
        accountArrList.get(i).setInitialBalance(afterAccountBalance);

        System.out.println("transaction successfully withdrew a total of $" + withdrawTotal);

        String clientFullName = clientArrayList.get(i).getFirstName() + " " + clientArrayList.get(i).getLastName();
        transactionLog("withdraw", clientFullName, "", withdrawTotal, account,"");
    }

    /**
     * Transfer method used to transfer money between accounts
     * @param clientArrayList array list for client
     * @param fromAccountList sender account
     * @param receivingAccountList receiving account
     * @param i index from array list
     * @param transferInput receives input
     * @param transferAmount receives amount
     * @return access granted or denied
     */

    public String transfer(ArrayList<Client> clientArrayList, ArrayList<Account> fromAccountList, ArrayList<Account> receivingAccountList, int i, int transferInput, double transferAmount){

        Scanner userInput = new Scanner(System.in);
        String fromAccount;
        String receivingAccount;

        if(transferInput == 1){
            System.out.println("Checking account...");
            System.out.println("Transfer to savings account");
            fromAccount = "checking";
            receivingAccount = "savings";
        }

        else{
            System.out.println("Savings account...");
            System.out.println("Transfer to Checking account");
            fromAccount = "Savings";
            receivingAccount = "Checking";
        }

        if(transferAmount < 0){
            return "transaction invalid";
        }

        if(fromAccountList.get(i).getInitialBalance() - transferAmount < 0){
            return "transaction invalid";
        }

        fromAccountList.get(i).setInitialBalance(fromAccountList.get(i).getInitialBalance() - transferAmount);
        receivingAccountList.get(i).setInitialBalance(receivingAccountList.get(i).getInitialBalance() + transferAmount);

        String clientFullName = clientArrayList.get(i).getFirstName() + " " + clientArrayList.get(i).getLastName();
        transactionLog("transfer", clientFullName, "", transferAmount, fromAccount, receivingAccount);

        return "Transaction successful, Transferred a total of $" + transferAmount;
    }

    /**
     * method to transfer money between users.
     *
     * @param clientArrayList client array list
     * @param fromAccountList sender account
     * @param receivingAccountList receiving account
     * @param i index from sender array list
     * @param j index from receiving array list
     */

    public void wireTransfer(ArrayList<Client> clientArrayList, ArrayList<Account> fromAccountList, ArrayList< Account> receivingAccountList, int i, int j){

        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter amount to wire transfer");
        double wireAmount = userInput.nextDouble();

        if(wireAmount < 0){
            System.out.println("Transaction invalid");
            return;
        }

        if(fromAccountList.get(i).getInitialBalance() - wireAmount< 0){
            System.out.println("Transaction invalid, insufficient funds");
            return;
        }

        fromAccountList.get(i).setInitialBalance(fromAccountList.get(i).getInitialBalance() - wireAmount);

        receivingAccountList.get(j).setInitialBalance(receivingAccountList.get(j).getInitialBalance() + wireAmount);

        System.out.println(" Successful Payment of $" + wireAmount + " to " + clientArrayList.get(j).getFirstName());

        String clientFullName = clientArrayList.get(i).getFirstName() + " " + clientArrayList.get(i).getLastName();
        String receivingFullName = clientArrayList.get(j).getFirstName() + " " + clientArrayList.get(j).getLastName();
        transactionLog("wireTransfer", clientFullName, receivingFullName, wireAmount, "", "");
    }



    /**
     * Method to create new sheet with updated balances after use
     * @param clientArrayList client array list
     * @param checkingList checking account array list
     * @param savingsList savings account array List
     * @param creditList credit array List
     */

    public void updatedSheet(ArrayList<Client> clientArrayList, ArrayList<Account> checkingList, ArrayList<Account> savingsList, ArrayList<Account> creditList){
        try(PrintWriter writer = new PrintWriter("updatedSheet.csv")){
            writer.println("First Name, Last Name, Date of Birth, Identification Number, Address, Phone Number, Checking Account Number, Savings Account Number, Credit Account Number, Checking Initial Balance, Savings Initial Balance, Credit Initial Balance");
            for (int i = 0; i < clientArrayList.size(); i++){
                writer.print(clientArrayList.get(i).getFirstName() + ",");
                writer.print(clientArrayList.get(i).getLastName() + ",");
                writer.print(clientArrayList.get(i).getDateOfBirth() + ",");
                writer.print(clientArrayList.get(i).getIdentificationNumber() + ",");
                writer.print(clientArrayList.get(i).getAddress() + ",");
                writer.print(clientArrayList.get(i).getPhoneNumber() + ",");
                writer.print(checkingList.get(i).getAccountNum() + ",");
                writer.print(savingsList.get(i).getAccountNum() + ",");
                writer.print(creditList.get(i).getAccountNum() + ",");
                writer.print(checkingList.get(i).getInitialBalance() + ",");
                writer.print(savingsList.get(i).getInitialBalance() + ",");
                writer.print(creditList.get(i).getInitialBalance());
                writer.print("\n");
            }
        }

        catch (FileNotFoundException e){
            System.out.println("error");
        }

    }




}//end of Account class
