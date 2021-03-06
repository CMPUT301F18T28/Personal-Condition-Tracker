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
*/

package ca.ualberta.cs.personal_condition_tracker.Model;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Serves to store both an ArrayList of Condition objects and an ArrayList of Listeners, thus providing access, setting,
 * getting and modification to the former and monitoring via the latter. In the case of a Care Provider, a conditionOfInterest attribute
 * provides for user selection from the respective list.
 *
 * @author  R. Voon; rcvoon@ualberta.ca
 * @author  D. Buksa; draydon@ualberta.ca
 * @author  W. Nichols; wnichols@ualberta.ca
 * @author  D. Douziech; douziech@ualberta.ca
 * @author  C. Neureuter; neureute@ualberta.ca
 * @version 1.1, 11-18-18
 * @since   1.0
 */

public class ConditionList{

    private ArrayList<Condition> conditionList;
    private transient ArrayList<Listener> listenerList = null;
    private Condition conditionOfInterest = null;

    /*
     * Instantiates a ConditionList type object
     */
    public ConditionList(){
        this.conditionList = new ArrayList<>();
    }

    /**
     *Simply appends a Condition object to the existing list of conditions.
     * <P>
     * @param condition A Condition object to be added to the list.
     * @return Nothing
     * @see Condition
     */

    public void addCondition(Condition condition){
        conditionList.add(condition);
        notifyListeners();
    }

    /**
     * Removes a specified condition from the list thereof.
     *
     * <P>
     * @param condition A Condition object to be removed from the list.
     * @return boolean True if the condition has been removed or false otherwise.  An error message
     * logged if the specified condition does not exist.
     */
    public boolean deleteCondition(Condition condition) {
        boolean boolResult = conditionList.remove(condition);
        notifyListeners();
        if (!boolResult) {
            Log.i("Error","The condition cannot be found.");
        }
        else {
            Log.i("Condition removed", "You are healed.");
        }
        return boolResult;
    }

    /**
     * Provides the ability to edit three of the attributes of a Condition object: title, date and description .
     *
     * <P>
     * @param condition Condition object to be modified
     * @param title title given to a condition
     * @param date Date object specifying the date a condition was logged
     * @param description a description of the condition
     * @return Nothing
     *
     */

    //Edit one or all of these three: title, date, description...
    public void editCondition(Condition condition, String title, Date date, String description){

        //First verify the condition exists...
        int index = conditionList.indexOf(condition);

        if(index == -1){
            Log.i("Error","The condition cannot be found.");  //could change this to: throws conditionNotFoundException...
        }

        else {
            condition.setTitle(title);
            condition.setDate(date);
            condition.setDescription(description);
        }
        notifyListeners();
    }

    /**
     * Provides the ability to edit all of the attributes of a Condition object except for the constant that is used to restrict the input length of String parameters.
     * <P>
     * @param condition Condition object to be modified
     * @param title title given to a condition
     * @param date Date object specifying the date a condition was logged
     * @param description a description of the condition
     * @param recordList an ArrayList of Record objects representing entries pertaining to a condition
     * @param commentList an ArrayList of comments; supplied by Care Providers in response to patient conditions
     * @return Nothing
     * @see Record
     * @see RecordList
     */

    //Edit the whole shebang...
    public void editCondition(Condition condition, String title, Date date, String description, RecordList recordList, CommentRecordList commentList){

        //First verify the condition exists...
        int index = conditionList.indexOf(condition);

        if(index == -1){
            Log.i("Error","The condition cannot be found."); //could change this to: throws conditionNotFoundException...
        }

        else {
            condition.setTitle(title);
            condition.setDate(date);
            condition.setDescription(description);
            condition.setRecordList(recordList);
            condition.setCommentRecordList(commentList);
        }
    }



    /**
     * Verifies the existence of a supplied condition for a particular patient.
     * <P>
     * @param condition Condition object to requiring verification
     * @return Condition The same object supplied for testing otherwise an error message is simply logged.
     */

    //This method is strictly a search using an actual Condition object.  Additional methods for searching the list of Conditions by
    // description, etc. may have to be added later.

    public Condition searchConditions(Condition condition){

        //First verify the condition exists...
        int index = conditionList.indexOf(condition);

        Condition foundCondition = new Condition();

        if(index == -1){
            Log.i("Error","The condition cannot be found.");  //This format could be modified to use: throws conditionNotFoundException...
        }
        else {
            foundCondition = conditionList.get(index);
        }

        return foundCondition;
    }

    /**
     * Provides the number of conditions currently recorded for a specific patient.
     * <P>
     * @return int size of the list of conditions.
     */
    public int sizeOfList(){
        return this.conditionList.size();
    }


    /**
     * Sorts the list of conditions chronologically by date.
     * <P>
     * @return Nothing
     * @see java.util.Date
     */
    public void sortByDate(){

        Collections.sort(conditionList, new Condition());
    }


    /**
     * Prints, in the form of a log entry, the list of conditions chronologically, by date.
     * <P>
     * @return Nothing
     */
    public void printListByDate(){
        for(Condition condition : conditionList)
            // System.out.println("Date: " + condition.getDate());
            Log.d("Date: ", condition.getDate().toString());
    }

    /**
     * Sorts the list of conditions chronologically by date.
     * <P>
     * @return Nothing
     */

    public int getIndex(Condition condition){
        int index = -1;
        for (int i = 0; i < conditionList.size(); i++) {
            if (condition.getId().equals(conditionList.get(i).getId())) {
                index = i;
            }
        }
        return index;
    }



    /**
     * Obtains the condition from the list of conditions according to a supplied index.
     * <P>
     * @return Condition
     * @throws IndexOutOfBoundsException when the index < 0 || index >= conditionList.size()
     * @see IndexOutOfBoundsException
     */

    public Condition getByIndex(int index){
        Condition condition = conditionList.get(index);
        return condition;
    }

    /**
     * Provides the list of conditions, that is, the conditionList attribute.
     * <P>
     * @return ArrayList<Condition>
     */

    public ArrayList<Condition> getConditions(){
        return conditionList;
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
     * Provides the conditionOfInterest attribute; a particular patient condition that has been selected.
     * <P>
     * @return Condition
     * @see Condition
     */

    public Condition getConditionOfInterest() {
        return conditionOfInterest;
    }


    /**
     * Registers a particular patient condition as selected by the user.
     * <P>
     * @param conditionOfInterest Condition object
     * @return Nothing
     */

    public void setConditionOfInterest(Condition conditionOfInterest) {
        this.conditionOfInterest = conditionOfInterest;
    }

    /**
     * Sets the conditions in the conditionlist to a provided list of a conditions
     * @param conditions
     */
    public void setConditions(ArrayList<Condition> conditions) {
        this.conditionList = conditions;
    }

}