package ca.ualberta.cs.personal_condition_tracker;

import java.util.Base64;

public class Photograph {
    private String filename;
    private Base64 thumbnail;

    Photograph() {
        this.filename = "";
        this.thumbnail = null;
    }
    Photograph(String new_filename) {
        this.filename = new_filename;
        this.thumbnail = null;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Base64 getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(Base64 thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void attachPhoto() {
        // will be completed in Part 4
    }
    public void readFromDisk() {
        // will be completed in Part 4
    }
    public void saveToDisk() {
        // will be completed in Part 4
    }

}
