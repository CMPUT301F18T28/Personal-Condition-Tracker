package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewRecordActivity extends Activity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition conditionOfInterest = accountOfInterest.getConditionList().getConditionOfInterest();
    private Record recordOfInterest = conditionOfInterest.getRecordList().getRecordOfInterest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        //Get information from recordOfInterest
        String recordTitle = recordOfInterest.getTitle();
        String recordDate = recordOfInterest.getDate().toString();
        String recordDescription = recordOfInterest.getDescription();

        //Set the information for this activity
        TextView recordTitleView = findViewById(R.id.viewRecordTitle);
        TextView recordDateView = findViewById(R.id.recordDate2View);
        TextView recordDescriptionView = findViewById(R.id.recordDescriptionView2);

        recordTitleView.setText(recordTitle);
        recordDateView.setText(recordDate);
        recordDescriptionView.setText(recordDescription);
    }
    public void viewGeoLocations(View v){
        Toast.makeText(this,"View geo locations", Toast.LENGTH_SHORT).show();
    }

    public void viewBodyLocations(View v){
        Toast.makeText(this,"View body locations", Toast.LENGTH_SHORT).show();
    }

}
