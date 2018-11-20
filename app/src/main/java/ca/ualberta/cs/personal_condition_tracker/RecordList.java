package ca.ualberta.cs.personal_condition_tracker;

/**
 * RecordList stores, provides editing capability and monitoring via Listeners, for all of the Records
 * pertaining to a particular Condition.
 * <p>
 * @see ca.ualberta.cs.personal_condition_tracker.Record
 * @see ca.ualberta.cs.personal_condition_tracker.Condition
 * </p>
 * @author     R. Voon; rcvoon@ualberta.ca
 * @author     D. Buksa; draydon@ualberta.ca
 * @author     W. Nichols; wnichols@ualberta.ca
 * @author     D. Douziech; douziech@ualberta.ca
 * @author     C. Neureuter; neureute@ualberta.ca
 * @version    1.1, 11-18-18
 * @since      1.0
 */

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class RecordList {
    private ArrayList<Record> record_list;
    private transient ArrayList<Listener> listenerList = null;

    //Constructor
    RecordList() {
        this.record_list = new ArrayList<Record>();
    }

    /**
     * Serves to check if a record is extant within the system.
     * @param new_record A record pertaining to a particular condition
     * @return boolean True if the record already exists, false otherwise
     * @see Record
     * @see Condition
     */

    public boolean hasRecord(Record new_record) {
        return this.record_list.contains(new_record);
    }

    /**
     * Obtains a particular record, via its index, pertaining to a particular condition registered by a Patient.
     * @param index The index of the record within the list thereof
     * @return Record A particular record/entry regarding a particular condition
     * @see Record
     * @see Condition
     */

    public Record getRecord(int index){
        return this.record_list.get(index);
    }

    /**
     * Serves to append a new record, pertaining to specific condition, to the list thereof.
     * @param new_record A record pertaining to a particular condition
     * @return Nothing
     * @see Record
     * @see Condition
     */

    public void addRecord(Record new_record) {
        this.record_list.add(new_record);
        notifyListeners();
    }

    /**
     * Serves to remove a record that had been previously registered to a specific condition.
     * @param new_record A record pertaining to a particular condition
     * @return Nothing
     * @see Record
     * @see Condition
     */

    public void deleteRecord(Record new_record) {
        this.record_list.remove(new_record);
        notifyListeners();
    }

    /**
     * Provides editing capability of a particular record by overwriting it at the same index with another.
     * @param index Index of the record to be edited
     * @param new_record A record pertaining to a particular condition
     * @return Nothing
     * @see Record
     */
    public void editRecord(int index, Record new_record) {
        this.record_list.set(index, new_record);
    }

    //NOTE: Several of the functions described below are yet to be fully constructed.

    /**
     * Chronologically orders a list of records pertaining to a particular condition.
     * @return ArrayList<Record> List of records pertaining to a particular condition
     * @see Record
     * @see Condition
     */

    public ArrayList<Record> sortByDate() {
        return new ArrayList<Record>();
    }

    /**
     * Serves to query a remote server database using elasticsearch to locate all records containing
     * the specified parameter.
     * @param keyword Phrase or word to query
     * @return  ArrayList<Record> List of all records containing the specified parameter
     * @see Record
     */

    public ArrayList<Record> queryByKeyword(String keyword) {
        return new ArrayList<Record>();
    }

    /**
     * Provides a list of records each containing geo-locations in or around the given location.
     * @param location Latitude and longitude to query; a LatLng object
     * @returns ArrayList<Record> List of all records containing or perhaps approximating the specified parameter
     * @see LatLng
     */

    public ArrayList<Record> queryByGeoLocation(LatLng location) {
        return new ArrayList<Record>();
    }

    /**
     * Provides a list of records each containing a body location equivalent to the supplied parameter.
     * @param keyword Specifies a region of the human body
     * @returns ArrayList<Record> List of all records containing the specified parameter
     * @see BodyLocation
     */

    public ArrayList<Record> queryByBodyLocation(String keyword) {
        return new ArrayList<Record>();
    }

    /**
     * Provides a list of records.
     * @returns ArrayList<Record> List of records
     * @see Record
     */

    public ArrayList<Record> getRecords() {
        return record_list;
    }

    /**
     * Appends a Listener object to the list thereof.
     * @param listener Listener object
     * @return Nothing
     * @see Listener
     */

    public void addListener(Listener listener){
        getListenerList().add(listener);;
    }

    /**
     * Removes a given Listener object from the list thereof.
     * @param listener Listener object
     * @return Nothing
     * @see Listener
     */

    public void removeListener(Listener listener) {
        getListenerList().remove(listener);
    }

    /**
     * Obtains the list of Listeners, that is, the listenerList attribute.
     * <P>
     * If called for the first time
     * this method serves to initialize the list.
     * </P>
     * @return ArrayList<Listener>
     */

    private ArrayList<Listener> getListenerList(){
        if(listenerList == null){
            listenerList = new ArrayList<>();
        }
        return listenerList;
    }

    /**
     * Serves to update all Listener objects contained in the list thereof.
     * @return Nothing
     * @see Listener
     */

    public void notifyListeners(){
        for(Listener listener: getListenerList()){
            listener.update();
        }
    }

    /**
     * Obtains the index corresponding to a particular record within the list thereof.
     * @param record Record object corresponding to a particular entry on a Condition
     * @return int Index of a record
     * @see Record
     * @see Condition
     */

    public int getRecordIndex(Record record) {
        int index = -1;
        if(record_list.contains(record)) {
            index = record_list.indexOf(record);
        }
        return index;
    }
}
