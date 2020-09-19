package com.sia.siaandroidapp.java.viewmodel.retrofit;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.sia.siaandroidapp.java.model.JwtTokenResponse;
import com.sia.siaandroidapp.java.model.errors.ErrorsMessages;
import com.sia.siaandroidapp.java.rest.ApiClient;
import com.sia.siaandroidapp.java.utils.pref.SiaSharedPref;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class RegisterViewModel extends AndroidViewModel {
    private static final String TAG = "RegisterViewModel";
    private MutableLiveData<JwtTokenResponse> registerResponse;
    //    private MutableLiveData<Boolean> error;
    private MutableLiveData<Throwable> error;
    private MutableLiveData<Boolean> loading;
    private ApiClient client = new ApiClient();
    private CompositeDisposable disposable = new CompositeDisposable();
    private SiaSharedPref utility = new SiaSharedPref();
    private SharedPreferences.Editor editor;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

    //    public LiveData<Boolean> getError() {
//        return error;
//    }
    public LiveData<Throwable> getError() {
        return error;
    }

    public LiveData<Boolean> startLoading() {
        return loading;
    }

//    public LiveData<ResponseBody> postRegister(String mobile,
//                                               String email,
//                                               String username,
//                                               String password,
////                                               List<RoleObject> roleObjects
////                                               String roleObjects
//                                               JSONObject roleObjects
//    ) {
//        registerRequest(mobile, email, username, password,  roleObjects);
//        return registerResponse;
//    }


    public LiveData<JwtTokenResponse> postRegister(String jsonObject) {
        registerRequest(jsonObject);
        return registerResponse;
    }


    private void registerRequest(String jsonObject) {
        error = new MutableLiveData<>();
        loading = new MutableLiveData<>();
        registerResponse = new MutableLiveData<>();
        loading.setValue(true);
        disposable.add(
                client.register(jsonObject)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<JwtTokenResponse>() {
                            @Override
                            public void onSuccess(JwtTokenResponse registerModel) {
                                Log.e(TAG, "onSuccess: " + registerModel);
//                                error.setValue(false);
                                error.setValue(null);
                                loading.setValue(false);
                                registerResponse.setValue(registerModel);

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: " + e.getLocalizedMessage());
                                loading.setValue(false);
//                                error.setValue(true);
                                error.setValue(e);

                                if (e instanceof HttpException) {
                                    // We had non-2XX http error
                                    Log.e(TAG, "onError: HttpException");
                                    Log.e(TAG, "onError: " + ((HttpException) e).code());
                                    Log.e(TAG, "onError: " + ((HttpException) e).response());
                                }
                                if (e instanceof IOException) {
                                    // A network or conversion error happened
                                    Log.e(TAG, "onError: IOException");
                                }

                            }
                        })
        );

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
