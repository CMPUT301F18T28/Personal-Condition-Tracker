package ca.ualberta.cs.personal_condition_tracker.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;
import ca.ualberta.cs.personal_condition_tracker.Controllers.BodyLocationListController;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;
import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.BodyLocation;
import ca.ualberta.cs.personal_condition_tracker.R;


public class ModifyBodyLocationActivity extends Activity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private BodyLocationListController bodyLocationListController = new BodyLocationListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition conditionOfInterest = accountOfInterest.getConditionList().getConditionOfInterest();
    private Record recordOfInterest = conditionOfInterest.getRecordList().getRecordOfInterest();

    private Intent intent;
    private String pinX = "0";
    private String pinY = "0";
    private static final int SELECTED_BODY_LOCATION_REQUEST_CODE = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_body_location);
        intent = getIntent();
        String frontOrBack = intent.getStringExtra("frontOrBack");
        String bodyPartType = intent.getStringExtra("bodyPartType");
        pinX = intent.getStringExtra("X");
        pinY = intent.getStringExtra("Y");
        Spinner frontOrBackSpinner = findViewById(R.id.frontOrBackSpinner);
        Spinner bodyPartTypeSpinner = findViewById(R.id.bodyPartTypeSpinner);
        int selection = 0;
        if (frontOrBack != null) {
            if (frontOrBack.equals("Back")) {
                selection = 1;
            }
        }
        frontOrBackSpinner.setSelection(selection);
        if (bodyPartType != null) {
            if (bodyPartType.equals("Chest")) {
                selection = 1;
            } else if (bodyPartType.equals("Left Arm")) {
                selection = 2;
            } else if (bodyPartType.equals("Right Arm")) {
                selection = 3;
            } else if (bodyPartType.equals("Left Leg")) {
                selection = 4;
            } else if (bodyPartType.equals("Right Leg")) {
                selection = 5;
            }
        }
        bodyPartTypeSpinner.setSelection(selection);

    }

    public void selectBodyLocation(View v) {
        Intent intent = new Intent(ModifyBodyLocationActivity.this, SelectBodyLocationActivity.class);
        if (pinX != null) {
            intent.putExtra("previousX", pinX);
            intent.putExtra("previousY", pinY);
        }
        startActivityForResult(intent, SELECTED_BODY_LOCATION_REQUEST_CODE);
    }

    public void modifyBodyLocationConfirm(View v) {
        Spinner frontOrBackSpinner = findViewById(R.id.frontOrBackSpinner);
        Spinner bodyPartTypeSpinner = findViewById(R.id.bodyPartTypeSpinner);
        String frontOrBack = frontOrBackSpinner.getSelectedItem().toString();
        String bodyPartType = bodyPartTypeSpinner.getSelectedItem().toString();

        if (pinX != null && pinY != null) {
            BodyLocation oldBodyLocation;
            BodyLocation newBodyLocation = new BodyLocation();
            newBodyLocation.setFrontOrBack(frontOrBack);
            newBodyLocation.setBodyPart(bodyPartType);
            newBodyLocation.setBodyXCoordinate(pinX);
            newBodyLocation.setBodyYCoordinate(pinY);
            newBodyLocation.setAssociatedRecordID(recordOfInterest.getId());
            bodyLocationListController.createBodyLocation(newBodyLocation);
            recordOfInterest.getBodyLocationList().addBodyLocation(newBodyLocation);

            setResult(Activity.RESULT_OK);
            this.finish();
        }
        else {
            Toast.makeText(this, "Please select a body location!", Toast.LENGTH_SHORT).show();
        }
    }

    // Cancel adding or editting a record.
    public void modifyBodyLocationCancel(View v) {
        setResult(Activity.RESULT_CANCELED);
        this.finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == SELECTED_BODY_LOCATION_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                pinX = data.getStringExtra("pinX");
                pinY = data.getStringExtra("pinY");
            }
        }
    }

}
