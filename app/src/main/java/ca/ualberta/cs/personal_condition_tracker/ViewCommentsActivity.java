package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ViewCommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comments);

        Button add_comment = (Button) findViewById(R.id.addCommentButton);
        add_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent intent = new Intent(ViewCommentsActivity.this, ModifyCommentActivity.class);
                startActivity(intent);
            }
        });

    }

}
