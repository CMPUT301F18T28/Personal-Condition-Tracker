package ca.ualberta.cs.personal_condition_tracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserAccountTest {

    @Test
    public void getAccountType() {
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setAccountType("Care");
        assertEquals(testUserAccount.getAccountType(),"Care");
    }

    @Test
    public void setAccountType() {
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setAccountType("Yang");
        assertEquals(testUserAccount.getAccountType(),"Yang");
    }

    @Test
    public void getUserID() {
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setUserID("Yang");
        assertEquals(testUserAccount.getUserID(),"Yang");
    }

    @Test
    public void setUserID() {
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setUserID("Yang");
        assertEquals(testUserAccount.getUserID(),"Yang");
    }

    @Test
    public void getEmail_address() {
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setEmailAddress("Yang@gmail.com");
        assertEquals(testUserAccount.getEmailAddress(),"Yang@gmail.com");
    }

    @Test
    public void setEmail_address() {
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setEmailAddress("Yang@gmail.com");
        assertEquals(testUserAccount.getEmailAddress(),"Yang@gmail.com");
    }

    @Test
    public void getPassword() {
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setPassword("Yang'sPassword");
        assertEquals(testUserAccount.getPassword(),"Yang'sPassword");
    }

    @Test
    public void setPassword() {
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setPassword("Yang'sPassword");
        assertEquals(testUserAccount.getPassword(),"Yang'sPassword");
    }

    @Test
    public void getId() {
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setId("qe2fs");
        assertEquals(testUserAccount.getId(),"qe2fs");
    }

    @Test
    public void setId() {
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setId("qe2fs");
        assertEquals(testUserAccount.getId(),"qe2fs");
    }

    @Test
    public void authenticate() {

        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setAccountType("Yang");
        testUserAccount.setUserID("Yang");
        testUserAccount.setEmailAddress("Yang@gmail.com");
        // Test that a user with a correct password can get in.
        assertTrue(testUserAccount.authenticate("Yang"));
        // Test that a user with an incorrect password cannot get in.
        assertFalse(testUserAccount.authenticate("Yang2"));
    }

    @Test
    public void equals() {
        UserAccount testUserAccountOne = new UserAccount();
        UserAccount testUserAccountTwo = new UserAccount();
        // Set the testUserAccountOne's UserID to "Yang"
        testUserAccountOne.setUserID("Yang");

        // Set the testUserAccountTwo's UserID to "Yang"
        testUserAccountTwo.setUserID("Yang");

        // Check that they are equal
        assertTrue(testUserAccountOne.equals(testUserAccountTwo));

        // Set the testUserAccountTwo's UserID to "Wrong"
        testUserAccountTwo.setUserID("Wrong");

        // Check that they are NOT equal
        assertFalse(testUserAccountOne.equals(testUserAccountTwo));
    }
}