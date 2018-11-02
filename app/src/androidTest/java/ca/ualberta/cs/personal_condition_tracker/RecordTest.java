package ca.ualberta.cs.personal_condition_tracker;

import com.google.android.gms.maps.model.LatLng;

import junit.framework.TestCase;

import java.util.Date;

public class RecordTest extends TestCase {

    public void testGetSetTitle() {
        Record new_record = new Record("Title", "I am a record.");
        assertTrue(new_record.getTitle().equals("Title"));
    }
    public void testSetTitle(){
        Record new_record = new Record("Title", "I am a record.");
        assertTrue(new_record.getTitle().equals("Title"));
        new_record.setTitle("New Title");
        assertTrue(new_record.getTitle().equals("New Title"));
    }
    public void testGetDate() {
        Date new_date = new Date();
        Record new_record = new Record("Title", "I am a record.");
        assertTrue(new_record.getDate().equals(new_date));
    }
    public void testSetDate() {
        Date current_date = new Date();
        Record new_record = new Record("Title",  "I am a record.");
        assertTrue(new_record.getTitle().equals("Title"));
        Date new_date = new Date(3000);
        new_record.setDate(new_date);
        assertTrue(new_record.getDate().equals(new_date));

    }
    public void testGetDescription() {
        Record new_record = new Record("Title",  "I am a record.");
        assertTrue(new_record.getDescription().equals("I am a record."));
    }
    public void testSetDescription() {
        Record new_record = new Record("Title", "I am a record.");
        assertTrue(new_record.getDescription().equals("I am a record."));
        new_record.setDescription("I am still a record.");
        assertTrue(new_record.getDescription().equals("I am still a record."));
    }
    public void testGeoLocation() {
        Record new_record = new Record("Title",  "I am a record.");
        new_record.setGeo_location(new LatLng(53.5444, 113.4909));
        assertEquals(new_record.getGeo_location(), new LatLng(53.5444, 113.4909));
    }
    public void testBodyLocation() {
        Record new_record = new Record("Title",  "I am a record.");
        new_record.setBody_location("Right Hand");
        assertEquals(new_record.getBody_location(), "Right Hand");
    }
    public void testHasPhotograph(){

        RecordList new_record_list = new RecordList();
        Record new_record = new Record("Title",  "I am a record.");
        new_record_list.addRecord(new_record);
        assertTrue(new_record_list.hasRecord(new_record));
    }

    public void testAddPhotograph() {
        Record new_record = new Record("Title", "I am a record.");

    }


}