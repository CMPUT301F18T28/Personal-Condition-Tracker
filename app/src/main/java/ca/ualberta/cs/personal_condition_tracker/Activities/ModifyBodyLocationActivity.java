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
Redistribution and use in source and binary forms, with or without
modification, are permitted (subject to the limitations in the disclaimer
below) provided that the following conditions are met:
     * Redistributions of source code must retain the above copyright notice,
     this list of conditions and the following disclaimer.
     * Redistributions in binary form must reproduce the above copyright
     notice, this list of conditions and the following disclaimer in the
     documentation and/or other materials provided with the distribution.
     * Neither the name of the copyright holder nor the names of its
     contributors may be used to endorse or promote products derived from this
     software without specific prior written permission.
NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY
THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
*/
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

/**
 * ModifyBodyLocationActivity is responsible for allowing a patient to specific paramters for a body location.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
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
    // Select a body location, set pin location to previous pin location.
    public void selectBodyLocation(View v) {
        Intent intent = new Intent(ModifyBodyLocationActivity.this, SelectBodyLocationActivity.class);
        if (pinX != null) {
            intent.putExtra("previousX", pinX);
            intent.putExtra("previousY", pinY);
        }
        startActivityForResult(intent, SELECTED_BODY_LOCATION_REQUEST_CODE);
    }
    // Commit changes to body location.
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
