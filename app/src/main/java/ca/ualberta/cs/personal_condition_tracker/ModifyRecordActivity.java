package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ModifyRecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_record);

        Button select_geo_location = (Button) findViewById(R.id.viewGeoLocationsButton);
        select_geo_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent intent = new Intent(ModifyRecordActivity.this, SelectGeoLocationActivity.class);
                startActivity(intent);
            }
        });

        Button select_body_location = (Button) findViewById(R.id.viewBodyLocationsButton);
        select_body_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent intent = new Intent(ModifyRecordActivity.this, SelectBodyLocationActivity.class);
                startActivity(intent);
            }
        });

    }

}
