package com.sia.siaandroidapp.java.di.module;


import com.sia.siaandroidapp.java.ui.activities.MainActivity;
import com.sia.siaandroidapp.java.ui.activities.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector()
    abstract SplashActivity contributeSplashActivity();

    @ContributesAndroidInjector(
            modules = {FragmentBindingModule.class,
                    ViewModelModule.class,

            }
    )
    abstract MainActivity contributeMainActivity();
}
