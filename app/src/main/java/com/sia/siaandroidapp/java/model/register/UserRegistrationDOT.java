package com.sia.siaandroidapp.java.model.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserRegistrationDOT {


    @SerializedName("userRegisterationDTO")
    @Expose
    private RegistrationModel registrationModel;

    public RegistrationModel getRegistrationModel() {
        return registrationModel;
    }

    public void setRegistrationModel(RegistrationModel registrationModel) {
        this.registrationModel = registrationModel;
    }



}
