package ca.ualberta.cs.personal_condition_tracker;

import static org.junit.Assert.*;
import junit.framework.TestCase;

public class PhotographListTest extends TestCase{

    public void testHasPhotograph(){
        PhotographList photos = new PhotographList();
        Photograph photo = new Photograph();
        photos.addPhotograph(photo);
        assertTrue(photos.hasPhotograph(photo));
    }

    public void testAddPhotograph(){
        PhotographList photos = new PhotographList();
        Photograph photo = new Photograph();
        photos.addPhotograph(photo);
        assertTrue(photos.hasPhotograph(photo));
    }

    public void testDeleteRecord() {
        PhotographList photos = new PhotographList();
        Photograph photo = new Photograph();
        photos.addPhotograph(photo);
        assertTrue(photos.hasPhotograph(photo));
        photos.deletePhotograph(photo);
        assertFalse(photos.hasPhotograph(photo));
    }

    public void testGetRecord() {
        PhotographList photos = new PhotographList();
        Photograph photo = new Photograph();
        photos.addPhotograph(photo);
        assertTrue(photos.getPhotograph(0).equals(photo));
    }

    public void testEditRecord() {
        PhotographList photos = new PhotographList();
        Photograph photo = new Photograph();
        photos.addPhotograph(photo);
        Photograph new_photo = new Photograph("New Photograph");
        photos.editPhotograph(0, new_photo);
        assertTrue(photos.getPhotograph(0).equals(new_photo));
    }

}