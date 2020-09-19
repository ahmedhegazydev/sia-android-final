package com.sia.siaandroidapp.java.di.module;


import com.sia.siaandroidapp.java.ui.fragments.CompleteAccountAsFragment;
import com.sia.siaandroidapp.java.ui.fragments.CreateAccountFragment;
import com.sia.siaandroidapp.java.ui.fragments.LoginFragment;
import com.sia.siaandroidapp.java.ui.fragments.VerifyEnterCodeFragment;
import com.sia.siaandroidapp.java.ui.fragments.know_u_better.KnowUBetter1Fragment;
import com.sia.siaandroidapp.java.ui.fragments.know_u_better.KnowUBetter2Fragment;
import com.sia.siaandroidapp.java.ui.fragments.know_u_better.KnowUBetter3Fragment;
import com.sia.siaandroidapp.java.ui.fragments.know_u_better.KnowUBetter4Fragment;
import com.sia.siaandroidapp.java.ui.fragments.know_u_better.KnowUMainFragment;
import com.sia.siaandroidapp.java.ui.fragments.reset.ResetPassPhase1Fragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    @ContributesAndroidInjector()
    abstract CompleteAccountAsFragment contributeCompAsFragment();

    @ContributesAndroidInjector()
    abstract KnowUMainFragment contributeKnowUMainFragment();

    @ContributesAndroidInjector()
    abstract KnowUBetter1Fragment contributeKnowUBetter1Fragment();

    @ContributesAndroidInjector()
    abstract KnowUBetter2Fragment contributeKnowUBetter2Fragment();

    @ContributesAndroidInjector()
    abstract KnowUBetter3Fragment contributeKnowUBetter3Fragment();

    @ContributesAndroidInjector()
    abstract KnowUBetter4Fragment contributeKnowUBetter4Fragment();

    @ContributesAndroidInjector()
    abstract CreateAccountFragment contributeCreateActFragment();

    @ContributesAndroidInjector()
    abstract LoginFragment contributeLoginFragment();

    @ContributesAndroidInjector()
    abstract VerifyEnterCodeFragment contributeVerifyEnterCodeFragment();


    @ContributesAndroidInjector()
    abstract ResetPassPhase1Fragment contributeResetPassPhase1FragmentFragment();


}
