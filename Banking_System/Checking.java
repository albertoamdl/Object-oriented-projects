import java.util.ArrayList;

/**
 * Checking class is child class of account
 *
 * @author Federico Marin
 * @since  September 29, 2020
 */

public class Checking extends Account {
    private int identificationNumber;

    /**
     * default constructor
     */
    public Checking(){
        super();
    }

    /**
     * Checking constructor
     * @param identificationNumberIn ID number
     * @param accountNumberIn account number
     * @param initialBalanceIn initial balance for designated account
     */

    public Checking(int identificationNumberIn, int accountNumberIn, double initialBalanceIn){
        super(accountNumberIn, initialBalanceIn);
        this.identificationNumber = identificationNumberIn;
    }

    /**
     * Identification number getter
     * @return ID number
     */
    public int getIdentificationNumber(){
        return this.identificationNumber;
    }

    /**
     * setter for ID number
     * @param identificationNumberIn ID number
     */
    public void setIdentificationNumber(int identificationNumberIn) {
        this.identificationNumber = identificationNumberIn;
    }

    /**
     * acquire balance method
     * @param accountList account list
     * @param i index of array list
     * @param account type of account
     */

    public void acquireBalance(ArrayList<Account> accountList, int i, String account){
        System.out.println(account + accountList.get(i).getInitialBalance());
    }

}
