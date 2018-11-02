package ca.ualberta.cs.personal_condition_tracker;

import com.google.android.gms.maps.model.LatLng;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class RecordListTest extends TestCase {


    public void testHasRecord(){
        RecordList new_record_list = new RecordList();
        Record new_record = new Record("Title",  "I am a record.");
        new_record_list.addRecord(new_record);
        assertTrue(new_record_list.hasRecord(new_record));
    }

    public void testAddRecord() {
        Record new_record = new Record("Title",  "I am a record.");
        RecordList new_record_list = new RecordList();
        new_record_list.addRecord(new_record);
        assertTrue(new_record_list.hasRecord(new_record));
    }

    public void testDeleteRecord() {
        RecordList new_record_list = new RecordList();
        Record new_record = new Record("Title", "I am a record.");
        new_record_list.addRecord(new_record);
        new_record_list.deleteRecord(new_record);
        assertFalse(new_record_list.hasRecord(new_record));
    }
    public void testGetRecord() {
        Record new_record = new Record("Title",  "I am a record.");
        RecordList new_record_list = new RecordList();
        new_record_list.addRecord(new_record);
        assertTrue(new_record_list.getRecord(0).equals(new_record));
    }
    public void testEditRecord() {
        Record new_record = new Record("Title", "I am a record.");
        RecordList new_record_list = new RecordList();
        new_record_list.addRecord(new_record);
        Record newer_record = new Record("Title",  "I am still a record.");
        new_record_list.editRecord(0, newer_record);
        assertTrue(new_record_list.getRecord(0).equals(newer_record));
    }
    public void testSortByDate() {
        RecordList new_record_list = new RecordList();
        Record record1 = new Record("Title", "I am the first record");
        record1.setDate(new Date(20000));
        Record record2 = new Record("Title", "I am the second record");
        record2.setDate(new Date(10000));

        Record record3 = new Record("Title", "I am the third record");
        record3.setDate(new Date(30000));

        new_record_list.addRecord(record1);
        new_record_list.addRecord(record2);
        new_record_list.addRecord(record3);
        ArrayList<Record> sorted_records = new_record_list.sortByDate();
        assertEquals(sorted_records, new ArrayList<Record>(Arrays.asList(record2, record1, record3)));
    }
    public void testQueryByKeyword() {
        RecordList new_record_list = new RecordList();
        Record record1 = new Record("Title", "I am the first record");
        Record record2 = new Record("Title",  "I am the second record");
        Record record3 = new Record("Title", "I am the third record");
        new_record_list.addRecord(record1);
        new_record_list.addRecord(record2);
        new_record_list.addRecord(record3);
        ArrayList<Record> queried_records = new_record_list.queryByKeyword("first");
        assertTrue(queried_records.contains(record2));
    }
    public void testQueryByGeoLocation() {
        RecordList new_record_list = new RecordList();
        Record record1 = new Record("Title",  "I am the first record");
        record1.setGeo_location(new LatLng(56.7264, 111.3803));
        Record record2 = new Record("Title",  "I am the second record");
        record2.setGeo_location(new LatLng(49.8951, 97.1384));
        Record record3 = new Record("Title",  "I am the third record");
        record3.setGeo_location(new LatLng(53.5444, 113.4909));
        new_record_list.addRecord(record1);
        new_record_list.addRecord(record2);
        new_record_list.addRecord(record3);
        ArrayList<Record> queried_records = new_record_list.queryByGeoLocation(new LatLng(53.5444, 113.4909));
        assertTrue(queried_records.contains(record3));
    }
    public void testQueryByBodyLocation() {
        RecordList new_record_list = new RecordList();
        Record record1 = new Record("Title",  "I am the first record");
        record1.setBody_location("Right Hand");
        Record record2 = new Record("Title",  "I am the second record");
        record2.setBody_location("Left Elbow");
        Record record3 = new Record("Title",  "I am the third record");
        record3.setBody_location("Chin");
        new_record_list.addRecord(record1);
        new_record_list.addRecord(record2);
        new_record_list.addRecord(record3);
        ArrayList<Record> queried_records = new_record_list.queryByBodyLocation("Right Hand");
        assertTrue(queried_records.contains(record1));
    }

}