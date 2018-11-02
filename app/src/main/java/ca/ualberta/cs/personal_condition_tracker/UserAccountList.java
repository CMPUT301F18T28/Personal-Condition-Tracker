package ca.ualberta.cs.personal_condition_tracker;

import java.util.ArrayList;

public class UserAccountList {
    private ArrayList<UserAccount> user_accounts;

    public void addUserAccount(UserAccount account_to_add) {
        this.user_accounts.add(account_to_add);
    }

    public void deleteUserAccount(UserAccount account_to_delete) {
        this.user_accounts.remove(account_to_delete);
    }

    public void changeUserAccount(UserAccount account_to_change) {
        if (this.user_accounts.contains(account_to_change)) {
            this.user_accounts.remove(account_to_change);
        }
        this.user_accounts.add(account_to_change);
    }
}
