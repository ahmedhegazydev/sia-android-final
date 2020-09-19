package com.sia.siaandroidapp.java.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.sia.siaandroidapp.java.utils.SiaUtils;

import dagger.android.AndroidInjection;
import dagger.android.support.DaggerAppCompatActivity;

//public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {
public abstract class BaseActivity<B extends ViewDataBinding> extends DaggerAppCompatActivity {

    protected B binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
//        SiaUtils.fullScreen(this);
        binding = DataBindingUtil.setContentView(this, getLayout());
        super.onCreate(savedInstanceState);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public abstract int getLayout();
}
