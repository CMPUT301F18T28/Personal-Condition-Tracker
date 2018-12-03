package ca.ualberta.cs.personal_condition_tracker.Managers;

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
import ca.ualberta.cs.personal_condition_tracker.Model.Photograph;

public class PhotographListManager {

    private static JestDroidClient client;

    /**
     * Add user accounts to the server
     * @param userAccounts the accounts to be added.
     */
    public static class AddPhotographsTask extends AsyncTask<Photograph, Void, Void> {

        @Override
        protected Void doInBackground(Photograph... photographs) {
            verifySettings();

            for (Photograph photograph : photographs) {
                Index index = new Index.Builder(photograph).index("cmput301f18t28test").type("photograph").build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        if (photograph.getId() == null) {
                            photograph.setId(result.getId());
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
    public static class GetPhotographsTask extends AsyncTask<String, Void, ArrayList<Photograph>> {
        @Override
        protected ArrayList<Photograph> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Photograph> photographs = new ArrayList<>();

            Search search = new Search.Builder( search_parameters[0])
                    //Search search = new Search.Builder( search_parameters[0] )
                    .addIndex("cmput301f18t28test")
                    .addType("photograph")
                    .build();

            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<Photograph> foundPhotos = result.getSourceAsObjectList(Photograph.class);
                    photographs.addAll(foundPhotos);
                    Log.e("Error", "Success.");
                }
                else {
                    Log.e("Error", "The search query failed to find any conditions that matched.");
                }
            }
            catch (Exception e) {
                Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            return photographs;
        }
    }
    /**
     * Get user accounts from the server.
     */
    public static class DeletePhotographsTask extends AsyncTask<Photograph, Void, Void> {
        @Override
        protected Void doInBackground(Photograph... photographs) {
            verifySettings();

            Photograph photograph = photographs[0];
            String jestID = photograph.getId();
            Delete delete = new Delete.Builder(jestID).index("cmput301f18t28test").type("photograph").build();

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
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://es2.softwareprocess.ca:8080/");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }


}
