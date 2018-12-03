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

package ca.ualberta.cs.personal_condition_tracker.Managers;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import ca.ualberta.cs.personal_condition_tracker.Model.Photograph;

/**
 * PhotographListManager stores, retrieves, and removes Photograph objects from the elasticsearch server.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
public class PhotographListManager {

    private static JestDroidClient client;

    /**
     * Add photographs to the server
     */
    public static class AddPhotographsTask extends AsyncTask<Photograph, Void, Void> {

        @Override
        protected Void doInBackground(Photograph... photographs) {
            verifySettings();

            for (Photograph photograph : photographs) {
                Index index = new Index.Builder(photograph).index("cmput301f18t28test").type("photograph").build();

                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        if (photograph.getId() == null) {
                            photograph.setId(result.getId());
                        }
                        Log.e("Error", "Success.");
                    }
                    else {
                        Log.e("Error", "Elastic search was not able to add the condition.");
                    }
                }
                catch (Exception e) {
                    Log.i("Error", "The application failed to build and send the conditions.");
                }

            }
            return null;
        }
    }

    /**
     * Get photographs from the server.
     */
    public static class GetPhotographsTask extends AsyncTask<String, Void, ArrayList<Photograph>> {
        @Override
        protected ArrayList<Photograph> doInBackground(String... search_parameters) {
            verifySettings();

            ArrayList<Photograph> photographs = new ArrayList<>();

            Search search = new Search.Builder( search_parameters[0])
                    //Search search = new Search.Builder( search_parameters[0] )
                    .addIndex("cmput301f18t28test")
                    .addType("photograph")
                    .build();

            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    List<Photograph> foundPhotos = result.getSourceAsObjectList(Photograph.class);
                    photographs.addAll(foundPhotos);
                    Log.e("Error", "Success.");
                }
                else {
                    Log.e("Error", "The search query failed to find any conditions that matched.");
                }
            }
            catch (Exception e) {
                Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            return photographs;
        }
    }
    /**
     * Remove photographs from the server.
     */
    public static class DeletePhotographsTask extends AsyncTask<Photograph, Void, Void> {
        @Override
        protected Void doInBackground(Photograph... photographs) {
            verifySettings();

            Photograph photograph = photographs[0];
            String jestID = photograph.getId();
            Delete delete = new Delete.Builder(jestID).index("cmput301f18t28test").type("photograph").build();

            try {
                DocumentResult result = client.execute(delete);
                if (result.isSucceeded()) {
                    Log.e("Error", "Success.");
                }
                else {
                    Log.e("Error", "The search query failed to find any conditions that matched.");
                }
            }
            catch (Exception e) {
                Log.e("Error", "Something went wrong when we tried to communicate with the elasticsearch server!");
            }
            return null;
        }
    }


    /**
     * Set up the server.
     */
    public static void verifySettings() {
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://es2.softwareprocess.ca:8080/");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }


}
