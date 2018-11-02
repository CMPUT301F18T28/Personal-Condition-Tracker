package ca.ualberta.cs.personal_condition_tracker;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import java.util.Base64;

public class PhotographTest extends TestCase {


    public void testGetFilename() {
        Photograph photo = new Photograph();
        photo.setFilename("Photograph");
        assertTrue(photo.getFilename().equals("Photograph"));
    }
    public void testSetFilename() {
        Photograph photo = new Photograph("Photograph");
        assertTrue(photo.getFilename().equals("Photograph"));
        photo.setFilename("StillAPhotograph");
        assertTrue(photo.getFilename().equals("StillAPhotograph"));
    }
    public void textGetThumbnail() {
    }
}