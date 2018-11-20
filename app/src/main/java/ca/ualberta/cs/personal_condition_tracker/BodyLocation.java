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
 * BodyLocation is responsible for dealing with all the functions that the user needs to mark their issue on a virtual person.
 * @author    R. Voon; rcvoon@ualberta.ca
 *            D. Buksa; draydon@ualberta.ca
 *            W. Nichols; wnichols@ualberta.ca
 *            D. Douziech; douziech@ualberta.ca
 *            C. Neureuter; neureute@ualberta.ca
 * @version     1.1, 11-18-18
 * @since       1.0
 */

public class BodyLocation {
    private String body_part;
    private double photo_x_coordinate;
    private double photo_y_coordinate;
    private double body_x_coordinate;
    private double body_y_coordinate;

    BodyLocation() {
        this.body_part = "";
        this.photo_x_coordinate = 0;
        this.photo_y_coordinate = 0;
        this.body_x_coordinate = 0;
        this.body_y_coordinate = 0;
    }

    public String getBody_part() {
        return body_part;
    }

    public void setBody_part(String body_part) {
        this.body_part = body_part;
    }

    public double getPhoto_x_coordinate() {
        return photo_x_coordinate;
    }

    public void setPhoto_x_coordinate(double photo_x_coordinate) {
        this.photo_x_coordinate = photo_x_coordinate;
    }

    public double getPhoto_y_coordinate() {
        return photo_y_coordinate;
    }

    public void setPhoto_y_coordinate(double photo_y_coordinate) {
        this.photo_y_coordinate = photo_y_coordinate;
    }

    public double getBody_x_coordinate() {
        return body_x_coordinate;
    }

    public void setBody_x_coordinate(double body_x_coordinate) {
        this.body_x_coordinate = body_x_coordinate;
    }

    public double getBody_y_coordinate() {
        return body_y_coordinate;
    }

    public void setBody_y_coordinate(double body_y_coordinate) {
        this.body_y_coordinate = body_y_coordinate;
    }

}
