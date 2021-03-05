import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Client class
 * child of person
 *
 * @author Federico Marin
 * @since September 29,2020
 */

public class Client extends Person implements Printable{

    /**
     * default constructor
     */
    public Client(){
        super();
    }

    /**
     * client constructor
     * @param firstNameIn first name of client
     * @param lastNameIN last name of client
     * @param dateOfBirthIn date of birth of client
     * @param identificationNumberIn ID number of client
     * @param addressIn address of client
     * @param phoneNumberIn phone number of client
     */

    public Client(String firstNameIn, String lastNameIN, String dateOfBirthIn, int identificationNumberIn, String addressIn, String phoneNumberIn, String passwordIn){
        super(firstNameIn, lastNameIN, dateOfBirthIn, identificationNumberIn, addressIn, phoneNumberIn, passwordIn);
    }

    /**
     * account search through ID number
     *
     * @param clientArrayList client array list
     * @param identificationNumber  ID number
     * @return index of array list
     */

    public int accountSearch(ArrayList<Client> clientArrayList, int identificationNumber){
        int index = 0;

        for(int i = 0; i < clientArrayList.size(); i++){
            if(identificationNumber == clientArrayList.get(i).getIdentificationNumber()){
                index = 1;
            }
        }
        return index;
    }

    public int accountSearch(ArrayList<Client> clientArrayList, String fName, String lName){
        int i = 0;

        for(i = 0; i < clientArrayList.size(); i++){
            if(fName.equals(clientArrayList.get(i).getFirstName()) && lName.equals((clientArrayList.get(i).getLastName()))){
                break;
            }
        }
        return i;
    }

//    public boolean test(String user, ArrayList<Client> clientArrayList) {
//
//        for (int i =0; i < clientArrayList.size(); i++){
//            if (!user.equals(clientArrayList.get(i).getPassword())) {
//                throw new WrongPasswordException("Incorrect password, please try again,");
//            }
//        }//end of while
//
//        return true;
//    }

    public String accountSearch(ArrayList<Client> clientArrayList, String password){
        int i = 0;

        for (i=0; i < clientArrayList.size(); i++){
            if (password.equals(clientArrayList.get(i).getPassword())){
                break;
            }
        }
        return String.valueOf(i);
    }

    public void contactInfo(ArrayList<Client> clientArrayList, String firstName, String lastName, String dateOfBirth, String address, String phoneNumber){

    }


    @Override
    public void welcomeMessage() throws InputMismatchException {
        System.out.println("Welcome to MinerBank");
        System.out.println("Before we start, a quick question");
        System.out.println("Please choose one of the following menu items");
        System.out.println("1. Client");
        System.out.println("2. Bank manager");
        System.out.println("3. Create new client account");
    }

    @Override
    public void menuOptions() {
        System.out.println("1. Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Wire Transfer");
        System.out.println("6. Print out account information");
        System.out.println("7. Exit");
    }

    @Override
    public void exitMessage() {
        System.out.println("Thank you for choosing MinerBank, See you soon!");
    }

    @Override
    public void printContactInfo(ArrayList<Client> clientArrayList, int i) {
            System.out.println("------------Contact Information for Client---------");
            System.out.println("First Name: " + (clientArrayList.get(i).getFirstName()));
            System.out.println("Last Name: " + (clientArrayList.get(i).getLastName()));
            System.out.println("Date Of Birth: " + (clientArrayList.get(i).getDateOfBirth()));
            System.out.println("Address: " + (clientArrayList.get(i).getAddress()));
            System.out.println("Phone Number: " + (clientArrayList.get(i).getPhoneNumber()));


    }
}

