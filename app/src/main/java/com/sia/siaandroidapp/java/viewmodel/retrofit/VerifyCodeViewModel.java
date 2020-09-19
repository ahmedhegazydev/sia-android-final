package com.sia.siaandroidapp.java.viewmodel.retrofit;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sia.siaandroidapp.java.model.errors.ErrorsMessages;
import com.sia.siaandroidapp.java.rest.ApiClient;
import com.sia.siaandroidapp.java.utils.pref.SiaSharedPref;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class VerifyCodeViewModel extends AndroidViewModel {
    private static final String TAG = "VerifyCodeViewModel";
    private MutableLiveData<ResponseBody> verifyCodeResponse;
    private MutableLiveData<Throwable> error;
//    private MutableLiveData<Boolean> error;
    private MutableLiveData<Boolean> loading;
    private ApiClient client = new ApiClient();
    private CompositeDisposable disposable = new CompositeDisposable();
    private SiaSharedPref utility = new SiaSharedPref();
    private SharedPreferences.Editor editor;

    public VerifyCodeViewModel(@NonNull Application application) {
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

    public LiveData<ResponseBody> postVerifyCode(String jsonObject,
                                                 String token) {
        verifyEmailCodeSent(jsonObject, token);
        return verifyCodeResponse;
    }

    private void verifyEmailCodeSent(String jsonObject, String token) {
        error = new MutableLiveData<>();
        loading = new MutableLiveData<>();
        verifyCodeResponse = new MutableLiveData<>();
        loading.setValue(true);
        disposable.add(
                client.verifyEmailCode(jsonObject, token)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ResponseBody>() {

                            @Override
                            public void onSuccess(ResponseBody responseBody) {
                                Log.e(TAG, "onSuccess: " + responseBody);
//                                error.setValue(false);
                                error.setValue(null);
                                loading.setValue(false);
                                verifyCodeResponse.setValue(responseBody);

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: " + e.getLocalizedMessage());
                                loading.setValue(false);
//                                error.setValue(true);
                                error.setValue(e);
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
