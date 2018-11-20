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

package ca.ualberta.cs.personal_condition_tracker;

/**
 * ViewPatientListActivity is responsible for allowing a care provider to see all their assigned patients.
 *
 * @author    R. Voon; rcvoon@ualberta.ca
 *            D. Buksa; draydon@ualberta.ca
 *            W. Nichols; wnichols@ualberta.ca
 *            D. Douziech; douziech@ualberta.ca
 *            C. Neureuter; neureute@ualberta.ca
 * @version     1.1, 11-18-18
 * @since       1.0
 */


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class ViewPatientListActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private CareProvider activeCareProvider = userAccountListController.getUserAccountList().getActiveCareProvider();
    private String selectedPatientID;
    private UserAccount accountOfInterest;


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

        //Ugly code of OnItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int finalPosition = position;
                selectedPatientID = patientIDs.get(finalPosition);
                Toast.makeText(ViewPatientListActivity.this,selectedPatientID, Toast.LENGTH_SHORT).show();
                userAccountListController.getUserAccountList().setUserAccounts(getUserAccounts());
                accountOfInterest =
                        userAccountListController.getUserAccountList().getPatientAccountByID(selectedPatientID);
                Patient newPatient = new Patient(accountOfInterest.getAccountType(), accountOfInterest.getUserID(), accountOfInterest.getEmail_address(), accountOfInterest.getPassword());
                userAccountListController.getUserAccountList().setAccountOfInterest(newPatient);

                Intent intent = new Intent(ViewPatientListActivity.this,
                        ViewConditionListAsCareProviderActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addPatient(View v){
        Intent intent = new Intent(ViewPatientListActivity.this, AddPatientActivity.class);
        startActivity(intent);
    }

    public ArrayList<UserAccount> getUserAccounts() {
        UserAccountListManager.GetUserAccountsTask getUserAccountsTask =
                new UserAccountListManager.GetUserAccountsTask();
        getUserAccountsTask.execute("");
        ArrayList<UserAccount> stored_users = new ArrayList<>();
        try {
            stored_users = getUserAccountsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        return stored_users;
    }

}
