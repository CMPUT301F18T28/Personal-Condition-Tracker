package ca.ualberta.cs.personal_condition_tracker;


import android.content.Intent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import ca.ualberta.cs.personal_condition_tracker.Activities.AddPatientActivity;
import ca.ualberta.cs.personal_condition_tracker.Activities.ViewPatientListActivity;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;
import ca.ualberta.cs.personal_condition_tracker.Model.CareProvider;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ViewPatientListActivityTest {
    @Rule
    public final ActivityTestRule<ViewPatientListActivity> ViewPatientListActivityRule = new ActivityTestRule<>(ViewPatientListActivity.class, true, false);

    @Test
    public void testClickAddNewPatient() throws Exception {
        Intents.init();
        UserAccountListController userAccountListController = new UserAccountListController();
        CareProvider careProvider = new CareProvider("care provider", "new care provider", null, "password");
        userAccountListController.getUserAccountList().setActiveCareProvider(careProvider);
        ViewPatientListActivityRule.launchActivity(new Intent());

        onView(withId(R.id.addPatientButton)).perform(click());

        intended(hasComponent(AddPatientActivity.class.getName()));
        Intents.release();
    }

}
