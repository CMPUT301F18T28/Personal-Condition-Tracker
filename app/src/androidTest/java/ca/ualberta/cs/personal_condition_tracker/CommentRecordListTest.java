package ca.ualberta.cs.personal_condition_tracker;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import ca.ualberta.cs.personal_condition_tracker.Model.CommentRecord;
import ca.ualberta.cs.personal_condition_tracker.Model.CommentRecordList;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;
import ca.ualberta.cs.personal_condition_tracker.Model.RecordList;

public class CommentRecordListTest extends TestCase {
    @Test
    public void testHasCommentRecord(){
        CommentRecordList commentRecordList = new CommentRecordList();
        CommentRecord commentRecord = new CommentRecord("Title", new Date(), "comment");
        commentRecordList.addCommentRecord(commentRecord);
        assertTrue(commentRecordList.hasCommentRecord(commentRecord));
        assertFalse(commentRecordList.hasCommentRecord(new CommentRecord()));
    }

    @Test
    public void testAddCommentRecord() {
        CommentRecordList commentRecordList = new CommentRecordList();
        CommentRecord commentRecord = new CommentRecord("Title", new Date(), "comment");
        commentRecordList.addCommentRecord(commentRecord);
        assertTrue(commentRecordList.hasCommentRecord(commentRecord));
    }

    @Test
    public void testDeleteCommentRecord() {
        CommentRecordList commentRecordList = new CommentRecordList();
        CommentRecord commentRecord = new CommentRecord("Title", new Date(), "comment");
        commentRecordList.addCommentRecord(commentRecord);
        commentRecordList.deleteCommentRecord(commentRecord);
        assertFalse(commentRecordList.hasCommentRecord(commentRecord));
    }

    @Test
    public void testGetCommentRecordByIndex() {
        CommentRecordList commentRecordList = new CommentRecordList();
        CommentRecord commentRecord = new CommentRecord("Title", new Date(), "comment");
        commentRecordList.addCommentRecord(commentRecord);
        assertTrue(commentRecordList.getCommentRecordByIndex(0).equals(commentRecord));
    }


    @Test
    public void testSortByDate() {

        CommentRecordList commentRecordList = new CommentRecordList();
        CommentRecord commentRecord1 = new CommentRecord("Title1", new Date(20000), "comment");
        CommentRecord commentRecord2 = new CommentRecord("Title2", new Date(10000), "comment");
        CommentRecord commentRecord3 = new CommentRecord("Title3", new Date(30000), "comment");
        commentRecordList.addCommentRecord(commentRecord1);
        commentRecordList.addCommentRecord(commentRecord2);
        commentRecordList.addCommentRecord(commentRecord3);

        ArrayList<CommentRecord> sorted_records = commentRecordList.sortByDate();
        assertEquals(sorted_records, new ArrayList<>(Arrays.asList(commentRecord3, commentRecord1, commentRecord2)));
    }
}
