package ca.ualberta.cs.personal_condition_tracker;

import android.media.Image;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Base64;

public class PhotographTest extends TestCase {
        public void testGetFilename(){
            String filename = "TestFilename";
            Photograph photograph = new Photograph(filename);
            assertTrue(photograph.getFilename().equals(filename));
        }
        public void testSetFilename(){
            String filename = "TestFilename";
            Photograph photograph = new Photograph(filename);
            String filename2 = "New Filename";
            photograph.setFilename(filename2);
            assertTrue(photograph.getFilename().equals(filename2));
        }
        public void testGetThumbnail(){
            String testString = "TestString";
            byte[] thumbnail = testString.getBytes();
            Photograph photograph = new Photograph("Filename", thumbnail);
            String testString2 = "TestString2";
            byte[] thumbnail2 = testString2.getBytes();
            photograph.setThumbnail(thumbnail2);
            assertTrue(Arrays.equals(photograph.getThumbnail(), thumbnail2));
        }
        public void testSetThumbnail(){
            String testString = "TestString";
            byte[] thumbnail = testString.getBytes();
            Photograph photograph = new Photograph("Filename", thumbnail);
            assertTrue(Arrays.equals(photograph.getThumbnail(), thumbnail));

        }

}