package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import ca.ualberta.cs.personal_condition_tracker.Activities.ModifyConditionActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ModifyConditionActivityTest {

    @Rule
    public final ActivityTestRule<ModifyConditionActivity> ModifyConditionActivityRule = new ActivityTestRule<>(ModifyConditionActivity.class, true, false);

//    @Test
//    public void testAddNewCondition() throws Exception {
//        Intents.init();
//        ModifyConditionActivityRule.launchActivity(new Intent());
//
//        onView(withId(R.id.conditionTitleView)).perform(typeText("New Condition")).perform(closeSoftKeyboard());
//        onView(withId(R.id.conditionDescriptionView)).perform(typeText("description")).perform(closeSoftKeyboard());
//
//        onView(withId(R.id.modifyConditionConfirmButton)).perform(click());
//
//        intended(hasComponent(ModifyConditionActivity.class.getName()));
//        Intents.release();
//
//    }

    @Test
    public void testCancel() throws Exception {
        Intents.init();
        ModifyConditionActivityRule.launchActivity(new Intent());

        onView(withId(R.id.modifyConditionCancelButton)).perform(click());

        intended(hasComponent(ModifyConditionActivity.class.getName()));
        Intents.release();

    }

}
