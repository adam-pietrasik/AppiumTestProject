package com.example.appiumtestproject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTestExample {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void changeText_sameActivity() {
        onView(withId(R.id.enterMessageId))
                .perform(typeText("ADAM PIETRASIK"), closeSoftKeyboard());
        onView(withId(R.id.enterMessageButtonId)).perform(click());

        onView(withId(R.id.messageTvId)).check(matches(withText("ADAM PIETRASIK")));
    }

    @Test
    public void changeText_next_clear_text_sameActivity() {
        onView(withId(R.id.enterMessageId))
                .perform(typeText("ADAM PIETRASIK"), closeSoftKeyboard());
        onView(withId(R.id.enterMessageButtonId)).perform(click());

        onView(withId(R.id.clearMessageButtonId)).perform(click());

        onView(withId(R.id.messageTvId)).check(matches(withText("")));
    }

    @Test
    public void change_activity_next_type_text_get_text_in_previous_activity() {
        onView(withId(R.id.goToActivityButtonId)).perform(click());

        onView(withId(R.id.secondActivityTvMessageId)).check(matches(withText("This is second activity")));

        onView(withId(R.id.editTextMessageId))
                .perform(typeText("ADAM PIETRASIK"), closeSoftKeyboard());


        onView(withId(R.id.sendTextBackButtonId)).perform(click());

        onView(withId(R.id.messageTvId)).check(matches(withText("ADAM PIETRASIK")));
    }

    @Test
    public void openRecyclerView_nextFindGithubUser() {
        onView(withId(R.id.goToRecyclerViewId)).perform(click());

        onView(withId(R.id.searchEditText)).perform(typeText("KamilSzus"), closeSoftKeyboard());

        onView(withId(R.id.searchButtonId)).perform(click());

        onView(withId(R.id.dataRecyclerView)).check(matches(isDisplayed()));

    }

    @Test
    public void openRecyclerView_nextBackToMainActivity(){
        onView(withId(R.id.goToRecyclerViewId)).perform(click());

        onView(withId(R.id.searchEditText)).perform(typeText("KamilSzus"), closeSoftKeyboard());

        onView(withId(R.id.searchButtonId)).perform(click());

        onView(isRoot()).perform(ViewActions.pressBack());

        onView(withId(R.id.enterMessageId))
                .perform(typeText("ADAM PIETRASIK"), closeSoftKeyboard());
        onView(withId(R.id.enterMessageButtonId)).perform(click());

        onView(withId(R.id.messageTvId)).check(matches(withText("ADAM PIETRASIK")));
    }

}