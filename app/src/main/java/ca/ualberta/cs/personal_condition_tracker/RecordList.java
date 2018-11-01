package ca.ualberta.cs.personal_condition_tracker;

import java.util.ArrayList;

public class RecordList {
    private ArrayList<Record> record_list;

    RecordList() {
        this.record_list = new ArrayList<Record>();
    }

    public boolean hasRecord(Record new_record) {
        return this.record_list.contains(new_record);
    }
    public Record getRecord(int index){
        return this.record_list.get(index);
    }

    public void addRecord(Record new_record) {
        this.record_list.add(new_record);
    }
    public void deleteRecord(Record new_record) {
        this.record_list.remove(new_record);
    }
    public void editRecord(int index, Record new_record) {
        this.record_list.set(index, new_record);
    }
    public ArrayList<Record> queryByKeyword(String keyword) {
        return new ArrayList<Record>();
    }

}
