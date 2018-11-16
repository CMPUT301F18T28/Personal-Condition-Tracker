package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button temp = (Button) findViewById(R.id.signInButton);
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setResult(RESULT_OK);
                EditText userIDText = (EditText) findViewById(R.id.userIDEntry);
                String userID = userIDText.getText().toString();
                UserAccountListController.GetUserAccountsTask getUserAccountsTask =
                        new UserAccountListController.GetUserAccountsTask();
                String query = "{ \"query\": {\"match\": { \"userID\" : \""+ userID +"\" } } }";
                getUserAccountsTask.execute(query);
                ArrayList<UserAccount> stored_users = new ArrayList<UserAccount>();
                try {
                    stored_users = getUserAccountsTask.get();
                    Toast.makeText(MainActivity.this, Integer.toString(stored_users.size()), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e("Error", "Failed to get the tweets out of the async object.");
                }
                if (stored_users.size() == 1) {
                    Intent intent = new Intent(MainActivity.this, ViewConditionListActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Unknown UserID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button sign_up_button = (Button) findViewById(R.id.signUpButton);
        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText userIDText = (EditText) findViewById(R.id.userIDEntry);
                String userID = userIDText.getText().toString();
                setResult(RESULT_OK);
                Intent intent = new Intent(MainActivity.this, ModifyUserAccountActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * This method handles the onClick of the "Sign In" button.
     *
     * @paramview the view of the button pressed
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

//    protected void onStart() {
//        // TODO Auto-generated method stub
//        super.onStart();
//        UserAccountListController.GetUserAccountsTask getUserAccountsTask =
//                new UserAccountListController.GetUserAccountsTask();
//        getUserAccountsTask.execute("");
//        try {
//            UserAccountListController.getUserAccountList().setUserAccounts(getUserAccountsTask.get());
//            Toast.makeText(this,Integer.toString(UserAccountListController.getUserAccountList().getUserAccounts().size()), Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Log.e("Error", "Failed to get the tweets out of the async object.");
//        }
//
//    }
}
