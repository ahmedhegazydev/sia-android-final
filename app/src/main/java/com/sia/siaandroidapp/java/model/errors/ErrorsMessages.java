package com.sia.siaandroidapp.java.model.errors;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorsMessages {

    @SerializedName("timestamp")
//    private LocalDateTime timestamp;
    private String timestamp;

    @SerializedName("errorCode")
    private Integer errorCode;

    @SerializedName("errorMessage")
    private String errorMessage;

    @SerializedName("debugMessage")
    private String debugMessage;

    @SerializedName("userLocalization")
    private String userLocalization;

    @SerializedName("subErrors")
//    private List<ApiSubError> subErrors;
    private String subErrors;

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public String getUserLocalization() {
        return userLocalization;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSubErrors() {
        return subErrors;
    }
}
