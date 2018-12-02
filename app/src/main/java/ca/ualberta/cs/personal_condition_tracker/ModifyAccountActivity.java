package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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
                editAccountSwitch.setVisibility(View.GONE);
                editAccountSwitch.setClickable(false);
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
        Button modifyAccountCancel = findViewById(R.id.modifyAccountCancelButton);
        Button modifyAccountConfirm = findViewById(R.id.modifyAccountConfirmButton);

        if(editAccountSwitch.isChecked()){
            emailAddressView.setEnabled(true);
            phoneNumberView.setEnabled(true);
            modifyAccountConfirm.setVisibility(View.VISIBLE);
            modifyAccountConfirm.setClickable(true);
        }
        else{
            emailAddressView.setEnabled(false);
            phoneNumberView.setEnabled(false);
            modifyAccountConfirm.setVisibility(View.GONE);
            modifyAccountConfirm.setClickable(false);
        }

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
