package com.sia.siaandroidapp.java.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.sia.siaandroidapp.R;

import java.util.Objects;

public final class DialogCreator {

    public static Dialog init(Context context) {
        Dialog loadingDialog = new Dialog(context, R.style.FullHeightDialog);
        loadingDialog.setContentView(R.layout.dialog_loading);
        Objects.requireNonNull(loadingDialog.getWindow())
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        loadingDialog.setCancelable(true);
//        loadingDialog.setCancelable(false);
        return loadingDialog;
    }
}
