package com.sia.siaandroidapp.java.viewmodel.retrofit;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sia.siaandroidapp.java.rest.ApiClient;
import com.sia.siaandroidapp.java.utils.pref.SiaSharedPref;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class VerifyTokenViewModel extends AndroidViewModel {
    private static final String TAG = "VerifyTokenViewModel";
    private MutableLiveData<ResponseBody> registerResponse;
//    private MutableLiveData<Boolean> error;
    private MutableLiveData<Throwable> error;
    private MutableLiveData<Boolean> loading;
    private ApiClient client = new ApiClient();
    private CompositeDisposable disposable = new CompositeDisposable();
    private SiaSharedPref utility = new SiaSharedPref();
    private SharedPreferences.Editor editor;

    public VerifyTokenViewModel(@NonNull Application application) {
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

    public LiveData<ResponseBody> postCreateEmailToken(String data,
                                                       String token) {
        createEmailTokenRequest(data, token);
        return registerResponse;
    }

    private void createEmailTokenRequest(String data, String token) {
        error = new MutableLiveData<>();
        loading = new MutableLiveData<>();
        registerResponse = new MutableLiveData<>();
        loading.setValue(true);
        disposable.add(
                client.verifyCreateEmailToken(data, token)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ResponseBody>() {
                            @Override
                            public void onSuccess(ResponseBody registerModel) {
                                Log.e(TAG, "onSuccess: " + registerModel);
//                                error.setValue(false);
                                error.setValue(null);
                                loading.setValue(false);
                                registerResponse.setValue(registerModel);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: " + e.getLocalizedMessage());
                                Log.e(TAG, "onError: " + e.getMessage());
                                loading.setValue(false);
                                error.setValue(e);
//                                error.setValue(true);
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
