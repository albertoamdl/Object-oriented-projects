import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main method for program
 * @author Fede & Alberto Miranda
 * @since September 29,2020
 */


public class Bank {


    public static void main(String[] args) {
        ArrayList<Client> clientArrayList = new ArrayList<>();
        ArrayList<Account> checkingArrayList = new ArrayList<>();
        ArrayList<Account> savingsArrayList = new ArrayList<>();
        ArrayList<Account> creditArrayList = new ArrayList<>();
        Scanner scanner = null;
        Scanner userInput = new Scanner(System.in);
        int userID;
        int bankchoice = 5;
        Client client;
        Account clientChecking = null;
        Account clientSavings;
        Account clientCredit;
        Checking managerChecking = null;
        Savings managerSavings = null;
        Credit manageCredit = null;
        Client managerClientObject = new Client();
        Checking managerCheckingObject = new Checking();
        Savings managerSavingsObject = new Savings();
        Credit managerCreditObject = new Credit();
        boolean terminate = false;

        File filepath = new File("CS 3331 - Bank Users 4.csv");

        try {
            scanner = new Scanner(filepath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }


        String thisScans = scanner.nextLine();

        String[] newLine = thisScans.split(",");
        int firstName = 0, lastName = 0, dateOfBirth = 0, identificationNumber = 0, phoneNumber = 0, address = 0, checkingAccountNumber = 0, savingsAccountNumber = 0, creditAccountNumber = 0, checkingInitialBalance = 0, savingsInitialBalance = 0, creditInitialBalance = 0, creditMax = 0, password = 0;
        for (int i = 0; i < newLine.length; i++) {
            if (newLine[i].equals("First Name")) firstName = i;
            else if (newLine[i].equals("Last Name")) lastName = i;
            else if (newLine[i].equals("Date of Birth")) dateOfBirth = i;
            else if (newLine[i].equals("Identification Number")) identificationNumber = i;
            else if (newLine[i].equals("Phone Number")) phoneNumber = i;
            else if (newLine[i].equals("Address")) address = i;
            else if (newLine[i].equals("Password")) password = i;
            else if (newLine[i].equals("Checking Account Number")) checkingAccountNumber = i;
            else if (newLine[i].equals("Savings Account Number")) savingsAccountNumber = i;
            else if (newLine[i].equals("Credit Account Number")) creditAccountNumber = i;
            else if (newLine[i].equals("Checking Starting Balance")) checkingInitialBalance = i;
            else if (newLine[i].equals("Savings Starting Balance")) savingsInitialBalance = i;
            else if (newLine[i].equals("Credit Starting Balance")) creditInitialBalance = i;
            else if (newLine[i].equals("Credit Max")) creditMax = i;


        }


        while (scanner.hasNextLine()) {
            thisScans = scanner.nextLine();
            newLine = thisScans.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");


            Client clientInfo = new Client(newLine[firstName], newLine[lastName], newLine[dateOfBirth], Integer.parseInt(newLine[identificationNumber]), newLine[address], newLine[phoneNumber], newLine[password]);
            Checking checkingInfo = new Checking(Integer.parseInt(newLine[identificationNumber]), Integer.parseInt(newLine[checkingAccountNumber]), Double.parseDouble(newLine[checkingInitialBalance]));
            Savings savingsInfo = new Savings(Integer.parseInt(newLine[identificationNumber]), Integer.parseInt(newLine[savingsAccountNumber]), Double.parseDouble(newLine[savingsInitialBalance]));
            Credit creditInfo = new Credit(Integer.parseInt(newLine[identificationNumber]), Integer.parseInt(newLine[creditAccountNumber]), Double.parseDouble(newLine[creditInitialBalance]), Double.parseDouble(newLine[creditMax]));

            clientArrayList.add(clientInfo);
            checkingArrayList.add(checkingInfo);
            savingsArrayList.add(savingsInfo);
            creditArrayList.add(creditInfo);


        }// end of while loop
        boolean ready = true;
        int i = 0;
        client = clientArrayList.get(i);

        do{
            client.welcomeMessage();
            try{

                bankchoice = userInput.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input, please try again");
                System.out.println(" ");
//                userInput.nextLine();
            }
            userInput.nextLine();
        }
        while(bankchoice > 4);


                //ready = false;

        if(bankchoice == 1){


            System.out.println("Enter account Identification Number: ");
            userID = userInput.nextInt();
            System.out.println("Please enter the password for the account");
            String userPass = userInput.next();






            i = 0;

            while(true){

                if(userID == clientArrayList.get(i).getIdentificationNumber() && userPass.equals(clientArrayList.get(i).getPassword())){
//                    System.out.println(clientArrayList.get(i).getPassword());
                    client = clientArrayList.get(i);
                    clientChecking = checkingArrayList.get(i);
                    clientSavings = savingsArrayList.get(i);
                    clientCredit = creditArrayList.get(i);
                    break;
                    }




                i++;
                if(i > clientArrayList.size() - 1){
                    i = 0;
                    System.out.println("ID number invalid or password incorrect, Try again");
                    userID = userInput.nextInt();
                }

            }//end of while

            System.out.println("\n");

           System.out.println("Welcome to minerBank " + client.getFirstName() + " " + client.getLastName());
            System.out.println("Please choose a menu option");

            try{
                while(!terminate){

                    int menuInput = 10;
                    do{
                        client.menuOptions();
                        try{

                            menuInput = userInput.nextInt();
                        }
                        catch(InputMismatchException e){
                            System.out.println("Invalid input, please try again");
                            System.out.println(" ");
//                userInput.nextLine();
                        }
                        userInput.nextLine();
                    }
                    while(menuInput > 7);


                    if(menuInput == 1){
                        System.out.println("Checking balance of: $" + clientChecking.getInitialBalance());
                        System.out.println("Savings balance of:  $" + clientSavings.getInitialBalance());
                        System.out.println("Credit of:   $" + clientCredit.getInitialBalance());
                    }//end of option 1

                    if (menuInput == 2){
                        System.out.println("Please select account.");
                        System.out.println("1. Checking");
                        System.out.println("2. Savings");
                        System.out.println("3. Credit");
                        int accountDepositChoice = userInput.nextInt();
                        do{
                            client.menuOptions();
                            try{

                                menuInput = userInput.nextInt();
                            }
                            catch(InputMismatchException e){
                                System.out.println("Invalid input, please try again");
                                System.out.println(" ");
                            }
                            userInput.nextLine();
                        }
                        while(menuInput > 7);




                        if (accountDepositChoice == 1){
                            System.out.print("Enter amount to deposit: ");
                            double depositAmount = userInput.nextDouble();
                            System.out.println(clientChecking.deposit(clientArrayList, checkingArrayList, i, depositAmount, "checking"));
                            clientChecking.updatedSheet(clientArrayList ,checkingArrayList,savingsArrayList, creditArrayList);

                        }

                        if(accountDepositChoice == 2){
                            System.out.print("Please enter amount: ");
                            double depositAmount = userInput.nextDouble();
                            System.out.println(clientSavings.deposit(clientArrayList, savingsArrayList, i, depositAmount, "savings"));
                        }

                        if (accountDepositChoice == 3){
                            System.out.print("Please enter amount: ");
                            double depositAmount = userInput.nextDouble();
                            System.out.println(clientCredit.deposit(clientArrayList,creditArrayList,i,depositAmount,"credit"));
                        }
                    }// end of option 2

                    if(menuInput == 3){
                        System.out.println("Select account to withdraw from");
                        System.out.println("1. Checking");
                        System.out.println("2. Savings");
                        int accountWithdrawChoice = userInput.nextInt();
                        if (accountWithdrawChoice == 1){
                            clientChecking.withdraw(clientArrayList,checkingArrayList,i,"checking");
                        }

                        if(accountWithdrawChoice == 2){
                            clientSavings.withdraw(clientArrayList,savingsArrayList,i,"savings");
                        }
                    }//end of option 3

                    if(menuInput == 4){
                        System.out.println("Select account would you like to transfer from.");
                        System.out.println("1. Checking");
                        System.out.println("2. Savings");
                        int accountTransferChoice = userInput.nextInt();
                        if(accountTransferChoice == 1){
                            System.out.println("transferring from checking to savings");
                            System.out.println("Enter amount to transfer");
                            double transferAmount = userInput.nextDouble();
                            System.out.println(clientChecking.transfer(clientArrayList,checkingArrayList,savingsArrayList,i,accountTransferChoice, transferAmount));
                        }

                        if (accountTransferChoice == 2){
                            System.out.print("transferring from savings to checking");
                            System.out.println("Enter amount to transfer");
                            double transferAmount = userInput.nextDouble();
                            System.out.println(clientSavings.transfer(clientArrayList,savingsArrayList,checkingArrayList,i,accountTransferChoice,transferAmount));
                        }

                    }//end of option 4


                    if(menuInput == 5){
                        System.out.println("Please select account.");
                        System.out.println("1. Checking");
                        System.out.println("2. Savings");
                        int wireTransferChoice = userInput.nextInt();
                        System.out.print("Enter the ID number belonging to the account you are trying to send money to: ");
                        int receiverIdentificationNumber = userInput.nextInt();
                        int receiverIndex = client.accountSearch(clientArrayList,receiverIdentificationNumber);
                        if (i == receiverIdentificationNumber) {
                            System.out.println("Invalid Transaction, same account");
                            continue;
                        }
                        System.out.println("Please select the receivers account type.");
                        System.out.println("1. Checking");
                        System.out.println("2. Savings");
                        int receiverAccountType = userInput.nextInt();
                        if(wireTransferChoice == 1){
                            if(receiverAccountType == 1){
                                clientChecking.wireTransfer(clientArrayList,checkingArrayList,checkingArrayList,i,receiverIndex);
                                clientChecking.updatedSheet(clientArrayList, checkingArrayList, savingsArrayList, creditArrayList);
                            }

                            if (receiverAccountType == 2){
                                clientChecking.wireTransfer(clientArrayList,checkingArrayList,savingsArrayList,i,receiverIndex);
                                clientChecking.updatedSheet(clientArrayList, checkingArrayList, savingsArrayList, creditArrayList);
                            }
                        }

                        if(wireTransferChoice == 2){
                            if(receiverAccountType == 1){
                                clientSavings.wireTransfer(clientArrayList,savingsArrayList,checkingArrayList,i,receiverIndex);
                                clientSavings.updatedSheet(clientArrayList, checkingArrayList, savingsArrayList, creditArrayList);
                            }

                            if (receiverAccountType == 2) {
                                clientSavings.wireTransfer(clientArrayList,savingsArrayList,savingsArrayList,i, receiverIndex);
                            }
                        }
                    }//end of option 5


                    if (menuInput == 6){
                        client.printContactInfo(clientArrayList,i);
                    }


                    if(menuInput == 7){
                        clientChecking.updatedSheet(clientArrayList,checkingArrayList,savingsArrayList,creditArrayList);
                        client.exitMessage();
                        terminate = true;
                    }// end of option 6
                }//end of menu while

            }catch (InputMismatchException e){
                System.out.println("Incorrect Value");
            }
        }//end of menu option 1

        if(bankchoice == 2){
            try {
                while (!terminate) {
                    System.out.println("1. Inquire account by name.");
                    System.out.println("2. Inquire account by type/number");
                    System.out.println("3. Inquire all accounts");
                    System.out.println("4. transaction action file");
                    System.out.println("5. Exit");
                    int managerMenuChoice = userInput.nextInt();
                    if (managerMenuChoice == 1){
                        System.out.println("enter first name of account");
                        String managerFNameChoice = userInput.next();
                        System.out.println("Enter last name of account");
                        String managerLNameChoice = userInput.next();
                        int clientIndex = managerClientObject.accountSearch(clientArrayList,managerFNameChoice, managerLNameChoice);
                        clientChecking = checkingArrayList.get(clientIndex);
                        clientSavings = savingsArrayList.get(clientIndex);
                        clientCredit = creditArrayList.get(clientIndex);
                        System.out.println("Account Summary for " + managerFNameChoice + " "+ managerLNameChoice);
                        System.out.println("Checking balance of: $" + clientChecking.getInitialBalance());
                        System.out.println("Savings balance of:  $" + clientSavings.getInitialBalance());
                        System.out.println("Credit of:   $" + clientCredit.getInitialBalance());
                    }// end of choice 1

                    if (managerMenuChoice == 2){
                        System.out.println("choose account type?");
                        System.out.println("1. Credit");
                        System.out.println("2. Savings");
                        System.out.println("3. Credit");
                        int accountTypeChoice = userInput.nextInt();
                        System.out.println("enter account number: ");
                        int managerAccountNumber = userInput.nextInt();
                        if(accountTypeChoice == 1){
                            int clientIndex = managerCheckingObject.accountSearch(checkingArrayList,managerAccountNumber);
                            try {
                                managerChecking = (Checking) checkingArrayList.get(clientIndex);
                                managerChecking.acquireBalance(checkingArrayList,clientIndex,"Checking: $");
                            }
                            catch (IndexOutOfBoundsException e){
                                System.out.println("Invalid account");
                            }
                        }

                        if(accountTypeChoice == 2){
                            int clientIndex = managerSavingsObject.accountSearch(savingsArrayList,managerAccountNumber);
                            try {
                                managerSavings = (Savings) savingsArrayList.get(clientIndex);
                                managerSavings.acquireBalance(savingsArrayList,clientIndex,"Savings: $");
                            }
                            catch (IndexOutOfBoundsException e){
                                System.out.println("Invalid Account");
                            }
                        }
                        if(accountTypeChoice == 3){
                            int clientIndex = managerCreditObject.accountSearch(creditArrayList,managerAccountNumber);
                            try {
                                manageCredit = (Credit) creditArrayList.get(clientIndex);
                                manageCredit.acquireBalance(creditArrayList, clientIndex,"Credit: $");
                            }
                            catch (IndexOutOfBoundsException e){
                                System.out.println("Invalid Account");
                            }
                        }
                    }

                    if (managerMenuChoice == 3){
                        for ( i = 0; i < clientArrayList.size(); i++){
                            System.out.println(clientArrayList.get(i).getFirstName() + " " + clientArrayList.get(i).getLastName());
                            System.out.println("Checking account balance of: $ " + checkingArrayList.get(i).getInitialBalance());
                            System.out.println("Savings account balance of: $ " + savingsArrayList.get(i).getInitialBalance());
                            System.out.println("Credit balance: $ " + creditArrayList.get(i).getInitialBalance());
                        }
                    }
                    if (managerMenuChoice == 6){

                        System.out.println("test");
                        try{
                            System.out.println("test");
                            BankStatement.BankStatement(managerClientObject, managerCheckingObject, managerSavingsObject, managerCreditObject);
                        }
                        catch (Exception e){
                            System.out.println("ah cabron");
                        }

                    }

                    if (managerMenuChoice == 4){
                        managerCheckingObject.transactionAction(clientArrayList,checkingArrayList, savingsArrayList, "Transaction Actions(3).csv");
                    }

                    if (managerMenuChoice == 5){
                        client.exitMessage();
                        terminate = true;
                    }
                }
            }
            catch (InputMismatchException e){
                System.out.println("Invalid Entry");
            }
        }

        if (bankchoice == 3){

            System.out.println("Welcome to MinerBank, We first need some information about you \n");
            System.out.println("What is your first name\n");
            String newFirst = userInput.next();
            System.out.println("What is your last name\n");
            String newLast = userInput.next();
            System.out.println("Please choose a password to enter.");
            String newPass = userInput.next();
            System.out.println("What is your date of birth\n");
            String newDate = userInput.next();
            System.out.println("what is your address\n");
            String newAddress = userInput.next();
            System.out.println("What is your phone number\n");
            String newPhone = userInput.next();
            System.out.println("A savings account will be created for you to start");


            int tempID = -1;
            int tempSavingsAccount = -1;
            int tempCheckingAccount = -1;
            int tempCreditAccount  = -1;
            for( i = 0; i < clientArrayList.size(); i++){
                if(tempID < clientArrayList.get(i).getIdentificationNumber()){
                    tempID = clientArrayList.get(i).getIdentificationNumber();
                }
                if(tempSavingsAccount < savingsArrayList.get(i).getAccountNum()){
                    tempSavingsAccount = savingsArrayList.get(i).getAccountNum();
                }
                if(tempCheckingAccount<checkingArrayList.get(i).getAccountNum()){
                    tempCheckingAccount = checkingArrayList.get(i).getAccountNum();
                }
                if(tempCreditAccount<creditArrayList.get(i).getAccountNum()){
                    tempCreditAccount = creditArrayList.get(i).getAccountNum();
                }
            }


            Client clientInfo = new Client(newFirst,newLast,newDate, tempID++, newAddress,newPhone, newPass);
            clientArrayList.add(clientInfo);
            Savings savingsInfo = new Savings(tempID++,tempSavingsAccount++,0);
            savingsArrayList.add(savingsInfo);

            System.out.println("Would you like to create a Checking account?\n");
            System.out.println("enter 1 for yes, 2 for no");
            int createCheck = userInput.nextInt();
            if (createCheck == 1){
                Checking checkingsInfo = new Checking(tempID++, tempCheckingAccount++, 0);
                checkingArrayList.add(checkingsInfo);
            }
            if (createCheck == 2){
                System.out.println("No checking will be created");
            }

            System.out.println("Would you like to create a Credit Account? \n");
            System.out.println("Enter 1 for yes, 2 for no");
            int createCredit = userInput.nextInt();
            if (createCredit == 1){
                Credit creditInfo = new Credit(tempID++, tempCreditAccount++, 0, 0);
                creditArrayList.add(creditInfo);
            }
            if (createCredit == 2){
                System.out.println("No credit account will be created");
            }

            System.out.println("your account has been created");

            //clientChecking.updatedSheet(clientArrayList,checkingArrayList,savingsArrayList,creditArrayList);
            //clientChecking.updatedSheet(clientArrayList,checkingArrayList,savingsArrayList,creditArrayList);
        }// end of menu choice 3
        //clientChecking.updatedSheet(clientArrayList,checkingArrayList,savingsArrayList,creditArrayList);

    }//end of main

}
