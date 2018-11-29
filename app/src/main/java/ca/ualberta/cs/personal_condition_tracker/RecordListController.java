package ca.ualberta.cs.personal_condition_tracker;

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



}
