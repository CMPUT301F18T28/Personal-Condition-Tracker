
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

import java.util.Objects;

import io.searchbox.annotations.JestId;

/**
 * UserAccount is the base class for the entire model hierarchy and defines the general attributes and
 * functionality for an account.
 * <p>
 * Both Patient and CareProvider classes extend from UserAccount.
 *</p>
 * @author     R. Voon; rcvoon@ualberta.ca
 * @author     D. Buksa; draydon@ualberta.ca
 * @author     W. Nichols; wnichols@ualberta.ca
 * @author     D. Douziech; douziech@ualberta.ca
 * @author     C. Neureuter; neureute@ualberta.ca
 * @version    1.1, 11-18-18
 * @since      1.0
 */

public class UserAccount {
    private String accountType;
    private String userID;
    private String email_address;
    private String phone_number;
    private String password;
    @JestId
    private String id;

    /**
     * Constructor serving to initialize the following attributes to empty Strings: accountType, userID, email_address and password.
     * @return  Nothing
     */
    UserAccount() {
        this.accountType = "";
        this.userID = "";
        this.email_address = "";
        this.password = "password";
    }
    /**
     * Constructor serving to set the following attributes: accountType, userID, email_address and password.
     * @param accountType String representing the type of user account, Care Provider or Patient.
     * @param userID String representing the name of the account holder; a username.
     * @param email_address Email address of the account holder.
     * @param password   Password for the account
     * @return Nothing
     */

    UserAccount(String accountType, String userID, String email_address, String password) {
        this.accountType = accountType;
        this.userID = userID;
        this.email_address = email_address;
        this.password = password;
    }

    /**
     * Provides the classification for the account, that is, the type of account: Patient or CareProvider
     * @return String
     * @see Patient
     * @see CareProvider
     */

    public String getAccountType() {
        return accountType;
    }
    /**
     * Serves to set the type of user account; valid entries are restricted to one of 'Patient
     * or 'Care Provider'.
     * @param accountType 'Patient' or 'Care Provider'; all other entries will result in an exception.
     * @return Nothing
     * @throws invalidAccountTypeException
     * @see Patient
     * @see CareProvider
     */

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    /**
     * Provides the ID of the user (userID); a username.
     * @return String
     */
    public String getUserID() {
        return userID;
    }
    /**
     * Set the username (userID) of the account holder.
     * @return Nothing
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
    /**
     * Provides the phone number of the account holder.
     * @return String
     */
    public String getPhone_number() {
        return phone_number;
    }
    /**
     * Sets the phone number of the account holder.
     * @return Nothing
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    /**
     * Provides the email address of the account holder.
     * @return String
     */
    public String getEmail_address() {
        return email_address;
    }
    /**
     * Sets the email address of the account holder.
     * @return Nothing
     */
    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }
    /**
     * Provides the password of the account holder.
     * @return String
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the password of the account holder.
     * @return Nothing
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Provides the JEST identification attribute used to distinguish a particular account.
     * @return String
     */

    public String getId() {
        return id;
    }
    /**
     * Sets the id attribute for an account.
     * @param id String used to identify the particular account.     */
    public void setId(String id) {
        this.id = id;
    }

      /**
     * Serves to verify the account holder by way of a username (userID) and a password.
     * @param userID Username of the account holder
     * @param password Password of the account holder
     * @return boolean True if both the user ID and the password match those stored for the account, false otherwise.
     */
  
    public boolean authenticate(String userID, String password) {
        if (this.userID.equals(userID) && this.password.equals(password)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Exhaustive equivalence test to confirm that the supplied username is the same as the one on file.
     * @param o Username of the account holder
     * @return boolean True if the supplied username is equivalent, false otherwise.
     */
    @Override
    public boolean equals(Object o) { // Checks userID only
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        if (userID.equals(that.userID)) {
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * Maps the username of the account holder to an integer.
     * @return int Hash value corresponding to the username of the account holder.
     */
    @Override
    public int hashCode() {

        return Objects.hash(userID);
    }
}
