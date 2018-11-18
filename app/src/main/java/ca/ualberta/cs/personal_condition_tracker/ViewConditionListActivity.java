package ca.ualberta.cs.personal_condition_tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

public class ViewConditionListActivity extends AppCompatActivity {

    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountofInterest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_condition_list);

        final Patient accountOfInterest = UserAccountListController.getUserAccountList().getAccountofInterest();

        //Setup adapter for condition list, and display the list.
        ListView listView = findViewById(R.id.conditionListView);
        Collection<Condition> conditionCollection = accountOfInterest.getConditionList().getConditions();
        final ArrayList<Condition> conditions = new ArrayList<> (conditionCollection);
        final ArrayAdapter<Condition> conditionArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, conditions);
        listView.setAdapter(conditionArrayAdapter);

        // Added a change observer
        UserAccountListController.getUserAccountList().getAccountofInterest().getConditionList().addListener(new Listener() {
            @Override
            public void update() {
                conditions.clear();
                Collection<Condition> conditionCollection = accountOfInterest.getConditionList().getConditions();
                conditions.addAll(conditionCollection);
                conditionArrayAdapter.notifyDataSetChanged();
            }
        });

    }

    public void addACondition(View v){
        Toast.makeText(this,"Adding a condition", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ViewConditionListActivity.this, ModifyConditionActivity.class);
        startActivityForResult(intent,1);
    }
    public void viewMapOfRecords(View v){
        Toast.makeText(this,"Viewing map of records", Toast.LENGTH_SHORT).show();
    }
    public void searchConditionsOrRecords(View v){
        Toast.makeText(this,"Searching conditions", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                accountOfInterest.getConditionList().notifyListeners();
            }
        }
    }

}


