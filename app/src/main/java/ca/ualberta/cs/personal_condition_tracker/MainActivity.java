package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userAccountListController.getUserAccountList();
        initializeTestAccounts();
    }
    @Override
    public void onResume(){
        super.onResume();
        clearText();
    }

    /**
     * This method handles the onClick of the "Sign In" button.
     *
     * @param v the view of the button pressed
     */
    public void signIn(View v){
        Toast.makeText(this,"Signing in", Toast.LENGTH_SHORT).show();
        /* if (authenticate()){
                Check user type, send them to correct activity.
                }
                else{displayError()}
         */
        //Get Sign in form data
        EditText userIdEntry = findViewById(R.id.userIDEntry);
        EditText passwordEntry = findViewById(R.id.passwordEntry);

        String userId = userIdEntry.getText().toString();
        String password = passwordEntry.getText().toString();
        Intent intent = null;
//        ArrayList<UserAccount> userAccountList = UserAccountListController.getUserAccountList().getUserAccounts();
        UserAccountListController.GetUserAccountsTask getUserAccountsTask =
                new UserAccountListController.GetUserAccountsTask();
        String query = "{ \"query\": {\"match\": { \"userID\" : \""+ userId +"\" } } }";
        getUserAccountsTask.execute(query);
        ArrayList<UserAccount> userAccountList = new ArrayList<UserAccount>();
        try {
            userAccountList = getUserAccountsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        //TODO Adjust for real ID/PW. maybe use size of UAL?
        for(UserAccount userAccount : userAccountList){
//            System.out.println(userAccount.getId() + " " + userAccount.getPassword());
            Toast.makeText(this,Integer.toString(userAccountList.size()), Toast.LENGTH_SHORT).show();

            if(userAccount.authenticate(userId, password)){
                Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show();

                //Check account type, direct to proper activity.
                if(userAccount.getAccountType().toLowerCase().trim().equals("patient")){
                    userAccountListController.getUserAccountList().setAccountOfInterest((Patient) userAccount);
                    intent = new Intent(MainActivity.this, ViewConditionListActivity.class);
                    startActivity(intent);
                }

                else if(userAccount.getAccountType().toLowerCase().trim().equals("care provider")){
                    userAccountListController.getUserAccountList().setActiveCareProvider((CareProvider) userAccount);
                    intent = new Intent(MainActivity.this, ViewPatientListActivity.class);
                    startActivity(intent);
                }
            }
        }

        if(intent == null) {
            Toast.makeText(this, "Incorrect login information", Toast.LENGTH_SHORT).show();
        }
    }

     /**
     * This method handles the onClick of the "Sign Up" button.
     *
     * @param  v the view of the button pressed
     */
    public void signUp(View v){
        Toast.makeText(this,"Signing up", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, ModifyUserAccountActivity.class);
        startActivity(intent);
    }

    public void clearText(){
        EditText userIdEntry = findViewById(R.id.userIDEntry);
        EditText passwordEntry = findViewById(R.id.passwordEntry);
        userIdEntry.getText().clear();
        passwordEntry.getText().clear();
    }

    public void initializeTestAccounts(){
        CareProvider testProvider = new CareProvider("Care Provider", "testCP","","password");
        Patient testPatient = new Patient("Patient", "testP","","password");
        Condition testCondition = new Condition("ConditionTitle", new Date(), "ConditionDescription", new RecordList(), null);
        Record testRecord = new Record("recordTitle", new Date(), "recordDescription", null, null);
        testCondition.getRecordList().addRecord(testRecord);
        testPatient.getConditionList().addCondition(testCondition);
        testProvider.getPatientList().addPatient(testPatient.getUserID());

        userAccountListController.getUserAccountList().addUserAccount(testProvider);
        userAccountListController.getUserAccountList().addUserAccount(testPatient);
        userAccountListController.getUserAccountList().setAccountOfInterest(testPatient);
        userAccountListController.getUserAccountList().setActiveCareProvider(testProvider);
    }
}
