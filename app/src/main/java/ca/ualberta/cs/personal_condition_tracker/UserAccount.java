package ca.ualberta.cs.personal_condition_tracker;

import java.util.Objects;

public class UserAccount {
    private String accountType;
    private String userID;
    private String email_address;
    private String phone_number;
    private String password;
    private String id;

    UserAccount() {
        this.accountType = "";
        this.userID = "";
        this.email_address = "";
        this.password = "password";
    }

    UserAccount(String accountType, String userID, String email_address, String password) {
        this.accountType = accountType;
        this.userID = userID;
        this.email_address = email_address;
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean authenticate(String userID, String password) {
        if (this.userID.equals(userID) && this.password.equals(password)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) { // Checks userID only
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        if (userID.equals(that.userID)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {

        return Objects.hash(userID);
    }
}
