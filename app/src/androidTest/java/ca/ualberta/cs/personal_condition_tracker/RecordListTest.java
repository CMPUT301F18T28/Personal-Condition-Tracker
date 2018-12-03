package ca.ualberta.cs.personal_condition_tracker;

import com.google.android.gms.maps.model.LatLng;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import ca.ualberta.cs.personal_condition_tracker.Model.Record;
import ca.ualberta.cs.personal_condition_tracker.Model.RecordList;

public class RecordListTest extends TestCase {


    public void testHasRecord(){
        RecordList recordList = new RecordList();
        Record record = new Record("Title",  "I am a record.");
        recordList.addRecord(record);
        assertTrue(recordList.hasRecord(record));
    }

    public void testAddRecord() {
        Record record = new Record("Title",  "I am a record.");
        RecordList recordList = new RecordList();
        recordList.addRecord(record);
        assertTrue(recordList.hasRecord(record));
    }

    public void testDeleteRecord() {
        RecordList recordList = new RecordList();
        Record record = new Record("Title", "I am a record.");
        recordList.addRecord(record);
        recordList.deleteRecord(record);
        assertFalse(recordList.hasRecord(record));
    }

    public void testGetRecord() {
        Record record = new Record("Title",  "I am a record.");
        RecordList recordList = new RecordList();
        recordList.addRecord(record);
        assertTrue(recordList.getRecord(0).equals(record));
    }

    public void testEditRecord() {
        Record record = new Record("Title", "I am a record.");
        RecordList recordList = new RecordList();
        recordList.addRecord(record);
        Record newerRecord = new Record("Title",  "I am still a record.");
        recordList.editRecord(0, newerRecord);
        assertTrue(recordList.getRecord(0).equals(newerRecord));
    }

    public void testSortByDate() {
        RecordList recordList = new RecordList();
        Record record1 = new Record("Title", "I am the first record");
        record1.setDate(new Date(20000));
        Record record2 = new Record("Title", "I am the second record");
        record2.setDate(new Date(10000));

        Record record3 = new Record("Title", "I am the third record");
        record3.setDate(new Date(30000));

        recordList.addRecord(record1);
        recordList.addRecord(record2);
        recordList.addRecord(record3);
        ArrayList<Record> sorted_records = recordList.sortByDate();
        assertEquals(sorted_records, new ArrayList<Record>(Arrays.asList(record3, record1, record2)));
    }

}
