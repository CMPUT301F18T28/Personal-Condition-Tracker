package ca.ualberta.cs.personal_condition_tracker;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

public class CommentRecordListManager {

    private static JestDroidClient client;

    /**
     * Add user accounts to the server
     */
    public static class AddCommentRecordsTask extends AsyncTask<CommentRecord, Void, Void> {

        @Override
        protected Void doInBackground(CommentRecord... commentRecords) {
            verifySettings();

            for (CommentRecord commentRecord : commentRecords) {
                Index index = new Index.Builder(commentRecord).index("cmput301f18t28test").type("commentRecord").build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        if (commentRecord.getId() == null) {
                            commentRecord.setId(result.getId());
                        }
                        Log.e("Error", "Success.");
                    }
                    else {
                        Log.e("Error", "Elastic search was not able to add the condition.");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the conditions.");
                }

            }
            return null;
        }
    }

    /**
     * Get user accounts from the server.
     */
    public static class GetCommentRecordsTask extends AsyncTask<String, Void, ArrayList<CommentRecord>> {
        @Override
        protected ArrayList<CommentRecord> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<CommentRecord> commentRecords = new ArrayList<>();

            Search search = new Search.Builder( search_parameters[0])
                    //Search search = new Search.Builder( search_parameters[0] )
                    .addIndex("cmput301f18t28test")
                    .addType("commentRecord")
                    .build();

            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<CommentRecord> foundRecords = result.getSourceAsObjectList(CommentRecord.class);
                    commentRecords.addAll(foundRecords);
                    Log.e("Error", "Success.");
                }
                else {
                    Log.e("Error", "The search query failed to find any conditions that matched.");
                }
            }
            catch (Exception e) {
                Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            return commentRecords;
        }
    }
    /**
     * Get user accounts from the server.
     */
    public static class DeleteCommentRecordsTask extends AsyncTask<CommentRecord, Void, Void> {
        @Override
        protected Void doInBackground(CommentRecord... commentRecords) {
            verifySettings();

            CommentRecord commentRecord = commentRecords[0];
            String jestID = commentRecord.getId();
            Delete delete = new Delete.Builder(jestID).index("cmput301f18t28test").type("record").build();

            try {
                DocumentResult result = client.execute(delete);
                if (result.isSucceeded()) {
                    Log.e("Error", "Success.");
                }
                else {
                    Log.e("Error", "The search query failed to find any conditions that matched.");
                }
            }
            catch (Exception e) {
                Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            return null;
        }
    }


    /**
     * Set up the server.
     */
    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080/");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

}
