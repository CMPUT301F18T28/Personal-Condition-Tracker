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

import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;
import ca.ualberta.cs.personal_condition_tracker.Model.RecordList;
import ca.ualberta.cs.personal_condition_tracker.Managers.RecordListManager;
/**
 * RecordListController performs operations on a RecordList object.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
public class RecordListController {

    private static RecordList recordList = null;
    /**
     * Gets the list of records.
     * @return recordList
     */
    static public RecordList getRecordList() {
        if ((recordList) == null) {
            recordList = new RecordList();
        }
        return recordList;
    }
    /**
     * Add a record to the record list.
     */
    public void addRecord(Record record){ getRecordList().addRecord(record);}

    /**
     * Get all conditions corresponding to a given condition from the server
     * @param condition
     * @return records
     */
    public ArrayList<Record> loadRecords(Condition condition) {
        ArrayList<Record>  records = new ArrayList<>();
        RecordListManager.GetRecordsTask getRecordsTask =
                new RecordListManager.GetRecordsTask();
        String query = "{ \"query\": {\"match\": { \"associatedConditionID\" : \""+ condition.getId() +"\" } } }";
        Log.e("Error", condition.getId());
        try {
            records = getRecordsTask.execute(query).get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the records out of the async object.");
        }
        Log.e("Error", Integer.toString(records.size()));
        return records;
    }

    // Add a record to the server.
    public void createRecord(Record newRecord) {
        // Check if the record already exists
        RecordListManager.GetRecordsTask getRecordsTask =
                new RecordListManager.GetRecordsTask();
        String query = "{ \"query\": {\"match\": { \"id\" : \"" + newRecord.getId() + "\" } } }";
        getRecordsTask.execute(query);
        ArrayList<Record> records = new ArrayList<>();
        try {
            records = getRecordsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }

        // Add the record to the database.
        if (records.size() == 0) {
            RecordListManager.AddRecordsTask addRecordsTask
                    = new RecordListManager.AddRecordsTask();
            addRecordsTask.execute(newRecord);
        }
    }

    /**
     * Remove a record from the server
     * @param selectedRecord
     */
    public void deleteRecord(Record selectedRecord) {
        RecordListManager.DeleteRecordsTask deleteRecordsTask =
                new RecordListManager.DeleteRecordsTask();
        deleteRecordsTask.execute(selectedRecord);
    }

    /**
     * Modify a selected record in the server database.
     * @param oldRecord
     * @param newRecord
     */
    public void editRecord(Record oldRecord, Record newRecord) {
        newRecord.setId(oldRecord.getId());
        RecordListManager.DeleteRecordsTask deleteRecordsTask =
                new RecordListManager.DeleteRecordsTask();
        deleteRecordsTask.execute(oldRecord);
        RecordListManager.AddRecordsTask addRecordsTask
                = new RecordListManager.AddRecordsTask();
        addRecordsTask.execute(newRecord);
    }

    /**
     * Get all recodrs that have a specified set of keywords for a given condition.
     * @param keywords
     * @param condition_id
     * @return records
     */
    public ArrayList<Record> searchByKeyword(String keywords, String condition_id) {
        ArrayList<Record>  records = new ArrayList<>();
        RecordListManager.GetRecordsTask getRecordsTask =
                new RecordListManager.GetRecordsTask();
        String query = "{ \"size\": 10," +
                "  \"query\": {" +
                "    \"bool\": {" +
                "      \"must\": " +
                "      [{\"multi_match\" : {\"query\":    \""+ keywords +"\", \"fields\": [ \"title\", \"description\" ]}}," +
                "       {\"match\": {\"associatedConditionID\": \""+ condition_id + "\"}}]" +
                "    }" +
                "  }" +
                "}";
        try {
            records = getRecordsTask.execute(query).get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        return records;
    }

    /**
     * Get all the records within a distance from a selected latitude and longitude.
     * @param latitude
     * @param longitude
     * @param distance
     * @param condition_id
     * @return
     */
    public ArrayList<Record> searchByGeoLocation(String latitude, String longitude, String distance, String condition_id) {
        ArrayList<Record>  records = new ArrayList<>();
        RecordListManager.GetRecordsTask getRecordsTask =
                new RecordListManager.GetRecordsTask();
        String query = "{" +
                "  \"query\": {" +
                "    \"bool\" : {" +
                "      \"must\" : {" +
                "        \"match\" : {\"associatedConditionID\" : \""+condition_id +"\"}" +
                "      }" +
                "    }" +
                "  }," +
                "  \"filter\" : {" +
                "    \"geo_distance\" : {" +
                "      \"distance\" : \"" + distance + " km" + "\"," +
                "      \"location\" : {" +
                "        \"lat\" : \""+ latitude + "\"," +
                "        \"lon\" : \"" + longitude + "\"" +
                "      }" +
                "    }" +
                "  }" +
                "}";

        try {
            records = getRecordsTask.execute(query).get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        return records;
    }


}
