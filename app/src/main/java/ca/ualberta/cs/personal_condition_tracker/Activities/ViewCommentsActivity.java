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

package ca.ualberta.cs.personal_condition_tracker.Activities;

/**
 * ViewCommentsActivity allows a user to see a care provider's comments for a given record.
 *
 * @author      R. Voon; rcvoon@ualberta.ca
 * @author      D. Buksa; draydon@ualberta.ca
 * @author      W. Nichols; wnichols@ualberta.ca
 * @author      D. Douziech; douziech@ualberta.ca
 * @author      C. Neureuter; neureute@ualberta.ca
 * @version     1.1, 11-18-18
 * @since       1.0
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;

import ca.ualberta.cs.personal_condition_tracker.Controllers.CommentRecordListController;
import ca.ualberta.cs.personal_condition_tracker.Model.CommentRecord;
import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.Listener;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;
import ca.ualberta.cs.personal_condition_tracker.R;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;
/**
 * ViewCommentsActivity lets a user view all the comments for a given condition.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
public class ViewCommentsActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private CommentRecordListController commentRecordListController = new CommentRecordListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition conditionOfInterest = accountOfInterest.getConditionList().getConditionOfInterest();
    private CommentRecord commentOfInterest = conditionOfInterest.getCommentRecordList().getCommentOfInterest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_comments);
        ArrayList<CommentRecord> commentRecords = commentRecordListController.loadCommentRecords(conditionOfInterest);
        userAccountListController.getUserAccountList().getAccountOfInterest().getConditionList().getConditionOfInterest().getCommentRecordList().setCommentRecords(commentRecords);

        setupListView();
    }

    public void setupListView(){
        //Setup adapter for condition list, and display the list.
        ListView listView = findViewById(R.id.commentListView);
        Collection<CommentRecord> commentCollection = conditionOfInterest.getCommentRecordList().getCommentRecords();
        final ArrayList<CommentRecord> comments = new ArrayList<> (commentCollection);
        final ArrayAdapter<CommentRecord> commentsArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, comments);
        listView.setAdapter(commentsArrayAdapter);

        // Added a change observer
        conditionOfInterest.addListener(new Listener() {
            @Override
            public void update() {
                comments.clear();
                Collection<CommentRecord> commentCollection = conditionOfInterest.getCommentRecordList().getCommentRecords();
                comments.addAll(commentCollection);
                commentsArrayAdapter.notifyDataSetChanged();
            }
        });

//        //Ugly code of OnItemLongClickListener
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                AlertDialog.Builder adb = new AlertDialog.Builder(ViewCommentsActivity.this);
//                adb.setMessage("Would you like to edit or delete " + comments.get(position).toString() + " ?");
//                adb.setCancelable(true);
//                final int finalPosition = position;
//
//                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//
//                adb.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//
//                adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //Do nothing, simply allow the dialog to close
//                    }
//                });
//                adb.show();
//                return true;
//            }
//        });
    }

}
