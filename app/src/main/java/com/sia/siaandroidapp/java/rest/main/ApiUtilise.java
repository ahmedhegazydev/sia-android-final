package com.sia.siaandroidapp.java.rest.main;

public class ApiUtilise {
    private ApiUtilise() {
    }

    public static IrApiService getAPIService() {

        return RetrofitClient.getClient().create(IrApiService.class);
    }
}