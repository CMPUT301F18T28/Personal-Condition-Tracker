package ca.ualberta.cs.personal_condition_tracker;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;

public class BodyLocationTest extends TestCase {

    public void testGetBody_part() {
        BodyLocation body_loc = new BodyLocation();
        body_loc.setBody_part("Right Hand");
        assertTrue(body_loc.getBody_part().equals("Right Hand"));
    }

    public void testSetBody_part() {
        BodyLocation body_loc = new BodyLocation();
        body_loc.setBody_part("Right Hand");
        assertTrue(body_loc.getBody_part().equals("Right Hand"));
    }

    public void testGetPhoto_coordinates() {
        BodyLocation body_loc = new BodyLocation();
        body_loc.setPhoto_x_coordinate(4);
        body_loc.setPhoto_y_coordinate(3);
        assertTrue((body_loc.getPhoto_x_coordinate() == 4) && (body_loc.getPhoto_y_coordinate() == 3));
    }

    public void testSetPhoto_coordinates() {
        BodyLocation body_loc = new BodyLocation();
        body_loc.setPhoto_x_coordinate(4);
        body_loc.setPhoto_y_coordinate(3);
        assertTrue((body_loc.getPhoto_x_coordinate() == 4) && (body_loc.getPhoto_y_coordinate() == 3));
    }


    public void testGetBody_coordinates() {
        BodyLocation body_loc = new BodyLocation();
        body_loc.setBody_x_coordinate(2);
        body_loc.setBody_y_coordinate(1);
        assertTrue((body_loc.getBody_x_coordinate() == 2) && (body_loc.getBody_y_coordinate() == 1));
    }

    public void testSetBody_coordinates() {
        BodyLocation body_loc = new BodyLocation();
        body_loc.setBody_x_coordinate(2);
        body_loc.setBody_y_coordinate(1);
        assertTrue((body_loc.getBody_x_coordinate() == 2) && (body_loc.getBody_y_coordinate() == 1));
    }
}