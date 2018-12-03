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
    private String pinX;
    private String pinY;
    private static final int SELECTED_BODY_LOCATION_REQUEST_CODE = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_body_location);

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
        //TODO fix dating, ensure working for edits add in Geo/body locations
        Toast.makeText(this, "Confirming record edit...", Toast.LENGTH_SHORT).show();
        Spinner frontOrBackSpinner = findViewById(R.id.frontOrBackSpinner);
        Spinner bodyPartTypeSpinner = findViewById(R.id.bodyPartTypeSpinner);
        String frontOrBack = frontOrBackSpinner.getSelectedItem().toString().toLowerCase().trim();
        String bodyPartType = bodyPartTypeSpinner.getSelectedItem().toString().toLowerCase().trim();

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
