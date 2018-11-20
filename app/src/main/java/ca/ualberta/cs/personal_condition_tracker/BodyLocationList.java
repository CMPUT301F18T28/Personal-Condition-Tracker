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
 * BodyLocationList is responsible for performing functions on a list of BodyLocation objects.
 * @author    R. Voon; rcvoon@ualberta.ca
 *            D. Buksa; draydon@ualberta.ca
 *            W. Nichols; wnichols@ualberta.ca
 *            D. Douziech; douziech@ualberta.ca
 *            C. Neureuter; neureute@ualberta.ca
 * @version     1.1, 11-18-18
 * @since       1.0
 */

import java.util.ArrayList;

public class BodyLocationList {
    private ArrayList<BodyLocation> body_locations;

    // Constructor
    BodyLocationList() {
        this.body_locations = new ArrayList<BodyLocation>();
    }

    /**
     * Check if a body location is present in the list.
     * @params new_body_location BodyLocation
     * @return boolean
     */
    public boolean hasBodyLocation(BodyLocation new_body_location) {
        return this.body_locations.contains(new_body_location);
    }
    /**
     * Get the body location at a specified index of the list.
     * @params index int
     * @return BodyLocation
     */
    public BodyLocation getBodyLocation(int index){
        return this.body_locations.get(index);
    }
    /**
     * Add a body location to the list.
     * @params new_body_location BodyLocation
     */
    public void addBodyLocation(BodyLocation new_body_location) {
        this.body_locations.add(new_body_location);
    }
    /**
     * Remove a body location from the list.
     * @params new_body_location BodyLocation
     */
    public void deleteBodyLocation(BodyLocation new_body_location) {
        this.body_locations.remove(new_body_location);
    }
    /**
     * Edit a body location at a specified index in the list.
     * @params index int, new_body_location BodyLocation
     */
    public void editBodyLocation(int index, BodyLocation new_body_location) {
        this.body_locations.set(index, new_body_location);
    }

}
