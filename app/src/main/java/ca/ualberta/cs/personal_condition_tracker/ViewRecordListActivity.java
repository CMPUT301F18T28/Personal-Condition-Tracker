package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ViewRecordListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record_list);

        Button add_record = (Button) findViewById(R.id.addARecordButton);
        add_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent intent = new Intent(ViewRecordListActivity.this, ModifyRecordActivity.class);
                startActivity(intent);
            }
        });

        Button view_comments = (Button) findViewById(R.id.viewCommentsButton);
        view_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent intent = new Intent(ViewRecordListActivity.this, ViewCommentsActivity.class);
                startActivity(intent);
            }
        });

    }

}
