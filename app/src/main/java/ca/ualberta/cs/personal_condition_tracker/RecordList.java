package ca.ualberta.cs.personal_condition_tracker;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class RecordList {
    private ArrayList<Record> record_list;
    private transient ArrayList<Listener> listenerList = null;
    private Record recordOfInterest = null;

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
        notifyListeners();
    }
    public void deleteRecord(Record new_record) {
        this.record_list.remove(new_record);
        notifyListeners();
    }
    public void editRecord(int index, Record new_record) {
        this.record_list.set(index, new_record);
    }
    public ArrayList<Record> sortByDate() {
        return new ArrayList<Record>();
    }
    public ArrayList<Record> queryByKeyword(String keyword) {
        return new ArrayList<Record>();
    }
    public ArrayList<Record> queryByGeoLocation(LatLng location) {
        return new ArrayList<Record>();
    }
    public ArrayList<Record> queryByBodyLocation(String keyword) {
        return new ArrayList<Record>();
    }

    public ArrayList<Record> getRecords() {
        return record_list;
    }

    public void addListener(Listener listener){
        getListenerList().add(listener);;
    }

    public void removeListener(Listener listener) {
        getListenerList().remove(listener);
    }

    private ArrayList<Listener> getListenerList(){
        if(listenerList == null){
            listenerList = new ArrayList<>();
        }
        return listenerList;
    }

    public void notifyListeners(){
        for(Listener listener: getListenerList()){
            listener.update();
        }
    }

    public int getRecordIndex(Record record) {
        int index = -1;
        if(record_list.contains(record)) {
            index = record_list.indexOf(record);
        }
        return index;
    }

    public Record getRecordOfInterest() {
        return recordOfInterest;
    }

    public void setRecordOfInterest(Record recordOfInterest) {
        this.recordOfInterest = recordOfInterest;
    }
}
