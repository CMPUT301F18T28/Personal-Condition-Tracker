package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;

public class ViewPatientListActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private CareProvider activeCareProvider = userAccountListController.getUserAccountList().getActiveCareProvider();


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
    }

    public void addPatient(View v){
        Intent intent = new Intent(ViewPatientListActivity.this, AddPatientActivity.class);
        startActivity(intent);
    }

}
