package ca.ualberta.cs.personal_condition_tracker.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.GeoLocation;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;
import ca.ualberta.cs.personal_condition_tracker.R;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;
import ca.ualberta.cs.personal_condition_tracker.Controllers.RecordListController;
import ca.ualberta.cs.personal_condition_tracker.Controllers.PhotographListController;

import ca.ualberta.cs.personal_condition_tracker.Model.Photograph;

import static ca.ualberta.cs.personal_condition_tracker.PermissionRequest.verifyPermission;
/**
 * ModifyRecordActivity allows a patient to input the fields for a record when a record is to be
 * added or editted.
 * @author    R. Voon; rcvoon@ualberta.ca
 * @author    D. Buksa; draydon@ualberta.ca
 * @author    W. Nichols; wnichols@ualberta.ca
 * @version   1.1, 11-18-18
 * @since     1.0
 */
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
    private RecordListController recordListController = new RecordListController();
    private PhotographListController photographListController = new PhotographListController();

    private LatLng location;
    private String new_image = "";

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

        //Set the information for this activity
        EditText recordTitleView = findViewById(R.id.recordTitleView);
        TextView recordDateView = findViewById(R.id.recordDateView);
        EditText recordDescriptionView = findViewById(R.id.recordDescriptionView);

        recordTitleView.setText(recordTitle);
        recordDateView.setText(recordDate);
        recordDescriptionView.setText(recordDescription);
    }
    // Commit changes to record.
    public void modifyRecordConfirm(View v) {
        //TODO fix dating, ensure working for edits add in Geo/body locations
        Toast.makeText(this, "Confirming record edit...", Toast.LENGTH_SHORT).show();
        EditText recordTitleView = findViewById(R.id.recordTitleView);
        EditText recordDescriptionView = findViewById(R.id.recordDescriptionView);

        String recordTitle = recordTitleView.getText().toString();
        Date recordDate = new_date;
        String recordDescription = recordDescriptionView.getText().toString();

        Record oldRecord;
        Record newRecord = new Record(recordTitle, recordDate, recordDescription, null, null);
        newRecord.setAssociatedConditionID(conditionOfInterest.getId());
        if (location != null) {
            newRecord.setGeoLocation(new GeoLocation(location.latitude, location.longitude));
        }

        //TODO change these nulls
        if (intent.getIntExtra("recordIndex", -1) == -1) {
            recordListController.createRecord(newRecord);
            conditionOfInterest.getRecordList().addRecord(newRecord);
        } else {
            int recordIndex = intent.getIntExtra("recordIndex", 0);
            oldRecord = conditionOfInterest.getRecordList().getRecord(recordIndex);
            recordListController.editRecord(oldRecord, newRecord);
            oldRecord.editRecord(recordTitle, recordDate, recordDescription, null, null);
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
    // Modify record date using date and time pickers.
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
    // Add a photo to the server.
    public void addPhoto(View v) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setMessage("Would you like to take a photo or upload a previous photo?");
        adb.setCancelable(true);

        adb.setPositiveButton("Take photo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                File image = null;
                try {
                    image = createImageFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                verifyPermission(ModifyRecordActivity.this);

                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    if (image != null) {
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

                        Uri photoURI = FileProvider.getUriForFile(ModifyRecordActivity.this,
                                "ca.ualberta.cs.personal_condition_tracker",image);
                        new_image = image.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                    }
                }
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
    // View all body locations for a record.
    public void selectBodyLoc(View v) {
        if (conditionOfInterest.getRecordList().getRecordOfInterest() != null) {
            Intent intent = new Intent(ModifyRecordActivity.this,
                    ViewBodyLocationListActivity.class);
            startActivity(intent);
        }
    }
    // Show all photos for a record in a slideshow.
    public void showSlideshowActivity(View v) {
        if (conditionOfInterest.getRecordList().getRecordOfInterest() != null) {
            Intent intent = new Intent(ModifyRecordActivity.this, SlideshowActivity.class);
            startActivity(intent);
        }
    }
    // Create a unique file for an image.
    private File createImageFile() throws IOException {
        File directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        String currentTime = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File image = File.createTempFile("tracker_" + currentTime,".jpg", directory);
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (requestCode == PICK_IMAGE) { // result from gallery
            if (resultCode == RESULT_OK) {

            }
        }
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) { // result from camera
            if (resultCode == RESULT_OK) {
                File mImageFile = new File(new_image);
                Bitmap bitmap = BitmapFactory.decodeFile(new_image);
                Photograph photo = new Photograph();
                photo.setBase64EncodedString(bitmap);
                photo.setRecordIDForPhotograph(conditionOfInterest.getRecordList().getRecordOfInterest().getId());
                photographListController.createPhotograph(photo);


                Uri imageUri = Uri.fromFile(mImageFile);

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(ModifyRecordActivity.this, "Photo canceled!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ModifyRecordActivity.this, "The photo could not be added", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == SELECTED_LOCATION_REQUEST_CODE) { // result from select geo location
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
    // Go to maps activity to select geo location.
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
