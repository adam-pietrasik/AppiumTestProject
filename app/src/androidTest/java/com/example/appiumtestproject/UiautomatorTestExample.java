package com.example.appiumtestproject;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class UiautomatorTestExample {


    private static final String BASIC_SAMPLE_PACKAGE
            = "com.example.appiumtestproject";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "TAPAN SINUT";
    private UiDevice mDevice;
    @Before
    public void startMainActivityFromHomeScreen() {
        mDevice = UiDevice.getInstance(getInstrumentation());

        mDevice.pressHome();

        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        Context context = getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }
    private String getLauncherPackageName() {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        PackageManager pm = getApplicationContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }


    @Test
    public void checkPreconditions() {
        assertThat(mDevice, notNullValue());
    }

    @Test
    public void testChangeText_sameActivity() {

        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "enterMessageId"))
                .setText(STRING_TO_BE_TYPED);

        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "enterMessageButtonId"))
                .click();

        UiObject2 changedText = mDevice
                .wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "messageTvId")),
                        500);
        assertThat(changedText.getText(), is(equalTo(STRING_TO_BE_TYPED)));
    }

    @Test
    public void testChangeText_clearText_sameActivity() {
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "enterMessageId"))
                .setText(STRING_TO_BE_TYPED);

        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "enterMessageButtonId"))
                .click();

        UiObject2 changedText = mDevice
                .wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "messageTvId")),
                        500);
        assertThat(changedText.getText(), is(equalTo(STRING_TO_BE_TYPED)));

        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "clearMessageButtonId"))
                .click();

        changedText = mDevice
                .wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "messageTvId")),
                        500);
        assertThat(changedText.getText(), is(equalTo("Text will be shown here")));
    }


    @Test
    public void testChangeText_newActivity() {

        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "goToActivityButtonId"))
                .click();

        UiObject2 textOnNewActivity = mDevice
                .wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "secondActivityTvMessageId")),
                        500);
        assertThat(textOnNewActivity.getText(), is(equalTo("This is second activity")));

        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "editTextMessageId"))
                .setText(STRING_TO_BE_TYPED);

        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "sendTextBackButtonId"))
                .click();

        UiObject2 changedText = mDevice
                .wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE, "messageTvId")),
                        500);
        assertThat(changedText.getText(), is(equalTo(STRING_TO_BE_TYPED)));
    }

    @Test
    public void openRecyclerView_nextFindGithubUser() {
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "goToRecyclerViewId"))
                .click();

        mDevice.wait(Until.findObject(By.res(BASIC_SAMPLE_PACKAGE,"searchEditText")),500).setText("KamilSzus");

        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "searchButtonId"))
                .click();
    }

}
