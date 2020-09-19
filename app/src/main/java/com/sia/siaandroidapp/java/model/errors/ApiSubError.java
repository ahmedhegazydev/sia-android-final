package com.sia.siaandroidapp.java.model.errors;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

public class ApiSubError {

    @SerializedName("subErrors")
    private String field;

    @SerializedName("subErrors")
    private Object rejectedValue;

    @SerializedName("subErrors")
    private String message;

    @SerializedName("subErrors")
    private int errorCode;

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public String getMessage() {
        return message;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
