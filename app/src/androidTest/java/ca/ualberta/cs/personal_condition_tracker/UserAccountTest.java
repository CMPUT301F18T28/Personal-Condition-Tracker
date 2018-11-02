package ca.ualberta.cs.personal_condition_tracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserAccountTest {

    @Test
    public void getName() {
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setName("Yang");
        assertEquals(testUserAccount.getName(),"Yang");
    }

    @Test
    public void setName() {
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setName("Yang");
        assertEquals(testUserAccount.getName(),"Yang");
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
        testUserAccount.setEmail_address("Yang@gmail.com");
        assertEquals(testUserAccount.getEmail_address(),"Yang@gmail.com");
    }

    @Test
    public void setEmail_address() {
        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setEmail_address("Yang@gmail.com");
        assertEquals(testUserAccount.getEmail_address(),"Yang@gmail.com");
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
    public void authenticate() {

        UserAccount testUserAccount = new UserAccount();
        testUserAccount.setName("Yang");
        testUserAccount.setUserID("Yang");
        testUserAccount.setEmail_address("Yang@gmail.com");
        testUserAccount.setPassword("Yang'sPassword");
        // Test that a user with a correct password can get in.
        assertTrue(testUserAccount.authenticate("Yang","Yang'sPassword"));
        // Test that a user with an incorrect password cannot get in.
        assertFalse(testUserAccount.authenticate("Yang","IncorrectPassword"));
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