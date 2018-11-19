
import java.util.ArrayList;


/**
 * UserAcountList serves to contain an arrayList of objects, inheriting from UserAccount, that represents
 * the entire set of users for the application.  Note that there are two subsets of users, Patients and Care Providers.
 * Care Providers have a list of patients, so this class provides a means of setting a patient account to gain access thereto.
 *
 * @author      W. Nichols
 * @version     1.1, 11-18-18
 * @since       1.0

*/

package ca.ualberta.cs.personal_condition_tracker;

import ca.ualberta.cs.personal_condition_tracker.Patient;
import ca.ualberta.cs.personal_condition_tracker.UserAccount;


public class UserAccountList<T extends UserAccount> {
    private ArrayList<T> user_accounts = new ArrayList<>();
    private UserAccount activeAccount = null;
    private Patient accountOfInterest = null;


    /**
     * Registers the arrayList of UserAccount objects.
     * <P>
     * @param user_accounts an arrayList of objects inheriting from UserAccount
     * @see ca.ualberta.cs.personal_condition_tracker.UserAccount
     */

    public void setUserAccounts(ArrayList<T> user_accounts) {
        this.user_accounts = user_accounts;
    }

    /**
     * Sets the arrayList of UserAccount objects.
     * <P>
     * @return Nothing
     */

    public ArrayList<T> getUserAccounts() {
        return this.user_accounts;
    }

    /**
     * Appends a user account to the existing arrayList
     * <P>
     * @param account_to_add element to be appended to the list of user accounts
     * @return Nothing
     */

    public void addUserAccount(T account_to_add) {
        this.user_accounts.add(account_to_add);
    }

    /**
     * Eliminates a provided user account object.
     * <P>
     * @param account_to_delete specifies the account to be removed from the list of accounts.
     * @return Nothing
     */

    public void deleteUserAccount(T account_to_delete) {
        this.user_accounts.remove(account_to_delete);
    }

    /**
     *
     * <P>
     *@return Nothing
     */

    public void changeUserAccount(T account_to_change) {
        if (this.user_accounts.contains(account_to_change)) {
            this.user_accounts.remove(account_to_change);
        }
        this.user_accounts.add(account_to_change);
    }

    /**
     * Provides the number of users by returning the length of the user_account ArrayList.
     * <P>
     * @return int Returns the number of objects in the list.
     */


    public int getNumberOfUsers() {
        return user_accounts.size();
    }

    /**
     * Sets the current account in question.
     * <P>
     * @return Nothing
     */

    public void setActiveAccount(UserAccount userAccount){this.activeAccount = userAccount;}


    /**
     * Supplies the activeAccount.
     * <P>
     * @return UserAccount Returns the activeAccount.
     */

    public UserAccount getActiveAccount(){return this.activeAccount;}


    /**
     * Sets the current account of interest.
     * <P>
     * @return Nothing
     */

    public void setAccountOfInterest(Patient patient){this.accountOfInterest = patient;}


    /**
     * Returns the patient account of interest.
     * <P>
     * @return Patient Return the patient account of interest.
     */

    public Patient getAccountofInterest(){return this.accountOfInterest;}
}
