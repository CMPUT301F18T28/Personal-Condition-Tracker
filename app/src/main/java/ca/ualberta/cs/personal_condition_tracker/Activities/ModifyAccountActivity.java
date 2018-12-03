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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import ca.ualberta.cs.personal_condition_tracker.R;
import ca.ualberta.cs.personal_condition_tracker.Model.UserAccount;
import ca.ualberta.cs.personal_condition_tracker.Model.UserAccountList;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;

/**
 * ModifyAccountActivity is responsible for allowing a user to modify or view their own contact information.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
public class ModifyAccountActivity extends AppCompatActivity {
    private Intent intent;
    private UserAccountListController userAccountListController = new UserAccountListController();
    private UserAccountList userAccountList = userAccountListController.getUserAccountList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_account);

        //Get information from the intent
        intent = getIntent();
        viewingSetup();
    }

    public void viewingSetup(){
        UserAccount user = new UserAccount();
        String accountType = intent.getStringExtra("accountType");
        if (accountType.equals("patient")){
            user = userAccountList.getAccountOfInterest();
            if(userAccountList.activeUserIsCareProvider()){
                Switch editAccountSwitch = findViewById(R.id.editAccountSwitch);
                TextView generateLoginCode = findViewById(R.id.generateLoginCode);
                editAccountSwitch.setVisibility(View.GONE);
                editAccountSwitch.setClickable(false);
                generateLoginCode.setVisibility(View.INVISIBLE);
            }
        }

        else if (accountType.equals("care provider")){
            user = userAccountList.getActiveCareProvider();
        }

        String userID = user.getUserID();
        String userEmailAddress= user.getEmailAddress();
        String userPhoneNumber = user.getPhoneNumber();

        //Set the information for this activity
        TextView userIDView = findViewById(R.id.userIDText);
        EditText emailAddressView = findViewById(R.id.emailAddressText);
        EditText phoneNumberView = findViewById(R.id.phoneNumberText);

        userIDView.setText(userID);
        emailAddressView.setText(userEmailAddress);
        phoneNumberView.setText(userPhoneNumber);

        //For viewing, the fields should not be editable.
        emailAddressView.setEnabled(false);
        phoneNumberView.setEnabled(false);

        Button modifyAccountCancel = findViewById(R.id.modifyAccountCancelButton);
        Button modifyAccountConfirm = findViewById(R.id.modifyAccountConfirmButton);
        modifyAccountCancel.setText("Back");
        modifyAccountConfirm.setVisibility(View.GONE);
        modifyAccountConfirm.setClickable(false);
    }

    public void editingSetup(View v){
        Switch editAccountSwitch = findViewById(R.id.editAccountSwitch);
        EditText emailAddressView = findViewById(R.id.emailAddressText);
        EditText phoneNumberView = findViewById(R.id.phoneNumberText);
        Button modifyAccountConfirm = findViewById(R.id.modifyAccountConfirmButton);
        TextView generateLoginCode = findViewById(R.id.generateLoginCode);

        if(editAccountSwitch.isChecked()){
            emailAddressView.setEnabled(true);
            phoneNumberView.setEnabled(true);
            modifyAccountConfirm.setVisibility(View.VISIBLE);
            modifyAccountConfirm.setClickable(true);
            //generateLoginCode.setVisibility(View.INVISIBLE);
        }

        else{
            emailAddressView.setEnabled(false);
            phoneNumberView.setEnabled(false);
            modifyAccountConfirm.setVisibility(View.GONE);
            modifyAccountConfirm.setClickable(false);
            generateLoginCode.setVisibility(View.VISIBLE);
        }

    }

    public void getLoginCode(View v){
        TextView loginCodeTextView = findViewById(R.id.loginCodeTextView);
        String loginCode;
        if(userAccountList.activeUserIsCareProvider()){
            loginCode = userAccountList.getActiveCareProvider().getShortCode();
        }
        else{
            loginCode = userAccountList.getAccountOfInterest().getShortCode();
        }
        loginCodeTextView.setText(loginCode);
    }

    public void modifyAccountConfirm(View v){
        Toast.makeText(this,"Editing account...", Toast.LENGTH_SHORT).show();
        EditText emailAddressView = findViewById(R.id.emailAddressText);
        EditText phoneNumberView = findViewById(R.id.phoneNumberText);
        String newEmailAddress = emailAddressView.getText().toString();
        String newPhoneNumber = phoneNumberView.getText().toString();

        if(userAccountList.activeUserIsCareProvider()){
            UserAccount oldUserAccount = userAccountList.getActiveCareProvider();
            UserAccount newUserAccount = new UserAccount();
            newUserAccount.setAccountType(oldUserAccount.getAccountType());
            newUserAccount.setUserID(oldUserAccount.getUserID());
            newUserAccount.setEmailAddress(newEmailAddress);
            newUserAccount.setPhoneNumber(newPhoneNumber);
            userAccountListController.editUserAccount(oldUserAccount, newUserAccount);
            userAccountList.getActiveCareProvider().setEmailAddress(newEmailAddress);
            userAccountList.getActiveCareProvider().setPhoneNumber(newPhoneNumber);
        }
        else{
            UserAccount oldUserAccount = userAccountList.getAccountOfInterest();
            UserAccount newUserAccount = new UserAccount();
            newUserAccount.setAccountType(oldUserAccount.getAccountType());
            newUserAccount.setUserID(oldUserAccount.getUserID());
            newUserAccount.setEmailAddress(newEmailAddress);
            newUserAccount.setPhoneNumber(newPhoneNumber);
            userAccountListController.editUserAccount(oldUserAccount, newUserAccount);
            userAccountList.getAccountOfInterest().setEmailAddress(newEmailAddress);
            userAccountList.getAccountOfInterest().setPhoneNumber(newPhoneNumber);
        }
        this.finish();
    }

    public void modifyAccountCancel(View v){
        this.finish();
    }

}
