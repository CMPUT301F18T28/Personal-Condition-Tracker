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
 * BodyLocation class provides functionality for the user to indicate the location of a particular condition within both a map of
 * human body and a photograph.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */

public class BodyLocation {
    private String bodyPart;
    private double photoXCoordinate;
    private double photoYCoordinate;
    private double bodyXCoordinate;
    private double bodyYCoordinate;

    BodyLocation() {
        this.bodyPart = "";
        this.photoXCoordinate = 0;
        this.photoYCoordinate = 0;
        this.bodyXCoordinate = 0;
        this.bodyYCoordinate = 0;
    }

    /**
     * Provides the name of a body part afflicted by a condition
     * @return String Name of the body part afflicted
     * @see Condition
     */
    public String getBodyPart() {
        return bodyPart;
    }

    /**
     * Registers a name for the part of the body afflicted by a condition
     * @param bodyPart Name of the afflicted body part
     * @return Nothing
     * @see Condition
     */
    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }

    /**
     * Provides the x-coordinate  corresponding to the location of a condition within a photograph of the afflicted body part
     * @return double x-coordinate of the location of the Condition within a photograph
     */
    public double getPhotoXCoordinate() {
        return photoXCoordinate;
    }

    /**
     * Registers the x-coordinate corresponding the location of a condition with respect to a map of the human body
     * @return Nothing
     */

    public void setPhotoXCoordinate(double photoXCoordinate) {
        this.photoXCoordinate = photoXCoordinate;
    }

    /**
     * Provides the y-coordinate corresponding to the location of a condition with respect to a map of the human body
     * @return double y-coordinate of the Condition with respect to a map of the human body
     */
    public double getPhotoYCoordinate() {
        return photoYCoordinate;
    }

    /**
     * Registers the y-coordinate corresponding the location of a condition with respect to a map of the human body
     * @return Nothing
     */
    public void setPhotoYCoordinate(double photoYCoordinate) {
        this.photoYCoordinate = photoYCoordinate;
    }

    /**
     * Registers an ID corresponding to the associated record.
     * @return Nothing
     */

    public double getBodyXCoordinate() {
        return bodyXCoordinate;
    }

    /**
     * Provides the ID corresponding to the associated record.
     * @return String associated record ID
     */
    public void setBodyXCoordinate(double bodyXCoordinate) {
        this.bodyXCoordinate = bodyXCoordinate;
    }

    /**
     * Registers an ID corresponding to the associated photograph.
     * @return Nothing
     */
    public double getBodyYCoordinate() {
        return bodyYCoordinate;
    }


    /**
     * Returns an ID corresponding to the associated photograph.
     * @return String ID of the associated photograph
     */
    public void setBodyYCoordinate(double bodyYCoordinate) {
        this.bodyYCoordinate = bodyYCoordinate;
    }


}