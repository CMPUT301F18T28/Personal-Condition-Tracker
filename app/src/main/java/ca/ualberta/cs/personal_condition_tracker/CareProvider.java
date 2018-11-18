package ca.ualberta.cs.personal_condition_tracker;

public class CareProvider extends UserAccount{
    private PatientList patient_list;
    CareProvider() {
        super();
    }

    public CareProvider(String accountType, String userID, String emailAddress, String password) {
        super(accountType,userID,emailAddress,password);
    }
}
