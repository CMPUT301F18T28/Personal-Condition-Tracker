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
*/

package ca.ualberta.cs.personal_condition_tracker.Model;


/**
 * The Patient class represents those accounts held by patients.
 * <p>
 * One of two account types that inherit directly from the UserAccount class.
 * </p>
 * @author     R. Voon; rcvoon@ualberta.ca
 * @author     D. Buksa; draydon@ualberta.ca
 * @author     W. Nichols; wnichols@ualberta.ca
 * @author     D. Douziech; douziech@ualberta.ca
 * @author     C. Neureuter; neureute@ualberta.ca
 * @version    1.1, 11-18-18
 * @since      1.0
 */

public class Patient extends UserAccount{

    private ConditionList conditionList = new ConditionList();

    /**
     * Constructor
     * @see UserAccount
     */

    public Patient() {
        super();
    }

    /**
     * Constructor with specified attributes: accountType, userID, and emailAddress.
     * @param accountType String representing the type of user account, Care Provider or Patient.
     * @param userID String representing the name of the account holder; a username.
     * @param emailAddress Email address of the account holder.
     * @see UserAccount
     */

    public Patient(String accountType, String userID, String emailAddress) {
        super(accountType,userID,emailAddress);
    }

    /**
     * Constructor with specified attributes: accountType, userID, and emailAddress.
     * @param accountType String representing the type of user account, Care Provider or Patient.
     * @param userID String representing the name of the account holder; a username.
     * @param emailAddress Email address of the account holder.
     * @param phoneNumber Phone number of the account holder.
     * @see UserAccount
     */
    public Patient(String accountType, String userID, String emailAddress, String phoneNumber) {
        super(accountType, userID, emailAddress, phoneNumber);
    }

    /**
     * Provides the list of patient conditions.
     * @return ConditionList An ArrayList of Condition objects
     * @see ConditionList
     * @see Condition
     */

    public ConditionList getConditionList() {
        return conditionList;
    }

    /**
     * Registers a list of conditions for a particular patient.
     * @param conditionList An ArrayList of Condition Objects; that is, a list of conditions specific to a patient.
     * @return Nothing
     * @see ConditionList
     * @see Condition
     */

    public void setConditionList(ConditionList conditionList) {
        this.conditionList = conditionList;
    }
}