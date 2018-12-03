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

        Toast.makeText(this,recordOfInterest.getTitle(), Toast.LENGTH_SHORT).show();

        ListView listView = findViewById(R.id.bodyLocationListView);
        Collection<BodyLocation> bodyLocationCollection = recordOfInterest.getBodyLocationList().getBodyLocations();
        final ArrayList<BodyLocation> bodyLocations = new ArrayList<> (bodyLocationCollection);
        final ArrayAdapter<BodyLocation> bodyLocationsArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bodyLocations);
        listView.setAdapter(bodyLocationsArrayAdapter);

        // Added a change observer
        recordOfInterest.addListener(new Listener() {
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
                adb.setMessage("Would you like to edit or delete " + bodyLocations.get(position).toString() + " ?");
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

                adb.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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

    }
    public void addBodyLocation(View v) {
        Intent intent = new Intent(ViewBodyLocationListActivity.this, ModifyBodyLocationActivity.class);
        intent.putExtra("index", -1);
        startActivityForResult(intent,1);
    }
}
