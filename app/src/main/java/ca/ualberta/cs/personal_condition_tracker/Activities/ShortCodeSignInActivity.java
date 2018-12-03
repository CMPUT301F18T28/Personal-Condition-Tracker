package ca.ualberta.cs.personal_condition_tracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import ca.ualberta.cs.personal_condition_tracker.Model.CareProvider;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;
import ca.ualberta.cs.personal_condition_tracker.R;
import ca.ualberta.cs.personal_condition_tracker.Model.UserAccount;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;
import ca.ualberta.cs.personal_condition_tracker.Managers.UserAccountListManager;

public class ShortCodeSignInActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_code_sign_in);
    }

    public void acceptShortCodeSignIn(View v){
        //Get Sign in form data
        EditText shortCodeEditText = findViewById(R.id.signInCodeEditText);

        String shortCode = shortCodeEditText.getText().toString().trim();
        Intent intent = null;

        UserAccountListManager.GetUserAccountsTask getUserAccountsTask =
                new UserAccountListManager.GetUserAccountsTask();
        getUserAccountsTask.execute("");
        ArrayList<UserAccount> userAccountList = new ArrayList<>();
        try {
            userAccountList = getUserAccountsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        userAccountListController.getUserAccountList().setUserAccounts(userAccountList);
        //TODO Refactor this so it is handled by a controller.
        for(UserAccount userAccount : userAccountList){
            if(userAccount.authenticateByShortCode(shortCode)){

                //Check account type, direct to proper activity.
                if(userAccount.getAccountType().toLowerCase().trim().equals("patient")){
                    Patient newPatient = new Patient(userAccount.getAccountType(), userAccount.getUserID(), userAccount.getEmailAddress(), userAccount.getPhoneNumber());
                    newPatient.setShortCode(userAccount.getShortCode());
                    userAccountListController.getUserAccountList().setAccountOfInterest(newPatient);
                    intent = new Intent(ShortCodeSignInActivity.this, ViewConditionListActivity.class);
                    startActivity(intent);
                    this.finish();
                }

                else if(userAccount.getAccountType().toLowerCase().trim().equals("care provider")){
                    CareProvider newCareProvider = new CareProvider(userAccount.getAccountType(), userAccount.getUserID(), userAccount.getEmailAddress(), userAccount.getPhoneNumber());
                    newCareProvider.setShortCode(userAccount.getShortCode());
                    userAccountListController.getUserAccountList().setActiveCareProvider(newCareProvider);
                    intent = new Intent(ShortCodeSignInActivity.this, ViewPatientListActivity.class);
                    startActivity(intent);
                    this.finish();
                }
            }
        }
        if(intent == null) {
            Toast.makeText(this, "Incorrect Code", Toast.LENGTH_SHORT).show();
        }

    }

    public void cancelShortCodeSignIn(View v){
        this.finish();
    }
}
