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
        userAccountListController.getUserAccountList();
    }
    /**
     * This method handles the onClick of the "Sign In" button.
     *
     * @paramview the view of the button pressed
     */
    public void signIn(View v){
        Toast.makeText(this,"Signing in", Toast.LENGTH_SHORT).show();
        /* if (authenticate()){
                Check user type, send them to correct activity.
                }
                else{displayError()}
         */
    }

     /**
     * This method handles the onClick of the "Sign Up" button.
     *
     * @param view the view of the button pressed
     */
    public void signUp(View v){
        Toast.makeText(this,"Signing up", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, ModifyUserAccountActivity.class);
        startActivity(intent);
    }

}
