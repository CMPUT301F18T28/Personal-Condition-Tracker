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

public class ViewConditionListAsCareProviderActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition selectedCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_condition_list_as_care_provider);

        final Patient accountOfInterest = UserAccountListController.getUserAccountList().getAccountOfInterest();

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

    public void viewMapOfRecords(View v){
        Toast.makeText(this,"Viewing map of records", Toast.LENGTH_SHORT).show();
    }

    public void searchConditionsOrRecords(View v){
        Toast.makeText(this,"Searching conditions", Toast.LENGTH_SHORT).show();
    }
}
