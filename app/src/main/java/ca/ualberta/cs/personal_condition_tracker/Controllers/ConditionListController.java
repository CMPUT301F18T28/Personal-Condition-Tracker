package ca.ualberta.cs.personal_condition_tracker.Controllers;

import android.util.Log;

import java.util.ArrayList;

import ca.ualberta.cs.personal_condition_tracker.Managers.ConditionListManager;
import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.ConditionList;

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
