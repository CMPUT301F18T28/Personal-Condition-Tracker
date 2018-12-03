package ca.ualberta.cs.personal_condition_tracker.Model;

import io.searchbox.annotations.JestId;

public class GeoLocation {

    private double lat;
    private double lon;
    private String recordIDForGeoLocation;
    @JestId
    private String id;

    public GeoLocation(double new_latitude, double new_longitude) {
        this.lat = new_latitude;
        this.lon = new_longitude;
    }

    public double getLatitude() {
        return lat;
    }

    public void setLatitude(double latitude) {
        this.lat = latitude;
    }

    public double getLongitude() {
        return lon;
    }

    public void setLongitude(double longitude) {
        this.lon = longitude;
    }

    public String getRecordIDForGeoLocation() {
        return recordIDForGeoLocation;
    }

    public void setRecordIDForGeoLocation(String recordIDForGeoLocation) {
        this.recordIDForGeoLocation = recordIDForGeoLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
