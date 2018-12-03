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
 * ViewRecordListActivity is responsible for allowing a patient to view all the records corresponding to
 * a given condition.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collection;

import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.Listener;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;
import ca.ualberta.cs.personal_condition_tracker.R;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;
import ca.ualberta.cs.personal_condition_tracker.Controllers.RecordListController;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;
/**
 * ViewRecordListActivity lets a patient view all the records for one of their conditions
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
public class ViewRecordListActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private RecordListController recordListController = new RecordListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition conditionOfInterest = accountOfInterest.getConditionList().getConditionOfInterest();
    private Record selectedRecord;

    private static final int SELECTED_LOCATION_REQUEST_CODE = 200;
    private String keywords = "";
    private LatLng selectedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record_list);
        ArrayList<Record> old_records = recordListController.loadRecords(conditionOfInterest);
        userAccountListController.getUserAccountList().getAccountOfInterest().getConditionList().getConditionOfInterest().getRecordList().setRecords(old_records);

        TextView conditionTitle = findViewById(R.id.conditionTextView);
        conditionTitle.setText(conditionOfInterest.getTitle());

        //Setup adapter for record list, and display the list.
        ListView listView = findViewById(R.id.recordListView);
        Collection<Record> recordCollection = conditionOfInterest.getRecordList().getRecords();
        final ArrayList<Record> records = new ArrayList<> (recordCollection);
        final ArrayAdapter<Record> recordArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, records);
        listView.setAdapter(recordArrayAdapter);

        // Added a change observer
        conditionOfInterest.getRecordList().addListener(new Listener() {
            @Override
            public void update() {
                records.clear();
                Collection<Record> recordCollection = conditionOfInterest.getRecordList().getRecords();
                records.addAll(recordCollection);
                recordArrayAdapter.notifyDataSetChanged();
            }
        });

        //Ugly code of OnItemLongClickListener
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(ViewRecordListActivity.this);
                adb.setMessage("Would you like to edit or delete " + records.get(position).toString() + " record?");
                adb.setCancelable(true);
                final int finalPosition = position;
                selectedRecord = records.get(finalPosition);
                conditionOfInterest.getRecordList().setRecordOfInterest(selectedRecord);
                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedRecord = records.get(finalPosition);
                        recordListController.deleteRecord(selectedRecord);
                        conditionOfInterest.getRecordList().deleteRecord(selectedRecord);
                    }
                });

                adb.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedRecord = records.get(finalPosition);
                        Intent intent = new Intent(ViewRecordListActivity.this,
                                ModifyRecordActivity.class);
                        intent.putExtra("recordIndex",
                                conditionOfInterest.getRecordList().getRecordIndex(selectedRecord));
                        intent.putExtra("recordTitle", selectedRecord.getTitle());
                        intent.putExtra("recordDate", selectedRecord.getDate().toString());
                        intent.putExtra("recordDescription", selectedRecord.getDescription());
                        startActivityForResult(intent,1);
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
                selectedRecord = records.get(finalPosition);
                conditionOfInterest.getRecordList().setRecordOfInterest(selectedRecord);
                Intent intent = new Intent(ViewRecordListActivity.this,
                        ViewRecordActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addARecord(View v){
        Toast.makeText(this,"Adding a Record", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ViewRecordListActivity.this, ModifyRecordActivity.class);
        intent.putExtra("recordIndex", -1);
        startActivityForResult(intent,1);
    }

    public void viewComments(View v){
        Toast.makeText(this,"Viewing comments", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ViewRecordListActivity.this, ViewCommentsActivity.class);
        startActivity(intent);
    }

    public void searchRecords(View v){
        AlertDialog.Builder choose_search_type_adb = new AlertDialog.Builder(ViewRecordListActivity.this);
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
                    selectGeoLocation();
                    searchByGeoLocation();
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
                ArrayList<Record> matched_records = new ArrayList<>();
                matched_records = recordListController.searchByKeyword(keywords, conditionOfInterest.getId());
                conditionOfInterest.getRecordList().setRecords(matched_records);
                //Setup adapter for record list, and display the list.
                ListView listView = findViewById(R.id.recordListView);
                Collection<Record> recordCollection = conditionOfInterest.getRecordList().getRecords();
                final ArrayList<Record> records = new ArrayList<> (recordCollection);
                final ArrayAdapter<Record> recordArrayAdapter = new ArrayAdapter<>(ViewRecordListActivity.this, android.R.layout.simple_list_item_1, records);
                listView.setAdapter(recordArrayAdapter);

                // Added a change observer
                conditionOfInterest.getRecordList().addListener(new Listener() {
                    @Override
                    public void update() {
                        records.clear();
                        Collection<Record> recordCollection = conditionOfInterest.getRecordList().getRecords();
                        records.addAll(recordCollection);
                        recordArrayAdapter.notifyDataSetChanged();
                    }
                });
                Toast.makeText(ViewRecordListActivity.this,Integer.toString(conditionOfInterest.getRecordList().getRecords().size()), Toast.LENGTH_SHORT).show();
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


    public void searchByGeoLocation(){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LayoutInflater factory = LayoutInflater.from(this);

//text_entry is an Layout XML file containing two text field to display in alert dialog
        final EditText latitude = new EditText(this);
        final EditText longitude = new EditText(this);
        final EditText distance = new EditText(this);
        latitude.setInputType(InputType.TYPE_CLASS_NUMBER);
        longitude.setInputType(InputType.TYPE_CLASS_NUMBER);
        distance.setInputType(InputType.TYPE_CLASS_NUMBER);
//        layout.addView(latitude);
//        layout.addView(longitude);
        layout.addView(distance);

        final AlertDialog.Builder enter_geo_location_adb = new AlertDialog.Builder(this);
        enter_geo_location_adb.setTitle("Enter distance:").setView(layout).setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
//                        String latitudeString = latitude.getText().toString();
//                        String longitudeString = longitude.getText().toString();
                        String latitudeString = "";
                        String longitudeString = "";
                        if (selectedLocation != null) {
                            latitudeString = Double.toString(selectedLocation.latitude);
                            longitudeString = Double.toString(selectedLocation.longitude);
                        }
                        String distanceString = distance.getText().toString();
                        ArrayList<Record> matched_records = new ArrayList<>();
                        matched_records = recordListController.searchByGeoLocation(latitudeString, longitudeString, distanceString, conditionOfInterest.getId());
                        conditionOfInterest.getRecordList().setRecords(matched_records);
                        //Setup adapter for record list, and display the list.
                        ListView listView = findViewById(R.id.recordListView);
                        Collection<Record> recordCollection = conditionOfInterest.getRecordList().getRecords();
                        final ArrayList<Record> records = new ArrayList<>(recordCollection);
                        final ArrayAdapter<Record> recordArrayAdapter = new ArrayAdapter<>(ViewRecordListActivity.this, android.R.layout.simple_list_item_1, records);
                        listView.setAdapter(recordArrayAdapter);

                        // Added a change observer
                        conditionOfInterest.getRecordList().addListener(new Listener() {
                            @Override
                            public void update() {
                                records.clear();
                                Collection<Record> recordCollection = conditionOfInterest.getRecordList().getRecords();
                                records.addAll(recordCollection);
                                recordArrayAdapter.notifyDataSetChanged();
                            }
                        });
                        Toast.makeText(ViewRecordListActivity.this, Integer.toString(conditionOfInterest.getRecordList().getRecords().size()), Toast.LENGTH_SHORT).show();
                    }}).setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                        dialog.cancel();
                    }
                });
        enter_geo_location_adb.show();

    }

    public void selectGeoLocation() {
        Intent mapIntent = new Intent(this, MapsActivity.class);
        mapIntent.putExtra("mapMode", "selection");
        startActivityForResult(mapIntent, SELECTED_LOCATION_REQUEST_CODE);
    }

    // A result code of 1 here simply means that we did actually make a change, and that
    // the listView should be updated
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                conditionOfInterest.getRecordList().notifyListeners();
            }
        }
        if (requestCode == SELECTED_LOCATION_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                selectedLocation = new LatLng(data.getDoubleExtra("latitude", 0.0),
                        data.getDoubleExtra("longitude", 0.0));
                Toast.makeText(ViewRecordListActivity.this, Double.toString(selectedLocation.longitude), Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
//        loadRecords();
        ArrayList<Record> old_records = recordListController.loadRecords(conditionOfInterest);
        userAccountListController.getUserAccountList().getAccountOfInterest().getConditionList().getConditionOfInterest().getRecordList().setRecords(old_records);

    }

}
