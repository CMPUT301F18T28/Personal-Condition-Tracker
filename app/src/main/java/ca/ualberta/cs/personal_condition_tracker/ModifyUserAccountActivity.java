package ca.ualberta.cs.personal_condition_tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModifyUserAccountActivity extends AppCompatActivity {
    public static Intent resultIntent;
    private UserAccountListController userAccountListController = new UserAccountListController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user_account);
        resultIntent = new Intent();

        //Get information from the intent
        Intent intent = getIntent();
        String accountType = intent.getStringExtra("accountType");
        String userID = intent.getStringExtra("userID");
        String emailAddress = intent.getStringExtra("emailAddress");
        String phoneNumber = intent.getStringExtra("phoneNumber");

        //Set the information for this activity
        EditText accountTypeDropdown = findViewById(R.id.accountTypeDropdown);
        EditText userIDText = findViewById(R.id.userIDText);
        EditText emailAddressText = findViewById(R.id.emailAddressText);
        EditText phoneNumberText = findViewById(R.id.phoneNumberText);

        accountTypeDropdown.setText(accountType);
        userIDText.setText(userID);
        emailAddressText.setText(emailAddress);
        phoneNumberText.setText(phoneNumber);
    }
    public void confirmAccountEdit(View v){
        //TODO
        //Check to see if the account exists
        //If it does not, we can create the account ( assuming input criteria is valid ).
        //If it does:
        //and we are trying to sign up, we need a different ..id?
        //and we are tying to edit, make the changes. ( assuming input criteria is valid ).
        //Currently just using this for creation.
        Toast.makeText(this,"Confirming account edit", Toast.LENGTH_SHORT).show();
        EditText accountTypeDropdown = findViewById(R.id.accountTypeDropdown);
        EditText userIDText = findViewById(R.id.userIDText);
        EditText emailAddressText = findViewById(R.id.emailAddressText);
        EditText phoneNumberText = findViewById(R.id.phoneNumberText);

        String accountType = accountTypeDropdown.getText().toString().toLowerCase().trim();
        String userID = userIDText.getText().toString();
        String emailAddress = emailAddressText.getText().toString();
        String phoneNumber = phoneNumberText.getText().toString();


        //TODO fix this. maybe w/ dropdown
        if(accountType.equals("patient")){
            Toast.makeText(this,"Making Patient", Toast.LENGTH_SHORT).show();
            Patient newUserAccount = new Patient(accountType, userID, emailAddress, "password");
            newUserAccount.setPhone_number(phoneNumber);
            System.out.println(newUserAccount.getId() + " " + newUserAccount.getPassword());
            userAccountListController.addUserAccount(newUserAccount);
            this.finish();
        }

        else if (accountType.equals("care provider")){
            Toast.makeText(this,"Making Care Provider", Toast.LENGTH_SHORT).show();
            CareProvider newUserAccount = new CareProvider(accountType, userID, emailAddress, "password");
            newUserAccount.setPhone_number(phoneNumber);
            System.out.println(newUserAccount.getId() + " " + newUserAccount.getPassword());
            userAccountListController.addUserAccount(newUserAccount);
            this.finish();
        }

        else {
            Toast.makeText(this, "Invalid account type", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelAccountEdit(View v){
        Toast.makeText(this,"Cancelling edit...", Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_CANCELED, resultIntent);
        this.finish();
    }
// This all belongs right below where all the files are "set", inside the onCreate

//        Button confirm_button = (Button) findViewById(R.id.modifyUserAccountConfirmButton);
//
//        confirm_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                setResult(RESULT_OK);
//                // Get data from EditText fields
//                EditText userIDText = (EditText) findViewById(R.id.userIDText);
//                String userID = userIDText.getText().toString();
//                EditText emailAddressText = (EditText) findViewById(R.id.emailAddressText);
//                String emailAddress = emailAddressText.getText().toString();
//                EditText phoneNumberText = (EditText) findViewById(R.id.phoneNumberText);
//                String phoneNumber = phoneNumberText.getText().toString();
//                // Create a new user with inputted data
//                Patient newUser = new Patient();
//                newUser.setAccountType("Patient");
//                newUser.setUserID(userID);
//                newUser.setEmail_address(emailAddress);
//                newUser.setPhone_number(phoneNumber);
//
//                // Check if the user has already signed up
//                UserAccountListController.GetUserAccountsTask getUserAccountsTask =
//                        new UserAccountListController.GetUserAccountsTask();
//                String query = "{ \"query\": {\"match\": { \"userID\" : \""+ userID +"\" } } }";
//                getUserAccountsTask.execute(query);
//                ArrayList<? extends UserAccount> stored_users = new ArrayList<UserAccount>();
//                try {
//                    stored_users = getUserAccountsTask.get();
//                } catch (Exception e) {
//                    Log.e("Error", "Failed to get the tweets out of the async object.");
//                }
//
//                // Add the user to the database.
//                if (stored_users.size() == 0) {
//                    UserAccountListController.getUserAccountList().addUserAccount(newUser);
//                    UserAccountListController.AddUserAccountsTask addUserAccountsTask
//                            = new UserAccountListController.AddUserAccountsTask();
//                    addUserAccountsTask.execute(newUser);
//                    Toast.makeText(ModifyUserAccountActivity.this,"Sign up successful!", Toast.LENGTH_SHORT).show();
//                    ModifyUserAccountActivity.this.finish();
//                }
//                else {
//                    Toast.makeText(ModifyUserAccountActivity.this, "This userID already exists!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        Button cancel_button = (Button) findViewById(R.id.modifyUserAccountCancelButton);
//        cancel_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                setResult(RESULT_CANCELED);
//                ModifyUserAccountActivity.this.finish();
//            }
//        });

//    @Override
//    protected void onStart() {
//        // TODO Auto-generated method stub
//        super.onStart();
//        UserAccountListController.GetUserAccountsTask getUserAccountsTask =
//                new UserAccountListController.GetUserAccountsTask();
//        getUserAccountsTask.execute("");
//        try {
//            UserAccountListController.getUserAccountList().setUserAccounts(getUserAccountsTask.get());
//            Toast.makeText(this,Integer.toString(UserAccountListController.getUserAccountList().getUserAccounts().size()), Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Log.e("Error", "Failed to get the tweets out of the async object.");
//        }
//
//    }
}
