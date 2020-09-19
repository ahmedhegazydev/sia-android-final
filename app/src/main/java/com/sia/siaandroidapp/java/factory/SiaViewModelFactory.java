package com.sia.siaandroidapp.java.factory;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sia.siaandroidapp.java.di.scope.AppScope;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;


@AppScope
public class SiaViewModelFactory implements ViewModelProvider.Factory {
    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    SiaViewModelFactory(final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(final Class<T> modelClass) {
        Provider<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (final Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) creator.get();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
}