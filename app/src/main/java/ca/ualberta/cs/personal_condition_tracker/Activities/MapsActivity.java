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
package ca.ualberta.cs.personal_condition_tracker.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import ca.ualberta.cs.personal_condition_tracker.Controllers.RecordListController;
import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;
import ca.ualberta.cs.personal_condition_tracker.R;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;
/**
 * MapsActivity is responsible for allowing a user to select a GeoLocation on a map.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @author    C. Neureuter; neureute@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    public static LatLng location;
    private Marker currentMarker;
    private String mapMode;
    private UserAccountListController userAccountListController = new UserAccountListController();
    private RecordListController recordListController = new RecordListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition conditionOfInterest = accountOfInterest.getConditionList().getConditionOfInterest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        mapMode = intent.getStringExtra("mapMode");
        if (mapMode.equals("selection") || mapMode.equals("view")) {
            location = new LatLng(intent.getDoubleExtra("latitude", 0), intent.getDoubleExtra("longitude", 0));

            if (location.latitude == 0 && location.longitude == 0) {
                location = null;
            }
        } else if (mapMode.equals("viewAll")) {

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googlemap);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
   @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng startingMarker;
        if (location == null) {

            /* Check if the fine and course locations are denied.*/
            if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                String[] permissions = new String[1];
                permissions[0] = Manifest.permission.ACCESS_FINE_LOCATION;
                ActivityCompat.requestPermissions(this, permissions, 1);
                return;
            }
            mMap.setMyLocationEnabled(true);
            startingMarker = getCurrentLocation();
        } else {
            startingMarker = new LatLng(location.latitude, location.longitude);

        }
       if (mapMode.equals("selection") || mapMode.equals("view")) {
           currentMarker = mMap.addMarker(new MarkerOptions().position(startingMarker).title("Selected record location"));
       } else if (mapMode.equals("viewAll")) {
            ArrayList<Condition> conditions = userAccountListController.getUserAccountList().getAccountOfInterest().getConditionList().getConditions();
            for (int j =0; j< conditions.size(); j++) {
                ArrayList<Record> records = recordListController.loadRecords(conditions.get(j));
                for (int i = 0; i < records.size(); i++) {
                    Record record = records.get(i);
                    if (record.getGeoLocation() != null) {
                        Double latitude = record.getGeoLocation().getLatitude();
                        Double longitude = record.getGeoLocation().getLongitude();
                        startingMarker = new LatLng(latitude, longitude);
                        mMap.addMarker(new MarkerOptions().position(startingMarker).title(record.getTitle()));

                    }
                }
            }

       }
       mMap.moveCamera(CameraUpdateFactory.newLatLng(startingMarker));
       mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(startingMarker, 11));
       if (mapMode.equals("selection")) {
           mMap.setOnMapClickListener(this);
           mMap.setOnMarkerClickListener(this);
       }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (mapMode.equals("selection")) { // Check if the map is in selection mode
            marker.remove();
            location = null;
        }
        return false;
    }

    @Override
    public void onMapClick(LatLng latlng) {
        if (mapMode.equals("selection")) { // Check if the map is in selection mode
            /* Remove the currentMarker and add a new one at the tapped spot */
            currentMarker.remove();
            currentMarker = mMap.addMarker(new MarkerOptions().position(latlng).title("Record Location"));
            /* Set the location variable to the coordinates of the tapped spot */
            location = new LatLng(latlng.latitude, latlng.longitude);
            Intent intent = new Intent(MapsActivity.this, ModifyRecordActivity.class);
            intent.putExtra("latitude", location.latitude);
            intent.putExtra("longitude", location.longitude);
            setResult(RESULT_OK, intent);
        }
        if (mapMode.equals("view")) {

        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (permissions.length == 1 && permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }
        }
    }

    private LatLng getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return new LatLng(0, 0);
        }
        LocationManager locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        Location current_location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (current_location == null) {
            return new LatLng(0, 0);
        }
        return new LatLng(current_location.getLatitude(), current_location.getLongitude());
    }

}
