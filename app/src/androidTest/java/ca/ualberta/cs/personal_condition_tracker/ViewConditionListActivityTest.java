package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import ca.ualberta.cs.personal_condition_tracker.Activities.ModifyConditionActivity;
import ca.ualberta.cs.personal_condition_tracker.Activities.ViewConditionListActivity;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ViewConditionListActivityTest {

    @Rule
    public final ActivityTestRule<ViewConditionListActivity> ViewConditionListActivityRule = new ActivityTestRule<>(ViewConditionListActivity.class, true, false);

    @Test
    public void testClickAddCondition() throws Exception {
        Intents.init();
        UserAccountListController userAccountListController = new UserAccountListController();
        Patient patient = new Patient("patient", "new patient", null, "password");
        userAccountListController.getUserAccountList().setAccountOfInterest(patient);
        ViewConditionListActivityRule.launchActivity(new Intent());

        onView(withId(R.id.addAConditionButton)).perform(click());

        intended(hasComponent(ModifyConditionActivity.class.getName()));
        Intents.release();
    }

}
