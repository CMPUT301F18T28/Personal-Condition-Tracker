package ca.ualberta.cs.personal_condition_tracker;

import com.google.android.gms.maps.model.LatLng;

import junit.framework.TestCase;

import java.util.Date;

import ca.ualberta.cs.personal_condition_tracker.Model.Photograph;
import ca.ualberta.cs.personal_condition_tracker.Model.PhotographList;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;

public class RecordTest extends TestCase {

    public void testGetTitle() {
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

//    public void testGetGeoLocation() {
//        Record new_record = new Record("Title",  "I am a record.");
//        new_record.setGeoLocation(new LatLng(53.5444, 113.4909));
//        assertEquals(new_record.getGeoLocation(), new LatLng(53.5444, 113.4909));
//    }
//
//    public void testSetGeoLocation() {
//        Record new_record = new Record("Title",  "I am a record.");
//        new_record.setGeoLocation(new LatLng(53.5444, 113.4909));
//        assertEquals(new_record.getGeoLocation(), new LatLng(53.5444, 113.4909));
//    }
//
//    public void testGetBodyLocation() {
//        Record new_record = new Record("Title",  "I am a record.");
//        new_record.setBodyLocation("Right Hand");
//        assertEquals(new_record.getBodyLocation(), "Right Hand");
//    }
//
//    public void testSetBodyLocation() {
//        Record new_record = new Record("Title",  "I am a record.");
//        new_record.setBodyLocation("Right Hand");
//        assertEquals(new_record.getBodyLocation(), "Right Hand");
//    }

    public void testGetPhotographs(){
        Record new_record = new Record("Title",  "I am a record.");
        PhotographList photo_list = new PhotographList();
        photo_list.addPhotograph(new Photograph("I am a photograph"));
        new_record.setPhotos(photo_list);
        assertTrue(new_record.getPhotos().equals(photo_list));
    }

    public void testSetPhotographs(){
        Record new_record = new Record("Title",  "I am a record.");
        PhotographList photo_list = new PhotographList();
        photo_list.addPhotograph(new Photograph("I am a photograph"));
        new_record.setPhotos(photo_list);
        assertTrue(new_record.getPhotos().equals(photo_list));
    }
}
