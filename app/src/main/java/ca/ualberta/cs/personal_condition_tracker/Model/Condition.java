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

import java.util.ArrayList;
import java.util.Date;
import java.util.Comparator;

import io.searchbox.annotations.JestId;

/**
 * The Condition class is used to model/document the ailments of a patient.
 * <p>
 * Note: Within the context of this application, a condition is a particular ailment of a patient,
 * a record is a specific entry pertaining to a
 * condition.
 * @see Record
 * @see RecordList
 * </p>
 * @author      D. Buska; draydon@ualberta.ca
 * @author      D. Douziech; douziech@ualberta.ca
 * @author      R. Voon; rcvoon@ualberta.ca
 * @author      W. Nichols; wnichols@ualberta.ca
 * @author      C. Neureuter; neureute@ualberta.ca
 * @version     1.1, 11-18-18
 * @since       1.0
 */


public class Condition implements Comparator<Condition>, Comparable<Condition> {

    private String title;
    private Date date;
    private String description;
    private RecordList recordList = new RecordList();
    private CommentRecordList commentRecordList = new CommentRecordList();
    private transient ArrayList<Listener> listenerList = null;
    private String associatedUserID;
    private static final int MAX_TITLE_LENGTH = 30;
    private static final int MAX_DESCRIPTION_LENGTH = 300;

    @JestId
    private String id = null;

    //Constructors

    Condition() {}

    public Condition(String title, Date date, String description){
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public Condition(String title, Date date, String description, RecordList recordList, CommentRecordList commentList) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.recordList = recordList;
        this.commentRecordList = commentList;
    }

    public Condition(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     * Compares the dates of this condition object against another one supplied, to determine the relative chronological ordering.
     * @param condition Condition object to be compared against
     * @return int Negative if the given parameter is less, zero if equal and positive if greater
     * @see Comparator
     * @see Date
     */

    public int compareTo(Condition condition){
        return this.getDate().compareTo(condition.getDate());
    }

    /**
     * Compares the dates of two Condition objects to determine the relative chronological ordering.
     * @param conditionOne The first Condition object; the first is compared against the second
     * @param conditionTwo The second Condition object; the first is compared against the second
     * @return int Negative if the given parameter is less, zero if equal and positive if greater
     * @see Comparator
     * @see Date
     */

    public int compare(Condition conditionOne, Condition conditionTwo){
        return conditionOne.getDate().compareTo(conditionTwo.getDate());
    }

    /**
     * Provides the title given to a particular condition.
     * @return String The title of a condition
     */

    public String getTitle() {
        return title;
    }

    /**
     * Registers the title for a particular condition.
     * @param title The appellation given to a condition serving to distinguish it amongst a list thereof.
     * @return Nothing
     */

    public void setTitle(String title) {this.title = title; }


    /**
     * Provides the date a particular condition was created.
     * @return date The calendar date specifying when a condition was first registered in the system.
     * @see Date
     */

    public Date getDate() {
        return date;
    }

    /**
     * Registers the current date; used to specify when a condition was first recorded within the system.
     * @return Nothing
     * @see Date
     */

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Provides the description given to a particular condition.
     * @return description Summary of an ailment
     */

    public String getDescription() {
        return description;
    }

    /**
     * Serves to register a description of a particular condition.
     * @param description Summary of an ailment
     * @return Nothing
     */

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Get the user ID the condition is related to.
     * @return String userID
     */
    public String getAssociatedUserID() {
        return associatedUserID;
    }

    /**
     * Set the user ID that the condition is related to.
     * @param associatedUserID
     */
    public void setAssociatedUserID(String associatedUserID) {
        this.associatedUserID = associatedUserID;
    }

    /**
     * Get id.
     * @return String id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set id.
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Provides the list of all recorded entries specific to a condition.
     * @return RecordList List of Record objects pertaining to a particular condition
     * @see Record
     * @see RecordList
     */

    public RecordList getRecordList() {
        return recordList;
    }

    /**
     * Serves to register a list of Record objects representing all entries pertaining to a condition.
     * @param recordList RecordList object; a record of all entries pertaining to a condition.
     * @return Nothing
     * @see Record
     * @see RecordList
     */

    public void setRecordList(RecordList recordList) {
        this.recordList = recordList;
    }

    /**
     * Provides the list of comments, made by Care Providers, on the conditions of a Patient.
     * @return ArrayList<String> A list of all comments provided by an assigned Care Provider
     * @see Record
     * @see RecordList
     */

    public CommentRecordList getCommentRecordList() {
        return commentRecordList;
    }

    /**
     * Serves to register a list of comments made by a Care Provider on the condition(s) of an assigned Patient.
     * @return Nothing
     */

    public void setCommentRecordList(CommentRecordList commentRecordList) {
        this.commentRecordList = commentRecordList;
    }

    /**
     * Serves to concatenate the title, date and description of a particular condition.
     * @return String A concatenation of the title, date and description of this condition
     * @see Class
     */

    @Override
    public String toString(){
        return getTitle() + "\n" + getDate().toString() + "\n" + getDescription();
    }

    /**
     * Appends a Listener object to the list thereof.
     * <P>
     * @param listener Listener object
     * @return Nothing
     * @see Listener
     */

    public void addListener(Listener listener){
        getListenerList().add(listener);
    }


    /**
     * Removes a given Listener object from the list thereof.
     * <P>
     * @param listener Listener object
     * @return Nothing
     * @see Listener
     */

    public void removeListener(Listener listener) {
        getListenerList().remove(listener);
    }


    /**
     * Obtains the list of Listeners, that is, the listenerList attribute. If called for the first time
     * this method serves to initialize the list.
     * <P>
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
     * <P>
     * @return Nothing
     * @see Listener
     */

    public void notifyListeners(){
        for(Listener listener: getListenerList()){
            listener.update();
        }
    }


    /**
     * Get the maximum length of a title for a condition
     * @return int MAX_TITLE_LENGTH
     */
    public static int getMaxTitleLength() {
        return MAX_TITLE_LENGTH;
    }

    /**
     * Get the maximum length of a description for a condition
     * @return int MAX_DESCRIPTION_LENGTH
     */
    public static int getMaxDescriptionLength() {
        return MAX_DESCRIPTION_LENGTH;
    }

}