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

package ca.ualberta.cs.personal_condition_tracker;

/**
 *
 *
 * @author      D. Douziech
 * @version     1.1, 11-18-18
 * @since       1.0
 */

public class Patient extends UserAccount{
    private ConditionList conditionList = new ConditionList();
    Patient() {
        super();
    }

    public Patient(String accountType, String userID, String emailAddress, String password) {
        super(accountType,userID,emailAddress,password);
    }

    public ConditionList getConditionList() {
        return conditionList;
    }

    public void setConditionList(ConditionList conditionList) {
        this.conditionList = conditionList;
    }
}
