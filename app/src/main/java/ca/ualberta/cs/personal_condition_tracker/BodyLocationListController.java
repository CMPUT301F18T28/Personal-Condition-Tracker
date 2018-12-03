package ca.ualberta.cs.personal_condition_tracker;

import android.util.Log;

import java.util.ArrayList;

public class BodyLocationListController {
    private static BodyLocationList bodyLocationList = null;
    /**
     * Gets the user account list.
     * @return UserAccountList userAccountList
     */
    static public BodyLocationList getBodyLocationList() {
        if ((bodyLocationList) == null) {
            bodyLocationList = new BodyLocationList();
        }
        return bodyLocationList;
    }

    public ArrayList<BodyLocation> loadBodyLocations(Record record) {
        ArrayList<BodyLocation>  bodyLocations = new ArrayList<>();
        BodyLocationListManager.GetBodyLocationsTask getBodyLocationsTask =
                new BodyLocationListManager.GetBodyLocationsTask();
        String query = "{ \"query\": {\"match\": { \"associatedRecordID\" : \""+ record.getId() +"\" } } }";
        Log.e("Error", record.getId());
        try {
            bodyLocations = getBodyLocationsTask.execute(query).get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the records out of the async object.");
        }
        Log.e("Error", Integer.toString(bodyLocations.size()));
        return bodyLocations;
    }

    // Add a care provider to the server.
    public void createBodyLocation(BodyLocation newBodyLocation) {
        // Check if the user has already signed up
        BodyLocationListManager.GetBodyLocationsTask getBodyLocationsTask =
                new BodyLocationListManager.GetBodyLocationsTask();
        String query = "{ \"query\": {\"match\": { \"id\" : \"" + newBodyLocation.getId() + "\" } } }";
        getBodyLocationsTask.execute(query);
        ArrayList<BodyLocation> bodyLocations = new ArrayList<>();
        try {
            bodyLocations = getBodyLocationsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }

        // Add the user to the database.
        if (bodyLocations.size() == 0) {
//            UserAccountListController.getUserAccountList().addUserAccount(newCareProvider);
            BodyLocationListManager.AddBodyLocationsTask addBodyLocationsTask
                    = new BodyLocationListManager.AddBodyLocationsTask();
            addBodyLocationsTask.execute(newBodyLocation);
        } else {
        }
    }

    public void deleteBodyLocation(BodyLocation selectedBodyLocation) {
        BodyLocationListManager.DeleteBodyLocationsTask deleteBodyLocationsTask =
                new BodyLocationListManager.DeleteBodyLocationsTask();
        deleteBodyLocationsTask.execute(selectedBodyLocation);
    }

}
