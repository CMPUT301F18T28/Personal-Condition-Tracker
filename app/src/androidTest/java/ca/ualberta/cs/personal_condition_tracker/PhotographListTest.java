package ca.ualberta.cs.personal_condition_tracker;

import static org.junit.Assert.*;

public class PhotographListTest {

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

}