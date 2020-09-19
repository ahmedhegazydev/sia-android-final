package com.sia.siaandroidapp.java.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<B extends ViewDataBinding> extends DaggerFragment {

    protected B binding;


    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        AndroidInjection.inject(getActivity());
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        return binding.getRoot();
    }

    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public abstract int getLayout();

//    public abstract View getRoot();


}
