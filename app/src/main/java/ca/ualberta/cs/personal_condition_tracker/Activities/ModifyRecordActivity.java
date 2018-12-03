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

package ca.ualberta.cs.personal_condition_tracker.Activities;

/**
 * ModifyRecordActivity allows a patient to input the fields for a record when a record is to be
 * added or editted.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    D. Douziech; douziech@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;
import java.io.File;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.GeoLocation;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;
import ca.ualberta.cs.personal_condition_tracker.R;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;
import ca.ualberta.cs.personal_condition_tracker.Managers.RecordListManager;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;

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
    private String pinX;
    private String pinY;
    //private BodyLocation;
    private LatLng location;

    private int year, month, day, hour, minute, second;
    private Date new_date = new Date();

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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pinX = extras.getString("pinX");
            pinY = extras.getString("pinY");
            Toast.makeText(this, pinX,  Toast.LENGTH_SHORT).show();
        }


        //Set the information for this activity
        EditText recordTitleView = findViewById(R.id.recordTitleView);
        TextView recordDateView = findViewById(R.id.recordDateView);
        EditText recordDescriptionView = findViewById(R.id.recordDescriptionView);

        recordTitleView.setText(recordTitle);
        recordDateView.setText(recordDate);
        recordDescriptionView.setText(recordDescription);
    }

    public void modifyRecordConfirm(View v) {
        //TODO fix dating, ensure working for edits add in Geo/body locations
        Toast.makeText(this, "Confirming record edit...", Toast.LENGTH_SHORT).show();
        EditText recordTitleView = findViewById(R.id.recordTitleView);
        EditText recordDescriptionView = findViewById(R.id.recordDescriptionView);

        String recordTitle = recordTitleView.getText().toString();
        Date recordDate = new_date;
        String recordDescription = recordDescriptionView.getText().toString();

        Record  newRecord = new Record(recordTitle, recordDate, recordDescription, location, null);
        newRecord.setAssociatedConditionID(conditionOfInterest.getId());
        if (location != null) {
            newRecord.setGeoLocation(new GeoLocation(location.latitude, location.longitude));
        }


        Record oldRecord;
        //TODO change these nulls
        if (intent.getIntExtra("recordIndex", -1) == -1) {
            createRecord(newRecord);
            conditionOfInterest.getRecordList().addRecord(newRecord);
        } else {
            int recordIndex = intent.getIntExtra("recordIndex", 0);
            oldRecord = conditionOfInterest.getRecordList().getRecord(recordIndex);
            editRecord(oldRecord, newRecord);
            oldRecord.editRecord(recordTitle, recordDate, recordDescription, location, null);
        }
        setResult(Activity.RESULT_OK);
        this.finish();
    }

    // Cancel adding or editting a record.
    public void modifyRecordCancel(View v) {
        Toast.makeText(this, "Cancelling record edit...", Toast.LENGTH_SHORT).show();
        setResult(Activity.RESULT_CANCELED);
        this.finish();
    }

    public void modifyRecordDate(View v) {
        final Calendar c = Calendar.getInstance();
        // Initialize values for year, month, day, hour, minute, and second.
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        // Set up a date picker dialog to let the user select the new day of the year.
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selected_year, int month_of_year, int day_of_month) {
                year = selected_year;
                month = month_of_year;
                day = day_of_month;
                // After the day has been selected, set up a time picker dialog to let the user select the new time.
                TimePickerDialog timePickerDialog = new TimePickerDialog(ModifyRecordActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hour_of_day,
                                          int selected_minute) {
                        hour = hour_of_day;
                        minute = selected_minute;
                        GregorianCalendar new_gregorian_calendar = new GregorianCalendar(year, month, day, hour, minute, second);
                        new_date = new_gregorian_calendar.getTime();
                        // Update the emotion record and change the date shown to the user.
                        TextView recordDateView = findViewById(R.id.recordDateView);
                        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
                        String recordDate = df.format(new_date);
                        recordDateView.setText(recordDate);

                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        }, year, month, day);
        datePickerDialog.show();
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

                File imageFile = new File(folder, "imagetest.jpg");
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
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});

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
        String query = "{ \"query\": {\"match\": { \"id\" : \"" + newRecord.getId() + "\" } } }";
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
            Toast.makeText(ModifyRecordActivity.this, "Added record successfully!", Toast.LENGTH_SHORT).show();
        } else {
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


    public void selectBodyLoc(View v) {
        Toast.makeText(this, "This is working", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ModifyRecordActivity.this, SelectBodyLocationActivity.class);
        if (pinX != null) {
            intent.putExtra("previousX", pinX);
            intent.putExtra("previousY", pinY);
        }
        startActivity(intent);
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
                location = new LatLng(data.getDoubleExtra("latitude", 0.0),
                        data.getDoubleExtra("longitude", 0.0));
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
            if (record.getGeoLocation() != null) {
                Double latitude = record.getGeoLocation().getLatitude();
                Double longitude = record.getGeoLocation().getLongitude();
                if (latitude != null && longitude != null) {
                    mapIntent.putExtra("latitude", latitude);
                    mapIntent.putExtra("longitude", longitude);
                }
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
