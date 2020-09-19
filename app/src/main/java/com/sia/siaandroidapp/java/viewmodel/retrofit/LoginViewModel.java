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

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LoginViewModel extends AndroidViewModel {
    //public class LoginViewModel extends ViewModel {
    //    private MutableLiveData<LoginModel> loginResponse;
    private MutableLiveData<ResponseBody> loginResponse;
    //    private MutableLiveData<Boolean> error;
//    private MutableLiveData<String> error;
    private MutableLiveData<Throwable> error;
    private MutableLiveData<Boolean> loading;
    private ApiClient client = new ApiClient();
    private CompositeDisposable disposable = new CompositeDisposable();
    private SiaSharedPref utility = new SiaSharedPref();
    private SharedPreferences.Editor editor;

    @Inject
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    //    public LiveData<String> getError() {
//        return error;
//    }
    public LiveData<Throwable> getError() {
        return error;
    }

    public LiveData<Boolean> startLoading() {
        return loading;
    }

    public LiveData<ResponseBody> getLogin(String jsonObject, String token) {
        loginRequest(jsonObject, token);
        return loginResponse;
    }

    private void loginRequest(String jsonObject, String token) {
        error = new MutableLiveData<>();
        loading = new MutableLiveData<>();
        loginResponse = new MutableLiveData<>();
        loading.setValue(true);
        disposable.add(
                client.login(jsonObject, token)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ResponseBody>() {
                            @Override
                            public void onSuccess(ResponseBody loginModel) {
                                Log.e("login", " success " + loginModel);
//                                error.setValue(false);
                                error.setValue(null);
                                loading.setValue(false);
                                loginResponse.setValue(loginModel);
                                
                            }

                            @Override
                            public void onError(Throwable e) {
//                                error.setValue(true);
//                                error.setValue(e.getLocalizedMessage());
                                error.setValue(e);
                                loading.setValue(false);
                                Log.e("login", " error with msg : " + e.getLocalizedMessage());

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
