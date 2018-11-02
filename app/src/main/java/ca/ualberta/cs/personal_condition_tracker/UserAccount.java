package ca.ualberta.cs.personal_condition_tracker;

public class UserAccount {
    private String name;
    private String userID;
    private String email_address;
    private String password;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean authenticate(String userID, String password) {
        if (this.userID.equals(userID) && this.userID.equals(password)) {
            return true;
        }
        else {
            return false;
        }
    }
}
