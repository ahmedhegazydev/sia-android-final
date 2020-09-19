package com.sia.siaandroidapp.java.model.responses;

public class BasicResponse<T> {
    private String jwttoken = null;
    private T data;
//    private Boolean successful;


    public String getJwttoken() {
        return jwttoken;
    }

    public T getData() {
        return data;
    }
}
