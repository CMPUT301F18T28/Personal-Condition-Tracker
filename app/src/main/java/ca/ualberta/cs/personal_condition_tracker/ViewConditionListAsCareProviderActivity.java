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
 * ViewConditionListAsCareProviderActivity is responsible for allowing a care provider to view
 * all the conditions of a patient from their patient list.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class ViewConditionListAsCareProviderActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition selectedCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_condition_list_as_care_provider);

        loadConditions();

        final Patient accountOfInterest = UserAccountListController.getUserAccountList().getAccountOfInterest();
        TextView patientName = findViewById(R.id.patientNameTextView);
        patientName.setText(accountOfInterest.getUserID());

        //Setup adapter for condition list, and display the list.
        ListView listView = findViewById(R.id.conditionListView);
        Collection<Condition> conditionCollection = accountOfInterest.getConditionList().getConditions();
        final ArrayList<Condition> conditions = new ArrayList<> (conditionCollection);
        final ArrayAdapter<Condition> conditionArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, conditions);
        listView.setAdapter(conditionArrayAdapter);

        //Ugly code of OnItemClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int finalPosition = position;
                selectedCondition = conditions.get(finalPosition);
                accountOfInterest.getConditionList().setConditionOfInterest(selectedCondition);
                Intent intent = new Intent(ViewConditionListAsCareProviderActivity.this,
                        ViewRecordListAsCareProviderActivity.class);

                intent.putExtra("conditionIndex",
                        accountOfInterest.getConditionList().getIndex(selectedCondition));
                startActivity(intent);
            }
        });
    }

    public void viewMapOfRecords(View v){
        Toast.makeText(this,"Viewing map of records", Toast.LENGTH_SHORT).show();
    }

    public void searchConditionsOrRecords(View v){
        Toast.makeText(this,"Searching conditions", Toast.LENGTH_SHORT).show();
    }

    public void loadConditions() {
        ConditionListManager.GetConditionsTask getConditionsTask =
                new ConditionListManager.GetConditionsTask();
        String query = "{ \"query\": {\"match\": { \"associatedUserID\" : \""+ accountOfInterest.getUserID() +"\" } } }";
        getConditionsTask.execute(query);
        try {
            userAccountListController.getUserAccountList().getAccountOfInterest().getConditionList().setConditions(getConditionsTask.get());
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
    }

}
