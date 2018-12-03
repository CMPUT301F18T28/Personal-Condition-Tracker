/* Personal Condition Tracker : A simple and attractive Android application that allows an individual to
 document, track and review the progression of a personal health issue (a 'condition'), thus serving to facilitate
 enhanced clarity of communicating between patient and care provider, early detection and accurate prognosis with the
 aim of obtaining medical treatment as soon as possible.

 Document the facts - get the treatment you deserve!

 Copyright (C) 2018

 R. Voon; rcvoon@ualberta.ca
 D. Buksa; draydon@ualberta.ca
 W. Nichols; wnichols@ualberta.ca
 D. Douziech; douziech@ualberta.ca
 C. Neureuter; neureute@ualberta.ca


Redistribution and use in source and binary forms, with or without
modification, are permitted (subject to the limitations in the disclaimer
below) provided that the following conditions are met:

     * Redistributions of source code must retain the above copyright notice,
     this list of conditions and the following disclaimer.

     * Redistributions in binary form must reproduce the above copyright
     notice, this list of conditions and the following disclaimer in the
     documentation and/or other materials provided with the distribution.

     * Neither the name of the copyright holder nor the names of its
     contributors may be used to endorse or promote products derived from this
     software without specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY
THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
*/

package ca.ualberta.cs.personal_condition_tracker.Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;

import ca.ualberta.cs.personal_condition_tracker.ChronologicalComparator;


/**
 * RecordList stores, provides editing capability and monitoring via Listeners, for all of the Records
 * pertaining to a particular Condition.
 * <p>
 * @see Record
 * @see Condition
 * </p>
 * @author     R. Voon; rcvoon@ualberta.ca
 * @author     D. Buksa; draydon@ualberta.ca
 * @author     W. Nichols; wnichols@ualberta.ca
 * @author     D. Douziech; douziech@ualberta.ca
 * @author     C. Neureuter; neureute@ualberta.ca
 * @version    1.1, 11-18-18
 * @since      1.0
 */

public class RecordList {
    private ArrayList<Record> recordList;
    private transient ArrayList<Listener> listenerList = null;
    private Record recordOfInterest = null;
    /**
     * Constructor which initializes the arraylist of records.
     */
    public RecordList() {
        this.recordList = new ArrayList<Record>();
    }
    /**
     * Check if the record list has a specific record.
     * @return boolean
     */
    public boolean hasRecord(Record newRecord) {
        return this.recordList.contains(newRecord);
    }
    /**
     * Get a record given an index from the list.
     * @params Record
     * @return Record
     */
    public Record getRecord(int index){
        return this.recordList.get(index);
    }
    /**
     * Add a record to the list.
     * @params Record
     */
    public void addRecord(Record newRecord) {
        this.recordList.add(newRecord);
        notifyListeners();
    }
    /**
     * Delete a record from the list
     * @params Record
     */
    public void deleteRecord(Record newRecord) {
        this.recordList.remove(newRecord);
        notifyListeners();
    }
    /**
     * Modify a record in the list.
     * @params index, newRecord
     */
    public void editRecord(int index, Record newRecord) {
        this.recordList.set(index, newRecord);
    }
    /**
     * Sort the list of records by date.
     * @return ArrayList<Record>
     */
    public ArrayList<Record> sortByDate() {
        Collections.sort(this.recordList, new ChronologicalComparator());
        return recordList;
    }
    /**
     * Get a list of records with a specified keyword.
     * @return ArrayList<Record>
     */
    public ArrayList<Record> queryByKeyword(String keyword) {
        return new ArrayList<Record>();
    }
    /**
     * Get a list of records at a specified geo location.
     * @params keyword
     * @return ArrayList<Record>
     */
    public ArrayList<Record> queryByGeoLocation(LatLng location) {
        return new ArrayList<Record>();
    }
    /**
     * Get a list of records at a specified body location.
     * @params location
     * @return ArrayList<Record>
     */
    public ArrayList<Record> queryByBodyLocation(String keyword) {
        return new ArrayList<Record>();
    }
    /**
     * Get all the records from the list.
     * @params keyword
     * @return ArrayList<Record>
     */
    public ArrayList<Record> getRecords() {
        return recordList;
    }

    /**
     * Add a listener object.
     * @params Listener
     */
    public void addListener(Listener listener){
        getListenerList().add(listener);;
    }
    /**
     * Remove a listener object.
     * @params Listener
     */
    public void removeListener(Listener listener) {
        getListenerList().remove(listener);
    }
    /**
     * Get all the listeners.
     * @return ArrayList<Listener>
     */
    private ArrayList<Listener> getListenerList(){
        if(listenerList == null){
            listenerList = new ArrayList<>();
        }
        return listenerList;
    }
    /**
     * Tell listeners to update.
     */
    public void notifyListeners(){
        for(Listener listener: getListenerList()){
            listener.update();
        }
    }
    /**
     * Get the index of a specified record.
     * @params record
     * @return index
     */
    public int getRecordIndex(Record record) {
        int index = -1;
        if(recordList.contains(record)) {
            index = recordList.indexOf(record);
        }
        return index;
    }
    /**
     * Get a specified record.
     * @return Record
     */
    public Record getRecordOfInterest() {
        return recordOfInterest;
    }

    /**
     * Set the specified record to a new record.
     * @params Record
     */
    public void setRecordOfInterest(Record recordOfInterest) {
        this.recordOfInterest = recordOfInterest;
    }
    public void setRecords(ArrayList<Record> records) {
        this.recordList = records;
    }
}
