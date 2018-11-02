package ca.ualberta.cs.personal_condition_tracker;

import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.TestCase;

public class BodyLocationListTest extends TestCase{


    public void testHasBodyLocation() {
        BodyLocationList body_locations = new BodyLocationList();
        BodyLocation body_location = new BodyLocation();
        body_locations.addBodyLocation(body_location);
        assertTrue(body_locations.hasBodyLocation(body_location));
    }

    public void testGetBodyLocation() {
        BodyLocationList body_locations = new BodyLocationList();
        BodyLocation body_location = new BodyLocation();
        body_locations.addBodyLocation(body_location);
        assertTrue(body_locations.getBodyLocation(0).equals(body_location));
    }

    public void testAddBodyLocation() {
        BodyLocationList body_locations = new BodyLocationList();
        BodyLocation body_location = new BodyLocation();
        body_locations.addBodyLocation(body_location);
        assertTrue(body_locations.hasBodyLocation(body_location));
    }

    public void testDeleteBodyLocation() {
        BodyLocationList body_locations = new BodyLocationList();
        BodyLocation body_location = new BodyLocation();
        body_locations.addBodyLocation(body_location);
        assertTrue(body_locations.hasBodyLocation(body_location));
        body_locations.deleteBodyLocation(body_location);
        assertFalse(body_locations.hasBodyLocation(body_location));
    }

    public void testEditBodyLocation() {
        BodyLocationList body_locations = new BodyLocationList();
        BodyLocation body_location = new BodyLocation();
        body_locations.addBodyLocation(body_location);
        BodyLocation new_body_location = new BodyLocation();
        body_locations.editBodyLocation(0, new_body_location);
        assertTrue(body_locations.getBodyLocation(0).equals(new_body_location));
    }
}