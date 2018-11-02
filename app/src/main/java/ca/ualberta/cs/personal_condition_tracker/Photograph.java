package ca.ualberta.cs.personal_condition_tracker;


import java.util.Base64;

public class Photograph {
    private String filename;
    private byte[] thumbnail;

    Photograph() {
        this.filename = "";
        this.thumbnail = null;
    }
    Photograph(String filename) {
        this.filename = filename;
        this.thumbnail = null;
    }
    Photograph(String filename, byte[] thumbnail) {
        this.filename = filename;
        this.thumbnail = thumbnail;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

}
