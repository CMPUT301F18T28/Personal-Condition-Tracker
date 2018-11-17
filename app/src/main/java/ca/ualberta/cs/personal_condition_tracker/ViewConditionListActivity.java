package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class ViewConditionListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_condition_list);

//        Patient accountOfInterest = (Patient) UserAccountListController.getAccountofInterest();
//
//        ListView listView = findViewById(R.id.conditionListView);
//        Collection<Condition> conditionCollection = accountOfInterest.getConditionList();
//       final ArrayList<Record> records = new ArrayList<Record>(recordCollection);
//        final ArrayAdapter<Record> recordArrayAdapter = new ArrayAdapter<Record>(this, android.R.layout.simple_list_item_1, records);
//        listView.setAdapter(recordArrayAdapter);

    }

    public void addACondition(View v){
        Toast.makeText(this,"Adding a condition", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ViewConditionListActivity.this, ModifyConditionActivity.class);
        startActivity(intent);

    }
    public void viewMapOfRecords(View v){
        Toast.makeText(this,"Viewing map of records", Toast.LENGTH_SHORT).show();

    }
    public void searchConditionsOrRecords(View v){
        Toast.makeText(this,"Searching conditions", Toast.LENGTH_SHORT).show();

    }

}
