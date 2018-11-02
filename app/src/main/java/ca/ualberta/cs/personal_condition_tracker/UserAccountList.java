package ca.ualberta.cs.personal_condition_tracker;

import java.util.ArrayList;

public class UserAccountList {
    private ArrayList<UserAccount> user_accounts;

    public void addUserAccount(UserAccount account_to_add) {
        this.user_accounts.add(account_to_add);
    }

}
