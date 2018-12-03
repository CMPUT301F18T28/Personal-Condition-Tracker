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

package ca.ualberta.cs.personal_condition_tracker.Controllers;

import android.util.Log;

import java.util.ArrayList;

import ca.ualberta.cs.personal_condition_tracker.Managers.ConditionListManager;
import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.ConditionList;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;

/**
 * ConditionListController performs operations on a ConditionList object.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
public class ConditionListController {

    private static ConditionList conditionList = null;
    /**
     * Gets the condition list.
     * @return conditionList
     */
    static public ConditionList getConditionList() {
        if ((conditionList) == null) {
            conditionList = new ConditionList();
        }
        return conditionList;
    }
    /**
     * Add a condition to the condition list.
     */
    public void addCondition(Condition condition){ getConditionList().addCondition(condition);}

    /**
     * Get the conditions for a specified patient from the server.
     * @param patient
     * @return
     */
    public ArrayList<Condition> loadConditions(Patient patient) {
        ArrayList<Condition> conditions = new ArrayList<>();
        ConditionListManager.GetConditionsTask getConditionsTask =
                new ConditionListManager.GetConditionsTask();
        String query = "{ \"query\": {\"match\": { \"associatedUserID\" : \""+ patient.getUserID() +"\" } } }";
        getConditionsTask.execute(query);
        try {
            conditions = getConditionsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        return conditions;
    }

    // Add a condition to the server.
    public void createCondition(Condition newCondition) {
        // Check if the condition already exists.
        ConditionListManager.GetConditionsTask getConditionsTask =
                new ConditionListManager.GetConditionsTask();
        String query = "{ \"query\": {\"match\": { \"id\" : \""+ newCondition.getId() +"\" } } }";
        getConditionsTask.execute(query);
        ArrayList<Condition> conditions = new ArrayList<>();
        try {
            conditions = getConditionsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }

        // Add the condition to the database.
        if (conditions.size() == 0) {
            ConditionListManager.AddConditionsTask addConditionsTask
                    = new ConditionListManager.AddConditionsTask();
            addConditionsTask.execute(newCondition);
        }
    }

    /**
     * Remove a condition from the server.
     * @param selectedCondition
     */
    public void deleteCondition(Condition selectedCondition) {
        ConditionListManager.DeleteConditionsTask deleteConditionsTask =
                new ConditionListManager.DeleteConditionsTask();
        deleteConditionsTask.execute(selectedCondition);
    }

    /**
     * Modify a selected condition in the server.
     * @param oldCondition
     * @param newCondition
     */
    public void editCondition(Condition oldCondition, Condition newCondition) {
        newCondition.setId(oldCondition.getId());
        ConditionListManager.DeleteConditionsTask deleteConditionsTask =
                new ConditionListManager.DeleteConditionsTask();
        deleteConditionsTask.execute(oldCondition);
        ConditionListManager.AddConditionsTask addConditionsTask
                = new ConditionListManager.AddConditionsTask();
        addConditionsTask.execute(newCondition);
    }

    /**
     * Get all the conditions that match a specific seto f keywords from the server.
     * @param keywords
     * @param userID
     * @return
     */
    public ArrayList<Condition> searchByKeyword(String keywords, String userID) {
        ArrayList<Condition>  conditions = new ArrayList<>();
        ConditionListManager.GetConditionsTask getConditionsTask =
                new ConditionListManager.GetConditionsTask();
        String query = "{ " +
                "  \"size\": 10," +
                "  \"query\": {" +
                "    \"bool\": {" +
                "      \"must\": " +
                "      [{\"multi_match\" : {\"query\":    \""+ keywords +"\", \"fields\": [ \"title\", \"description\" ]}}," +
                "       {\"match\": {\"associatedUserID\": \""+ userID + "\"}}]" +
                "    }" +
                "  }" +
                "}";
        try {
            conditions = getConditionsTask.execute(query).get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        return conditions;
    }
}
