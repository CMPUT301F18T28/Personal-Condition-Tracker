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

/**
 * BodyLocationList stores an array of objects from the BodyLocation class.
 * <p>
 * @see ca.ualberta.cs.personal_condition_tracker.Model.BodyLocation
 * </p>
 * @author      R. Voon; rcvoon@ualberta.ca
 * @author      D. Buksa; draydon@ualberta.ca
 * @author      W. Nichols; wnichols@ualberta.ca
 * @author      D. Douziech; douziech@ualberta.ca
 * @author      C. Neureuter; neureute@ualberta.ca
 * @version     1.1, 11-18-18
 * @since       1.0
 */
import java.util.ArrayList;

public class BodyLocationList {

    private ArrayList<BodyLocation> bodyLocations;
    private BodyLocation bodyLocationOfInterest = null;
    private transient ArrayList<Listener> listenerList = null;
    BodyLocationList() {
        this.bodyLocations = new ArrayList<BodyLocation>();
    }

    /**
     * Serves to confirm that a particular BodyLocation object is extant within the list thereof
     * @param newBodyLocation BodyLocation object to check
     * @return Boolean True if it exists within the list, false otherwise
     * @see BodyLocation
     */
    public boolean hasBodyLocation(BodyLocation newBodyLocation) {
        return this.bodyLocations.contains(newBodyLocation);
    }

    /**
     * Retrieves a particular BodyLocation objects based on its index
     * @param index Index location of the BodyLocation object within the bodyLocations ArrayList
     * @return BodyLocation An object representing the location of a condition on a patient's body
     * @see Patient
     * @see Condition
     */
    public BodyLocation getBodyLocation(int index){
        return this.bodyLocations.get(index);
    }

    /**
     * Appends a BodyLocation object to the list thereof.
     * @param bodyLocation An object representing the location of a condition on a patient's body
     * @return Nothing
     * @see Patient
     * @see Condition
     */
    public void addBodyLocation(BodyLocation bodyLocation) {
        this.bodyLocations.add(bodyLocation);
    }

    /**
     * Removes a BodyLocation object from the list thereof.
     * @param bodyLocation An object representing the location of a condition on a patient's body
     * @return Nothing
     * @see Patient
     * @see Condition
     */
    public void deleteBodyLocation(BodyLocation bodyLocation) {
        this.bodyLocations.remove(bodyLocation);
    }

    /**
     * Serves to replace an existing BodyLocation object with another
     * @param index Index location of the BodyLocation object to be replaced
     * @param bodyLocation An object representing the location of a condition on a patient's body
     * @return Nothing
     * @see Patient
     * @see Condition
     */
    public void editBodyLocation(int index, BodyLocation bodyLocation) {
        this.bodyLocations.set(index, bodyLocation);
    }

    public ArrayList<BodyLocation> getBodyLocations() {
        return this.bodyLocations;
    }
    public void setBodyLocations(ArrayList<BodyLocation> bodyLocations) {
        this.bodyLocations = bodyLocations;
    }

    public BodyLocation getBodyLocationOfInterest() {
        return bodyLocationOfInterest;
    }

    public void setBodyLocationOfInterest(BodyLocation bodyLocationOfInterest) {
        this.bodyLocationOfInterest = bodyLocationOfInterest;
    }

    /**
     * Add listener.
     *
     * @param listener the listener
     */
    public void addListener(Listener listener){
        getListenerList().add(listener);;
    }

    /**
     * Remove a listener object.
     *
     * @param listener the listener
     * @params Listener
     */
    public void removeListener(Listener listener) {
        getListenerList().remove(listener);
    }
    /**
     * Get all the listeners.
     * @return ArrayList<Listener>
     */
    private ArrayList<Listener> getListenerList(){
        if(listenerList == null){
            listenerList = new ArrayList<>();
        }
        return listenerList;
    }

    /**
     * Tell listeners to update.
     */
    public void notifyListeners(){
        for(Listener listener: getListenerList()){
            listener.update();
        }
    }

}