package ca.ualberta.cs.personal_condition_tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class ModifyConditionActivity extends AppCompatActivity {
    public static Intent resultIntent;
    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountofInterest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_condition);
        resultIntent = new Intent();

        //Get information from the intent
        Intent intent = getIntent();
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

        Condition newCondition = new Condition(conditionTitle, conditionDate, conditionDescription);

        accountOfInterest.getConditionList().addCondition(newCondition);
        setResult(Activity.RESULT_OK);
        this.finish();
    }

    public void modifyConditionCancel(View v){
        Toast.makeText(this,"Cancelling condition edit...", Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_CANCELED);
        this.finish();
    }
}
