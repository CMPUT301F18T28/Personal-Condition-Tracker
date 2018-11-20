package ca.ualberta.cs.personal_condition_tracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddPatientActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private CareProvider activeCareProvider = userAccountListController.getUserAccountList().getActiveCareProvider();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
    }

    public void addPatientConfirm(View v){
        EditText patientIDText = findViewById(R.id.addPatientText);
        String newPatientID = patientIDText.getText().toString();
        if (checkIfPatientExists(newPatientID) == true) {
            Toast.makeText(this,"Adding new patient!", Toast.LENGTH_SHORT).show();
            activeCareProvider.getPatientList().addPatient(newPatientID);
            this.finish();
        }
        else {
            Toast.makeText(this,"Unknown patient ID", Toast.LENGTH_SHORT).show();
        }
    }

    public void addPatientCancel(View v){
        Toast.makeText(this,"Cancelling patient add", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    public boolean checkIfPatientExists(String patientID) {
        boolean doesExist = false;
        UserAccountListManager.GetUserAccountsTask getUserAccountsTask =
                new UserAccountListManager.GetUserAccountsTask();
        String query = "{ \"query\": {\"match\": { \"userID\" : \"" + patientID + "\" } } }";
        getUserAccountsTask.execute(query);
        ArrayList<? extends UserAccount> stored_users = new ArrayList<UserAccount>();
        try {
            stored_users = getUserAccountsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        if (stored_users.size() != 0) {
            doesExist = true;
        }
        return doesExist;
    }

}
