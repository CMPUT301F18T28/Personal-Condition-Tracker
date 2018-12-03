package ca.ualberta.cs.personal_condition_tracker.Controllers;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;
import ca.ualberta.cs.personal_condition_tracker.Model.RecordList;
import ca.ualberta.cs.personal_condition_tracker.Managers.RecordListManager;

public class RecordListController {

    private static RecordList recordList = null;
    /**
     * Gets the user account list.
     * @return UserAccountList userAccountList
     */
    static public RecordList getRecordList() {
        if ((recordList) == null) {
            recordList = new RecordList();
        }
        return recordList;
    }
    /**
     * Add an account to the user account list.
     */
    public void addRecord(Record record){ getRecordList().addRecord(record);}

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

    // Add a care provider to the server.
    public void createRecord(Record newRecord) {
        // Check if the user has already signed up
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

        // Add the user to the database.
        if (records.size() == 0) {
//            UserAccountListController.getUserAccountList().addUserAccount(newCareProvider);
            RecordListManager.AddRecordsTask addRecordsTask
                    = new RecordListManager.AddRecordsTask();
            addRecordsTask.execute(newRecord);
        } else {
        }
    }

    public void deleteRecord(Record selectedRecord) {
        RecordListManager.DeleteRecordsTask deleteRecordsTask =
                new RecordListManager.DeleteRecordsTask();
        deleteRecordsTask.execute(selectedRecord);
    }

    public void editRecord(Record oldRecord, Record newRecord) {
        newRecord.setId(oldRecord.getId());
        RecordListManager.DeleteRecordsTask deleteRecordsTask =
                new RecordListManager.DeleteRecordsTask();
        deleteRecordsTask.execute(oldRecord);
        RecordListManager.AddRecordsTask addRecordsTask
                = new RecordListManager.AddRecordsTask();
        addRecordsTask.execute(newRecord);
    }


    public ArrayList<Record> searchByKeyword(String keywords, String condition_id) {
        ArrayList<Record>  records = new ArrayList<>();
        RecordListManager.GetRecordsTask getRecordsTask =
                new RecordListManager.GetRecordsTask();
        String query = "{ \"size\": 100," +
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
