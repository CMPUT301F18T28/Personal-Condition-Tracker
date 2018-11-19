package ca.ualberta.cs.personal_condition_tracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;

public class ViewPatientListActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private CareProvider activeCareProvider = userAccountListController.getUserAccountList().getActiveCareProvider();
    private String selectedPatientID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient_list);

        //Setup adapter for condition list, and display the list.
        ListView listView = findViewById(R.id.patientListView);
        Collection<String> patientIDCollection = activeCareProvider.getPatientList().getPatientIDs();
        final ArrayList<String> patientIDs = new ArrayList<> (patientIDCollection);
        final ArrayAdapter<String> patientIDArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, patientIDs);
        listView.setAdapter(patientIDArrayAdapter);

        // Added a change observer
        UserAccountListController.getUserAccountList().getActiveCareProvider().getPatientList().addListener(new Listener() {
            @Override
            public void update() {
                patientIDs.clear();
                Collection<String> patientIDCollection = activeCareProvider.getPatientList().getPatientIDs();
                patientIDs.addAll(patientIDCollection);
                patientIDArrayAdapter.notifyDataSetChanged();
            }
        });

        //Ugly code of OnItemLongClickListener
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(ViewPatientListActivity.this);
                adb.setMessage("Would you like remove " + patientIDs.get(position).toString() + "?");
                adb.setCancelable(true);
                final int finalPosition = position;

                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedPatientID= patientIDs.get(finalPosition);
                        activeCareProvider.getPatientList().deletePatient(selectedPatientID);
                    }
                });

                adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing, simply allow the dialog to close
                    }
                });

                adb.show();
                return false;
            }
        });
    }

    public void addPatient(View v){
        Intent intent = new Intent(ViewPatientListActivity.this, AddPatientActivity.class);
        startActivity(intent);
    }

}
