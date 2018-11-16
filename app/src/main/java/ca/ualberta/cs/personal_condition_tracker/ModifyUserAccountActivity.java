package ca.ualberta.cs.personal_condition_tracker;

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
    private UserAccountListController userAccountListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user_account);

        resultIntent = new Intent();

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String emailAddress = intent.getStringExtra("emailAddress");
        String phoneNumber = intent.getStringExtra("phoneNumber");

        EditText userIDText = findViewById(R.id.userIDText);
        EditText emailAddressText = findViewById(R.id.emailAddressText);
        EditText phoneNumberText = findViewById(R.id.phoneNumberText);

        userIDText.setText(userID);
        emailAddressText.setText(emailAddress);
        phoneNumberText.setText(phoneNumber);

        Button confirm_button = (Button) findViewById(R.id.modifyUserAccountConfirmButton);
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                // Get data from EditText fields
                EditText userIDText = (EditText) findViewById(R.id.userIDText);
                String userID = userIDText.getText().toString();
                EditText emailAddressText = (EditText) findViewById(R.id.emailAddressText);
                String emailAddress = emailAddressText.getText().toString();
                EditText phoneNumberText = (EditText) findViewById(R.id.phoneNumberText);
                String phoneNumber = phoneNumberText.getText().toString();
                // Create a new user with inputted data
                UserAccount newUser = new UserAccount();
                newUser.setAccountType("Patient");
                newUser.setUserID(userID);
                newUser.setEmail_address(emailAddress);
                newUser.setPhone_number(phoneNumber);

                // Add the user to the database.
                UserAccountListController.getUserAccountList().addUserAccount(newUser);
                UserAccountListController.AddUserAccountsTask addUserAccountsTask
                        = new UserAccountListController.AddUserAccountsTask();
                addUserAccountsTask.execute(newUser);
                Toast.makeText(ModifyUserAccountActivity.this,Integer.toString(UserAccountListController.getUserAccountList().getUserAccounts().size()), Toast.LENGTH_SHORT).show();
            }
        });

        Button cancel_button = (Button) findViewById(R.id.modifyUserAccountCancelButton);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setResult(RESULT_CANCELED);
                UserAccountListController.GetUserAccountsTask getUserAccountsTask =
                new UserAccountListController.GetUserAccountsTask();
                getUserAccountsTask.execute("");
                try {
                    UserAccountListController.getUserAccountList().setUserAccounts(getUserAccountsTask.get());
                    Toast.makeText(ModifyUserAccountActivity.this,Integer.toString(UserAccountListController.getUserAccountList().getUserAccounts().size()), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                Log.e("Error", "Failed to get the tweets out of the async object.");
                }
            }
        });

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        UserAccountListController.GetUserAccountsTask getUserAccountsTask =
                new UserAccountListController.GetUserAccountsTask();
        getUserAccountsTask.execute("");
        try {
            UserAccountListController.getUserAccountList().setUserAccounts(getUserAccountsTask.get());
            Toast.makeText(this,Integer.toString(UserAccountListController.getUserAccountList().getUserAccounts().size()), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }

    }
}
