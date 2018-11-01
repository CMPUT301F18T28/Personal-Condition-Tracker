package ca.ualberta.cs.personal_condition_tracker;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;

public class RecordListTest extends TestCase {


    public void testHasRecord(){
        RecordList new_record_list = new RecordList();
        Record new_record = new Record("Title", new Date(), "I am a record.");
        new_record_list.addRecord(new_record);
        assertTrue(new_record_list.hasRecord(new_record));
    }

    public void testAddRecord() {
        Record new_record = new Record("Title", new Date(), "I am a record.");
        RecordList new_record_list = new RecordList();
        new_record_list.addRecord(new_record);
        assertTrue(new_record_list.hasRecord(new_record));
    }

    public void testDeleteRecord() {
        RecordList new_record_list = new RecordList();
        Record new_record = new Record("Title", new Date(), "I am a record.");
        new_record_list.addRecord(new_record);
        new_record_list.deleteRecord(new_record);
        assertFalse(new_record_list.hasRecord(new_record));
    }
    public void testGetRecord() {
        Record new_record = new Record("Title", new Date(), "I am a record.");
        RecordList new_record_list = new RecordList();
        new_record_list.addRecord(new_record);
        assertTrue(new_record_list.getRecord(0).equals(new_record));
    }
    public void testEditRecord() {
        Record new_record = new Record("Title", new Date(), "I am a record.");
        RecordList new_record_list = new RecordList();
        new_record_list.addRecord(new_record);
        Record newer_record = new Record("Title", new Date(), "I am still a record.");
        new_record_list.editRecord(0, newer_record);
        assertTrue(new_record_list.getRecord(0).equals(newer_record));
    }
    public void testQueryByKeyword() {
        RecordList new_record_list = new RecordList();
        Record record1 = new Record("Title", new Date(), "I am the first record");
        Record record2 = new Record("Title", new Date(), "I am the second record");
        Record record3 = new Record("Title", new Date(), "I am the third record");
        new_record_list.addRecord(record1);
        new_record_list.addRecord(record2);
        new_record_list.addRecord(record3);
        ArrayList<Record> queried_records = new_record_list.queryByKeyword("first");
        assertTrue(queried_records.contains(record2));
    }

//    public void testSortRecordsByDate() {
//
//    }

}