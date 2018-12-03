package ca.ualberta.cs.personal_condition_tracker;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.Date;

import ca.ualberta.cs.personal_condition_tracker.Model.CommentRecord;


public class CommentRecordTest extends TestCase {

    @Test
    public void testGetTitle() {
        CommentRecord commentRecord = new CommentRecord("Title", new Date(), "comment");
        assertTrue(commentRecord.getTitle().equals("Title"));
        assertFalse(commentRecord.getTitle().equals("WrongTitle"));
    }

    @Test
    public void testSetTitle(){
        CommentRecord commentRecord = new CommentRecord("Title", new Date(), "comment");
        commentRecord.setTitle("New Title");
        assertTrue(commentRecord.getTitle().equals("New Title"));
        assertFalse(commentRecord.getTitle().equals("Title"));
    }

    @Test
    public void testGetDate() {
        Date date = new Date();
        CommentRecord commentRecord = new CommentRecord("Title", date, "comment");
        assertTrue(commentRecord.getDate().equals(date));
    }

    @Test
    public void testSetDate() {
        Date date = new Date();
        CommentRecord commentRecord = new CommentRecord("Title", date, "comment");
        Date newDate = new Date(3000);
        commentRecord.setDate(newDate);
        assertTrue(commentRecord.getDate().equals(newDate));
        assertFalse(commentRecord.getDate().equals(date));
    }

    @Test
    public void testGetComment() {
        CommentRecord commentRecord = new CommentRecord("Title", new Date(), "comment");
        assertTrue(commentRecord.getComment().equals("comment"));
    }

    @Test
    public void testSetComment() {
        CommentRecord commentRecord = new CommentRecord("Title", new Date(), "comment");
        commentRecord.setComment("new comment");
        assertTrue(commentRecord.getComment().equals("new comment"));
        assertFalse(commentRecord.getComment().equals("comment"));
    }
}
