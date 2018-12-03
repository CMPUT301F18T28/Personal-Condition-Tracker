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
import android.util.Log;

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
import ca.ualberta.cs.personal_condition_tracker.Managers.RecordListManager;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;

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
