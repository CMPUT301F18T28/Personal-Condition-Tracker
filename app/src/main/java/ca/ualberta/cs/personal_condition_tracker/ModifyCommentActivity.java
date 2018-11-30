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

package ca.ualberta.cs.personal_condition_tracker;

/**
 * Allows a care provider to add or edit a comment to a patient's record.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


//TODO: For Project Part 5: Implement this activity.
public class ModifyCommentActivity extends AppCompatActivity {
    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition conditionOfInterest = accountOfInterest.getConditionList().getConditionOfInterest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_comment);
        setup();
    }

    public void setup(){
        Intent intent = new Intent();
        EditText commentText = findViewById(R.id.modifyCommentText);
        String oldComment = intent.getStringExtra("comment");
        commentText.setText(oldComment);
    }

    public void modifyCommentConfirm(View v){
        //Check to see if this is an old comment, or a new one.
        Intent intent = new Intent();
        EditText commentText = findViewById(R.id.modifyCommentText);
        String oldComment = intent.getStringExtra("comment");
        String newComment = commentText.getText().toString();
        if(oldComment == null){
            conditionOfInterest.addComment(newComment);
        }
        else{
            conditionOfInterest.removeComment(oldComment);
            conditionOfInterest.addComment(newComment);
        }
        this.finish();
    }

    public void modifyCommentCancel(View v){
        this.finish();
    }

}
