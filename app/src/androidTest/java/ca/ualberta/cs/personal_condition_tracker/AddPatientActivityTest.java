package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.After;
import org.junit.Before;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class AddPatientActivityTest {

    @Rule
    public final ActivityTestRule<AddPatientActivity> AddPatientActivityRule = new ActivityTestRule<>(AddPatientActivity.class, true, false);

    // Test to make sure an unknown patient can't be added.
    @Test
    public void testAddUnknownPatient() throws Exception {
        Intents.init();
        AddPatientActivityRule.launchActivity(new Intent());

        onView(withId(R.id.addPatientText)).perform(typeText("random")).perform(closeSoftKeyboard());

        onView(withId(R.id.addPatientConfirmButton)).perform(click());

        intended(hasComponent(AddPatientActivity.class.getName()));
        Intents.release();

    }

    // Test to make sure a known patient is successfully added.
    @Test
    public void testAddKnownPatient() throws Exception {
        Intents.init();
        AddPatientActivityRule.launchActivity(new Intent());

        onView(withId(R.id.addPatientText)).perform(typeText("testPatient")).perform(closeSoftKeyboard());

        onView(withId(R.id.addPatientConfirmButton)).perform(click());

        intended(hasComponent(ViewPatientListActivity.class.getName()));
        Intents.release();

    }

}