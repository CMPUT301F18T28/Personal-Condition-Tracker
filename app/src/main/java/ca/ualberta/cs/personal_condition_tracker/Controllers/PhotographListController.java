package ca.ualberta.cs.personal_condition_tracker;

import android.util.Log;

import java.util.ArrayList;

public class PhotographListController {

    private static PhotographList photographList = null;

    static public PhotographList getPhotographList() {
        if ((photographList) == null) {
            photographList = new PhotographList();
        }
        return photographList;
    }

    public ArrayList<Photograph> loadPhotographs(Record record) {
        ArrayList<Photograph>  photographs = new ArrayList<>();
        PhotographListManager.GetPhotographsTask getPhotographsTask =
                new PhotographListManager.GetPhotographsTask();
        String query = "{ \"query\": {\"match\": { \"recordIDForPhotograph\" : \""+ record.getId() +"\" } } }";
        try {
            photographs = getPhotographsTask.execute(query).get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the records out of the async object.");
        }
        Log.e("Error", Integer.toString(photographs.size()));
        return photographs;
    }

    // Add a care provider to the server.
    public void createPhotograph(Photograph newPhotograph) {
        // Check if the user has already signed up
        PhotographListManager.GetPhotographsTask getPhotographsTask =
                new PhotographListManager.GetPhotographsTask();
        String query = "{ \"query\": {\"match\": { \"id\" : \"" + newPhotograph.getId() + "\" } } }";
        getPhotographsTask.execute(query);
        ArrayList<Photograph> photographs = new ArrayList<>();
        try {
            photographs = getPhotographsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }

        // Add the user to the database.
        if (photographs.size() == 0) {
//            UserAccountListController.getUserAccountList().addUserAccount(newCareProvider);
            PhotographListManager.AddPhotographsTask addPhotographsTask
                    = new PhotographListManager.AddPhotographsTask();
            addPhotographsTask.execute(newPhotograph);
        } else {
        }
    }

}
