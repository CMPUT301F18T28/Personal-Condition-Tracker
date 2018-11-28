package ca.ualberta.cs.personal_condition_tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyAccountActivity extends AppCompatActivity {
    private Intent intent;
    private UserAccountListController userAccountListController = new UserAccountListController();
    private UserAccountList userAccountList = userAccountListController.getUserAccountList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_account);

        //Get information from the intent
        intent = getIntent();
        viewingSetup();
    }

    public void viewingSetup(){
        UserAccount user = new UserAccount();
        String accountType = intent.getStringExtra("accountType");
        if (accountType.equals("patient")){
            user = userAccountList.getAccountOfInterest();
            if(userAccountList.activeUserIsCareProvider()){
                Button editButton = findViewById(R.id.modifyAccountConfirmButton);
                editButton.setVisibility(View.GONE);
                editButton.setClickable(false);
            }
        }

        else if (accountType.equals("care provider")){
            user = userAccountList.getActiveCareProvider();
        }

        String userID = user.getUserID();
        String userEmailAddress= user.getEmail_address();
        String userPhoneNumber = user.getPhone_number();


        //Set the information for this activity
        TextView userIDView = findViewById(R.id.userIDText);
        EditText emailAddressView = findViewById(R.id.emailAddressText);
        EditText phoneNumberView = findViewById(R.id.phoneNumberText);

        userIDView.setText(userID);
        emailAddressView.setText(userEmailAddress);
        phoneNumberView.setText(userPhoneNumber);

        //For viewing, the fields should not be editable.
        emailAddressView.setEnabled(false);
        phoneNumberView.setEnabled(false);
    }

    public void editingSetup(){

    }

    public void modifyAccountConfirm(View v){
        Toast.makeText(this,"Dead for now", Toast.LENGTH_SHORT).show();
    }

    public void modifyAccountCancel(View v){
        this.finish();
    }


}
