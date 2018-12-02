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
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> MainActivityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Test
    public void testSignInUnknown() throws Exception {
        Intents.init();
        MainActivityRule.launchActivity(new Intent());

        onView(withId(R.id.userIDEntry)).perform(typeText("random")).perform(closeSoftKeyboard());

        onView(withId(R.id.signInButton)).perform(click());

        intended(hasComponent(MainActivity.class.getName()));
        Intents.release();

    }

    // Test to make sure signing in as a patient leads to the correct activity.
    @Test
    public void testSignInValidPatient() {
        Intents.init();
        MainActivityRule.launchActivity(new Intent());

        onView(withId(R.id.userIDEntry)).perform(typeText("testPatient")).perform(closeSoftKeyboard());

        onView(withId(R.id.signInButton)).perform(click());

        intended(hasComponent(ViewConditionListActivity.class.getName()));
        Intents.release();

    }

    // Test to make sure signing in as a care provider leads to the correct activity.
    @Test
    public void testSignInValidCareProvider() {
        Intents.init();
        MainActivityRule.launchActivity(new Intent());
        onView(withId(R.id.userIDEntry)).perform(typeText("testCareProvider")).perform(closeSoftKeyboard());

        onView(withId(R.id.signInButton)).perform(click());

        intended(hasComponent(ViewPatientListActivity.class.getName()));
        Intents.release();

    }

    // Test ot make sure signing up leads to the correct activity.
    @Test
    public void testSignUp() {
        Intents.init();
        MainActivityRule.launchActivity(new Intent());
        onView(withId(R.id.signUpButton)).perform(click());
        intended(hasComponent(SignUpActivity.class.getName()));
        Intents.release();

    }
}