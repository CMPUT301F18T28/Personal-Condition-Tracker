/* Personal Condition Tracker : A simple and attractive Android application that allows an individual to
 document, track and review the progression of a personal health issue (a 'condition'), thus serving to facilitate
 enhanced clarity of communicating between patient and care provider, early detection and accurate prognosis with the
 aim of obtaining medical treatment as soon as possible.

 Document the facts - get the treatment you deserve!

 Copyright (C) 2018

 R. Voon; rcvoon@ualberta.ca
 D. Buksa; draydon@ualberta.ca
 W. Nichols; wnichols@ualberta.ca
 D. Douziech; douziech@ualberta.ca
 C. Neureuter; neureute@ualberta.ca


Redistribution and use in source and binary forms, with or without
modification, are permitted (subject to the limitations in the disclaimer
below) provided that the following conditions are met:

     * Redistributions of source code must retain the above copyright notice,
     this list of conditions and the following disclaimer.

     * Redistributions in binary form must reproduce the above copyright
     notice, this list of conditions and the following disclaimer in the
     documentation and/or other materials provided with the distribution.

     * Neither the name of the copyright holder nor the names of its
     contributors may be used to endorse or promote products derived from this
     software without specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY
THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
*/

package ca.ualberta.cs.personal_condition_tracker;

import java.util.ArrayList;


/**
 * UserAccountList serves to contain an arrayList of objects, inheriting from UserAccount, that represents
 * the entire set of users for the application.
 * <P>
 * Note that there are two subsets of users, Patients and Care Providers.
 * Care Providers have a list of patients, so this class provides a means of setting a patient account to gain access thereto.
 *</P>
 * @author     W. Nichols
 * @author     R. Voon; rcvoon@ualberta.ca
 * @author     D. Buksa; draydon@ualberta.ca
 * @author     W. Nichols; wnichols@ualberta.ca
 * @author     D. Douziech; douziech@ualberta.ca
 * @author     C. Neureuter; neureute@ualberta.ca
 * @version    1.1, 11-18-18
 * @since      1.0
 */


public class UserAccountList {
    private ArrayList<UserAccount> user_accounts = new ArrayList<>();
    private CareProvider activeCareProvider = null;
    private Patient accountOfInterest = null;
    /**
     * Registers the arrayList of UserAccount objects.
     * @param user_accounts an arrayList of objects inheriting from UserAccount
     * @see UserAccount
     */
    public void setUserAccounts(ArrayList<UserAccount> user_accounts) {
        this.user_accounts = user_accounts;
    }
    /**
     * Sets the arrayList of UserAccount objects.
     * @return Nothing
     */
    public ArrayList<UserAccount> getUserAccounts() {
        return this.user_accounts;
    }
    /**
     * Appends a user account to the existing arrayList
     * @param account_to_add element to be appended to the list of user accounts
     * @return Nothing
     */

    public void addUserAccount(UserAccount account_to_add) {
        this.user_accounts.add(account_to_add);
    }
    /**
     * Eliminates a provided user account object.
     * @param account_to_delete specifies the account to be removed from the list of accounts.
     * @return Nothing
     */
    public void deleteUserAccount(UserAccount account_to_delete) {
        this.user_accounts.remove(account_to_delete);
    }
    /**
     *
     *@return Nothing
     */
    public void changeUserAccount(UserAccount account_to_change) {
        if (this.user_accounts.contains(account_to_change)) {
            this.user_accounts.remove(account_to_change);
        }
        this.user_accounts.add(account_to_change);
    }
    /**
     * Provides the number of users by returning the length of the user_account ArrayList.
     * @return int Returns the number of objects in the list.
     */
    public int getNumberOfUsers() {
        return user_accounts.size();
    }
    /**
     * Sets the current account in question.
     * @return Nothing
     */
    public void setActiveCareProvider(CareProvider careProvider){this.activeCareProvider = careProvider;}
    /**
     * Supplies the activeAccount.
     * @return UserAccount Returns the activeAccount.
     */

    public CareProvider getActiveCareProvider(){return this.activeCareProvider;}
    /**
     * Sets the current account of interest.
     * @return Nothing
     */
    public void setAccountOfInterest(Patient patient){this.accountOfInterest = patient;}
    /**
     * Returns the patient account of interest.
     * @return Patient Returns the patient account of interest.
     */
    public Patient getAccountOfInterest(){return this.accountOfInterest;}
    /**
     * Returns a patient account given an ID.
     * @return UserAccount Returns the patient account of interest.
     */
    public UserAccount getPatientAccountByID(String patientID){
        for(UserAccount userAccount : user_accounts){
            if(userAccount.getUserID().equals(patientID)){
                return userAccount;
            }
        }
        return null;
    }
}
