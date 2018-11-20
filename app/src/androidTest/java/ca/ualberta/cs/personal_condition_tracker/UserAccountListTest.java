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
        testUserAccount.setAccountType("Care");
        testUserAccount.setUserID("Yang");
        testUserAccount.setEmail_address("Yang@gmail.com");
        testUserAccount.setPassword("Yang'sPassword");

        /** Creates a User Account to add to the ArrayList<UserAccount> */
        UserAccount testUserAccount2 = new UserAccount();
        testUserAccount2.setAccountType("Care");
        testUserAccount2.setUserID("Yang");
        testUserAccount2.setEmail_address("Yang@gmail.com");
        testUserAccount2.setPassword("Yang'sPassword");

        /** Create a ArrayList<UserAccount> called test_user_accounts to hold the actual values */
        ArrayList<UserAccount> test_user_accounts = new ArrayList<UserAccount>();

        /** Add the testUserAccount to the test_user_accounts ArrayList */
        test_user_accounts.add(testUserAccount2);

        /** Add the testUserAccount to the test_user_account_list */
        test_user_account_list.addUserAccount(testUserAccount);

        /** Check that the test_user_account_list is equal to the test_user_accounts ArrayList */
        assertEquals(test_user_account_list.getUserAccounts(), test_user_accounts);

        /** Remove testUserAccount from test_user_accounts */
        test_user_accounts.remove(testUserAccount);

        /** Check that the test_user_account_list does NOT equal to test_user_accounts */
        assertFalse(test_user_account_list.getUserAccounts().equals(test_user_accounts));

        /** Change the UserID of testUserAccount2 */
        testUserAccount2.setUserID("Wrong");
        test_user_accounts.add(testUserAccount2);

        /** Check that the test_user_account_list does NOT equal to test_user_accounts */
        assertFalse(test_user_account_list.getUserAccounts().equals(test_user_accounts));
    }

    @Test
    public void deleteUserAccount() {
        UserAccountList test_user_account_list = new UserAccountList();

        /** Creates a User Account to add to the UserAccountList */
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setAccountType("Care");
        testUserAccount.setUserID("Yang");
        testUserAccount.setEmail_address("Yang@gmail.com");
        testUserAccount.setPassword("Yang'sPassword");

        /** Creates a User Account to add to the ArrayList<UserAccount> */
        UserAccount testUserAccount2 = new UserAccount();
        testUserAccount2.setAccountType("Care");
        testUserAccount2.setUserID("Yang");
        testUserAccount2.setEmail_address("Yang@gmail.com");
        testUserAccount2.setPassword("Yang'sPassword");

        /** Create a ArrayList<UserAccount> called test_user_accounts to hold the actual values */
        ArrayList<UserAccount> test_user_accounts = new ArrayList<UserAccount>();

        /** Add the testUserAccount2 to the test_user_accounts ArrayList */
        test_user_accounts.add(testUserAccount2);

        /** Add the testUserAccount to the test_user_account_list */
        test_user_account_list.addUserAccount(testUserAccount);

        /** Check that the test_user_account_list is equal to the test_user_accounts ArrayList */
        assertEquals(test_user_account_list.getUserAccounts(), test_user_accounts);

        /** Remove testUserAccount2 from test_user_accounts */
        test_user_accounts.remove(testUserAccount2);

        /** Check that the test_user_account_list does NOT equal to test_user_accounts */
        assertFalse(test_user_account_list.getUserAccounts().equals(test_user_accounts));

        /** Remove testUserAccount from test_user_account_list */
        test_user_account_list.deleteUserAccount(testUserAccount);

        /** Check that the test_user_account_list is equal to the test_user_accounts ArrayList */
        assertEquals(test_user_account_list.getUserAccounts(), test_user_accounts);

    }

    @Test
    public void changeUserAccount() {
        UserAccountList test_user_account_list = new UserAccountList();

        /** Creates a User Account to add to the UserAccountList */
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setAccountType("Care");
        testUserAccount.setUserID("Yang");
        testUserAccount.setEmail_address("Yang@gmail.com");
        testUserAccount.setPassword("Yang'sPassword");

        /** Creates a User Account to add to the ArrayList<UserAccount> */
        UserAccount testUserAccount2 = new UserAccount();
        testUserAccount2.setAccountType("Care");
        testUserAccount2.setUserID("Yang");
        testUserAccount2.setEmail_address("Yang@gmail.com");
        testUserAccount2.setPassword("Yang'sPassword");

        /** Create a ArrayList<UserAccount> called test_user_accounts to hold the actual values */
        ArrayList<UserAccount> test_user_accounts = new ArrayList<UserAccount>();

        /** Add the testUserAccount2 to the test_user_accounts ArrayList */
        test_user_accounts.add(testUserAccount2);

        /** Add the testUserAccount to the test_user_account_list */
        test_user_account_list.addUserAccount(testUserAccount);

        /** Check that the test_user_account_list is equal to the test_user_accounts ArrayList */
        assertEquals(test_user_account_list.getUserAccounts(), test_user_accounts);

        /** Change the UserID of testUserAccount */
        testUserAccount2.setUserID("Wrong");

        /** Remove testUserAccount from test_user_accounts and re-add it */
        test_user_accounts.remove(testUserAccount2);
        test_user_accounts.add(testUserAccount2);

        /** Check that the test_user_account_list does NOT equal to test_user_accounts */
        assertFalse(test_user_account_list.getUserAccounts().equals(test_user_accounts));

        /** Change the UserID of testUserAccount */
        testUserAccount.setUserID("Wrong");

        /** Change the testUserAccount in the test_user_account_list */
        test_user_account_list.changeUserAccount(testUserAccount);

        /** Check that the test_user_account_list is equal to the test_user_accounts ArrayList */
        assertEquals(test_user_account_list.getUserAccounts(), test_user_accounts);

    }

}