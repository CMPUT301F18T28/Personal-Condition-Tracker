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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

import io.searchbox.annotations.JestId;

/**
 * The Photograph class is used to store the thumbnail for the image that is taken by the user.
 * @author   R. Voon; rcvoon@ualberta.ca
 * @author   D. Buksa; draydon@ualberta.ca
 * @author   W. Nichols; wnichols@ualberta.ca
 * @author   D. Douziech; douziech@ualberta.ca
 * @author   C. Neureuter; neureute@ualberta.ca
 * @version  1.1, 11-18-18
 * @since    1.0
 */

//import java.util.Base64;

public class Photograph {
    private String base64EncodedString;
    private String recordIDForPhotograph;
    @JestId
    private String id;

    //Constructors:

    Photograph() {
        this.base64EncodedString = "";
    }

    public Bitmap getPhotographImage() {
        byte[] decodedBase64 = Base64.decode(base64EncodedString, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBase64,0, decodedBase64.length);
        return bitmap;
    }

    public void setBase64EncodedString(Bitmap bitmap) {
        bitmap = Bitmap.createScaledBitmap(bitmap,60,140,true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        this.base64EncodedString = Base64.encodeToString(b, Base64.DEFAULT);
    }

    public String getRecordIDForPhotograph() {
        return recordIDForPhotograph;
    }

    public void setRecordIDForPhotograph(String recordIDForPhotograph) {
        this.recordIDForPhotograph = recordIDForPhotograph;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}