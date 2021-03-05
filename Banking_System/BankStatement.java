import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class BankStatement {
    public static void BankStatement(Client client,Checking checking, Savings savings, Credit credit) throws FileNotFoundException, UnsupportedEncodingException{
        PrintWriter writer = new PrintWriter(client.getLastName() + "_" + client.getFirstName() + "_BankStatement.txt", "UTF-8");
        File bankState = new File("transactionLog.txt");
        Scanner scnr = new Scanner(bankState);
        writer.println("Name: " + client.getFirstName() + " " + client.getLastName());
        writer.println("Date of Birth: " + client.getDateOfBirth());
        writer.println("ID Number: " + client.getIdentificationNumber());
        writer.println("Address: " + client.getAddress());
        writer.println("Phone Number: " + client.getPhoneNumber());
        writer.println("");
        writer.println("Checking Account Number: " + checking.getAccountNum() + " Checking Starting Account Balance: " + checking.getInitialBalance());
        writer.println("Savings Account Number: " + savings.getAccountNumber() + " Savings Starting Account Balance: " + savings.getInitialBalance());
        writer.println("Credit Account Number: " + credit.getAccountNum() + " Credit Starting Account Balance: " + credit.getInitialBalance());
        writer.println("");
        while(scnr.hasNextLine()){
            String transaction = scnr.nextLine();
            if(transaction.contains((client.getFirstName()))){
                if (transaction.contains(client.getLastName())) writer.println(transaction + " on: " + java.time.LocalDate.now());
            }
        }
        writer.println("");
        writer.println("The Final balance for Checking Account is: $ " + checking.getInitialBalance());
        writer.println("The Final Balance for Savings account is: $ " + savings.getInitialBalance());
        writer.println("The Final Balance for Credit Account is: $ " + credit.getInitialBalance());
        writer.close();
    }
}
