package ca.ualberta.cs.personal_condition_tracker;


import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;


public class ConditionTest extends TestCase {

    public void testGetTitle() {
        Condition condition = new Condition("Title", "I am a Condition.");
        assertTrue(condition.getTitle().equals("Title"));
    }
    public void testSetTitle(){
        Condition condition = new Condition("Title", "I am a Condition.");
        assertTrue(condition.getTitle().equals("Title"));
        condition.setTitle("New Title");
        assertTrue(condition.getTitle().equals("New Title"));
    }
    public void testGetDate() {
        Date date = new Date();
        Condition condition = new Condition("Title", date, "I am a Condition.", null, null);
        assertTrue(condition.getDate().equals(date));
    }
    public void testSetDate() {
        Date date = new Date();
        Condition condition = new Condition("Title", date, "I am a Condition.", null, null);
        assertTrue(condition.getDate().equals(date));
        Date newDate = new Date(3000);
        condition.setDate(newDate);
        assertTrue(condition.getDate().equals(newDate));
    }
    public void testGetDescription() {
        Condition condition = new Condition("Title", "I am a Condition.");
        assertTrue(condition.getDescription().equals("I am a Condition."));
    }
    public void testSetDescription() {
        Condition condition = new Condition("Title", "I am a Condition.");
        assertTrue(condition.getDescription().equals("I am a Condition."));
        condition.setDescription("I am still a Condition.");
        assertTrue(condition.getDescription().equals("I am still a Condition."));
    }
    public void testGetRecordList() {
        RecordList recordList = new RecordList();
        Condition condition = new Condition("Title", null, "I am a Condition.", recordList, null);
        assertTrue(condition.getRecordList().equals(recordList));
    }
    public void testSetRecordList() {
        RecordList recordList = new RecordList();
        Condition condition = new Condition("Title", null, "I am a Condition.", recordList, null);
        RecordList recordList2 = new RecordList();
        condition.setRecordList(recordList2);
        assertTrue(condition.getRecordList().equals(recordList2));
    }
    public void testGetCommentList() {
        ArrayList<String> commentList = new ArrayList<>();
        Condition condition = new Condition("Title", null, "I am a Condition.", null, commentList);
        assertTrue(condition.getCommentList().equals(commentList));
    }
    public void testSetCommentList() {
        ArrayList<String> commentList = new ArrayList<>();
        Condition condition = new Condition("Title", null, "I am a Condition.", null, commentList);
        ArrayList<String> commentList2 = new ArrayList<>();
        condition.setCommentList(commentList2);
        assertTrue(condition.getCommentList().equals(commentList2));
    }
}