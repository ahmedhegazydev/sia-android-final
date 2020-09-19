package com.sia.siaandroidapp.java.rest;

import com.sia.siaandroidapp.java.constants.Constants;
import com.sia.siaandroidapp.java.model.JwtTokenResponse;
import com.sia.siaandroidapp.java.model.RoleResponse;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {
    private static final String BASE_URL = Constants.BASE_URL;
    private Retrofit retrofit = null;
    private RetrofitApiInterface apiInterface = null;


    /**
     *
     */
    public ApiClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(30, TimeUnit.SECONDS)
//                .readTimeout(30, TimeUnit.SECONDS)
//                .addInterceptor(interceptor)
//                .build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .client(client)
                    .build();
        }
        apiInterface = retrofit.create(RetrofitApiInterface.class);
    }


    /**
     * @param jsonObject
     * @param jwttkon
     * @return
     */
//    public Single<ResponseBody> login(String email, String password) {
//        return apiInterface.login(email, password);
//    }
    public Single<ResponseBody> login(String jsonObject, String jwttkon) {
        return apiInterface.login(jsonObject, jwttkon);
    }


    //    public Single<ResponseBody> register(
//            String mobile,
//            String email,
//            String username,
//            String password,
////            List<RoleObject> roles
////            String roles
//            JSONObject roles
//    ) {
//        return apiInterface.register(mobile, email, username, password, roles);
//    }
    public Single<JwtTokenResponse> register(String jsonObject) {
        return apiInterface.register(jsonObject);
    }


    /**
     *
     * @return
     */
//    public Single<ResponseBody> getAllRoles() {
//        return apiInterface.getAllRoles();
//    }
    public Single<List<RoleResponse>> getAllRoles() {
        return apiInterface.getAllRoles();
    }


    public Single<ResponseBody> verifyCreateEmailToken(String jsonObject,
                                                       String token) {
        return apiInterface.verifyCreateEmailToken(jsonObject, token);
    }

    public Single<ResponseBody> verifyEmailCode(String jsonObject,
                                                String token) {
        return apiInterface.verifyEmailCode(jsonObject, token);
    }

}
