package ca.ualberta.cs.personal_condition_tracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPatientActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private CareProvider activeCareProvider = userAccountListController.getUserAccountList().getActiveCareProvider();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
    }

    public void addPatientConfirm(View v){
        Toast.makeText(this,"Adding new patient!", Toast.LENGTH_SHORT).show();
        EditText patientIDText = findViewById(R.id.addPatientText);
        String newPatientID = patientIDText.getText().toString();
        activeCareProvider.getPatientList().addPatient(newPatientID);
        this.finish();
    }

    public void addPatientCancel(View v){
        Toast.makeText(this,"Cancelling patient add", Toast.LENGTH_SHORT).show();
        this.finish();
    }

}
