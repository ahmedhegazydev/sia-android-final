package com.sia.siaandroidapp.java.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JwtTokenResponse {

    @Expose
    @SerializedName("jwttoken")
    private String jwttoken;

    public JwtTokenResponse() {
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public void setJwttoken(String jwttoken) {
        this.jwttoken = jwttoken;
    }
}
