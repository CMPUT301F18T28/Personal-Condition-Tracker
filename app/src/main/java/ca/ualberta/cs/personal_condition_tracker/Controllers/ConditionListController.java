package ca.ualberta.cs.personal_condition_tracker.Controllers;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import ca.ualberta.cs.personal_condition_tracker.Activities.ModifyConditionActivity;
import ca.ualberta.cs.personal_condition_tracker.Managers.ConditionListManager;
import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.ConditionList;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;
import ca.ualberta.cs.personal_condition_tracker.Model.UserAccount;

public class ConditionListController {

    private static ConditionList conditionList = null;
    /**
     * Gets the user account list.
     * @return UserAccountList userAccountList
     */
    static public ConditionList getConditionList() {
        if ((conditionList) == null) {
            conditionList = new ConditionList();
        }
        return conditionList;
    }
    /**
     * Add an account to the user account list.
     */
    public void addCondition(Condition condition){ getConditionList().addCondition(condition);}

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

    // Add a care provider to the server.
    public void createCondition(Condition newCondition) {
        // Check if the user has already signed up
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

        // Add the user to the database.
        if (conditions.size() == 0) {
//            UserAccountListController.getUserAccountList().addUserAccount(newCareProvider);
            ConditionListManager.AddConditionsTask addConditionsTask
                    = new ConditionListManager.AddConditionsTask();
            addConditionsTask.execute(newCondition);
        }
    }

    public void editCondition(Condition oldCondition, Condition newCondition) {
        newCondition.setId(oldCondition.getId());
        ConditionListManager.DeleteConditionsTask deleteConditionsTask =
                new ConditionListManager.DeleteConditionsTask();
        deleteConditionsTask.execute(oldCondition);
        ConditionListManager.AddConditionsTask addConditionsTask
                = new ConditionListManager.AddConditionsTask();
        addConditionsTask.execute(newCondition);
    }

    public ArrayList<Condition> searchByKeyword(String keywords, String userID) {
        ArrayList<Condition>  conditions = new ArrayList<>();
        ConditionListManager.GetConditionsTask getConditionsTask =
                new ConditionListManager.GetConditionsTask();
        String query = "{ " +
                "  \"size\": 100," +
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
