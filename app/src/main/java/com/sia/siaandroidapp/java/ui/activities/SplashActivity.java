package com.sia.siaandroidapp.java.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;

import com.pixplicity.easyprefs.library.Prefs;
import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.base.BaseActivity;
import com.sia.siaandroidapp.java.constants.Constants;


/**
 * The SplashActivity Class that will start once for the user, incaseof he is
 * logged in,
 * SplashActivity will go to the applicable Login/Home screen.
 *
 * @author Ahmed Hegazy
 * @version 1.0
 * @since June 2, 2020
 */
public class SplashActivity extends BaseActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private Handler mHandlerSessionManager = null;
    private Runnable mRunnableSessionManager = null;

    /**
     * Override the getLayout by the convenient layout res
     *
     * @return
     */
    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    /**
     * This method will call accessing of handler and it's runnable for session manager
     * and then calling the starting this passed runnable after a 1500 sec.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        initHandlerSessionManager();

        startSessionHandler();

    }

    /**
     * Initialize the handler and its passed runnable that will holds the
     *
     * @run method for firing its content after a 1500 sec.
     */
    private void initHandlerSessionManager() {
        mHandlerSessionManager = new Handler();
        mRunnableSessionManager = () -> {
            startActivity(getApplicableIntent());
            finish();
        };

    }

    /**
     * Start the applicable intent/activity for the user session purpose,for
     * handling if the user is logged in or not, so he will be directed to the
     * HomePage if logged either will be redirected to the Login/Main page
     * if not.
     */
    private void startSessionHandler() {
        mHandlerSessionManager.postDelayed(mRunnableSessionManager, Constants.MILL_SEC);
    }

    /**
     * Get the applicable intent in case of user session, for
     * handling if the user is logged in or not, so he will be directed to the
     * HomePage if logged either will be redirected to the Login/Main page
     * if not.
     *
     * @return
     */
    private Intent getApplicableIntent() {
        boolean loggedIn = Prefs.getPreferences()
                .getBoolean(Constants.MyPrefs.IS_LOGIN, true);
        Intent intent;
        if (loggedIn) {
            //for login
            intent = new Intent(this, MainActivity.class);
        } else {
            //for home page
            intent = new Intent(this, HomeActivity.class);
        }
        return intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        removeHandlerCallbacks();

    }

    /**
     * Remove your callback from your handler
     */
    private void removeHandlerCallbacks() {
        if (mHandlerSessionManager != null) {
            mHandlerSessionManager.removeCallbacks(mRunnableSessionManager);
            Log.e(TAG, "onDestroy: 2");
        }
    }

}
