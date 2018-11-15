package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button temp = (Button) findViewById(R.id.signInButton);
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent intent = new Intent(MainActivity.this, ViewConditionListActivity.class);
                startActivity(intent);
            }
        });

        Button temp2 = (Button) findViewById(R.id.signUpButton);
        temp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                Intent intent = new Intent(MainActivity.this, ViewPatientListActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * This method handles the onClick of the "Sign In" button.
     *
     * @param view the view of the button pressed
     */
//    public void handleSignIn(View view) {
//
//        findViewById(R.id.userIDEntry);
//    }
//
//    /**
//     * This method handles the onClick of the "Sign Up" button.
//     *
//     * @param view the view of the button pressed
//     */
//    public void handleSignUp(View view) {
//
//    }
}
