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

import java.util.ArrayList;

/**
 * The PatientList class represents a list of patients assigned to a Care Provider.
 * <p>
 * Note that a patient can exist in the system without having been assigned to a Care Provider.
 * </p>
 * @author  R. Voon; rcvoon@ualberta.ca
 *          D. Buksa; draydon@ualberta.ca
 *          W. Nichols; wnichols@ualberta.ca
 *          D. Douziech; douziech@ualberta.ca
 *          C. Neureuter; neureute@ualberta.ca
 * @version     1.1, 11-18-18
 * @since       1.0
 */

public class PatientList {

    private ArrayList<String> patientIDs;
    private transient ArrayList<Listener> listenerList = null;

    PatientList(){
        this.patientIDs = new ArrayList<>();
    }

    /**
     * Serves to look up the username of a patient by its index location within the list thereof.
     * @param index Index of the patient's username with the list of patientIDs
     * @return  String Username of the patient in question
     */

    public String getPatientByIndex(int index) {
        return patientIDs.get(index);
    }

    /**
     * Utilizes elasticsearch to obtain the details of a patient's account.
     * @param patientID Username of a patient
     * @return  Patient A Patient object representing the account details of a patient
     * @see Patient
     */

    public Patient getPatient(String patientID){
        //todo, use this for loading a patient account w/ elasticsearch
        return null;
    }

    /**
     * Provides the list of patient usernames
     * @return  ArrayList<String> List of patient usernames
     */

    public ArrayList<String> getPatientIDs(){return patientIDs;}

    /**
     * Serves to confirm or refute the existence of a particular patient within a care providers list of patients.
     * @param patientID Username of the patient in question
     * @return  boolean True if the username of the patient is contained within the care provider's
     * list of patients, false otherwise.
     */

    public boolean hasPatient(String patientID) {
        return patientIDs.contains(patientID);
    }

    /**
     * Serves to add a patient to the list patients assigned to a particular Care Provider.
     * @param patientID Username of the patient
     * @return  Nothing
     */

    public void addPatient(String patientID) {
        patientIDs.add(patientID);
        notifyListeners();
    }


    /**
     * In practice, this method serves to remove a patient from a Care Providers list of patients
     * and to notify the listeners of the change.
     * @param patientID Username of the patient
     * @return  Nothing
     * @see Listener
     */

    public void deletePatient(String patientID) {
        patientIDs.remove(patientID);
        notifyListeners();
    }

    /**
     * Serves to add a Listener to the list thereof.
     * @param listener  Listener object used for monitoring and updating changes to the list of patients IDs.
     * @return  Nothing
     * @see Listener
     */

    public void addListener(Listener listener){
        getListenerList().add(listener);;
    }

    /**
     * Serves to remove a Listener from the list thereof.
     * @param listener Listener object to removed
     * @return  Nothing
     * @see Listener
     */

    public void removeListener(Listener listener) {
        getListenerList().remove(listener);
    }


    /**
     * Provides the list of Listener objects used for monitoring changes to the list of patient IDs.
     * <p>
     * When called for the first time, the ArrayList is initialized, prior to that it is null.
     * </p>
     * @return  ArrayList<Listener> List of Listener objects
     * @see Listener
     */

    private ArrayList<Listener> getListenerList(){
        if(listenerList == null){
            listenerList = new ArrayList<>();
        }
        return listenerList;
    }

    /**
     * Serves to iterate through the list of Listener objects and to update them accordingly.
     * @return  Nothing
     */

    public void notifyListeners(){
        for(Listener listener: getListenerList()){
            listener.update();
        }
    }
}