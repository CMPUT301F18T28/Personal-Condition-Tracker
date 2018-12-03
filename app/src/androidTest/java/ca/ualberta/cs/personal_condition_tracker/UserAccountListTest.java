package ca.ualberta.cs.personal_condition_tracker;

import org.junit.Test;

import java.util.ArrayList;

import ca.ualberta.cs.personal_condition_tracker.Model.UserAccount;
import ca.ualberta.cs.personal_condition_tracker.Model.UserAccountList;

import static org.junit.Assert.*;

public class UserAccountListTest {

    @Test
    public void addUserAccount() {
        UserAccountList testUserAccountList = new UserAccountList();

        /** Creates a User Account to add to the UserAccountList */
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setAccountType("Care");
        testUserAccount.setUserID("Yang");
        testUserAccount.setEmailAddress("Yang@gmail.com");
        testUserAccount.setPassword("Yang'sPassword");

        /** Creates a User Account to add to the ArrayList<UserAccount> */
        UserAccount testUserAccount2 = new UserAccount();
        testUserAccount2.setAccountType("Care");
        testUserAccount2.setUserID("Yang");
        testUserAccount2.setEmailAddress("Yang@gmail.com");
        testUserAccount2.setPassword("Yang'sPassword");

        /** Create a ArrayList<UserAccount> called test_user_accounts to hold the actual values */
        ArrayList<UserAccount> testUserAccounts = new ArrayList<UserAccount>();

        /** Add the testUserAccount to the test_user_accounts ArrayList */
        testUserAccounts.add(testUserAccount2);

        /** Add the testUserAccount to the test_user_account_list */
        testUserAccountList.addUserAccount(testUserAccount);

        /** Check that the test_user_account_list is equal to the test_user_accounts ArrayList */
        assertEquals(testUserAccountList.getUserAccounts(), testUserAccounts);

        /** Remove testUserAccount from test_user_accounts */
        testUserAccounts.remove(testUserAccount);

        /** Check that the test_user_account_list does NOT equal to test_user_accounts */
        assertFalse(testUserAccountList.getUserAccounts().equals(testUserAccounts));

        /** Change the UserID of testUserAccount2 */
        testUserAccount2.setUserID("Wrong");
        testUserAccounts.add(testUserAccount2);

        /** Check that the test_user_account_list does NOT equal to test_user_accounts */
        assertFalse(testUserAccountList.getUserAccounts().equals(testUserAccounts));
    }

    @Test
    public void deleteUserAccount() {
        UserAccountList testUserAccountList = new UserAccountList();

        /** Creates a User Account to add to the UserAccountList */
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setAccountType("Care");
        testUserAccount.setUserID("Yang");
        testUserAccount.setEmailAddress("Yang@gmail.com");
        testUserAccount.setPassword("Yang'sPassword");

        /** Creates a User Account to add to the ArrayList<UserAccount> */
        UserAccount testUserAccount2 = new UserAccount();
        testUserAccount2.setAccountType("Care");
        testUserAccount2.setUserID("Yang");
        testUserAccount2.setEmailAddress("Yang@gmail.com");
        testUserAccount2.setPassword("Yang'sPassword");

        /** Create a ArrayList<UserAccount> called test_user_accounts to hold the actual values */
        ArrayList<UserAccount> testUserAccounts = new ArrayList<UserAccount>();

        /** Add the testUserAccount2 to the test_user_accounts ArrayList */
        testUserAccounts.add(testUserAccount2);

        /** Add the testUserAccount to the test_user_account_list */
        testUserAccountList.addUserAccount(testUserAccount);

        /** Check that the test_user_account_list is equal to the test_user_accounts ArrayList */
        assertEquals(testUserAccountList.getUserAccounts(), testUserAccounts);

        /** Remove testUserAccount2 from test_user_accounts */
        testUserAccounts.remove(testUserAccount2);

        /** Check that the test_user_account_list does NOT equal to test_user_accounts */
        assertFalse(testUserAccountList.getUserAccounts().equals(testUserAccounts));

        /** Remove testUserAccount from test_user_account_list */
        testUserAccountList.deleteUserAccount(testUserAccount);

        /** Check that the test_user_account_list is equal to the test_user_accounts ArrayList */
        assertEquals(testUserAccountList.getUserAccounts(), testUserAccounts);
    }

    @Test
    public void changeUserAccount() {
        UserAccountList testUserAccountList = new UserAccountList();

        /** Creates a User Account to add to the UserAccountList */
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setAccountType("Care");
        testUserAccount.setUserID("Yang");
        testUserAccount.setEmailAddress("Yang@gmail.com");
        testUserAccount.setPassword("Yang'sPassword");

        /** Creates a User Account to add to the ArrayList<UserAccount> */
        UserAccount testUserAccount2 = new UserAccount();
        testUserAccount2.setAccountType("Care");
        testUserAccount2.setUserID("Yang");
        testUserAccount2.setEmailAddress("Yang@gmail.com");
        testUserAccount2.setPassword("Yang'sPassword");

        /** Create a ArrayList<UserAccount> called test_user_accounts to hold the actual values */
        ArrayList<UserAccount> testUserAccounts = new ArrayList<UserAccount>();

        /** Add the testUserAccount2 to the test_user_accounts ArrayList */
        testUserAccounts.add(testUserAccount2);

        /** Add the testUserAccount to the test_user_account_list */
        testUserAccountList.addUserAccount(testUserAccount);

        /** Check that the test_user_account_list is equal to the test_user_accounts ArrayList */
        assertEquals(testUserAccountList.getUserAccounts(), testUserAccounts);

        /** Change the UserID of testUserAccount */
        testUserAccount2.setUserID("Wrong");

        /** Remove testUserAccount from test_user_accounts and re-add it */
        testUserAccounts.remove(testUserAccount2);
        testUserAccounts.add(testUserAccount2);

        /** Check that the test_user_account_list does NOT equal to test_user_accounts */
        assertFalse(testUserAccountList.getUserAccounts().equals(testUserAccounts));

        /** Change the UserID of testUserAccount */
        testUserAccount.setUserID("Wrong");

        /** Change the testUserAccount in the test_user_account_list */
        testUserAccountList.changeUserAccount(testUserAccount);

        /** Check that the test_user_account_list is equal to the test_user_accounts ArrayList */
        assertEquals(testUserAccountList.getUserAccounts(), testUserAccounts);

    }

}