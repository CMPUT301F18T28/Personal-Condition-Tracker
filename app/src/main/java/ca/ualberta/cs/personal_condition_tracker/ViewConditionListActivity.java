package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ViewConditionListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_condition_list);

        Button add_a_condition = (Button) findViewById(R.id.addAConditionButton);
        add_a_condition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent intent = new Intent(ViewConditionListActivity.this, ModifyConditionActivity.class);
                startActivity(intent);
            }
        });

        Button view_map_of_records = (Button) findViewById(R.id.viewMapOfRecordsButton);
        view_map_of_records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent intent = new Intent(ViewConditionListActivity.this, ViewMapOfRecordsActivity.class);
                startActivity(intent);
            }
        });

        Button search_button = (Button) findViewById(R.id.searchButton);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent intent = new Intent(ViewConditionListActivity.this, ViewRecordListActivity.class);
                startActivity(intent);
            }
        });

    }

}
