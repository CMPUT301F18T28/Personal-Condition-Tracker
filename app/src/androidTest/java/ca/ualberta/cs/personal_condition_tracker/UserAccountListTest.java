package ca.ualberta.cs.personal_condition_tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserAccountListTest {

    @Test
    public void addUserAccount() {
        UserAccountList test_user_account_list = new UserAccountList();

        /** Creates a User Account to add to the UserAccountList */
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setName("Yang");
        testUserAccount.setUserID("Yang");
        testUserAccount.setEmail_address("Yang@gmail.com");
        testUserAccount.setPassword("Yang'sPassword");

        /** Create a ArrayList<UserAccount> called test_user_accounts to hold the actual values */
        ArrayList<UserAccount> test_user_accounts = new ArrayList<UserAccount>();

        /** Add the testUserAccount to the test_user_accounts ArrayList */
        test_user_accounts.add(testUserAccount);

        /** Add the testUserAccount to the test_user_account_list */
        test_user_account_list.addUserAccount(testUserAccount);

        /** Check that the test_user_account_list is equal to the test_user_accounts ArrayList */
        assertEquals(test_user_account_list, test_user_accounts);

        /** Remove testUserAccount from test_user_accounts */
        test_user_accounts.remove(testUserAccount);

        /** Check that the test_user_account_list does NOT equal to test_user_accounts */
        assertFalse(test_user_account_list.equals(test_user_accounts));

        /** Change the UserID of testUserAccount */
        testUserAccount.setUserID("Wrong");
        test_user_accounts.add(testUserAccount);

        /** Check that the test_user_account_list does NOT equal to test_user_accounts */
        assertFalse(test_user_account_list.equals(test_user_accounts));
    }

    @Test
    public void deleteUserAccount() {
        UserAccountList test_user_account_list = new UserAccountList();

        /** Creates a User Account to add to the UserAccountList */
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setName("Yang");
        testUserAccount.setUserID("Yang");
        testUserAccount.setEmail_address("Yang@gmail.com");
        testUserAccount.setPassword("Yang'sPassword");

        /** Create a ArrayList<UserAccount> called test_user_accounts to hold the actual values */
        ArrayList<UserAccount> test_user_accounts = new ArrayList<UserAccount>();

        /** Add the testUserAccount to the test_user_accounts ArrayList */
        test_user_accounts.add(testUserAccount);

        /** Add the testUserAccount to the test_user_account_list */
        test_user_account_list.addUserAccount(testUserAccount);

        /** Check that the test_user_account_list is equal to the test_user_accounts ArrayList */
        assertEquals(test_user_account_list, test_user_accounts);

        /** Remove testUserAccount from test_user_accounts */
        test_user_accounts.remove(testUserAccount);

        /** Check that the test_user_account_list does NOT equal to test_user_accounts */
        assertFalse(test_user_account_list.equals(test_user_accounts));

        /** Remove testUserAccount from test_user_account_list */
        test_user_account_list.deleteUserAccount(testUserAccount);

        /** Check that the test_user_account_list is equal to the test_user_accounts ArrayList */
        assertEquals(test_user_account_list, test_user_accounts);

    }

    @Test
    public void changeUserAccount() {
    }
}