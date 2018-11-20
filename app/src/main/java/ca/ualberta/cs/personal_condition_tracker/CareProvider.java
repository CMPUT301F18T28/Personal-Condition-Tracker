package ca.ualberta.cs.personal_condition_tracker;


/**
 * The CareProvider class is used to represent all of the account information pertaining to Care Providers.
 * @author  C. Neureuter; neureute@ualberta.ca
 * @author  R. Voon; rcvoon@ualberta.ca
 * @author  D. Buksa; draydon@ualberta.ca
 * @author  W. Nichols; wnichols@ualberta.ca
 * @author  D. Douziech; douziech@ualberta.ca
 * @version 1.1, 11-18-18
 * @since   1.0
 */

public class CareProvider extends UserAccount{

    private PatientList patientList = new PatientList();

    CareProvider() {
        super();
    }

    CareProvider(String accountType, String userID, String emailAddress, String password) {
        super(accountType,userID,emailAddress,password);
    }

    /**
     * Provides the list of Patient objects representing the patients that are assigned to a particular Care Provider.
     * @return  patientList List of patient that are assigned to a particular Care Provider
     * @see PatientList
     * @see Patient
     */
    public PatientList getPatientList() {
        return patientList;
    }


    /**
     * Sets the list of patients assigned to a particular Care Provider.
     * @param patientList List of patients, that is, a list of Patient objects
     * @return  Nothing
     * @see PatientList
     * @see Patient
     */

    public void setPatientList(PatientList patientList) {
        this.patientList = patientList;
    }
}

