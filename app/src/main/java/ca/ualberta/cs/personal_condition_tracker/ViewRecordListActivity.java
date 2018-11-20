package ca.ualberta.cs.personal_condition_tracker;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class ViewRecordListActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition conditionOfInterest = accountOfInterest.getConditionList().getConditionOfInterest();
    private Record selectedRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record_list);

        //Setup adapter for condition list, and display the list.
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

                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedRecord = records.get(finalPosition);
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
                return false;
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
    }

    public void showSlideshow(View v){
        Toast.makeText(this,"Showing slideshow", Toast.LENGTH_SHORT).show();
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
    }

}
