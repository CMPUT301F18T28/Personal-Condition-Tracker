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

package ca.ualberta.cs.personal_condition_tracker;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;




public class ConditionList{

    private ArrayList<Condition> condition_list;

    ConditionList(){

        this.condition_list = new ArrayList<Condition>();
    }

    public void addCondition(Condition condition){
        condition_list.add(condition);
    }


    public boolean deleteCondition(Condition condition) {
        boolean boolResult = condition_list.remove(condition);
        if (!boolResult) {
            Log.i("Error","The condition cannot be found.");
        }
        else {
            Log.i("Condition removed", "You are healed.");
        }
        return boolResult;
    }


    //Edit one or all of these three: title, date, description...
    public void editCondition(Condition condition, String title, Date date, String description){

        //First verify the condition exists...
        int index = condition_list.indexOf(condition);

        if(index == -1){
            Log.i("Error","The condition cannot be found.");  //could change this to: throws conditionNotFoundException...
        }

        else {
            condition.setTitle(title);
            condition.setDate(date);
            condition.setDescription(description);
        }
    }

    //Edit the whole shebang...
    public void editCondition(Condition condition, String title, Date date, String description, RecordList recordList, ArrayList<String> commentList){

            //First verify the condition exists...
        int index = condition_list.indexOf(condition);

        if(index == -1){
            Log.i("Error","The condition cannot be found."); //could change this to: throws conditionNotFoundException...
        }

        else {
            condition.setTitle(title);
            condition.setDate(date);
            condition.setDescription(description);
            condition.setRecordList(recordList);
            condition.setCommentList(commentList);
        }
    }


    //This method is strictly a search using an actual Condition object.  Additional methods for searching the list of Conditions by
    // description, etc. may have to be added later.

    public Condition searchConditions(Condition condition){

        //First verify the condition exists...
        int index = condition_list.indexOf(condition);

        Condition foundCondition = new Condition();

        if(index == -1){
            Log.i("Error","The condition cannot be found.");  //This format could be modified to use: throws conditionNotFoundException...
        }
        else {
            foundCondition = condition_list.get(index);
        }

        return foundCondition;
    }

    public int sizeOfList(){
        return this.condition_list.size();
    }


    public void sortByDate(){

        Collections.sort(condition_list, new Condition());
    }


    public void printListByDate(){
        for(Condition condition : condition_list)
            // System.out.println("Date: " + condition.getDate());
            Log.d("Date: ", condition.getDate().toString());
    }

    public Condition getByIndex(int index){

        Condition condition = condition_list.get(index);
        return condition;
    }

}

