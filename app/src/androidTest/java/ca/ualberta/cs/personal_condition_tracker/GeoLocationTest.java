package ca.ualberta.cs.personal_condition_tracker;

import junit.framework.TestCase;

import ca.ualberta.cs.personal_condition_tracker.Model.GeoLocation;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;

public class GeoLocationTest extends TestCase {

    public void testGetLat() {
        GeoLocation geoLocation = new GeoLocation(53.5444, 113.4909);
        assertTrue(geoLocation.getLatitude() == 53.5444);
    }

    public void testSetLat() {
        GeoLocation geoLocation = new GeoLocation(53.5444, 113.4909);
        geoLocation.setLatitude(55.5444);
        assertTrue(geoLocation.getLatitude() == 55.5444);
    }

    public void testGetLon() {
        GeoLocation geoLocation = new GeoLocation(53.5444, 113.4909);
        assertTrue(geoLocation.getLongitude() == 113.4909);
    }

    public void testSetLon() {
        GeoLocation geoLocation = new GeoLocation(53.5444, 113.4909);
        geoLocation.setLongitude(115.4909);
        assertTrue(geoLocation.getLongitude() == 115.4909);
    }

}
