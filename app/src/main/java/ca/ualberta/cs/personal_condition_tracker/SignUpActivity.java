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

package ca.ualberta.cs.personal_condition_tracker;

/**
 * ModifyUserAccountActivity allows a user to fill in contact information and account details when
 * they are signing up for the app, or when they wish to change the contact information of their account.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    public static Intent resultIntent;
    private UserAccountListController userAccountListController = new UserAccountListController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void confirmSignUp(View v){
        //Get information from user inputs
        Toast.makeText(this,"Confirming account edit", Toast.LENGTH_SHORT).show();
        Spinner accountTypeSpinner = findViewById(R.id.accountTypeSpinner);
        EditText userIDText = findViewById(R.id.userIDText);
        EditText emailAddressText = findViewById(R.id.emailAddressText);
        EditText phoneNumberText = findViewById(R.id.phoneNumberText);

        // Convert user inputs to strings
        String accountType = accountTypeSpinner.getSelectedItem().toString().toLowerCase().trim();
        String userID = userIDText.getText().toString();
        String emailAddress = emailAddressText.getText().toString();
        String phoneNumber = phoneNumberText.getText().toString();

        //TODO fix this. maybe w/ dropdown
        if(accountType.equals("patient")){
            // Make a new patient account.
            Toast.makeText(this,"Making Patient", Toast.LENGTH_SHORT).show();
            Patient newUserAccount = new Patient(accountType, userID, emailAddress);
            newUserAccount.setPhone_number(phoneNumber);
            createPatient(newUserAccount, userID);
            userAccountListController.addUserAccount(newUserAccount);
            this.finish();
        }
        else if (accountType.equals("care provider")){
            // Make a new care provider account.
            Toast.makeText(this,"Making Care Provider", Toast.LENGTH_SHORT).show();
            CareProvider newUserAccount = new CareProvider(accountType, userID, emailAddress);
            newUserAccount.setPhone_number(phoneNumber);
            createCareProvider(newUserAccount, userID);
            userAccountListController.addUserAccount(newUserAccount);
            this.finish();
        }

        else {
            Toast.makeText(this, "Invalid account type", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelSignUp(View v){
        Toast.makeText(this,"Cancelling edit...", Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_CANCELED, resultIntent);
        this.finish();
    }

    // Add a patient to the server.
    public void createPatient(Patient newPatient, String userID) {
        // Check if the user has already signed up
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

        // Add the user to the database.
        if (stored_users.size() == 0) {
            UserAccountListManager.AddUserAccountsTask addUserAccountsTask
                    = new UserAccountListManager.AddUserAccountsTask();
            addUserAccountsTask.execute(newPatient);
            Toast.makeText(SignUpActivity.this,"Sign up successful!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(SignUpActivity.this, "This userID already exists!", Toast.LENGTH_SHORT).show();
        }
    }

    // Add a care provider to the server.
    public void createCareProvider(CareProvider newCareProvider, String userID) {
        // Check if the user has already signed up
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

        // Add the user to the database.
        if (stored_users.size() == 0) {
//            UserAccountListController.getUserAccountList().addUserAccount(newCareProvider);
            UserAccountListManager.AddUserAccountsTask addUserAccountsTask
                    = new UserAccountListManager.AddUserAccountsTask();
            addUserAccountsTask.execute(newCareProvider);
            Toast.makeText(SignUpActivity.this,"Sign up successful!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(SignUpActivity.this, "This userID already exists!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        UserAccountListManager.GetUserAccountsTask getUserAccountsTask =
                new UserAccountListManager.GetUserAccountsTask();
        getUserAccountsTask.execute("");
        try {
            userAccountListController.getUserAccountList().setUserAccounts(getUserAccountsTask.get());
            Toast.makeText(this,Integer.toString(UserAccountListController.getUserAccountList().getUserAccounts().size()), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
    }
}
