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
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class ModifyRecordActivity extends AppCompatActivity {
    public static Intent resultIntent;
    private Intent intent;
    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition conditionOfInterest = accountOfInterest.getConditionList().getConditionOfInterest();
    private String pinX;
    private String pinY;

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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pinX = extras.getString("pinX");
            pinY = extras.getString("pinY");
            Toast.makeText(this, pinX, Toast.LENGTH_SHORT).show();
        }




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

        Record oldRecord;
        Record  newRecord = new Record(recordTitle, recordDate, recordDescription, null, null);
        newRecord.setAssociatedConditionID(conditionOfInterest.getId());
        //TODO change these nulls
        if (intent.getIntExtra("recordIndex", -1) == -1){
            createRecord(newRecord);
            conditionOfInterest.getRecordList().addRecord(newRecord);
        }
        else{
            int recordIndex = intent.getIntExtra("recordIndex", 0);
            oldRecord = conditionOfInterest.getRecordList().getRecord(recordIndex);
            editRecord(oldRecord, newRecord);
            oldRecord.editRecord(recordTitle, recordDate, recordDescription, null, null);
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

    // Add a care provider to the server.
    public void createRecord(Record newRecord) {
        // Check if the user has already signed up
        RecordListManager.GetRecordsTask getRecordsTask =
                new RecordListManager.GetRecordsTask();
        String query = "{ \"query\": {\"match\": { \"id\" : \""+ newRecord.getId() +"\" } } }";
        getRecordsTask.execute(query);
        ArrayList<Record> records = new ArrayList<>();
        try {
            records = getRecordsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }

        // Add the user to the database.
        if (records.size() == 0) {
//            UserAccountListController.getUserAccountList().addUserAccount(newCareProvider);
            RecordListManager.AddRecordsTask addRecordsTask
                    = new RecordListManager.AddRecordsTask();
            addRecordsTask.execute(newRecord);
            Toast.makeText(ModifyRecordActivity.this,"Added record successfully!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ModifyRecordActivity.this, "This record already exists!", Toast.LENGTH_SHORT).show();
        }
    }

    public void editRecord(Record oldRecord, Record newRecord) {
        newRecord.setId(oldRecord.getId());
        RecordListManager.DeleteRecordsTask deleteRecordsTask =
                new RecordListManager.DeleteRecordsTask();
        deleteRecordsTask.execute(oldRecord);
        RecordListManager.AddRecordsTask addRecordsTask
                = new RecordListManager.AddRecordsTask();
        addRecordsTask.execute(newRecord);
    }

    public void selectBodyLoc(View v) {
        Toast.makeText(this,"This is working", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ModifyRecordActivity.this, SelectBodyLocationActivity.class);
        if (pinX != null){
            intent.putExtra("previousX", pinX);
            intent.putExtra("previousY", pinY);
        }
        startActivity(intent);

    }

}
