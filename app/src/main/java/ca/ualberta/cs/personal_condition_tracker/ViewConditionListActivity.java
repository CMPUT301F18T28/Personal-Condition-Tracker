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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

public class ViewConditionListActivity extends AppCompatActivity {

    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountofInterest();
    private Condition selectedCondition;

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

        //Ugly code of OnItemLongClickListener
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(ViewConditionListActivity.this);
                adb.setMessage("Would you like to edit or delete " + conditions.get(position).toString() + " condition?");
                adb.setCancelable(true);
                final int finalPosition = position;

                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedCondition= conditions.get(finalPosition);
                        accountOfInterest.getConditionList().deleteCondition(selectedCondition);
                    }
                });

                adb.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedCondition = conditions.get(finalPosition);
                        Intent intent = new Intent(ViewConditionListActivity.this,
                                ModifyConditionActivity.class);
                        intent.putExtra("index",
                                accountOfInterest.getConditionList().getIndex(selectedCondition));
                        intent.putExtra("conditionTitle", selectedCondition.getTitle());
                        intent.putExtra("conditionDate", selectedCondition.getDate().toString());
                        intent.putExtra("conditionDescription", selectedCondition.getDescription());
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

        //Ugly code of OnItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int finalPosition = position;
                selectedCondition = conditions.get(finalPosition);
                accountOfInterest.getConditionList().setConditionOfInterest(selectedCondition);
                Intent intent = new Intent(ViewConditionListActivity.this,
                        ViewRecordListActivity.class);

                intent.putExtra("conditionIndex",
                        accountOfInterest.getConditionList().getIndex(selectedCondition));
                startActivity(intent);
            }
        });
    }

    public void addACondition(View v){
        Toast.makeText(this,"Adding a condition", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ViewConditionListActivity.this, ModifyConditionActivity.class);
        intent.putExtra("index", -1);
        startActivityForResult(intent,1);
    }
    public void viewMapOfRecords(View v){
        Toast.makeText(this,"Viewing map of records", Toast.LENGTH_SHORT).show();
    }
    public void searchConditionsOrRecords(View v){
        Toast.makeText(this,"Searching conditions", Toast.LENGTH_SHORT).show();
    }

    // A result code of 1 here simply means that we did actually make a change, and that
    // the listView should be updated
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                accountOfInterest.getConditionList().notifyListeners();
            }
        }
    }

}


