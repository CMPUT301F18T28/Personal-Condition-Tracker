package ca.ualberta.cs.personal_condition_tracker.Activities;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.util.ArrayList;
import ca.ualberta.cs.personal_condition_tracker.Controllers.PhotographListController;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;
import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.Record;
import ca.ualberta.cs.personal_condition_tracker.Model.Photograph;
import ca.ualberta.cs.personal_condition_tracker.PhotographPagerAdapter;
import ca.ualberta.cs.personal_condition_tracker.R;

public class SlideshowActivity extends Activity {

    private PhotographListController photographListController = new PhotographListController();
    private UserAccountListController userAccountListController = new UserAccountListController();
    private Patient accountOfInterest = userAccountListController.getUserAccountList().getAccountOfInterest();
    private Condition conditionOfInterest = accountOfInterest.getConditionList().getConditionOfInterest();
    private Record recordOfInterest = conditionOfInterest.getRecordList().getRecordOfInterest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        ArrayList<Photograph> photos = photographListController.loadPhotographs(recordOfInterest);
        Toast.makeText(this, Integer.toString(photos.size()), Toast.LENGTH_SHORT).show();

        PhotographPagerAdapter mCustomPagerAdapter = new PhotographPagerAdapter(this, photos);

        ViewPager viewPager = findViewById(R.id.photographSlideshowViewpager);
        viewPager.setAdapter(mCustomPagerAdapter);

    }



}
