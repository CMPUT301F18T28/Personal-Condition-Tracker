package ca.ualberta.cs.personal_condition_tracker;

import io.searchbox.annotations.JestId;

public class GeoLocation {

    private Double latitude;
    private Double longitude;
    private String recordIDForGeoLocation;
    @JestId
    private String id;

    GeoLocation(Double new_latitude, Double new_longitude) {
        this.latitude = new_latitude;
        this.longitude = new_longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
