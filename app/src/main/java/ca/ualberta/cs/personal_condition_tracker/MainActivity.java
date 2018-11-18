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
    private UserAccountListController userAccountListController = new UserAccountListController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userAccountListController.getUserAccountList();
    }

    /**
     * This method handles the onClick of the "Sign In" button.
     *
     * @param v the view of the button pressed
     */
    public void signIn(View v){
        Toast.makeText(this,"Signing in", Toast.LENGTH_SHORT).show();
        /* if (authenticate()){
                Check user type, send them to correct activity.
                }
                else{displayError()}
         */
        //Get Sign in form data
        EditText userIdEntry = findViewById(R.id.userIDEntry);
        EditText passwordEntry = findViewById(R.id.passwordEntry);

        String userId = userIdEntry.getText().toString();
        String password = passwordEntry.getText().toString();

        ArrayList<UserAccount> userAccountList = UserAccountListController.getUserAccountList().getUserAccounts();
        //TODO Adjust for real ID/PW. maybe use size of UAL?
        for(UserAccount userAccount : userAccountList){
            System.out.println(userAccount.getId() + " " + userAccount.getPassword());
            if(userAccount.authenticate(userId, password)){
                Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show();
                userAccountListController.getUserAccountList().setActiveAccount(userAccount);

                //Check account type, direct to proper activity.
                if(userAccount.getAccountType().equals("Patient")){
                    userAccountListController.getUserAccountList().setAccountOfInterest((Patient) userAccount);
                    Intent intent = new Intent(MainActivity.this, ViewConditionListActivity.class);
                    startActivity(intent);
                }

                else if(userAccount.getAccountType().equals("Care Provider")){
                    Intent intent = new Intent(MainActivity.this, ViewPatientListActivity.class);
                    startActivity(intent);
                }
            }
        }
        Toast.makeText(this,"Incorrect login information", Toast.LENGTH_SHORT).show();
    }

     /**
     * This method handles the onClick of the "Sign Up" button.
     *
     * @param  v the view of the button pressed
     */
    public void signUp(View v){
        Toast.makeText(this,"Signing up", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, ModifyUserAccountActivity.class);
        startActivity(intent);
    }

}
