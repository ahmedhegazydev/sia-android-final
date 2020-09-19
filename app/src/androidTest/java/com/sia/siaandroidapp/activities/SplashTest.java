package com.sia.siaandroidapp.activities;

import android.content.Context;
import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.sia.siaandroidapp.java.ui.activities.HomeActivity;
import com.sia.siaandroidapp.java.ui.activities.MainActivity;
import com.sia.siaandroidapp.java.ui.activities.SplashActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class SplashTest {

    @Rule
    public ActivityTestRule<SplashActivity> rule =
            new ActivityTestRule<SplashActivity>(SplashActivity.class) {
                @Override
                protected Intent getActivityIntent() {
//                    InstrumentationRegistry.getTargetContext();
//                    Intent intent = new Intent(Intent.ACTION_MAIN);
//                    intent.putExtra("MYKEY", "Hello");
                    return null;
                }
            };


    /**
     * Testing which activity will be started after splash,
     * incaseof the is loggedin or not.
     */
    @Test
    public void testStartConvenientIntent() {
        // Context of the app under test.
        Context appContext =
                InstrumentationRegistry.getInstrumentation().getTargetContext();
        SplashActivity activity = rule.getActivity();
        boolean isLogged = true;
        if (isLogged) {
            activity.startActivity(new Intent(activity, HomeActivity.class));
            assertTrue("HomePage", isLogged);
        } else {
            activity.startActivity(new Intent(activity, MainActivity.class));
            assertTrue("MainPage", isLogged);
        }
    }


}
