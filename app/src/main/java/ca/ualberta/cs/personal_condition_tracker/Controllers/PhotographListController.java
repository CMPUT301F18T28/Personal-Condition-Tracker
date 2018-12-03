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

package ca.ualberta.cs.personal_condition_tracker.Controllers;

import android.util.Log;

import java.util.ArrayList;
import ca.ualberta.cs.personal_condition_tracker.Model.PhotographList;
import ca.ualberta.cs.personal_condition_tracker.Model.Photograph;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;
import ca.ualberta.cs.personal_condition_tracker.Managers.PhotographListManager;

/**
 * PhotographListController performs operations on a PhotographList object.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
public class PhotographListController {

    private static PhotographList photographList = null;

    /**
     * Gets the PhotographList
     * @return photographList
     */
    static public PhotographList getPhotographList() {
        if ((photographList) == null) {
            photographList = new PhotographList();
        }
        return photographList;
    }

    /**
     * Load the photographs corresponding to specific record from the server.
     * @param record
     * @return photographs
     */
    public ArrayList<Photograph> loadPhotographs(Record record) {
        ArrayList<Photograph>  photographs = new ArrayList<>();
        PhotographListManager.GetPhotographsTask getPhotographsTask =
                new PhotographListManager.GetPhotographsTask();
        String query = "{ \"query\": {\"match\": { \"recordIDForPhotograph\" : \""+ record.getId() +"\" } } }";
        try {
            photographs = getPhotographsTask.execute(query).get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the records out of the async object.");
        }
        Log.e("Error", Integer.toString(photographs.size()));
        return photographs;
    }

    // Add a photograph to the server.
    public void createPhotograph(Photograph newPhotograph) {
        // Check if the photograph already exists
        PhotographListManager.GetPhotographsTask getPhotographsTask =
                new PhotographListManager.GetPhotographsTask();
        String query = "{ \"query\": {\"match\": { \"id\" : \"" + newPhotograph.getId() + "\" } } }";
        getPhotographsTask.execute(query);
        ArrayList<Photograph> photographs = new ArrayList<>();
        try {
            photographs = getPhotographsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }

        // Add the photograph to the database.
        if (photographs.size() == 0) {
            PhotographListManager.AddPhotographsTask addPhotographsTask
                    = new PhotographListManager.AddPhotographsTask();
            addPhotographsTask.execute(newPhotograph);
        }
    }

}
