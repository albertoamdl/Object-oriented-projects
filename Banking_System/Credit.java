import java.util.ArrayList;

/**
 * class child of Account
 *
 * @author Fede
 * @since September 29. 2020
 */


public class Credit extends Account{
    private int identificationNumber;
    private double creditMax;

    /**
     * default constructor
     */
    public Credit(){
        super();
    }

    /**
     * credit constructor
     * @param identificationNumberIn ID num construct
     * @param accountNumberIn Account num construct
     * @param initialBalanceIn Initial balance of account construct
     */

    public Credit(int identificationNumberIn, int accountNumberIn, double initialBalanceIn, double creditMaxIn){
        super(accountNumberIn, initialBalanceIn);
        this.identificationNumber = identificationNumberIn;
        this.creditMax = creditMaxIn;
    }

    public double getCreditMax(){
        return this.creditMax;
    }

    public void setCreditMax(double creditMaxIn){
        this.creditMax = creditMaxIn;
    }
    /**
     * ID number getter
     * @return ID number
     */
    public int getIdentificationNumber(){
        return this.identificationNumber;
    }

    /**
     * ID number setter
     * @param identificationNumberIn ID num set
     */
    public void setIdentificationNumber(int identificationNumberIn){
        this.identificationNumber = identificationNumberIn;
    }

    /**
     *acquire method
     * @param accountList account array list
     * @param i index for the array
     * @param account type of account
     */

    public void acquireBalance(ArrayList<Account> accountList, int i, String account){
        System.out.println(account + accountList.get(i).getInitialBalance());
    }


}
