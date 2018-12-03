/* Personal Condition Tracker : A simple and attractive Android application that allows an individual to
 document, track and review the progression of a personal health issue (a 'condition'), thus serving to facilitate
 enhanced clarity of communicating between patient and care provider, early detection and accurate prognosis with the
 aim of obtaining medical treatment as soon as possible.

 Document the facts - get the treatment you deserve!

 Copyright (C) 2018

 R. Voon; rcvoon@ualberta.ca
 D. Buksa; draydon@ualberta.ca
 W. Nichols; wnichols@ualberta.ca
 D. Douziech; douziech@ualberta.ca
 C. Neureuter; neureute@ualberta.ca


Redistribution and use in source and binary forms, with or without
modification, are permitted (subject to the limitations in the disclaimer
below) provided that the following conditions are met:

     * Redistributions of source code must retain the above copyright notice,
     this list of conditions and the following disclaimer.

     * Redistributions in binary form must reproduce the above copyright
     notice, this list of conditions and the following disclaimer in the
     documentation and/or other materials provided with the distribution.

     * Neither the name of the copyright holder nor the names of its
     contributors may be used to endorse or promote products derived from this
     software without specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY
THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
*/

package ca.ualberta.cs.personal_condition_tracker.Controllers;

import android.util.Log;

import java.util.ArrayList;

import ca.ualberta.cs.personal_condition_tracker.Model.CareProvider;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;
import ca.ualberta.cs.personal_condition_tracker.Model.UserAccount;
import ca.ualberta.cs.personal_condition_tracker.Model.UserAccountList;
import ca.ualberta.cs.personal_condition_tracker.Managers.UserAccountListManager;

/**
 * UserAccountListController performs operations on a list of user accounts.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
public class UserAccountListController {
    private static UserAccountList userAccountList = null;
    /**
     * Gets the user account list.
     * @return UserAccountList userAccountList
     */
    static public UserAccountList getUserAccountList() {
        if ((userAccountList) == null) {
            userAccountList = new UserAccountList();
        }
        return userAccountList;
    }
    /**
     * Add an account to the user account list.
     * @param userAccount the account to be added.
     */
    public void addUserAccount(UserAccount userAccount){ getUserAccountList().addUserAccount(userAccount);}

    /**
     * Load all available user accounts from the server.
     * @return users
     */
    public ArrayList<UserAccount> loadUserAccounts() {
        ArrayList<UserAccount> users = new ArrayList<>();
        UserAccountListManager.GetUserAccountsTask getUserAccountsTask =
                new UserAccountListManager.GetUserAccountsTask();
        getUserAccountsTask.execute("");
        try {
            users = getUserAccountsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        return users;
    }

    /**
     * Load all patients associated with a care provider from the server
     * @param careProvider
     * @return patientIDs
     */
    public ArrayList<String> loadPatients(CareProvider careProvider) {
        UserAccountListManager.GetUserAccountsTask getPatientsTask =
                new UserAccountListManager.GetUserAccountsTask();
        String query = "{ \"query\": {\"match\": { \"associatedId\" : \""+ careProvider.getUserID() +"\" } } }";
        getPatientsTask.execute(query);
        ArrayList<UserAccount> patients = new ArrayList<>();
        ArrayList<String> patientIDs = new ArrayList<>();
        try {
            patients = getPatientsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        for (UserAccount patient : patients) {
            if (patient != null) {
                patientIDs.add(patient.getUserID());
            }
        }
        return patientIDs;
    }

    // Add a patient to the server.
    public void createPatient(Patient newPatient, String userID) {
        // Check if the patient has already signed up
        UserAccountListManager.GetUserAccountsTask getUserAccountsTask =
                new UserAccountListManager.GetUserAccountsTask();
        String query = "{ \"query\": {\"match\": { \"userID\" : \""+ userID +"\" } } }";
        getUserAccountsTask.execute(query);
        ArrayList<? extends UserAccount> stored_users = new ArrayList<UserAccount>();

        try {
            stored_users = getUserAccountsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }

        // Add the patient to the database.
        if (stored_users.size() == 0) {
            UserAccountListManager.AddUserAccountsTask addUserAccountsTask
                    = new UserAccountListManager.AddUserAccountsTask();
            addUserAccountsTask.execute(newPatient);
        }
    }

    // Add a care provider to the server.
    public void createCareProvider(CareProvider newCareProvider, String userID) {
        // Check if the care provider has already signed up
        UserAccountListManager.GetUserAccountsTask getUserAccountsTask =
                new UserAccountListManager.GetUserAccountsTask();

        String query = "{ \"query\": {\"match\": { \"userID\" : \""+ userID +"\" } } }";
        getUserAccountsTask.execute(query);
        ArrayList<? extends UserAccount> stored_users = new ArrayList<>();
        try {
            stored_users = getUserAccountsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }

        // Add the care provider to the database.
        if (stored_users.size() == 0) {
            UserAccountListManager.AddUserAccountsTask addUserAccountsTask
                    = new UserAccountListManager.AddUserAccountsTask();
            addUserAccountsTask.execute(newCareProvider);
        }
    }

    /**
     * Check if a patient exists in the server
     */
    public boolean checkIfPatientExists(String patientID) {
        boolean doesExist = false;
        UserAccountListManager.GetUserAccountsTask getUserAccountsTask =
                new UserAccountListManager.GetUserAccountsTask();
        String query = "{ \"query\": {\"match\": { \"userID\" : \"" + patientID + "\" } } }";
        getUserAccountsTask.execute(query);
        ArrayList<? extends UserAccount> storedUsers = new ArrayList<>();
        try {
            storedUsers = getUserAccountsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        if (storedUsers.size() != 0) {
            doesExist = true;
        }
        return doesExist;
    }

    /**
     * Get a specific patient from the server.
     * @param patientID
     * @return
     */
    public UserAccount getPatient(String patientID) {
        boolean doesExist = false;
        UserAccountListManager.GetUserAccountsTask getUserAccountsTask =
                new UserAccountListManager.GetUserAccountsTask();
        String query = "{ \"query\": {\"match\": { \"userID\" : \"" + patientID + "\" } } }";
        getUserAccountsTask.execute(query);
        ArrayList<UserAccount> storedUsers = new ArrayList<UserAccount>();
        try {
            storedUsers = getUserAccountsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        return storedUsers.get(0);
    }


    /**
     * Edit a user in the server.
     * @param oldUserAccount
     * @param newUserAccount
     */
    public void editUserAccount(UserAccount oldUserAccount, UserAccount newUserAccount) {
        newUserAccount.setId(oldUserAccount.getId());
        UserAccountListManager.DeleteUserAccountsTask deleteUserAccountsTask =
                new UserAccountListManager.DeleteUserAccountsTask();
        deleteUserAccountsTask.execute(oldUserAccount);
        UserAccountListManager.AddUserAccountsTask addUserAccountsTask
                = new UserAccountListManager.AddUserAccountsTask();
        addUserAccountsTask.execute(newUserAccount);
    }

}
