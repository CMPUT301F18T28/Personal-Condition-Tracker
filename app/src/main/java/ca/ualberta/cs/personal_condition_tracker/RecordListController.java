package ca.ualberta.cs.personal_condition_tracker;

import android.util.Log;

import java.util.ArrayList;

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

    public ArrayList<Record> loadRecords(String conditionID) {
        ArrayList<Record>  records = new ArrayList<>();
        Log.e("Error", conditionID);
        RecordListManager.GetRecordsTask getRecordsTask =
                new RecordListManager.GetRecordsTask();
        String query = "{ \"query\": {\"match\": { \"associatedConditionID\" : \""+ conditionID +"\" } } }";
        getRecordsTask.execute(query);
        try {
            records = getRecordsTask.execute(query).get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        return records;
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
//
//    public ArrayList<Record> searchByGeoLocation(String latitude, String longitude, String distance, String condition_id) {
//        ArrayList<Record>  records = new ArrayList<>();
//        RecordListManager.GetRecordsTask getRecordsTask =
//                new RecordListManager.GetRecordsTask();
//        String search_query = "{" +
//                "  \"query\": {" +
//                "    \"bool\" : {" +
//                "      \"must\" : {" +
//                "        \"match\" : {\"problemUUID\" : \"%record_problem_uuid%\"}" +
//                "      }" +
//                "    }" +
//                "  }," +
//                "  \"filter\" : {" +
//                "    \"geo_distance\" : {" +
//                "      \"distance\" : \"%kms_away% km\"," +
//                "      \"location\" : {" +
//                "        \"lat\" : \"%search_lat%\"," +
//                "        \"lon\" : \"%search_lon%\"" +
//                "      }" +
//                "    }" +
//                "  }" +
//                "}";
//
//        search_query = search_query.replace("%search_lat%", Integer.toString(lat));
//        search_query = search_query.replace("%search_lon%", Integer.toString(lon));
//        search_query = search_query.replace("%kms_away%", Integer.toString(distance));
//        search_query = search_query.replace("%record_problem_uuid%", problem_uuid);
//        try {
//            records = getRecordsTask.execute(query).get();
//        } catch (Exception e) {
//            Log.e("Error", "Failed to get the tweets out of the async object.");
//        }
//        return records;
//    }


}
