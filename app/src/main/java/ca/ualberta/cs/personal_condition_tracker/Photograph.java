package ca.ualberta.cs.personal_condition_tracker;

/**
 * The Photograph class is used to store the thumbnail for the image that is taken by the user.
 * @author      ?
 * @version     1.1, 11-18-18
 * @since       1.0
 */

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
