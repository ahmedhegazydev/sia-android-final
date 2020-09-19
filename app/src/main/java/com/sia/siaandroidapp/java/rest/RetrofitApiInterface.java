package com.sia.siaandroidapp.java.rest;

import com.sia.siaandroidapp.java.constants.Constants;
import com.sia.siaandroidapp.java.model.JwtTokenResponse;
import com.sia.siaandroidapp.java.model.RoleResponse;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * an Interface to define our
 * different methods that will be used for network transactions.
 */

public interface RetrofitApiInterface {


    /**
     * @param jsonObject
     * @param jwttkon
     * @return
     */
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
//    @POST(Constants.LOGIN)
//    Single<LoginModel> login(@Query("username") String email, @Query("Password") String password);
    @POST(Constants.LOGIN)
//    Single<ResponseBody> login(@Query("username") String email, @Query("password") String password);
    Single<ResponseBody> login(@Body String jsonObject,
                               @Header("Authorization") String jwttkon);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
//            "Cache-Control: no-cache",
    })
//    @FormUrlEncoded
    @POST(Constants.REGISTER)
    Single<JwtTokenResponse> register(@Body String jsonObject);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    @GET(Constants.END_GET_ALL_ROLES)
    Single<List<RoleResponse>> getAllRoles();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
//            "Cache-Control: no-cache",
    })
    @POST(Constants.END_VERIFY_CREATE_EMAIL_TOKEN)
    Single<ResponseBody> verifyCreateEmailToken(@Body String jsonObject,
                                                @Header("Authorization") String jwttkon);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
//            "Cache-Control: no-cache",
    })
    @POST(Constants.END_VERIFY_EMAIL_CODE)
    Single<ResponseBody> verifyEmailCode(@Body String jsonObject,
                                         @Header("Authorization") String jwttkon);


}
