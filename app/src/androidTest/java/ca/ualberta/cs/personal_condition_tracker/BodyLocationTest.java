package ca.ualberta.cs.personal_condition_tracker;

import junit.framework.TestCase;

public class BodyLocationTest extends TestCase {

    public void testGetBodyPart() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.setBodyPart("Right Hand");
        assertTrue(bodyLocation.getBodyPart().equals("Right Hand"));
    }

    public void testSetBodyPart() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.setBodyPart("Right Hand");
        assertTrue(bodyLocation.getBodyPart().equals("Right Hand"));
    }

    public void testGetPhotoCoordinates() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.setPhotoXCoordinate(4);
        bodyLocation.setPhotoYCoordinate(3);
        assertTrue((bodyLocation.getPhotoXCoordinate() == 4) && (bodyLocation.getPhotoYCoordinate() == 3));
    }

    public void testSetPhotoCoordinates() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.setPhotoXCoordinate(4);
        bodyLocation.setPhotoYCoordinate(3);
        assertTrue((bodyLocation.getPhotoXCoordinate() == 4) && (bodyLocation.getPhotoYCoordinate() == 3));
    }


    public void testGetBodyCoordinates() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.setBodyXCoordinate(2);
        bodyLocation.setBodyYCoordinate(1);
        assertTrue((bodyLocation.getBodyXCoordinate() == 2) && (bodyLocation.getBodyYCoordinate() == 1));
    }

    public void testSetBodyCoordinates() {
        BodyLocation bodyLocation = new BodyLocation();
        bodyLocation.setBodyXCoordinate(2);
        bodyLocation.setBodyYCoordinate(1);
        assertTrue((bodyLocation.getBodyXCoordinate() == 2) && (bodyLocation.getBodyYCoordinate() == 1));
    }
}