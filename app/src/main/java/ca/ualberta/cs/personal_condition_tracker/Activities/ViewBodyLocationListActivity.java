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

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;
import ca.ualberta.cs.personal_condition_tracker.Controllers.BodyLocationListController;
import ca.ualberta.cs.personal_condition_tracker.Model.Photograph;

import ca.ualberta.cs.personal_condition_tracker.Model.Patient;
import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;
import ca.ualberta.cs.personal_condition_tracker.Model.BodyLocation;
import ca.ualberta.cs.personal_condition_tracker.Model.Listener;
import ca.ualberta.cs.personal_condition_tracker.R;

/**
 * ViewBodyLocationListActivity lets a user view a given body location.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
public class ViewBodyLocationListActivity extends Activity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private BodyLocationListController bodyLocationListController = new BodyLocationListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition conditionOfInterest = accountOfInterest.getConditionList().getConditionOfInterest();
    private Record recordOfInterest = conditionOfInterest.getRecordList().getRecordOfInterest();

    private BodyLocation selectedBodyLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_body_location_list);

        ArrayList<BodyLocation> old_body_locations = bodyLocationListController.loadBodyLocations(recordOfInterest);
        userAccountListController.getUserAccountList().getAccountOfInterest().getConditionList().getConditionOfInterest().getRecordList().getRecordOfInterest().getBodyLocationList().setBodyLocations(old_body_locations);

        ListView listView = findViewById(R.id.bodyLocationListView);
        Collection<BodyLocation> bodyLocationCollection = recordOfInterest.getBodyLocationList().getBodyLocations();
        final ArrayList<BodyLocation> bodyLocations = new ArrayList<> (bodyLocationCollection);
        final ArrayAdapter<BodyLocation> bodyLocationsArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bodyLocations);
        listView.setAdapter(bodyLocationsArrayAdapter);

        // Added a change observer
        recordOfInterest.getBodyLocationList().addListener(new Listener() {
            @Override
            public void update() {
                bodyLocations.clear();
                Collection<BodyLocation> bodyLocationCollection = recordOfInterest.getBodyLocationList().getBodyLocations();
                bodyLocations.addAll(bodyLocationCollection);
                bodyLocationsArrayAdapter.notifyDataSetChanged();
            }
        });

        //Ugly code of OnItemLongClickListener
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(ViewBodyLocationListActivity.this);
                adb.setMessage("Would you like to delete " + bodyLocations.get(position).toString() + " ?");
                adb.setCancelable(true);
                final int finalPosition = position;

                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedBodyLocation = bodyLocations.get(finalPosition);
                        bodyLocationListController.deleteBodyLocation(selectedBodyLocation);
                        recordOfInterest.getBodyLocationList().deleteBodyLocation(selectedBodyLocation);
                    }
                });
                adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing, simply allow the dialog to close
                    }
                });

                adb.show();
                return true;
            }
        });
        //Ugly code of OnItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int finalPosition = position;
                selectedBodyLocation = bodyLocations.get(finalPosition);
                recordOfInterest.getBodyLocationList().setBodyLocationOfInterest(selectedBodyLocation);
                Intent intent = new Intent(ViewBodyLocationListActivity.this,
                        ModifyBodyLocationActivity.class);

                intent.putExtra("bodyLocationIndex",
                        recordOfInterest.getBodyLocationList().getIndex(selectedBodyLocation));
                intent.putExtra("mode", "view");
                intent.putExtra("frontOrBack",
                        selectedBodyLocation.getFrontOrBack());
                intent.putExtra("bodyPartType",
                        selectedBodyLocation.getBodyPart());
                intent.putExtra("X", selectedBodyLocation.getBodyXCoordinate());
                intent.putExtra("Y", selectedBodyLocation.getBodyYCoordinate());
                startActivity(intent);
            }
        });

    }
    // Add body location to the list.
    public void addBodyLocation(View v) {
        Intent intent = new Intent(ViewBodyLocationListActivity.this, ModifyBodyLocationActivity.class);
        intent.putExtra("index", -1);
        startActivityForResult(intent,1);
    }
}
