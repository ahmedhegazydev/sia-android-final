package com.sia.siaandroidapp.java.ui.snacks;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.irozon.sneaker.Sneaker;
import com.sia.siaandroidapp.R;

public final class Snacks {

    public static void showNetErrRetry(Context context, View container, View.OnClickListener action) {
        Snackbar.make(container, context.getString(R.string.no_internet),
                Snackbar.LENGTH_LONG)
                .setAction(context.getString(R.string.try_word), action)
                .setActionTextColor(context.getResources().getColor(android.R.color.holo_red_light))
                .show();
    }

    public static void showNetErrNoRetry(Context context, View container) {
        Snackbar.make(container, context.getString(R.string.no_internet),
                Snackbar.LENGTH_LONG)
                .setActionTextColor(context.getResources().getColor(android.R.color.holo_red_light))
                .show();
    }

    public static void showSuccess(Activity activity, String message) {
        Sneaker.with(activity)
//                        .setTitle(activity.getString(R.string.email_craeted_success))
                .setMessage(message)
                .sneakSuccess();
    }

    public static void showError(Activity activity, String message) {
        Sneaker.with(activity)
//                        .setTitle(activity.getString(R.string.email_craeted_success))
                .setMessage(message)
                .sneakError();
    }

    public static void showWarning(Activity activity, String message) {
        Sneaker.with(activity)
//                        .setTitle(activity.getString(R.string.email_craeted_success))
                .setMessage(message)
                .sneakWarning();
    }


}
