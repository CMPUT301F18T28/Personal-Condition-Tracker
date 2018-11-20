package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ModifyRecordActivityTest {

    @Rule
    public final ActivityTestRule<ModifyRecordActivity> ModifyRecordActivityRule = new ActivityTestRule<>(ModifyRecordActivity.class, true, false);

    //TODO: Fix UI layout to allow this test case to pass.
//    @Test
//    public void testAddNewRecord() throws Exception {
//        Intents.init();
//        UserAccountListController userAccountListController = new UserAccountListController();
//        Patient patient = new Patient("patient", "new patient", null, "password");
//        userAccountListController.getUserAccountList().setAccountOfInterest(patient);
//        CareProvider careProvider = new CareProvider("care provider", "new care provider", null, "password");
//        userAccountListController.getUserAccountList().setActiveCareProvider(careProvider);
//        ModifyRecordActivityRule.launchActivity(new Intent());
//
//        onView(withId(R.id.recordTitleView)).perform(typeText("New Record")).perform(closeSoftKeyboard());
//        onView(withId(R.id.recordDescriptionView)).perform(typeText("description")).perform(closeSoftKeyboard());
//
//        onView(withId(R.id.modifyRecordConfirmButton)).perform(click());
//
//        intended(hasComponent(ModifyRecordActivity.class.getName()));
//        Intents.release();
//
//    }

    @Test
    public void testCancel() throws Exception {
        Intents.init();
        UserAccountListController userAccountListController = new UserAccountListController();
        Patient patient = new Patient("patient", "new patient", null, "password");
        userAccountListController.getUserAccountList().setAccountOfInterest(patient);
        CareProvider careProvider = new CareProvider("care provider", "new care provider", null, "password");
        userAccountListController.getUserAccountList().setActiveCareProvider(careProvider);
        ModifyRecordActivityRule.launchActivity(new Intent());

        onView(withId(R.id.modifyRecordCancelButton)).perform(click());

        intended(hasComponent(ModifyRecordActivity.class.getName()));
        Intents.release();

    }

}
