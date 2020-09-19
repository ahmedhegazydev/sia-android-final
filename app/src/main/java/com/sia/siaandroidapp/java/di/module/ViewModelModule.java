package com.sia.siaandroidapp.java.di.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sia.siaandroidapp.java.di.scope.ViewModelKey;
import com.sia.siaandroidapp.java.factory.SiaViewModelFactory;
import com.sia.siaandroidapp.java.viewmodel.retrofit.LoginViewModel;
import com.sia.siaandroidapp.java.viewmodel.retrofit.roles.RolesViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RolesViewModel.class)
    abstract ViewModel bindGetAllRolesViewModel(RolesViewModel loginViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindSiaViewModelFactory(SiaViewModelFactory factory);
}
