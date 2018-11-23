/* Personal Condition Tracker : A simple and attractive Android application that allows an individual to
 document, track and review the progression of a personal health issue (a 'condition'), thus serving to facilitate
 enhanced clarity of communicating between patient and care provider, early detection and accurate prognosis with the
 aim of obtaining medical treatment as soon as possible.

 Document the facts - get the treatment you deserve!

 Copyright (C) 2018

 R. Voon; rcvoon@ualberta.ca
 D. Buksa; draydon@ualberta.ca
 W. Nichols; wnichols@ualberta.ca
 D. Douziech; douziech@ualberta.ca
 C. Neureuter; neureute@ualberta.ca


Redistribution and use in source and binary forms, with or without
modification, are permitted (subject to the limitations in the disclaimer
below) provided that the following conditions are met:

     * Redistributions of source code must retain the above copyright notice,
     this list of conditions and the following disclaimer.

     * Redistributions in binary form must reproduce the above copyright
     notice, this list of conditions and the following disclaimer in the
     documentation and/or other materials provided with the distribution.

     * Neither the name of the copyright holder nor the names of its
     contributors may be used to endorse or promote products derived from this
     software without specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY
THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.

*/


package ca.ualberta.cs.personal_condition_tracker;

/**
 * Record stores all of the information associated with a condition added by a user/Patient.
 * @author     R. Voon; rcvoon@ualberta.ca
 * @author     D. Buksa; draydon@ualberta.ca
 * @author     W. Nichols; wnichols@ualberta.ca
 * @author     D. Douziech; douziech@ualberta.ca
 * @author     C. Neureuter; neureute@ualberta.ca
 * @version    1.1, 11-18-18
 * @since      1.0
 */

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;

import io.searchbox.annotations.JestId;

public class Record {
    private String title;
    private Date date;
    private String description;
    private LatLng geo_location;
    private String body_location;
    private PhotographList photos;
    private String associatedConditionID;
    @JestId
    private String id;

    //Constructors:

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

    /**
     * Provides the title of the Record
     * @return String Title of this Record object
     */

    public String getTitle() {
        return title;
    }

    /**
     * Registers the title for a Record
     * @return Nothing
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Provides the date assigned to a record; generally corresponds to the creation of a Record
     * @return Date Date assigned to a record
     * @see Date
     */

    public Date getDate() {
        return date;
    }

    /**
     * Registers the date for a Record
     * @param date Specifies the date for a record; generally the date of its creation
     * @return Nothing
     * @see Date
     */

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Retrieves the description of a condition contained within a Record thereof.
     * @return String Description of the condition
     * @see Condition
     *
     */

    public String getDescription() {
        return description;
    }

    /**
     * Registers a description of a condition
     * @return Nothing
     * @see Condition
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Provides the geo-locational data of a Record; generally the location where the corresponding Condition arose.
     * @return LatLng The latitude and longitude of the location within the Record of a Condition
     * @see LatLng
     * @see Condition
     */

    public LatLng getGeo_location() {
        return geo_location;
    }

    /**
     * Registers the geo-locational data of a Record; generally the location where the corresponding Condition arose.
     * @param geo_location The latitude and longitude of the location (specific to a Condition)
     * @see LatLng
     * @see Condition
     */

    public void setGeo_location(LatLng geo_location) {
        this.geo_location = geo_location;
    }

    /**
     * Provides the geo-locational data of a Record; generally the location where the corresponding Condition arose.
     * @return LatLng The latitude and longitude of the location within the Record of a Condition
     * @see LatLng
     * @see Condition
     */

    public String getBody_location() {
        return body_location;
    }

    /**
     * Registers the location on the body where the Condition resides.
     * @param body_location Description of a location on the human body
     * @return Nothing
     * @see Condition
     */

    public void setBody_location(String body_location) {
        this.body_location = body_location;
    }

    /**
     * Provides the list of Photograph objects corresponding to the Record of a Condition.
     * @return PhotographList List of Photographs
     * @see Photograph
     * @see PhotographList
     * @see Condition
     */

    public PhotographList getPhotos() {
        return photos;
    }

    /**
     * Registers the list of Photograph objects on a Record of a Condition.
     * @param photos List of Photograph Objects; a PhotographList object
     * @return Nothing
     * @see Photograph
     * @see PhotographList
     */

    public void setPhotos(PhotographList photos) {
        this.photos = photos;
    }

    /**
     * Provides editing capability for the various attributes of a Record object.
     * @param recordTitle Title for the Record
     * @param recordDate  Date of the Record
     * @param recordDescription Description of the Condition
     * @param latLng Geo-locational data specifying the latitude and longitude
     * @param body_location A description of the body location specific to the Condition
     * @return LatLng The latitude and longitude of the location within the Record of a Condition
     * @see Condition
     * @see LatLng
     * @see Date
     */


    public String getAssociatedConditionID() {
        return associatedConditionID;
    }

    public void setAssociatedConditionID(String associatedConditionID) {
        this.associatedConditionID = associatedConditionID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void editRecord(String recordTitle, Date recordDate, String recordDescription, LatLng latLng, String body_location) {
        this.setTitle(recordTitle);
        this.setDate(recordDate);
        this.setDescription(recordDescription);
        this.setGeo_location(latLng);
        this.setBody_location(body_location);
    }

    /**
     * Serves to concatenate the title, date and description of a particular Record.
     * @return String A concatenation of the title, date and description of this Record
     * @see Class
     */

    @Override
    public String toString(){
        return getTitle() + "\n" + getDate().toString() + "\n" + getDescription();
    }
}