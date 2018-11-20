/* Personal Condition Tracker : A simple and attractive Android application that allows an individual to
 document, track and review the progression of a personal health issue (a 'condition'), thus serving to facilitate
 enhanced clarity of communicating between patient and care provider, early detection and accurate prognosis with the
 aim of obtaining medical treatment as soon as possible.
 Document the facts - get the treatment you deserve!
 Copyright (C) 2018
 R. Voon; rcvoon@ualberta.ca
 D. Buksa; draydon@ualberta.ca
 W. Nichols; wnichols@ualberta.ca
 D. Douziech; douziech@ualberta.ca
 C. Neureuter; neureute@ualberta.ca
Redistribution and use in source and binary forms, with or without
modification, are permitted (subject to the limitations in the disclaimer
below) provided that the following conditions are met:
     * Redistributions of source code must retain the above copyright notice,
     this list of conditions and the following disclaimer.
     * Redistributions in binary form must reproduce the above copyright
     notice, this list of conditions and the following disclaimer in the
     documentation and/or other materials provided with the distribution.
     * Neither the name of the copyright holder nor the names of its
     contributors may be used to endorse or promote products derived from this
     software without specific prior written permission.
NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY
THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
*/

package ca.ualberta.cs.personal_condition_tracker;

/**
 * AddPatientActivity is responsible for adding a patient to a care provider's patient list.
 * @author    R. Voon; rcvoon@ualberta.ca
 *            D. Buksa; draydon@ualberta.ca
 *            W. Nichols; wnichols@ualberta.ca
 *            D. Douziech; douziech@ualberta.ca
 *            C. Neureuter; neureute@ualberta.ca
 * @version     1.1, 11-18-18
 * @since       1.0
 */

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
    // Add a patient to a care provider's patient list.
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
    // Cancel adding a patient
    public void addPatientCancel(View v){
        Toast.makeText(this,"Cancelling patient add", Toast.LENGTH_SHORT).show();
        this.finish();
    }
    // Check if a patient exists in the server
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
