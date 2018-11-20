package ca.ualberta.cs.personal_condition_tracker;

/**
 * The Photograph class is used to store the thumbnail for the image that is taken by the user.
 * @author   R. Voon; rcvoon@ualberta.ca
 * @author   D. Buksa; draydon@ualberta.ca
 * @author   W. Nichols; wnichols@ualberta.ca
 * @author   D. Douziech; douziech@ualberta.ca
 * @author   C. Neureuter; neureute@ualberta.ca
 * @version  1.1, 11-18-18
 * @since    1.0
 */

//import java.util.Base64;

public class Photograph {
    private String filename;
    private byte[] thumbnail;

    //Constructors:

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

    /**
     * Provides the filename of a Photograph
     * @return String Filename associated with a Photograph
     */

    public String getFilename() {
        return this.filename;
    }

    /**
     * Registers a filename with a Photograph
     * @param filename Filename to be associated with the Photograph
     *@return Nothing
     */

    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * Provides the thumbnail of an image associated with the Photograph
     * @return byte[] Binary array representing the image data of a photograph
     */

    public byte[] getThumbnail() {
        return this.thumbnail;
    }


    /**
     * Registers the thumbnail of an image associated with the Photograph
     * @param thumbnail Binary array representing the image data of a photograph
     * @return Nothing
     */

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

}
