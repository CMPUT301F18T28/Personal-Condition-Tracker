package ca.ualberta.cs.personal_condition_tracker;

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
     * @param userAccount the account to be added.
     */
    public void addCondition(Condition condition){ getConditionList().addCondition(condition);}


}
