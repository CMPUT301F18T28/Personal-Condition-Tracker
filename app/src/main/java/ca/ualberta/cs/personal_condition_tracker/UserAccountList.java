package ca.ualberta.cs.personal_condition_tracker;

import java.util.ArrayList;

public class UserAccountList<T extends UserAccount> {
    private ArrayList<T> user_accounts = new ArrayList<>();
    private UserAccount activeAccount = null;
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
    public void setActiveAccount(UserAccount userAccount){this.activeAccount = userAccount;}
    public UserAccount getActiveAccount(){return this.activeAccount;}
    public void setAccountOfInterest(Patient patient){this.accountOfInterest = patient;}
    public UserAccount getAccountofInterest(){return this.accountOfInterest;}
}
