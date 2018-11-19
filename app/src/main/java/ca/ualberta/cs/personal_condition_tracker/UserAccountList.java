package ca.ualberta.cs.personal_condition_tracker;

import java.util.ArrayList;

public class UserAccountList<T extends UserAccount> {
    private ArrayList<T> user_accounts = new ArrayList<>();
    private CareProvider activeCareProvider = null;
    private Patient accountOfInterest = null;

    public void setUserAccounts(ArrayList<T> user_accounts) {
        this.user_accounts = user_accounts;
    }
    public ArrayList<T> getUserAccounts() {
        return this.user_accounts;
    }

    public void addUserAccount(T account_to_add) {
        this.user_accounts.add(account_to_add);
    }

    public void deleteUserAccount(T account_to_delete) {
        this.user_accounts.remove(account_to_delete);
    }

    public void changeUserAccount(T account_to_change) {
        if (this.user_accounts.contains(account_to_change)) {
            this.user_accounts.remove(account_to_change);
        }
        this.user_accounts.add(account_to_change);
    }
    public int getNumberOfUsers() {
        return user_accounts.size();
    }
    public void setActiveCareProvider(CareProvider careProvider){this.activeCareProvider = careProvider;}
    public CareProvider getActiveCareProvider(){return this.activeCareProvider;}
    public void setAccountOfInterest(Patient patient){this.accountOfInterest = patient;}
    public Patient getAccountOfInterest(){return this.accountOfInterest;}
    public Patient getPatientAccountByID(String patientID){
        for(UserAccount userAccount : user_accounts){
            if(userAccount.getUserID().equals(patientID)){
                return (Patient) userAccount;
            }
        }
        return null;
    }
}
