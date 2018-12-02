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
 * PhotographList contains a list of Photographs and allows the user to add, delete and replace them.
 * @author  R. Voon; rcvoon@ualberta.ca
 * @author  D. Buksa; draydon@ualberta.ca
 * @author  W. Nichols; wnichols@ualberta.ca
 * @author  D. Douziech; douziech@ualberta.ca
 * @author  C. Neureuter; neureute@ualberta.ca
 * @version 1.1, 11-18-18
 * @since   1.0
 */

import java.util.ArrayList;

public class PhotographList {

    private ArrayList<Photograph> photos;

    PhotographList() {
        this.photos = new ArrayList<>();
    }

    /**
     * Serves to confirm the presence of a Photograph within the list thereof
     * @return boolean True if the Photograph is contained within the list, false otherwise
     * @see Photograph
     */
    public boolean hasPhotograph(Photograph photograph) {
        return this.photos.contains(photograph);
    }

    /**
     * Retrieves a Photograph based on its index.
     * @param index Index of the photograph to retrieve
     * @return Photograph Photograph corresponding to the index specified
     * @see Photograph
     */
    public Photograph getPhotograph(int index){
        return this.photos.get(index);
    }

    /**
     * Appends a Photograph to the list thereof
     * @param photograph Photograph to append
     * @return Nothing
     * @see Photograph
     */
    public void addPhotograph(Photograph photograph) {
        this.photos.add(photograph);
    }

    /**
     * Removes a Photograph from the list thereof
     * @param photograph Photograph to remove
     * @return Nothing
     * @see Photograph
     */
    public void deletePhotograph(Photograph photograph) {
        this.photos.remove(photograph);
    }

    /**
     * Swaps out an existing Photograph at the specified index with another.
     * @param index Index of the Photograph to replace
     * @param photograph Photograph to replace the one currently at the specified index location
     * @return Nothing
     * @see Photograph
     */
    //Note: This method should be refactored to something like 'swapPhoto'; editing implies making changes
    // existing photograph, not replacing it entirely.
    public void editPhotograph(int index, Photograph photograph) {
        this.photos.set(index, photograph);
    }


}