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
 * ModifyRecordActivity allows a patient to input the fields for a record when a record is to be
 * added or editted.
 *
 * @author       R. Voon; rcvoon@ualberta.ca
 *               D. Buksa; draydon@ualberta.ca
 *               W. Nichols; wnichols@ualberta.ca
 *               D. Douziech; douziech@ualberta.ca
 *               C. Neureuter; neureute@ualberta.ca
 * @version     1.1, 11-18-18
 * @since       1.0
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class ModifyRecordActivity extends AppCompatActivity {
    public static Intent resultIntent;
    private Intent intent;
    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition conditionOfInterest = accountOfInterest.getConditionList().getConditionOfInterest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_record);
        resultIntent = new Intent();

        //Get information from the intent
        intent = getIntent();
        String recordTitle = intent.getStringExtra("recordTitle");
        String recordDate = intent.getStringExtra("recordDate");
        String recordDescription = intent.getStringExtra("recordDescription");


        //Set the information for this activity
        EditText recordTitleView = findViewById(R.id.recordTitleView);
        EditText recordDateView = findViewById(R.id.recordDateView);
        EditText recordDescriptionView = findViewById(R.id.recordDescriptionView);

        recordTitleView.setText(recordTitle);
        recordDateView.setText(recordDate);
        recordDescriptionView.setText(recordDescription);
    }

    public void modifyRecordConfirm(View v){
        //TODO fix dating, ensure working for edits add in Geo/body locations
        Toast.makeText(this,"Confirming record edit...", Toast.LENGTH_SHORT).show();
        EditText recordTitleView = findViewById(R.id.recordTitleView);
        EditText recordDateView = findViewById(R.id.recordDateView);
        EditText recordDescriptionView = findViewById(R.id.recordDescriptionView);

        String recordTitle = recordTitleView.getText().toString();
        Date recordDate = new Date();
        String recordDescription = recordDescriptionView.getText().toString();

        Record newRecord;

        //TODO change these nulls
        if (intent.getIntExtra("recordIndex", -1) == -1){
            newRecord = new Record(recordTitle, recordDate, recordDescription, null, null);
            conditionOfInterest.getRecordList().addRecord(newRecord);
        }
        else{
            int recordIndex = intent.getIntExtra("recordIndex", 0);
            newRecord = conditionOfInterest.getRecordList().getRecord(recordIndex);

            newRecord.editRecord(recordTitle, recordDate, recordDescription, null, null);
        }
        setResult(Activity.RESULT_OK);
        this.finish();
    }
    // Cancel adding or editting a record.
    public void modifyRecordCancel(View v){
        Toast.makeText(this,"Cancelling record edit...", Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_CANCELED);
        this.finish();
    }



}
