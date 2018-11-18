package ca.ualberta.cs.personal_condition_tracker;

public class CareProvider extends UserAccount{
    private PatientList patientList;
    CareProvider() {
        super();
    }

    public CareProvider(String accountType, String userID, String emailAddress, String password) {
        super(accountType,userID,emailAddress,password);
    }

    public PatientList getPatientList() {
        return patientList;
    }

    public void setPatientList(PatientList patientList) {
        this.patientList = patientList;
    }
}
