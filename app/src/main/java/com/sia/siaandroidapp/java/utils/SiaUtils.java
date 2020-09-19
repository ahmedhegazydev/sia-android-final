package com.sia.siaandroidapp.java.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.pixplicity.easyprefs.library.Prefs;
import com.sia.siaandroidapp.R;
import com.sia.siaandroidapp.java.constants.Constants;
import com.sia.siaandroidapp.java.model.PhoneValidateResponse;
import com.sia.siaandroidapp.java.model.RoleResponse;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public final class SiaUtils {

    /**
     * @param activity
     */
    public static void fullScreen(Activity activity) {
        // remove title
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * @param target
     * @return
     */
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    /**
     * @param list
     * @param <T>
     * @return
     */
    // Generic function to convert list to set
    public static <T> Set<T> convertListToSet(List<T> list) {
        // create an empty set
        Set<T> set = new HashSet<>();
        // Add each element of list into the set
        for (T t : list)
            set.add(t);
        // return the set
        return set;
    }


    /**
     * @param getSelectedPos
     * @return
     */
    public static String getSignUpAs(int getSelectedPos) {
        String signUpAs = "";
        Type dataType = new TypeToken<List<RoleResponse>>() {
        }.getType();
        List<RoleResponse> fetchedRoles = new Gson().fromJson(Prefs.getString(Constants
                .MyPrefs.FETCHED_ROLES, ""), dataType);
        for (int i = 0; i < fetchedRoles.size(); i++) {
            if (i + 1 == getSelectedPos) {
                return fetchedRoles.get(i + 1).getName();
            }
        }
        return signUpAs;
    }

    /**
     * @param phoneNumber
     * @param countryCode
     * @return
     */
//    public static boolean isPhoneNumberValid(Context context, String phoneNumber, String countryCode) {
//        //NOTE: This should probably be a member variable.
////        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
//        PhoneNumberUtil phoneUtil = new PhoneNumberUtil;
//        try {
//            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phoneNumber, countryCode);
//            return phoneUtil.isValidNumber(numberProto);
//        } catch (NumberParseException e) {
//            System.err.println("NumberParseException was thrown: " + e.toString());
//        }
//        return false;
//    }


    /**
     * @param mobNumber
     * @param countryCode
     * @return
     */
    public static PhoneValidateResponse isPhoneNumberValid(String mobNumber,
                                                           String countryCode) {
        PhoneValidateResponse phoneNumberValidate = new PhoneValidateResponse();
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = null;
        boolean finalNumber = false;
        PhoneNumberUtil.PhoneNumberType isMobile = null;
        boolean isValid = false;
        try {
            String isoCode = phoneNumberUtil.getRegionCodeForCountryCode(Integer.parseInt(countryCode));
            phoneNumber = phoneNumberUtil.parse(mobNumber, isoCode);
            isValid = phoneNumberUtil.isValidNumber(phoneNumber);
            isMobile = phoneNumberUtil.getNumberType(phoneNumber);
            phoneNumberValidate.setCode(String.valueOf(phoneNumber.getCountryCode()));
            phoneNumberValidate.setPhone(String.valueOf(phoneNumber.getNationalNumber()));
            phoneNumberValidate.setValid(false);

        } catch (NumberParseException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (isValid && (PhoneNumberUtil.PhoneNumberType.MOBILE == isMobile ||
                PhoneNumberUtil.PhoneNumberType.FIXED_LINE_OR_MOBILE == isMobile)) {
            finalNumber = true;
            phoneNumberValidate.setValid(true);
        }

        return phoneNumberValidate;
    }

    private static final String TAG = "SiaUtils";

    public static boolean isEmailOrPhone(String phoneOrEmail) {
        if (SiaUtils.isValidEmail(phoneOrEmail)) {
            Log.e(TAG, "isEmailOrPhone: " + phoneOrEmail);
            return true;
        } else {
            if (SiaUtils.isPhoneNumberValid(phoneOrEmail, "20")
                    .isValid()) {
                return false;
            }
        }
        return false;
    }

    public static Spinner setArrayAdapterToSpinner(Context context,
                                                   Spinner spinnerSpecialization, int arr) {
        String[] myResArray = context.getResources().getStringArray(arr);
        List<String> myResArrayList = Arrays.asList(myResArray);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, myResArrayList);
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, R.layout.spinner_item,myResArrayList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSpecialization.setAdapter(dataAdapter);
        return spinnerSpecialization;
    }

    //Check if our device connecting to Network or not.
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void openUrlWebBrowser(Context context, String url){
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }

}
