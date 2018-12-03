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

/**
 * ViewConditionListAsCareProviderActivity is responsible for allowing a care provider to view
 * all the conditions of a patient from their patient list.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

import ca.ualberta.cs.personal_condition_tracker.Controllers.ConditionListController;
import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.Listener;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;
import ca.ualberta.cs.personal_condition_tracker.R;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;

public class ViewConditionListAsCareProviderActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private ConditionListController conditionListController = new ConditionListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition selectedCondition;

    private String keywords = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_condition_list_as_care_provider);

        ArrayList<Condition> old_conditions = conditionListController.loadConditions(accountOfInterest);
        userAccountListController.getUserAccountList().getAccountOfInterest().getConditionList().setConditions(old_conditions);

        final Patient accountOfInterest = UserAccountListController.getUserAccountList().getAccountOfInterest();
        TextView patientName = findViewById(R.id.patientNameTextView);
        patientName.setText(accountOfInterest.getUserID());

        //Setup adapter for condition list, and display the list.
        ListView listView = findViewById(R.id.conditionListView);
        Collection<Condition> conditionCollection = accountOfInterest.getConditionList().getConditions();
        final ArrayList<Condition> conditions = new ArrayList<> (conditionCollection);
        final ArrayAdapter<Condition> conditionArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, conditions);
        listView.setAdapter(conditionArrayAdapter);

        //Ugly code of OnItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int finalPosition = position;
                selectedCondition = conditions.get(finalPosition);
                accountOfInterest.getConditionList().setConditionOfInterest(selectedCondition);
                Intent intent = new Intent(ViewConditionListAsCareProviderActivity.this,
                        ViewRecordListAsCareProviderActivity.class);

                intent.putExtra("conditionIndex",
                        accountOfInterest.getConditionList().getIndex(selectedCondition));
                startActivity(intent);
            }
        });
    }
    // View all records with geolocations on a map.
    public void viewMapOfRecords(View v){
        Toast.makeText(this,"Viewing map of records", Toast.LENGTH_SHORT).show();
        Intent mapIntent = new Intent(ViewConditionListAsCareProviderActivity.this, MapsActivity.class);
        mapIntent.putExtra("mapMode", "viewAll");
        startActivityForResult(mapIntent, 1);
    }
    // Repopulate the list of conditions with conditions that meet the search criteria.
    public void searchConditionsOrRecords(View v){
        AlertDialog.Builder choose_search_type_adb = new AlertDialog.Builder(ViewConditionListAsCareProviderActivity.this);
        choose_search_type_adb.setTitle("Search by:");
        CharSequence[] emotions = new CharSequence[] {"Keywords", "Geo-Location", "Body-Location"};
        // Set up the dialog builder for the pop-up and instantiate a new emotion depending on which button is pressed.
        // Update the emotion field and save the data after a new selection is made.
        choose_search_type_adb.setSingleChoiceItems(emotions, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    searchByKeywords();
                }
                else if (which == 1) {

                }
                else if (which == 2) {

                }
                dialog.dismiss();
            }
        });
        choose_search_type_adb.setCancelable(true);
        AlertDialog choose_search_type_dialog = choose_search_type_adb.create();
        choose_search_type_dialog.show();

    }
    // Display account details and contact information.
    public void showAccountInformation(View v){
        Intent intent = new Intent(ViewConditionListAsCareProviderActivity.this, ModifyAccountActivity.class);
        intent.putExtra("accountType", "patient");
        startActivity(intent);
    }
    // Get conditions from the server that have the inputted keywords.
    public void searchByKeywords(){
        AlertDialog.Builder enter_keywords_adb = new AlertDialog.Builder(this);
        final EditText new_comment_input = new EditText(this);
        new_comment_input.setInputType(InputType.TYPE_CLASS_TEXT);
        new_comment_input.setText(keywords);
        enter_keywords_adb.setTitle("Enter your keywords below:");
        enter_keywords_adb.setView(new_comment_input);
        enter_keywords_adb.setCancelable(true);
        // When "OK" is pressed, change the emotion record's comment to the inputted text, display the
        // new comment, and save the changes.
        enter_keywords_adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String temp_text = new_comment_input.getText().toString();
                keywords = temp_text;
                ArrayList<Condition> matched_conditions = new ArrayList<>();
                matched_conditions = conditionListController.searchByKeyword(keywords, accountOfInterest.getUserID());
                accountOfInterest.getConditionList().setConditions(matched_conditions);
                //Setup adapter for condition list, and display the list.
                ListView listView = findViewById(R.id.conditionListView);
                Collection<Condition> conditionCollection = accountOfInterest.getConditionList().getConditions();
                final ArrayList<Condition> conditions = new ArrayList<> (conditionCollection);
                final ArrayAdapter<Condition> conditionArrayAdapter = new ArrayAdapter<>(ViewConditionListAsCareProviderActivity.this, android.R.layout.simple_list_item_1, conditions);
                listView.setAdapter(conditionArrayAdapter);

                // Added a change observer
                UserAccountListController.getUserAccountList().getAccountOfInterest().getConditionList().addListener(new Listener() {
                    @Override
                    public void update() {
                        conditions.clear();
                        Collection<Condition> conditionCollection = accountOfInterest.getConditionList().getConditions();
                        conditions.addAll(conditionCollection);
                        conditionArrayAdapter.notifyDataSetChanged();
                    }
                });
            }

        });
        enter_keywords_adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        enter_keywords_adb.show();
    }

}
