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

package ca.ualberta.cs.personal_condition_tracker.Activities;

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

import ca.ualberta.cs.personal_condition_tracker.Model.CareProvider;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;
import ca.ualberta.cs.personal_condition_tracker.R;
import ca.ualberta.cs.personal_condition_tracker.Model.UserAccount;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;
import ca.ualberta.cs.personal_condition_tracker.Managers.UserAccountListManager;

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
        Integer numberOfAccounts = userAccountListController.getUserAccountList().size();
        if (userID.length() > UserAccount.getMinUseridChars()) {
            //TODO fix this. maybe w/ dropdown
            if(accountType.equals("patient")){
                // Make a new patient account.
                Toast.makeText(this,"Making Patient", Toast.LENGTH_SHORT).show();
                Patient newUserAccount = new Patient(accountType, userID, emailAddress, phoneNumber);
                newUserAccount.setShortCode(numberOfAccounts.toString());
                userAccountListController.createPatient(newUserAccount, userID);
                userAccountListController.addUserAccount(newUserAccount);
                this.finish();
            }
            else if (accountType.equals("care provider")){
                // Make a new care provider account.
                Toast.makeText(this,"Making Care Provider", Toast.LENGTH_SHORT).show();
                CareProvider newUserAccount = new CareProvider(accountType, userID, emailAddress, phoneNumber);
                newUserAccount.setShortCode(numberOfAccounts.toString());
                userAccountListController.createCareProvider(newUserAccount, userID);
                userAccountListController.addUserAccount(newUserAccount);
                this.finish();
            }
            else {
                Toast.makeText(this, "Invalid account type", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "UserID is too short!", Toast.LENGTH_SHORT).show();
        }

    }

    public void cancelSignUp(View v){
        setResult(Activity.RESULT_CANCELED, resultIntent);
        this.finish();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        ArrayList<UserAccount> users = userAccountListController.loadUserAccounts();
        userAccountListController.getUserAccountList().setUserAccounts(users);
    }
}
