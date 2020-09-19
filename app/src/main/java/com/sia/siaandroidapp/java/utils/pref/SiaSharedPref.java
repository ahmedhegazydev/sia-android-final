package com.sia.siaandroidapp.java.utils.pref;

import android.content.Context;
import android.content.SharedPreferences;

public class SiaSharedPref {

    //store SharedPreferences according to its name
    public SharedPreferences.Editor saveData(Context context, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return sharedPreferences.edit();
    }

    //get  SharedPreferences data according to its name
    public SharedPreferences loadData(Context context, String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    //clear SharedPreferences according to its name
    public void deleteData(Context context, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }


}
