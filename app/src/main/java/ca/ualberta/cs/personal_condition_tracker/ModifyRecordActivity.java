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
*/

package ca.ualberta.cs.personal_condition_tracker;

/**
 * ModifyRecordActivity allows a patient to input the fields for a record when a record is to be
 * added or editted.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import static ca.ualberta.cs.personal_condition_tracker.PermissionRequest.verifyPermission;


public class ModifyRecordActivity extends AppCompatActivity {

    public static Intent resultIntent;
    private static final int PICK_IMAGE = 1;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int SELECTED_LOCATION_REQUEST_CODE = 200;
    private Uri imageFileUri;
    private Intent intent;
    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition conditionOfInterest = accountOfInterest.getConditionList().getConditionOfInterest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_record);
        resultIntent = new Intent();

        //Get information from the intent
        intent = getIntent();
        String recordTitle = intent.getStringExtra("recordTitle");
        String recordDate = intent.getStringExtra("recordDate");
        String recordDescription = intent.getStringExtra("recordDescription");


        //Set the information for this activity
        EditText recordTitleView = findViewById(R.id.recordTitleView);
        EditText recordDateView = findViewById(R.id.recordDateView);
        EditText recordDescriptionView = findViewById(R.id.recordDescriptionView);

        recordTitleView.setText(recordTitle);
        recordDateView.setText(recordDate);
        recordDescriptionView.setText(recordDescription);
    }

    public void modifyRecordConfirm(View v){
        //TODO fix dating, ensure working for edits add in Geo/body locations
        Toast.makeText(this,"Confirming record edit...", Toast.LENGTH_SHORT).show();
        EditText recordTitleView = findViewById(R.id.recordTitleView);
        EditText recordDateView = findViewById(R.id.recordDateView);
        EditText recordDescriptionView = findViewById(R.id.recordDescriptionView);

        String recordTitle = recordTitleView.getText().toString();
        Date recordDate = new Date();
        String recordDescription = recordDescriptionView.getText().toString();

        Record oldRecord;
        Record  newRecord = new Record(recordTitle, recordDate, recordDescription, null, null);
        newRecord.setAssociatedConditionID(conditionOfInterest.getId());
        //TODO change these nulls
        if (intent.getIntExtra("recordIndex", -1) == -1){
            createRecord(newRecord);
            conditionOfInterest.getRecordList().addRecord(newRecord);
        }
        else{
            int recordIndex = intent.getIntExtra("recordIndex", 0);
            oldRecord = conditionOfInterest.getRecordList().getRecord(recordIndex);
            editRecord(oldRecord, newRecord);
            oldRecord.editRecord(recordTitle, recordDate, recordDescription, null, null);
        }
        setResult(Activity.RESULT_OK);
        this.finish();
    }
    // Cancel adding or editting a record.
    public void modifyRecordCancel(View v){
        Toast.makeText(this,"Cancelling record edit...", Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_CANCELED);
        this.finish();
    }
    public void addPhoto(View v) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setMessage("Would you like to take a photo or upload a previous photo?");
        adb.setCancelable(true);

        adb.setPositiveButton("Take photo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download";
                File folderF = new File(folder);
                if (!folderF.exists()) {
                    folderF.mkdir();
                }

                try {
                    Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                    m.invoke(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                verifyPermission(ModifyRecordActivity.this);

                String imageFilePath = folder + "/" + String.valueOf(System.currentTimeMillis()) + "jpg";

                File imageFile = new File(folder,"imagetest.jpg");
                imageFileUri = Uri.fromFile(imageFile);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });

        adb.setNegativeButton("Upload from gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, PICK_IMAGE);
            }
        });

        adb.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Do nothing, simply allow the dialog to close
            }
        });

        adb.show();
    }

    // Add a care provider to the server.
    public void createRecord(Record newRecord) {
        // Check if the user has already signed up
        RecordListManager.GetRecordsTask getRecordsTask =
                new RecordListManager.GetRecordsTask();
        String query = "{ \"query\": {\"match\": { \"id\" : \""+ newRecord.getId() +"\" } } }";
        getRecordsTask.execute(query);
        ArrayList<Record> records = new ArrayList<>();
        try {
            records = getRecordsTask.get();
        } catch (Exception e) {
            Log.e("Error", "Failed to get the tweets out of the async object.");
        }

        // Add the user to the database.
        if (records.size() == 0) {
//            UserAccountListController.getUserAccountList().addUserAccount(newCareProvider);
            RecordListManager.AddRecordsTask addRecordsTask
                    = new RecordListManager.AddRecordsTask();
            addRecordsTask.execute(newRecord);
            Toast.makeText(ModifyRecordActivity.this,"Added record successfully!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ModifyRecordActivity.this, "This record already exists!", Toast.LENGTH_SHORT).show();
        }
    }

    public void editRecord(Record oldRecord, Record newRecord) {
        newRecord.setId(oldRecord.getId());
        RecordListManager.DeleteRecordsTask deleteRecordsTask =
                new RecordListManager.DeleteRecordsTask();
        deleteRecordsTask.execute(oldRecord);
        RecordListManager.AddRecordsTask addRecordsTask
                = new RecordListManager.AddRecordsTask();
        addRecordsTask.execute(newRecord);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(ModifyRecordActivity.this, "Photo added!", Toast.LENGTH_SHORT).show();
//                ImageButton button = (ImageButton) findViewById(R.id.TakeAPhoto);
//                button.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(ModifyRecordActivity.this, "Photo canceled!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ModifyRecordActivity.this, "The photo could not be added", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(ModifyRecordActivity.this, "Photo added!", Toast.LENGTH_SHORT).show();
//                ImageButton button = (ImageButton) findViewById(R.id.TakeAPhoto);
//                button.setImageDrawable(Drawable.createFromPath(imageFileUri.getPath()));
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(ModifyRecordActivity.this, "Photo canceled!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ModifyRecordActivity.this, "The photo could not be added", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == SELECTED_LOCATION_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Record record = getRecordFromIntent();
                if (record != null) {
                    record.setGeo_location(new LatLng(data.getDoubleExtra("latitude", 0.0),
                            data.getDoubleExtra("longitude", 0.0)));
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(ModifyRecordActivity.this, "Map change canceled!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ModifyRecordActivity.this, "The geo-location could not be changed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void modifyGeoLocation(View v) {
        Intent mapIntent = new Intent(ModifyRecordActivity.this, MapsActivity.class);
        mapIntent.putExtra("mapMode", "selection");
        Record record = getRecordFromIntent();
        if (record != null) {
            LatLng latlng = record.getGeo_location();
            if (latlng != null) {
                mapIntent.putExtra("latitude", latlng.latitude);
                mapIntent.putExtra("longitude", latlng.longitude);
            }
        }
        startActivityForResult(mapIntent, SELECTED_LOCATION_REQUEST_CODE);
    }

    /**
     * Get the Record that is being modified from the current intent,
     * using getIntent().
     *
     * @return the Record that is currently being modified.
     */
    @Nullable
    private Record getRecordFromIntent() {
        int recordIndex = getIntent().getIntExtra("recordIndex", -1);
        if (recordIndex != -1) {
            return conditionOfInterest.getRecordList().getRecord(recordIndex);
        }
        else {
            return null;
        }
    }

}
