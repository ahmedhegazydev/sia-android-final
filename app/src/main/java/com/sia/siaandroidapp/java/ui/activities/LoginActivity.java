package com.sia.siaandroidapp.java.ui.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.utils.SiaUtils;
import com.sia.siaandroidapp.java.viewmodel.retrofit.LoginViewModel;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etEnterEmailOrPhoneNumber)
    EditText etEnterEmailOrPhoneNumber;
    @BindView(R.id.etEnterPassword)
    EditText etEnterPassword;
    private LoginViewModel viewModel;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //making activity fullscreen
        SiaUtils.fullScreen(this);

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);


    }

    @OnClick(R.id.btnLogin)
    public void onBtnLoginClicked(View v) {

        observeLogin(
                etEnterEmailOrPhoneNumber.getText().toString().trim(),
                etEnterPassword.getText().toString().trim());


    }

    @OnClick(R.id.btnLoginWithFacebook)
    public void onBtnLoginWithFBClicked(View v) {


    }

    private void observeLogin(String email,
                              String password) {
//        viewModel.getLogin(email, password)
//                .observe(this, response -> {
//
//                });
//
//
//        viewModel.getError().observe(this, error -> {
//            if (error) {
//                Toasty.error(this,
//                        getString(R.string.error),
//                        Toasty.LENGTH_LONG, true).show();
//            }
//        });
//
//        viewModel.startLoading().observe(this, loading -> {
//            if (loading) {
//                loadingDialog.show();
//            } else {
//                loadingDialog.dismiss();
//            }
//        });

    }


    private void initializeLoadingDialog() {
        loadingDialog = new Dialog(getApplicationContext(), R.style.FullHeightDialog);
        loadingDialog.setContentView(R.layout.dialog_loading);
        Objects.requireNonNull(loadingDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        loadingDialog.setCancelable(false);
    }



}
