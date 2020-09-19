package com.sia.siaandroidapp.java.model;

import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("ErrorMessage")
    private String errorMag;
    @SerializedName("LoginStatus")
    private int mLoginStatus;
    @SerializedName("UserID")
    private int mUserID;
    @SerializedName("UserName")
    private String mUserName;

    public LoginModel(String errorMag, int mLoginStatus, int mUserID, String mUserName) {
        this.errorMag = errorMag;
        this.mLoginStatus = mLoginStatus;
        this.mUserID = mUserID;
        this.mUserName = mUserName;
    }

    public String getErrorMag() {
        return errorMag;
    }

    public void setErrorMag(String errorMag) {
        this.errorMag = errorMag;
    }

    public int getmLoginStatus() {
        return mLoginStatus;
    }

    public void setmLoginStatus(int mLoginStatus) {
        this.mLoginStatus = mLoginStatus;
    }

    public int getmUserID() {
        return mUserID;
    }

    public void setmUserID(int mUserID) {
        this.mUserID = mUserID;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }
}
