package com.sia.siaandroidapp.java.app_config;

import android.content.Context;
import android.content.ContextWrapper;

import androidx.multidex.MultiDex;

import com.crashlytics.android.Crashlytics;
import com.pixplicity.easyprefs.library.Prefs;
import com.sia.siaandroidapp.java.di.component.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.fabric.sdk.android.Fabric;


/**
 * The fully qualified name of an Application subclass implemented
 * for the application.
 * When the application process is started,
 * this class is instantiated before any of the application's components.
 */

public class AppConfig extends DaggerApplication {
//public class AppConfig extends Application implements HasActivityInjector {
//public class AppConfig extends Application {

//    @Inject
//    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

//    @Inject
//    public AppConfig() {
//    }

    private static final String TAG = "AppConfig";
    private static AppConfig mInstance;

    public static synchronized AppConfig getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        mInstance = this;

        // Initialize the Prefs class (Builder)
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();

//        DaggerApplicationComponent
//                .builder()
//                .application(this)
//                .build()
//                .inject(this);


    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent
                .builder()
                .application(this)
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

//    @Override
//    public AndroidInjector<Activity> activityInjector() {
//        return activityDispatchingAndroidInjector;
//    }

}
