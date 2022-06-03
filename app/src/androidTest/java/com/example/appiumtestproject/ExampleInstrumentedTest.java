package com.example.appiumtestproject;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.enterMessageId))
                .perform(typeText("ADAM PIETRASIK"), closeSoftKeyboard());
        onView(withId(R.id.enterMessageButtonId)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.messageTvId)).check(matches(withText("ADAM PIETRASIK")));
    }

    @Test
    public void changeText_next_clear_text_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.enterMessageId))
                .perform(typeText("ADAM PIETRASIK"), closeSoftKeyboard());
        onView(withId(R.id.enterMessageButtonId)).perform(click());

        onView(withId(R.id.clearMessageButtonId)).perform(click());

        onView(withId(R.id.messageTvId)).check(matches(withText("")));
    }

    @Test
    public void change_activity_next_type_text_get_text_in_previous_activity() {
        // Type text and then press the button.

        onView(withId(R.id.goToActivityButtonId)).perform(click());

        onView(withId(R.id.secondActivityTvMessageId)).check(matches(withText("This is second activity")));

        onView(withId(R.id.editTextMessageId))
                .perform(typeText("ADAM PIETRASIK"), closeSoftKeyboard());


        onView(withId(R.id.sendTextBackButtonId)).perform(click());

        onView(withId(R.id.messageTvId)).check(matches(withText("ADAM PIETRASIK")));
    }
}