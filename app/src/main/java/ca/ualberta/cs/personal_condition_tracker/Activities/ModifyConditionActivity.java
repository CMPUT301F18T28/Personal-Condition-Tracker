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

package ca.ualberta.cs.personal_condition_tracker.Activities;

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
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Managers.ConditionListManager;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;
import ca.ualberta.cs.personal_condition_tracker.R;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;

public class ModifyConditionActivity extends AppCompatActivity {
    public static Intent resultIntent;
    private Intent intent;
    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition selectedCondition;
    private int year, month, day, hour, minute, second;
    private Date new_date = new Date();

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
        TextView conditionDateView = findViewById(R.id.conditionDateView);
        EditText conditionDescriptionView = findViewById(R.id.conditionDescriptionView);

        conditionTitleView.setText(conditionTitle);
        conditionDateView.setText(conditionDate);
        conditionDescriptionView.setText(conditionDescription);
    }

    public void modifyConditionConfirm(View v){
        //TODO fix dating, ensure working for edits
        Toast.makeText(this,"Confirming condition edit...", Toast.LENGTH_SHORT).show();
        EditText conditionTitleView = findViewById(R.id.conditionTitleView);
        TextView conditionDateView = findViewById(R.id.conditionDateView);
        EditText conditionDescriptionView = findViewById(R.id.conditionDescriptionView);

        String conditionTitle = conditionTitleView.getText().toString();
        Date conditionDate = new_date;
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

    public void modifyConditionDate(View v) {
        final Calendar c = Calendar.getInstance();
        // Initialize values for year, month, day, hour, minute, and second.
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        // Set up a date picker dialog to let the user select the new day of the year.
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selected_year, int month_of_year, int day_of_month) {
                year = selected_year;
                month = month_of_year;
                day = day_of_month;
                // After the day has been selected, set up a time picker dialog to let the user select the new time.
                TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyConditionActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour_of_day,
                                          int selected_minute) {
                        hour = hour_of_day;
                        minute = selected_minute;
                        GregorianCalendar new_gregorian_calendar = new GregorianCalendar(year, month, day, hour, minute, second);
                        new_date = new_gregorian_calendar.getTime();
                        // Update the emotion record and change the date shown to the user.
                        TextView conditionDateView = findViewById(R.id.conditionDateView);
                        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z"	);
                        String conditionDate = df.format(new_date);
                        conditionDateView.setText(conditionDate);

                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        }, year, month, day);
        datePickerDialog.show();
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
