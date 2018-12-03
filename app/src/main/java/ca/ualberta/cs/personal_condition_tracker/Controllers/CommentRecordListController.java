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

package ca.ualberta.cs.personal_condition_tracker.Controllers;

import android.util.Log;

import java.util.ArrayList;

import ca.ualberta.cs.personal_condition_tracker.Managers.CommentRecordListManager;
import ca.ualberta.cs.personal_condition_tracker.Model.CommentRecord;
import ca.ualberta.cs.personal_condition_tracker.Model.CommentRecordList;
import ca.ualberta.cs.personal_condition_tracker.Model.Condition;

/**
 * CommentRecordListController performs operations on a CommentRecordList object.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
public class CommentRecordListController {

    private static CommentRecordList commentRecordList = null;
    /**
     * Gets the comment record list.
     * @return commentRecordList
     */
    static public CommentRecordList getCommentRecordList() {
        if ((commentRecordList) == null) {
            commentRecordList = new CommentRecordList();
        }
        return commentRecordList;
    }

    /**
     * Load the comment records for a given condition from the server.
     * @param condition
     * @return
     */
    public ArrayList<CommentRecord> loadCommentRecords(Condition condition) {
        ArrayList<CommentRecord> commentRecords = new ArrayList<>();
        CommentRecordListManager.GetCommentRecordsTask getCommentRecordsTask =
                new CommentRecordListManager.GetCommentRecordsTask();
        String query = "{ \"query\": {\"match\": { \"conditionIDForComment\" : \""+ condition.getId() +"\" } } }";
        getCommentRecordsTask.execute(query);
        try {
            commentRecords = getCommentRecordsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }
        return commentRecords;
    }
    // Add a comment record to the server.
    public void createCommentRecord(CommentRecord newCommentRecord) {
        // Check if the comment record already exists
        CommentRecordListManager.GetCommentRecordsTask getCommentRecordsTask =
                new CommentRecordListManager.GetCommentRecordsTask();
        String query = "{ \"query\": {\"match\": { \"id\" : \""+ newCommentRecord.getId() +"\" } } }";
        getCommentRecordsTask.execute(query);
        ArrayList<CommentRecord> records = new ArrayList<>();
        try {
            records = getCommentRecordsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }

        // Add the comment record to the database.
        if (records.size() == 0) {
            CommentRecordListManager.AddCommentRecordsTask addCommentRecordsTask
                    = new CommentRecordListManager.AddCommentRecordsTask();
            addCommentRecordsTask.execute(newCommentRecord);
        }
    }
}
