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

import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.Comparator;



public class Condition implements Comparator<Condition>, Comparable<Condition> {

    private String title;
    private Date date;
    private String description;
    private RecordList recordList;
    private ArrayList<String> commentList;
    private static final Integer MAX_CHARACTERS = 100;



    //Start of new code....


    // I needed to explicitly state the default constructor for use elsewhere, and I thought that
    // we should have a constructor that just takes in title, date and description.  I also wanted it
    // for doing some testing in the conditionList class.

   // -DD

    Condition() {}

    Condition(String title, Date date, String description){
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public int compareTo(Condition condition){
        return this.getDate().compareTo(condition.getDate());
    }


    public int compare(Condition conditionOne, Condition conditionTwo){
        return conditionOne.getDate().compareTo(conditionTwo.getDate());
    }


    //End of new code




    Condition(String title, String description) {

    }


    Condition(String title, Date date, String description, RecordList recordList, ArrayList<String> commentList) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.recordList = recordList;
        this.commentList = commentList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {this.title = title; }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecordList getRecordList() {
        return recordList;
    }

    public void setRecordList(RecordList recordList) {
        this.recordList = recordList;
    }

    public ArrayList<String> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<String> commentList) {
        this.commentList = commentList;
    }
}
