package ca.ualberta.cs.personal_condition_tracker;

import android.content.Intent;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import ca.ualberta.cs.personal_condition_tracker.Activities.AddPatientActivity;
import ca.ualberta.cs.personal_condition_tracker.Activities.ModifyRecordActivity;
import ca.ualberta.cs.personal_condition_tracker.Activities.ViewCommentsActivity;
import ca.ualberta.cs.personal_condition_tracker.Activities.ViewPatientListActivity;
import ca.ualberta.cs.personal_condition_tracker.Activities.ViewRecordListActivity;
import ca.ualberta.cs.personal_condition_tracker.Controllers.UserAccountListController;
import ca.ualberta.cs.personal_condition_tracker.Model.CareProvider;
import ca.ualberta.cs.personal_condition_tracker.Model.Condition;
import ca.ualberta.cs.personal_condition_tracker.Model.ConditionList;
import ca.ualberta.cs.personal_condition_tracker.Model.Patient;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ViewRecordListActivityTest {
    @Rule
    public final ActivityTestRule<ViewRecordListActivity> ViewRecordListActivityRule = new ActivityTestRule<>(ViewRecordListActivity.class, true, false);

    @Test
    public void testViewRecordList() throws Exception {
        Intents.init();
        UserAccountListController userAccountListController = new UserAccountListController();
        Patient patient = new Patient("patient", "new patient", null, null);
        userAccountListController.getUserAccountList().setAccountOfInterest(patient);
        ConditionList conditionList = new ConditionList();
        Condition condition = new Condition();
        condition.setTitle("condition");
        condition.setId("0");
        conditionList.addCondition(condition);
        userAccountListController.getUserAccountList().getAccountOfInterest().getConditionList().setConditionOfInterest(condition);
        ViewRecordListActivityRule.launchActivity(new Intent());
        intended(hasComponent(ViewRecordListActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void testClickViewComments() throws Exception {
        Intents.init();
        UserAccountListController userAccountListController = new UserAccountListController();
        Patient patient = new Patient("patient", "new patient", null, null);
        userAccountListController.getUserAccountList().setAccountOfInterest(patient);
        ConditionList conditionList = new ConditionList();
        Condition condition = new Condition();
        condition.setTitle("condition");
        condition.setId("0");
        conditionList.addCondition(condition);
        userAccountListController.getUserAccountList().getAccountOfInterest().getConditionList().setConditionOfInterest(condition);
        ViewRecordListActivityRule.launchActivity(new Intent());

        onView(withId(R.id.viewCommentsButton)).perform(click());
        intended(hasComponent(ViewCommentsActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void testClickAddRecord() throws Exception {
        Intents.init();
        UserAccountListController userAccountListController = new UserAccountListController();
        Patient patient = new Patient("patient", "new patient", null, null);
        userAccountListController.getUserAccountList().setAccountOfInterest(patient);
        ConditionList conditionList = new ConditionList();
        Condition condition = new Condition();
        condition.setTitle("condition");
        condition.setId("0");
        conditionList.addCondition(condition);
        userAccountListController.getUserAccountList().getAccountOfInterest().getConditionList().setConditionOfInterest(condition);
        ViewRecordListActivityRule.launchActivity(new Intent());

        onView(withId(R.id.addACommentButton)).perform(click());
        intended(hasComponent(ModifyRecordActivity.class.getName()));
        Intents.release();
    }

}
