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
 * ModifyConditionActivity allows a patient to specify the fields for a condition when
 * a condition is to be added or editted.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class ModifyConditionActivity extends AppCompatActivity {
    public static Intent resultIntent;
    private Intent intent;
    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition selectedCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_condition);
        resultIntent = new Intent();

        //Get information from the intent
        intent = getIntent();
        String conditionTitle = intent.getStringExtra("conditionTitle");
        String conditionDate = intent.getStringExtra("conditionDate");
        String conditionDescription = intent.getStringExtra("conditionDescription");


        //Set the information for this activity
        EditText conditionTitleView = findViewById(R.id.conditionTitleView);
        EditText conditionDateView = findViewById(R.id.conditionDateView);
        EditText conditionDescriptionView = findViewById(R.id.conditionDescriptionView);

        conditionTitleView.setText(conditionTitle);
        conditionDateView.setText(conditionDate);
        conditionDescriptionView.setText(conditionDescription);
    }

    public void modifyConditionConfirm(View v){
        //TODO fix dating, ensure working for edits
        Toast.makeText(this,"Confirming condition edit...", Toast.LENGTH_SHORT).show();
        EditText conditionTitleView = findViewById(R.id.conditionTitleView);
        EditText conditionDateView = findViewById(R.id.conditionDateView);
        EditText conditionDescriptionView = findViewById(R.id.conditionDescriptionView);

        String conditionTitle = conditionTitleView.getText().toString();
        Date conditionDate = new Date();
        String conditionDescription = conditionDescriptionView.getText().toString();
        Condition oldCondition;
        Condition newCondition = new Condition(conditionTitle, conditionDate, conditionDescription);
        newCondition.setAssociatedUserID(accountOfInterest.getUserID());

        if (intent.getIntExtra("index", -1) == -1){
            createCondition(newCondition);
            accountOfInterest.getConditionList().addCondition(newCondition);
        }
        else{
            int index = intent.getIntExtra("index", 0);
            oldCondition = accountOfInterest.getConditionList().getByIndex(index);
            editCondition(oldCondition, newCondition);
            accountOfInterest.getConditionList().editCondition(oldCondition, conditionTitle,
                    conditionDate, conditionDescription);
        }
        setResult(Activity.RESULT_OK);
        this.finish();
    }
    // Cancel adding or editing a condition.
    public void modifyConditionCancel(View v){
        Toast.makeText(this,"Cancelling condition edit...", Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_CANCELED);
        this.finish();
    }

    // Add a care provider to the server.
    public void createCondition(Condition newCondition) {
        // Check if the user has already signed up
        ConditionListManager.GetConditionsTask getConditionsTask =
                new ConditionListManager.GetConditionsTask();
        String query = "{ \"query\": {\"match\": { \"id\" : \""+ newCondition.getId() +"\" } } }";
        getConditionsTask.execute(query);
        ArrayList<Condition> conditions = new ArrayList<>();
        try {
            conditions = getConditionsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }

        // Add the user to the database.
        if (conditions.size() == 0) {
//            UserAccountListController.getUserAccountList().addUserAccount(newCareProvider);
            ConditionListManager.AddConditionsTask addConditionsTask
                    = new ConditionListManager.AddConditionsTask();
            addConditionsTask.execute(newCondition);
            Toast.makeText(ModifyConditionActivity.this,"Sign up successful!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ModifyConditionActivity.this, "This condition already exists!", Toast.LENGTH_SHORT).show();
        }
    }

    public void editCondition(Condition oldCondition, Condition newCondition) {
        newCondition.setId(oldCondition.getId());
        ConditionListManager.DeleteConditionsTask deleteConditionsTask =
                new ConditionListManager.DeleteConditionsTask();
        deleteConditionsTask.execute(oldCondition);
        ConditionListManager.AddConditionsTask addConditionsTask
                = new ConditionListManager.AddConditionsTask();
        addConditionsTask.execute(newCondition);
    }

}
