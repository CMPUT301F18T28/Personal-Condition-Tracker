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
*/

package ca.ualberta.cs.personal_condition_tracker;

/**
 * MainActivity is responsible for allowing a user to choose between logging into the app and signing up.
 * This activity performs verification to ensure that the user who is logging in exists in the database,
 * and that their inputted user ID is verifiable.
 * @author  R. Voon; rcvoon@ualberta.ca
 * @author  D. Buksa; draydon@ualberta.ca
 * @author  W. Nichols; wnichols@ualberta.ca
 * @author  D. Douziech; douziech@ualberta.ca
 * @author  C. Neureuter; neureute@ualberta.ca
 * @version 1.1, 11-18-18
 * @since   1.0
 */


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userAccountListController.getUserAccountList();
    }

    @Override
    public void onResume(){
        super.onResume();
        clearText();
        clearAccounts();
    }


    /**
     * This method handles the onClick of the "Sign In" button.
     *
     * @param v the view of the button pressed
     */
    public void signIn(View v){

        //Get Sign in form data
        EditText userIdEntry = findViewById(R.id.userIDEntry);

        String userId = userIdEntry.getText().toString();
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
        //TODO Adjust for real ID/PW. maybe use size of UAL?
        for(UserAccount userAccount : userAccountList){
            if(userAccount.authenticate(userId)){

                //Check account type, direct to proper activity.
                if(userAccount.getAccountType().toLowerCase().trim().equals("patient")){
                    Patient newPatient = new Patient(userAccount.getAccountType(), userAccount.getUserID(), userAccount.getEmailAddress(), userAccount.getPhoneNumber());
                    userAccountListController.getUserAccountList().setAccountOfInterest(newPatient);
                    intent = new Intent(MainActivity.this, ViewConditionListActivity.class);
                    startActivity(intent);
                }

                else if(userAccount.getAccountType().toLowerCase().trim().equals("care provider")){
                    CareProvider newCareProvider = new CareProvider(userAccount.getAccountType(), userAccount.getUserID(), userAccount.getEmailAddress(), userAccount.getPhoneNumber());
                    userAccountListController.getUserAccountList().setActiveCareProvider(newCareProvider);
                    intent = new Intent(MainActivity.this, ViewPatientListActivity.class);
                    startActivity(intent);
                }
            }
        }
        if(intent == null) {
            Toast.makeText(this, "Incorrect Username", Toast.LENGTH_SHORT).show();
        }
    }

     /**
     * This method handles the onClick of the "Sign Up" button.
     *
     * @param  v the view of the button pressed
     */
    public void signUp(View v){
        Toast.makeText(this,"Signing up", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    // Clear the text from the username field.
    public void clearText(){
        EditText userIdEntry = findViewById(R.id.userIDEntry);
        userIdEntry.getText().clear();
    }

    private void clearAccounts() {
        userAccountListController.getUserAccountList().setAccountOfInterest(null);
        userAccountListController.getUserAccountList().setActiveCareProvider(null);
    }

}
