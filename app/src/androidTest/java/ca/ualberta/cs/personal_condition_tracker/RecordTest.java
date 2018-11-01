package ca.ualberta.cs.personal_condition_tracker;

import junit.framework.TestCase;

import java.util.Date;

public class RecordTest extends TestCase {

    public void testGetSetTitle() {
        Record new_record = new Record("Title", new Date(), "I am a record.");
        assertTrue(new_record.getTitle().equals("Title"));
    }
    public void testSetTitle(){
        Record new_record = new Record("Title", new Date(), "I am a record.");
        assertTrue(new_record.getTitle().equals("Title"));
        new_record.setTitle("New Title");
        assertTrue(new_record.getTitle().equals("New Title"));
    }
    public void testGetDate() {
        Date new_date = new Date();
        Record new_record = new Record("Title", new_date, "I am a record.");
        assertTrue(new_record.getDate().equals(new_date));
    }
    public void testGetDescription() {
        Record new_record = new Record("Title", new Date(), "I am a record.");
        assertTrue(new_record.getDescription().equals("I am a record."));
    }
    public void testSetDescription() {
        Record new_record = new Record("Title", new Date(), "I am a record.");
        assertTrue(new_record.getDescription().equals("I am a record."));
        new_record.setDescription("I am still a record.");
        assertTrue(new_record.getDescription().equals("I am still a record."));
    }
    public void testAddPhotograph() {

    }

}