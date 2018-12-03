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

package ca.ualberta.cs.personal_condition_tracker.Model;

import io.searchbox.annotations.JestId;

/**
 * Geolocation class contains a latitude and a longitude which correspond to the location of a record
 * on a map of the world.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */

public class GeoLocation {

    private double lat;
    private double lon;
    private String recordIDForGeoLocation;
    @JestId
    private String id;

    /*
     * Instantiates a GeoLocation type object.
     */
    public GeoLocation(double new_latitude, double new_longitude) {
        this.lat = new_latitude;
        this.lon = new_longitude;
    }

    /**
     * Get the latitude of a GeoLocation object.
     * @return double latitude
     */
    public double getLatitude() {
        return lat;
    }

    /**
     * Set the latitude of a GeoLocation object.
     * @param double latitude
     */
    public void setLatitude(double latitude) {
        this.lat = latitude;
    }

    /**
     * Get the longitude of a GeoLocation object
     * @return double longitude
     */
    public double getLongitude() {
        return lon;
    }

    /**
     * Set the longitude of a GeoLocation object
     * @param double longitude
     */
    public void setLongitude(double longitude) {
        this.lon = longitude;
    }

    /**
     * Get the corresponding record ID for a GeoLocation object.
     * @return String recordIDForGeoLocation
     */
    public String getRecordIDForGeoLocation() {
        return recordIDForGeoLocation;
    }

    /**
     * Set the corresponding record ID for a GeoLocation object.
     * @param String recordIDForGeoLocation
     */
    public void setRecordIDForGeoLocation(String recordIDForGeoLocation) {
        this.recordIDForGeoLocation = recordIDForGeoLocation;
    }

    /**
     * Get id.
     * @return String id
     */
    public String getId() {
        return id;
    }

    /**
     * Set id
     * @param String id
     */
    public void setId(String id) {
        this.id = id;
    }

}
