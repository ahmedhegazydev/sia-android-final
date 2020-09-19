package com.sia.siaandroidapp.java.di.component;

import com.sia.siaandroidapp.java.app_config.AppConfig;
import com.sia.siaandroidapp.java.di.module.ActivityBindingModule;
import com.sia.siaandroidapp.java.di.module.AppModule;
import com.sia.siaandroidapp.java.di.module.FragmentBindingModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

//@AppScope//My Custom Scope
@Singleton
@Component(modules = {
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        FragmentBindingModule.class,

})
public interface ApplicationComponent extends AndroidInjector<AppConfig> {

    void inject(AppConfig application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(AppConfig application);

        ApplicationComponent build();
    }
}