package com.sia.siaandroidapp.java.ui.fragments.progress;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.sia.siaandroidapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import dagger.android.support.DaggerDialogFragment;

//public class ProgressDialog extends DialogFragment {
public class ProgressDialog extends DaggerDialogFragment {


    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new
                AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        //use layoutinflater to inflate xml
        LayoutInflater inflater = getActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View progress =
                inflater.inflate(R.layout.dialog_loading, null);
        builder.setView(progress);
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
        int height = getResources().getDimensionPixelSize(R.dimen.popup_height);
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setLayout(width, height);
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}