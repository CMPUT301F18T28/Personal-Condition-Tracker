package ca.ualberta.cs.personal_condition_tracker.Controllers;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import ca.ualberta.cs.personal_condition_tracker.Activities.ModifyCommentActivity;
import ca.ualberta.cs.personal_condition_tracker.Managers.CommentRecordListManager;
import ca.ualberta.cs.personal_condition_tracker.Model.BodyLocationList;
import ca.ualberta.cs.personal_condition_tracker.Model.CommentRecord;
import ca.ualberta.cs.personal_condition_tracker.Model.CommentRecordList;
import ca.ualberta.cs.personal_condition_tracker.Model.Condition;

public class CommentRecordListController {

    private static CommentRecordList commentRecordList = null;
    /**
     * Gets the user account list.
     * @return UserAccountList userAccountList
     */
    static public CommentRecordList getCommentRecordList() {
        if ((commentRecordList) == null) {
            commentRecordList = new CommentRecordList();
        }
        return commentRecordList;
    }

    public ArrayList<CommentRecord> loadCommentRecords(Condition condition) {
        ArrayList<CommentRecord> commentRecords = new ArrayList<>();
        CommentRecordListManager.GetCommentRecordsTask getCommentRecordsTask =
                new CommentRecordListManager.GetCommentRecordsTask();
        String query = "{ \"query\": {\"match\": { \"conditionIDForComment\" : \""+ condition.getId() +"\" } } }";
        getCommentRecordsTask.execute(query);
        try {
            commentRecords = getCommentRecordsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        return commentRecords;
    }
    // Add a care provider to the server.
    public void createCommentRecord(CommentRecord newCommentRecord) {
        // Check if the user has already signed up
        CommentRecordListManager.GetCommentRecordsTask getCommentRecordsTask =
                new CommentRecordListManager.GetCommentRecordsTask();
        String query = "{ \"query\": {\"match\": { \"id\" : \""+ newCommentRecord.getId() +"\" } } }";
        getCommentRecordsTask.execute(query);
        ArrayList<CommentRecord> records = new ArrayList<>();
        try {
            records = getCommentRecordsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }

        // Add the user to the database.
        if (records.size() == 0) {
//            UserAccountListController.getUserAccountList().addUserAccount(newCareProvider);
            CommentRecordListManager.AddCommentRecordsTask addCommentRecordsTask
                    = new CommentRecordListManager.AddCommentRecordsTask();
            addCommentRecordsTask.execute(newCommentRecord);
        }
    }
}
