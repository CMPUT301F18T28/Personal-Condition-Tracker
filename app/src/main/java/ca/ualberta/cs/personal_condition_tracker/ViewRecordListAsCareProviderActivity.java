package ca.ualberta.cs.personal_condition_tracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class ViewRecordListAsCareProviderActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition conditionOfInterest = accountOfInterest.getConditionList().getConditionOfInterest();
    private Record selectedRecord;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record_list_as_care_provider);

        //Setup adapter for condition list, and display the list.
        ListView listView = findViewById(R.id.recordListView);
        Collection<Record> recordCollection = conditionOfInterest.getRecordList().getRecords();
        final ArrayList<Record> records = new ArrayList<> (recordCollection);
        final ArrayAdapter<Record> recordArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, records);
        listView.setAdapter(recordArrayAdapter);

        //Ugly code of OnItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int finalPosition = position;
                selectedRecord = records.get(finalPosition);
                conditionOfInterest.getRecordList().setRecordOfInterest(selectedRecord);
                Intent intent = new Intent(ViewRecordListAsCareProviderActivity.this,
                        ViewRecordActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addAComment(View v){
        Toast.makeText(this,"Adding a Comment", Toast.LENGTH_SHORT).show();
    }

    public void viewComments(View v){
        Toast.makeText(this,"Viewing comments", Toast.LENGTH_SHORT).show();
    }

    public void showSlideshow(View v){
        Toast.makeText(this,"Showing slideshow", Toast.LENGTH_SHORT).show();
    }

}
