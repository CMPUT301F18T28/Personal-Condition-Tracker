package ca.ualberta.cs.personal_condition_tracker;

/**
 * Record stores all of the information associated with a conditon added by the user.
 * @author     R. Voon; rcvoon@ualberta.ca
 *             D. Buksa; draydon@ualberta.ca
 *             W. Nichols; wnichols@ualberta.ca
 *             D. Douziech; douziech@ualberta.ca
 *             C. Neureuter; neureute@ualberta.ca
 * @version     1.1, 11-18-18
 * @since       1.0
 */

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;

public class Record {
    private String title;
    private Date date;
    private String description;
    private LatLng geo_location;
    private String body_location;
    private PhotographList photos;


    Record() {
        this.title = "Title";
        this.date = new Date();
        this.description = "";
        this.geo_location = null;
        this.body_location = "";
        this.photos = new PhotographList();
    }
    Record(String new_title, String new_description) {
        this.title = new_title;
        this.date = new Date();
        this.description = new_description;
        this.geo_location = null;
        this.body_location = "";
        this.photos = new PhotographList();
    }
    Record(String new_title, Date new_date, String new_description, LatLng new_geo_location, String new_body_location) {
        this.title = new_title;
        this.date = new_date;
        this.description = new_description;
        this.geo_location = null;
        this.body_location = "";
        this.photos = new PhotographList();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LatLng getGeo_location() {
        return geo_location;
    }

    public void setGeo_location(LatLng geo_location) {
        this.geo_location = geo_location;
    }

    public String getBody_location() {
        return body_location;
    }

    public void setBody_location(String body_location) {
        this.body_location = body_location;
    }

    public PhotographList getPhotos() {
        return photos;
    }

    public void setPhotos(PhotographList photos) {
        this.photos = photos;
    }

    public void editRecord(String recordTitle, Date recordDate, String recordDescription, LatLng latLng, String body_location) {
        this.setTitle(recordTitle);
        this.setDate(recordDate);
        this.setDescription(recordDescription);
        this.setGeo_location(latLng);
        this.setBody_location(body_location);
    }
    @Override
    public String toString(){
        return getTitle() + "\n" + getDate().toString() + "\n" + getDescription();
    }
}
