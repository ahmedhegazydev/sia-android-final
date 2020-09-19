package com.sia.siaandroidapp.java.di.module;


import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.app_config.AppConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {


    @Singleton
    @Provides
    static String someString() {
        return "this is a test string";
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions() {
        return RequestOptions.placeholderOf(R.drawable.logo_white)
                .error(R.drawable.logo_white);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(AppConfig application,
                                               RequestOptions requestOptions) {
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable provideAppDrawable(AppConfig application) {
        return ContextCompat.getDrawable(application,
                R.drawable.sia_logo_holo_dark);
    }


}
