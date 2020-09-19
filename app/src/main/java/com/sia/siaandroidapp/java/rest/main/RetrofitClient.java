package com.sia.siaandroidapp.java.rest.main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sia.siaandroidapp.java.constants.Constants;

import java.lang.invoke.ConstantCallSite;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {

    private static Retrofit retrofit = null;
    private static Retrofit retrofitMyIpAddress = null;
    private static Retrofit retrofitMyLocationIpAddress = null;
    private static Retrofit retrofitMyGeoCodeAddress = null;
    private static Retrofit retrofitMyGeoCodeAddress2 = null;

    static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL_UPLOAD_FILE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    static Retrofit getClient(String baseUrl) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        if (retrofitMyIpAddress == null) {
            retrofitMyIpAddress = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofitMyIpAddress;
    }
}